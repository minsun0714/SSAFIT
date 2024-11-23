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
import { useDebounce } from '@vueuse/core'
import TimerModalPagination from './TimerModalPagination.vue'
import TimerModalNoResult from './TimerModalNoResult.vue'
import MemberApiFacade from '@/api/apiFacade/MemberApiFacade'

const exerciseStore = useExerciseStore()

const router = useRouter()
const route = useRoute()

const exerciseType = ref(route.query.exerciseType || '')

const debouncedExerciseType = useDebounce(exerciseType, 300)

const { data: pagedExerciseType, refetch } = ExerciseTypeApiFacade.useFetchPagedExerciseType(5)
const { data: memberInfo } = MemberApiFacade.useFetchUserInfo()

// 디바운싱된 값이 변경될 때 동작
watch(
  debouncedExerciseType,
  (newKeyword) => {
    router.push({ query: { ...route.query, exerciseType: newKeyword, page: 1 } })
    refetch()
  },
  { immediate: true },
)
</script>

<template>
  <div class="w-[480px] h-full min-h-[600px] p-4 flex flex-col">
    <div class="flex flex-row justify-start border rounded-sm items-center p-3 my-3 gap-2">
      <SlMagnifier />
      <input
        type="text"
        v-model="exerciseType"
        class="border border-none border-input-none outline-none w-full"
      />
    </div>
    <p
      class="h-12 mb-2 bg-blue-400 text-white font-bold rounded-md flex justify-center items-center"
    >
      {{ exerciseStore.exercise ? exerciseStore.exercise : '운동을 선택해주세요.' }}
    </p>
    <div v-if="pagedExerciseType?.data.length">
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
            v-for="(item, index) in pagedExerciseType?.data"
            :key="index"
            class="cursor-pointer flex flex-row"
            @click="exerciseStore.handleClickExercise(item.운동명)"
            :class="exerciseStore.exercise === item.운동명 && 'outline-2'"
          >
            <TableCell class="flex justify-center w-full">{{ item.운동명 }}</TableCell>
            <TableCell class="flex justify-center w-full"
              >{{ item['MET 계수'] }} X {{ memberInfo?.weight }} =
              {{ item['MET 계수'] * memberInfo.weight }}kcal</TableCell
            >
          </TableRow>
        </TableBody>
      </Table>
      <TimerModalPagination :data="pagedExerciseType" />
    </div>
    <div v-if="pagedExerciseType?.totalItems === 0"><TimerModalNoResult /></div>
  </div>
</template>
