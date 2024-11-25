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
import { FaRegTrashAlt } from 'vue3-icons/fa'

import { type DateValue } from '@internationalized/date'
import { pad } from '@/utils/helperFunction'
import CalendarChart from './CalendarChart.vue'

const { weekDate, month } = defineProps<{
  weekDate: DateValue
  month: DateValue
}>()

const { data } = ExerciseLogApiFacade.useFetchExerciseLogsByDate(
  weekDate.year + '-' + pad(weekDate.month) + '-' + pad(weekDate.day),
)

console.log(data.value)

const { mutate } = ExerciseLogApiFacade.useDeleteExerciseLog()
</script>
<template>
  <div class="w-full">
    <Dialog class="p-8">
      <DialogTrigger class="w-full text-end flex flex-col h-24">
        <CalendarCellTrigger :day="weekDate" :month="month" />
      </DialogTrigger>
      <DialogContent>
        <DialogHeader>
          <DialogTitle>
            <div>{{ weekDate.year }}년 {{ weekDate.month }}월 {{ weekDate.day }}일 운동</div>
          </DialogTitle>
          <DialogDescription> 총 운동 시간 : {{ data?.dailyTotalExerciseTime }}초 </DialogDescription>
        </DialogHeader>
        <CalendarChart :exerciseLogVO="data?.exerciseLogVO || []" />
        <ul v-if="data?.exerciseLogVO.length">
          <li
            v-for="log in data?.exerciseLogVO"
            :key="log.exerciseLogId"
            class="flex flex-row justify-between items-center w-full h-12 border-b"
          >
            <div class="flex flex-row gap-4">
              <span>{{ log.exerciseType }}</span>
              <span>{{ log.exerciseTime }}초</span>
              <span class="text-blue-400">{{ log.caloriesBurned.toFixed(2) }}kcal</span>
            </div>

            <button
              class="flex justify-between"
              @click="() => mutate({ exerciseLogId: log.exerciseLogId })"
            >
              <FaRegTrashAlt class="text-gray-400" />
            </button>
          </li>
        </ul>
        <div v-else class="flex justify-center p-16">해당 날짜의 운동 데이터가 없습니다.</div>
      </DialogContent>
    </Dialog>
  </div>
</template>
