<script setup lang="ts">
import {
  Table,
  TableBody,
  TableCell,
  TableHead,
  TableHeader,
  TableRow,
} from '@/components/ui/table'

import { SlMagnifier } from 'vue3-icons/sl'
import { useExerciseStore } from '@/stores/exerciseType'
import ExerciseTypeApiFacade from '@/api/apiFacade/ExerciseTypeApiFacade'
import { ref, watch } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import TimerModalPagination from './TimerModalPagination.vue'
import TimerModalNoResult from './TimerModalNoResult.vue'

const { handleClickExercise } = useExerciseStore()

const router = useRouter()
const route = useRoute()

const exerciseType = ref(route.query.exerciseType || '')

const { data, refetch } = ExerciseTypeApiFacade.useFetchPagedExerciseType(5)

watch(
  exerciseType,
  (newKeyword) => {
    router.push({ query: { ...route.query, exerciseType: newKeyword, page: 1 } })
    refetch()
  },
  { immediate: true },
)
</script>

<template>
  <div class="w-[480px] h-[600px] p-4">
    <div class="flex flex-row justify-start border rounded-sm items-center p-3 my-3 gap-2">
      <SlMagnifier />
      <input
        type="text"
        v-model="exerciseType"
        class="border border-none border-input-none outline-none w-full"
      />
    </div>
    <div v-if="data?.data.length">
      <Table class="border">
        <TableHeader>
          <TableRow class="flex flex-row items-center justify-center">
            <TableHead class="flex items-center justify-center w-full border">운동명</TableHead>
            <TableHead class="flex items-center justify-center w-full border"
              >시간(h)당 예상 칼로리 소모량</TableHead
            >
          </TableRow>
        </TableHeader>
        <TableBody>
          <TableRow
            v-for="(item, index) in data?.data"
            :key="index"
            class="cursor-pointer flex flex-row"
            @click="() => handleClickExercise(item.운동명)"
          >
            <TableCell class="flex justify-center w-full">{{ item.운동명 }}</TableCell>
            <TableCell class="flex justify-center w-full"
              >{{ item['MET 계수'] }} X {{ 60 }} = {{ item['MET 계수'] * 60 }}kcal</TableCell
            >
          </TableRow>
        </TableBody>
      </Table>
      <TimerModalPagination :data="data" />
    </div>
    <div v-if="data?.totalItems === 0"><TimerModalNoResult /></div>
  </div>
</template>
