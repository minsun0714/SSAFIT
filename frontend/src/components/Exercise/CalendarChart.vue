<script setup lang="ts">
import { Chart as ChartJS, ArcElement, Tooltip, Legend } from 'chart.js'
import { Doughnut } from 'vue-chartjs'
import { useCalendarChartStore } from '@/stores/calendarChart'
import { ExerciseTimerResponse } from '@/api/interfaces/response'

// Chart.js 플러그인 등록
ChartJS.register(ArcElement, Tooltip, Legend)

const { exerciseLogVO } = defineProps<{
  exerciseLogVO: ExerciseTimerResponse[]
}>()

const calendarChartStore = useCalendarChartStore()
calendarChartStore.setChartData(exerciseLogVO)
</script>

<template>
  <div
    class="w-full h-[400px] flex justify-center items-center border"
    v-if="calendarChartStore.chartData.datasets[0].data.length"
  >
    <Doughnut :data="calendarChartStore.chartData" />
  </div>
</template>
