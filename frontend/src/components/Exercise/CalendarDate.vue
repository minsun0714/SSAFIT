<script lang="ts" setup>
import ExerciseLogApiFacade from '@/api/apiFacade/ExerciseLogApiFacade'
import CalendarCellTrigger from '../ui/calendar/CalendarCellTrigger.vue'
import {
  Dialog,
  DialogContent,
  DialogDescription,
  DialogFooter,
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

const totalExerciseTime = computed(() => {
  const exerciseTimeData = data.value?.map((d) => d.exerciseTime) || []
  return exerciseTimeData.reduce((a, b) => a + b, 0)
})
const totalKcalBurn = computed(() => {
  const kcalBurnData = data.value?.map((d) => d.caloriesBurned) || []
  return kcalBurnData.reduce((a, b) => a + b, 0)
})

</script>
<template>
  <div class="w-full">
    <Dialog>
      <DialogTrigger>
        <CalendarCellTrigger :day="weekDate" :month="month" />
      </DialogTrigger>
      <DialogContent>
        <DialogHeader>
          <DialogTitle
            ><div>
              {{ weekDate.year }}년 {{ weekDate.month }}월 {{ weekDate.day }}일 운동
            </div></DialogTitle
          >
          <DialogDescription>
            총 운동 시간 : {{ totalExerciseTime }}
            {{ totalKcalBurn }}kcal
          </DialogDescription>
        </DialogHeader>
        <ul v-if="data?.length">
          <li v-for="d in data" :key="d.exerciseLogId">
              {{ d.exerciseType }}
              {{ d.exerciseTime }}초
              {{ d.caloriesBurned }}kcal
          </li>
        </ul>
        <div v-else>해당 날짜의 운동 데이터가 없습니다.</div>
      </DialogContent>
    </Dialog>
  </div>
</template>
