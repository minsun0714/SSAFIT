<script setup lang="ts">
import {
  Table,
  TableBody,
  TableCell,
  TableHead,
  TableHeader,
  TableRow,
} from '@/components/ui/table'

import { FormControl, FormField, FormItem, FormMessage } from '@/components/ui/form'
import { Input } from '@/components/ui/input'

import { toTypedSchema } from '@vee-validate/zod'
import { useForm } from 'vee-validate'
import * as z from 'zod'

import { SlMagnifier } from 'vue3-icons/sl'

const formSchema = toTypedSchema(
  z.object({
    memberId: z.string().min(2).max(50),
    password: z.string().min(8).max(50),
  }),
)

const { handleSubmit } = useForm({
  validationSchema: formSchema,
})

const data = {
  currentPage: 1,
  pageSize: 10,
  totalPages: 26,
  totalItems: 259,
  data: [
    {
      운동명: '다트',
      'MET 계수': 2.5,
    },
    {
      운동명: '야구 캐치볼',
      'MET 계수': 2.5,
    },
    {
      운동명: '당구',
      'MET 계수': 2.5,
    },
    {
      운동명: '요가',
      'MET 계수': 2.5,
    },
    {
      운동명: '하타 요가',
      'MET 계수': 2.5,
    },
    {
      운동명: '파워요가',
      'MET 계수': 4.0,
    },
    {
      운동명: '수리야 나마스까 요가',
      'MET 계수': 3.3,
    },
    {
      운동명: '나디소다나 요가',
      'MET 계수': 2.0,
    },
    {
      운동명: '스트레칭(가볍게) - 전신',
      'MET 계수': 2.5,
    },
    {
      운동명: '낚시',
      'MET 계수': 3.0,
    },
  ],
}

const onSubmit = handleSubmit((value) => {
  console.log(value)
})
</script>

<template>
  <div>
    <form class="" @submit="onSubmit">
      <FormField v-slot="{ componentField }" name="memberId">
        <FormItem>
          <FormControl class="flex flex-row rounded-md border items-center">
            <div class="flex">
              <SlMagnifier class="flex justify-center w-9 items-center h-3" />
              <Input
                type="text"
                v-bind="componentField"
                class="border border-none border-input-none outline-none"
              />
            </div>
          </FormControl>
        </FormItem>
      </FormField>
    </form>
    <Table>
      <TableHeader>
        <TableRow>
          <TableHead>운동명</TableHead>
          <TableHead>시간당 예상 칼로리 소모량</TableHead>
        </TableRow>
      </TableHeader>
      <TableBody>
        <TableRow v-for="(item, index) in data.data" :key="index">
          <TableCell>{{ item.운동명 }}</TableCell>
          <TableCell>{{ item['MET 계수'] }}</TableCell>
        </TableRow>
      </TableBody>
    </Table>
  </div>
</template>
