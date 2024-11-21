<script setup lang="ts">
import { Chart as ChartJS, ArcElement, Tooltip, Legend } from 'chart.js'
import { Doughnut } from 'vue-chartjs'
import { useCalendarChartStore } from '@/stores/calendarChart'
import { ExerciseTimerResponse } from '@/api/interfaces/response'

// Chart.js 플러그인 등록
ChartJS.register(ArcElement, Tooltip, Legend)

const { data } = defineProps<{
  data: ExerciseTimerResponse[]
}>()

const calendarChartStore = useCalendarChartStore()
calendarChartStore.setChartData(data)
</script>

<template>
  <div class="w-full h-[400px] flex justify-center">
    <Doughnut :data="calendarChartStore.chartData" />
  </div>
</template>
