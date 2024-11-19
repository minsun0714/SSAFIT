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

const formSchema = toTypedSchema(
  z.object({
    memberId: z.string().min(2).max(50),
    password: z.string().min(8).max(50),
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
    <form class="" @submit="onSubmit">
      <FormField v-slot="{ componentField }" name="memberId">
        <FormItem>
          <FormControl>
            <Input type="text" v-bind="componentField" />
          </FormControl>
          <div class="min-h-5">
            <FormMessage />
          </div>
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
        <TableRow>
          <TableCell class="font-medium"> INV001 </TableCell>
          <TableCell>Paid</TableCell>
        </TableRow>
      </TableBody>
    </Table>
  </div>
</template>
