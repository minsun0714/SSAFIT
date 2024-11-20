<script lang="ts" setup>
import ExerciseLogApiFacade from '@/api/apiFacade/ExerciseLogApiFacade'
import CalendarCellTrigger from '../ui/calendar/CalendarCellTrigger.vue'
import {
  Dialog,
  DialogContent,
  DialogDescription,
  DialogHeader,
  DialogTitle,
  DialogTrigger,
} from '@/components/ui/dialog'

import { type DateValue } from '@internationalized/date'
import { pad } from '@/utils/helperFunction'
import { computed } from 'vue'

const { weekDate, month } = defineProps<{
  weekDate: DateValue
  month: DateValue
}>()

const { data } = ExerciseLogApiFacade.useFetchExerciseLogsByDate(
  weekDate.year + '-' + pad(weekDate.month) + '-' + pad(weekDate.day),
)

const { mutate } = ExerciseLogApiFacade.useDeleteExerciseLog()

const groupedData = computed(() => {
  if (!data.value) return {}

  return data.value.reduce(
    (acc, log) => {
      if (!acc[log.exerciseType]) {
        acc[log.exerciseType] = {
          totalExerciseTime: 0,
          totalCaloriesBurned: 0,
          logs: [],
        }
      }

      acc[log.exerciseType].totalExerciseTime += log.exerciseTime
      acc[log.exerciseType].totalCaloriesBurned += log.caloriesBurned
      acc[log.exerciseType].logs.push(log)

      return acc
    },
    {} as Record<string, { totalExerciseTime: number; totalCaloriesBurned: number; logs: any[] }>,
  )
})

const totalExerciseTime = computed(() => {
  return Object.values(groupedData.value || {}).reduce(
    (total, group) => total + group.totalExerciseTime,
    0,
  )
})
</script>
<template>
  <div class="w-full">
    <Dialog>
      <DialogTrigger class="w-full text-end flex flex-col h-24">
        <CalendarCellTrigger :day="weekDate" :month="month" />
      </DialogTrigger>
      <DialogContent>
        <DialogHeader>
          <DialogTitle>
            <div>{{ weekDate.year }}년 {{ weekDate.month }}월 {{ weekDate.day }}일 운동</div>
          </DialogTitle>
          <DialogDescription> 총 운동 시간 : {{ totalExerciseTime }}초 </DialogDescription>
        </DialogHeader>
        <!-- <div class="flex flex-row justify-evenly border w-full h-full">
              <strong>{{ exerciseType }}</strong>
              <p>{{ group.totalExerciseTime.toFixed(2) }}초</p>
              <p>{{ group.totalCaloriesBurned.toFixed(2) }}kcal</p>
            </div> -->
        <ul v-if="Object.keys(groupedData).length">
          <li
            v-for="(group, exerciseType) in groupedData"
            :key="exerciseType"
            class="flex flex-col justify-between items-center"
          >
            <div class="flex flex-row justify-evenly items-center border-b w-full h-12">
              <strong>{{ exerciseType }}</strong>
              <p>{{ group.totalExerciseTime.toFixed(2) }}초</p>
              <p class="text-blue-400">{{ group.totalCaloriesBurned.toFixed(2) }}kcal</p>
            </div>
            <!-- <ul>
              <li
                v-for="log in group.logs"
                :key="log.exerciseLogId"
                class="flex flex-row justify-evenly border w-[480px] h-full"
              >
                <p>{{ log.exerciseType }}</p>
                <p>{{ log.exerciseTime.toFixed(2) }}초</p>
                <p>{{ log.caloriesBurned.toFixed(2) }}kcal</p>

                <button
                  class="flex w-full justify-between"
                  @click="() => mutate({ exerciseLogId: log.exerciseLogId as any })"
                >
                  삭제
                </button>
              </li>
            </ul> -->
          </li>
        </ul>
        <div v-else>해당 날짜의 운동 데이터가 없습니다.</div>
      </DialogContent>
    </Dialog>
  </div>
</template>
