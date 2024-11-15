<script setup lang="ts">
import { FormControl, FormField, FormItem, FormLabel, FormMessage } from '@/components/ui/form'
import { Input } from '@/components/ui/input'

import { toTypedSchema } from '@vee-validate/zod'
import { useForm } from 'vee-validate'
import * as z from 'zod'
import BlueButton from '../common/BlueButton.vue'
import { useRouter } from 'vue-router'

const router = useRouter()

const formSchema = toTypedSchema(
  z.object({
    name: z.string().min(2).max(50),
    userId: z.string().min(2).max(50),
    password: z.string().min(8).max(50),
    passwordConfirm: z.string().min(8).max(50),
  }),
)

const { handleSubmit } = useForm({
  validationSchema: formSchema,
})

const onSubmit = handleSubmit((value) => {
  console.log(value)
})
</script>

<template>
  <div>
    <button v-text="'< Back'" class="p-8 text-gray-400" @click="router.back()"></button>
  </div>
  <form class="flex flex-col justify-center items-center space-y-6 p-10" @submit="onSubmit">
    <h1 class="font-bold text-xl w-[210px]">회원가입</h1>
    <h2 class="text-gray-400 text-sm w-[210px]">회원이 되어 다양한 운동 서비스를 즐겨보세요!</h2>
    <FormField v-slot="{ componentField }" name="name">
      <FormItem>
        <FormLabel>이름</FormLabel>
        <FormControl>
          <Input type="text" v-bind="componentField" />
        </FormControl>
        <FormMessage />
      </FormItem>
    </FormField>
    <FormField v-slot="{ componentField }" name="userId">
      <FormItem>
        <FormLabel>아이디</FormLabel>
        <FormControl>
          <Input type="text" v-bind="componentField" />
        </FormControl>
        <FormMessage />
      </FormItem>
    </FormField>
    <FormField v-slot="{ componentField }" name="password">
      <FormItem>
        <FormLabel>비밀번호</FormLabel>
        <FormControl>
          <Input type="password" v-bind="componentField" />
        </FormControl>
        <FormMessage />
      </FormItem>
    </FormField>
    <FormField v-slot="{ componentField }" name="passwordConfirm">
      <FormItem>
        <FormLabel>비밀번호 확인</FormLabel>
        <FormControl>
          <Input type="password" v-bind="componentField" />
        </FormControl>
        <FormMessage />
      </FormItem>
    </FormField>
    <BlueButton type="submit" text="회원가입" :width="210" :height="45" />
  </form>
</template>
