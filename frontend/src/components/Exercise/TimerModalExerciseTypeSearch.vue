<script setup lang="ts">
import {
  Table,
  TableBody,
  TableCell,
  TableHead,
  TableHeader,
  TableRow,
} from '@/components/ui/table'

import { FormControl, FormField, FormItem } from '@/components/ui/form'
import { Input } from '@/components/ui/input'

import { toTypedSchema } from '@vee-validate/zod'
import { useForm } from 'vee-validate'
import * as z from 'zod'

import { SlMagnifier } from 'vue3-icons/sl'
import { useExerciseStore } from '@/stores/exerciseType'
import ExerciseTypeApiFacade from '@/api/apiFacade/ExerciseTypeApiFacade'
import { ref, watch } from 'vue'
import { useRoute, useRouter } from 'vue-router'

const { handleClickExercise } = useExerciseStore()

const router = useRouter()
const route = useRoute()

const exerciseType = ref(route.query.exerciseType || '')



const { data, refetch } = ExerciseTypeApiFacade.useFetchPagedExerciseType(1)
console.log(data.value)

watch(
  exerciseType,
  (newKeyword) => {
    router.push({ query: { ...route.query, exerciseType: newKeyword } })
    refetch()
  },
  { immediate: true },
)
</script>

<template>
  <div>
    <div class="flex flex-row justify-start items-center border p-3 gap-2">
      <SlMagnifier />
      <input
        type="text"
        v-model="exerciseType"
        class="border border-none border-input-none outline-none"
      />
    </div>
    <Table>
      <TableHeader>
        <TableRow>
          <TableHead>운동명</TableHead>
          <TableHead>시간당 예상 칼로리 소모량</TableHead>
        </TableRow>
      </TableHeader>
      <TableBody>
        <TableRow v-for="(item, index) in data?.data" :key="index" class="cursor-pointer">
          <TableCell @click="() => handleClickExercise(item.운동명)">{{ item.운동명 }}</TableCell>
          <TableCell>{{ item['MET 계수'] }}</TableCell>
        </TableRow>
      </TableBody>
    </Table>
  </div>
</template>
