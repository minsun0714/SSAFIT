<script setup lang="ts">
import { useForm } from 'vee-validate'
import { toTypedSchema } from '@vee-validate/zod'
import * as z from 'zod'

import { FormControl, FormField, FormItem, FormLabel, FormMessage } from '@/components/ui/form'
import { Input } from '@/components/ui/input'
import BlueButton from '../common/BlueButton.vue'

const formSchema = toTypedSchema(
  z.object({
    nickname: z.string().min(2).max(50),
    password: z.string().min(8).max(50),
    passwordConfirm: z.string().min(8).max(50),
  }),
)

const form = useForm({
  validationSchema: formSchema,
})

const onSubmit = form.handleSubmit((values) => {
  console.log('Form submitted!', values)
})
</script>

<template>
  <form class="flex flex-col justify-center items-center space-y-10" @submit="onSubmit">
    <div class="flex flex-col justify-center items-center gap-x-6 w-screen py-16">
      <FormField v-slot="{ componentField }" name="nickname">
        <FormItem class="flex flex-col w-full items-center">
          <div class="flex flex-row w-2/3 max-w-[500px] justify-center">
            <FormLabel class="w-full">닉네임</FormLabel>
            <FormControl>
              <Input type="text" v-bind="componentField" class="min-w-[280px]" />
            </FormControl>
          </div>
          <div class="min-h-5 flex justify-end w-2/3">
            <FormMessage />
          </div>
        </FormItem>
      </FormField>
      <FormField v-slot="{ componentField }" name="nickname">
        <FormItem class="flex flex-col w-full items-center">
          <div class="flex flex-row w-2/3 max-w-[500px] justify-center">
            <FormLabel class="w-full">비밀번호 변경</FormLabel>
            <FormControl>
              <Input type="text" v-bind="componentField" class="min-w-[280px]" />
            </FormControl>
          </div>
          <div class="min-h-5 flex justify-end w-2/3">
            <FormMessage />
          </div>
        </FormItem>
      </FormField>
      <FormField v-slot="{ componentField }" name="nickname">
        <FormItem class="flex flex-col w-full items-center">
          <div class="flex flex-row w-2/3 max-w-[500px] justify-center">
            <FormLabel class="w-full">비밀번호 확인</FormLabel>
            <FormControl>
              <Input type="text" v-bind="componentField" class="min-w-[280px]" />
            </FormControl>
          </div>
          <div class="min-h-5 flex justify-end w-2/3">
            <FormMessage />
          </div>
        </FormItem>
      </FormField>
    </div>
    <div class="flex w-2/3 justify-end">
      <BlueButton type="submit" text="수정" :width="100" :height="45" />
    </div>
  </form>
</template>
