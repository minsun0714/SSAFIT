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

const formSchema = toTypedSchema(
  z.object({
    name: z.string(),
    memberId: z.string(),
    nickname: z.string().min(2).max(50),
    password: z.string().min(8).max(50),
    passwordConfirm: z.string().min(8).max(50),
    profileImg: z.string(),
  }),
)

const { data } = MemberApiFacade.useFetchUserInfo()

const form = useForm({
  validationSchema: formSchema,
  initialValues: {
    name: data?.value.name,
    memberId: data?.value.memberId,
    nickname: data?.value.nickname,
  },
})

const onSubmit = form.handleSubmit((values) => {
  console.log('Form submitted!', values)
})

const previewImage = ref<string | null>(null)

const handleFileChange = (event: Event) => {
  const file = (event.target as HTMLInputElement)?.files?.[0]
  if (file) {
    const reader = new FileReader()
    reader.onload = () => {
      previewImage.value = reader.result as string
    }
    reader.readAsDataURL(file)
  } else {
    previewImage.value = null
  }
}
</script>

<template>
  <form class="flex flex-col justify-center items-center space-y-10" @submit="onSubmit">
    <div class="flex flex-col justify-center items-center gap-x-6 w-screen py-16">
      <FormField v-slot="{ componentField }" name="name">
        <FormItem class="flex flex-col w-full items-center">
          <div class="flex flex-row w-2/3 max-w-[500px] justify-center items-center">
            <FormLabel class="w-full">이름</FormLabel>
            <FormControl>
              <Input
                type="text"
                disabled
                v-bind="componentField"
                class="min-w-[280px] border border-none"
              />
            </FormControl>
          </div>
          <div class="min-h-5 flex justify-end w-2/3 max-w-[500px]">
            <FormMessage />
          </div>
        </FormItem>
      </FormField>
      <FormField v-slot="{ componentField }" name="memberId">
        <FormItem class="flex flex-col w-full items-center">
          <div class="flex flex-row w-2/3 max-w-[500px] justify-center items-center">
            <FormLabel class="w-full">아이디</FormLabel>
            <FormControl>
              <Input
                type="text"
                disabled
                v-bind="componentField"
                class="min-w-[280px] border border-none"
              />
            </FormControl>
          </div>
          <div class="min-h-5 flex justify-end w-2/3 max-w-[500px]">
            <FormMessage />
          </div>
        </FormItem>
      </FormField>
      <FormField v-slot="{ componentField }" name="nickname">
        <FormItem class="flex flex-col w-full items-center">
          <div class="flex flex-row w-2/3 max-w-[500px] justify-center">
            <FormLabel class="w-full">닉네임</FormLabel>
            <FormControl>
              <Input type="text" v-bind="componentField" class="min-w-[280px]" />
            </FormControl>
          </div>
          <div class="min-h-5 flex justify-end w-2/3 max-w-[500px]">
            <FormMessage />
          </div>
        </FormItem>
      </FormField>
      <FormField v-slot="{ componentField }" name="password">
        <FormItem class="flex flex-col w-full items-center">
          <div class="flex flex-row w-2/3 max-w-[500px] justify-center">
            <FormLabel class="w-full">비밀번호 변경</FormLabel>
            <FormControl>
              <Input type="password" v-bind="componentField" class="min-w-[280px]" />
            </FormControl>
          </div>
          <div class="min-h-5 flex justify-end w-2/3 max-w-[500px]">
            <FormMessage />
          </div>
        </FormItem>
      </FormField>
      <FormField v-slot="{ componentField }" name="passwordConfirm">
        <FormItem class="flex flex-col w-full items-center">
          <div class="flex flex-row w-2/3 max-w-[500px] justify-center">
            <FormLabel class="w-full">비밀번호 확인</FormLabel>
            <FormControl>
              <Input type="password" v-bind="componentField" class="min-w-[280px]" />
            </FormControl>
          </div>
          <div class="min-h-5 flex justify-end w-2/3 max-w-[500px]">
            <FormMessage />
          </div>
        </FormItem>
      </FormField>
      <FormField v-slot="{ componentField }" name="profileImg">
        <FormItem class="flex flex-col w-full items-center">
          <div class="flex flex-row w-2/3 max-w-[500px] justify-center items-center">
            <FormControl>
              <FormLabel class="w-full">프로필 이미지</FormLabel>
              <div>
                <div class="h-40 overflow-hidden h-20 flex flex-col justify-center items-center">
                  <img
                    v-if="previewImage"
                    :src="previewImage"
                    alt="Preview"
                    class="w-28 h-28 object-cover rounded-full border border-gray-200"
                  />
                  <img
                    v-else
                    :src="profileImg"
                    class="w-28 h-28 object-cover rounded-full border border-gray-200"
                  />
                </div>
                <label
                  class="custom-file-upload min-w-[280px] border-none underline flex flex-col items-center"
                >
                  <span class="bg-white cursor-pointer">이미지 선택</span>
                  <Input
                    type="file"
                    v-bind="componentField"
                    class="hidden"
                    @change="handleFileChange"
                  />
                </label>
              </div>
            </FormControl>
          </div>
          <div class="min-h-5 flex justify-end w-2/3 max-w-[500px]">
            <FormMessage />
          </div>
        </FormItem>
      </FormField>
    </div>

    <!-- 제출 버튼 -->
    <div class="flex w-2/3 justify-end">
      <BlueButton type="submit" text="수정" :width="100" :height="45" />
    </div>
  </form>
</template>
