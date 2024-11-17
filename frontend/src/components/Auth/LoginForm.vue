<script setup lang="ts">
import { FormControl, FormField, FormItem, FormLabel, FormMessage } from '@/components/ui/form'
import { Input } from '@/components/ui/input'

import { toTypedSchema } from '@vee-validate/zod'
import { useForm } from 'vee-validate'
import * as z from 'zod'
import BlueButton from '../common/BlueButton.vue'
import { useRouter } from 'vue-router'
import LottieAnimation from '@/components/Auth/LottieAnimation.vue'
import { LOTTIE_ANIMATION_PATH } from '@/utils/enum'
import AuthApiFacade from '@/api/apiFacade/AuthApiFacade'

const router = useRouter()

const formSchema = toTypedSchema(
  z.object({
    memberId: z.string().min(2).max(50),
    password: z.string().min(8).max(50),
  }),
)

const { handleSubmit } = useForm({
  validationSchema: formSchema,
})

const { mutate } = AuthApiFacade.useLogin()

const onSubmit = handleSubmit((value) => {
  mutate(value)
})
</script>

<template>
  <div class="flex flex-row justify-center h-[calc(100vh_-_98px)]">
    <div class="w-full">
      <LottieAnimation :animationPath="LOTTIE_ANIMATION_PATH.LOGIN" :loop="true" :autoplay="true" />
    </div>
    <div class="w-full">
      <div>
        <button v-text="'< Back'" class="p-8 text-gray-400" @click="router.back()"></button>
      </div>
      <form class="flex flex-col justify-center items-center space-y-6 p-10" @submit="onSubmit">
        <h1 class="font-bold text-xl w-[300px]">로그인</h1>
        <h2 class="text-gray-400 text-sm w-[300px]">로그인 후 다양한 운동 서비스를 즐겨보세요!</h2>
        <FormField v-slot="{ componentField }" name="memberId">
          <FormItem>
            <FormLabel>아이디</FormLabel>
            <FormControl>
              <Input type="text" v-bind="componentField" />
            </FormControl>
            <div class="min-h-5">
              <FormMessage />
            </div>
          </FormItem>
        </FormField>
        <FormField v-slot="{ componentField }" name="password">
          <FormItem>
            <FormLabel>비밀번호</FormLabel>
            <FormControl>
              <Input type="password" v-bind="componentField" />
            </FormControl>
            <div class="min-h-5">
              <FormMessage />
            </div>
          </FormItem>
        </FormField>
        <BlueButton type="submit" text="로그인" :width="300" :height="45" />
      </form>
    </div>
  </div>
</template>
