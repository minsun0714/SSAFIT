<script setup lang="ts">
import { ref } from 'vue'
import { useForm } from 'vee-validate'
import { toTypedSchema } from '@vee-validate/zod'
import * as z from 'zod'

import { FormControl, FormField, FormItem, FormLabel, FormMessage } from '@/components/ui/form'
import { Input } from '@/components/ui/input'
import BlueButton from '../common/BlueButton.vue'
import profileImg from '@/assets/NavigationBar/profileImg.svg'
import MemberApiFacade from '@/api/apiFacade/MemberApiFacade'

// Zod 스키마 정의
const zodSchema = z.object({
  name: z.string(),
  memberId: z.string(),
  nickname: z
    .string()
    .min(2, '닉네임은 최소 2자 이상이어야 합니다.')
    .max(50, '닉네임은 최대 50자까지 가능합니다.'),
  password: z
    .string()
    .min(8, '비밀번호는 최소 8자 이상이어야 합니다.')
    .max(50, '비밀번호는 최대 50자까지 가능합니다.'),
  passwordConfirm: z.string().min(8).max(50),
})

const formSchema = toTypedSchema(zodSchema)

type FormValues = z.infer<typeof zodSchema>

// 서버에서 유저 데이터 불러오기
const { data } = MemberApiFacade.useFetchUserInfo()
console.log(data?.value.profileImg)
const { mutate } = MemberApiFacade.useUpdateUser()

// 폼 초기화
const form = useForm<FormValues>({
  validationSchema: formSchema,
  initialValues: {
    name: data?.value.name ?? '',
    memberId: data?.value.memberId ?? '',
    nickname: data?.value.nickname ?? '',
    password: '',
    passwordConfirm: '',
  },
})

// 파일 업로드 및 미리보기
const selectedFile = ref<File | null>(null)
const previewImage = ref<string | null>(null)

const handleFileChange = (event: Event) => {
  const file = (event.target as HTMLInputElement)?.files?.[0]
  if (file) {
    selectedFile.value = file
    const reader = new FileReader()
    reader.onload = () => {
      previewImage.value = reader.result as string
    }
    reader.readAsDataURL(file)
  } else {
    selectedFile.value = null
    previewImage.value = null
  }
}

// 폼 제출
const onSubmit = form.handleSubmit((values: FormValues) => {
  const formData = new FormData()

  if (selectedFile.value) {
    formData.append('profileImg', selectedFile.value)
  } else {
    console.log('No file selected')
  }

  // FormData에 값 추가
  Object.entries(values).forEach(([key, value]) => {
    formData.append(key, value)
  })

  // 서버로 데이터 전송
  mutate({...values, profileImg: selectedFile.value})
})
</script>

<template>
  <form class="flex flex-col justify-center items-center space-y-10" @submit.prevent="onSubmit">
    <div class="flex flex-col justify-center items-center gap-x-6 w-screen py-16">
      <!-- 이름 필드 -->
      <FormField v-slot="{ componentField }" name="name">
        <FormItem>
          <FormLabel>이름</FormLabel>
          <FormControl>
            <Input type="text" disabled v-bind="componentField" />
          </FormControl>
        </FormItem>
      </FormField>

      <!-- 아이디 필드 -->
      <FormField v-slot="{ componentField }" name="memberId">
        <FormItem>
          <FormLabel>아이디</FormLabel>
          <FormControl>
            <Input type="text" disabled v-bind="componentField" />
          </FormControl>
        </FormItem>
      </FormField>

      <!-- 닉네임 필드 -->
      <FormField v-slot="{ componentField }" name="nickname">
        <FormItem>
          <FormLabel>닉네임</FormLabel>
          <FormControl>
            <Input type="text" v-bind="componentField" />
          </FormControl>
          <FormMessage />
        </FormItem>
      </FormField>

      <!-- 비밀번호 필드 -->
      <FormField v-slot="{ componentField }" name="password">
        <FormItem>
          <FormLabel>비밀번호 변경</FormLabel>
          <FormControl>
            <Input type="password" v-bind="componentField" />
          </FormControl>
          <FormMessage />
        </FormItem>
      </FormField>

      <!-- 비밀번호 확인 필드 -->
      <FormField v-slot="{ componentField }" name="passwordConfirm">
        <FormItem>
          <FormLabel>비밀번호 확인</FormLabel>
          <FormControl>
            <Input type="password" v-bind="componentField" />
          </FormControl>
          <FormMessage />
        </FormItem>
      </FormField>

      <!-- 프로필 이미지 업로드 -->
      <div class="flex flex-col w-2/3 max-w-[500px] items-center">
        <label class="text-gray-700">프로필 이미지</label>
        <div class="flex flex-col items-center">
          <img
            v-if="previewImage"
            :src="previewImage"
            alt="Preview"
            class="w-28 h-28 object-cover rounded-full border border-gray-200"
          />
          <img
            v-else
            :src="profileImg"
            alt="Default"
            class="w-28 h-28 object-cover rounded-full border border-gray-200"
          />
          <label class="custom-file-upload mt-4">
            <span class="cursor-pointer text-blue-500 underline">이미지 선택</span>
            <input type="file" class="hidden" @change="handleFileChange" />
          </label>
        </div>
      </div>
    </div>

    <!-- 제출 버튼 -->
    <div class="flex w-2/3 justify-end">
      <BlueButton type="submit" text="수정" :width="100" :height="45" />
    </div>
  </form>
</template>
