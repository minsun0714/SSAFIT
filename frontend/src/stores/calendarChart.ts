import { ref } from 'vue'
import { defineStore } from 'pinia'
import type { ExerciseTimerResponse } from '@/api/interfaces/response'

export const useCalendarChartStore = defineStore('calendarChart', () => {
  const generateRandomColor = () => {
    const r = Math.floor(Math.random() * 256)
    const g = Math.floor(Math.random() * 256)
    const b = Math.floor(Math.random() * 256)
    return {
      backgroundColor: `rgba(${r}, ${g}, ${b}, 0.2)`,
      borderColor: `rgba(${r}, ${g}, ${b}, 1)`,
    }
  }

  const chartData = ref({
    labels: [] as string[],
    datasets: [
      {
        label: '',
        data: [] as number[],
        backgroundColor: [] as string[],
        borderColor: [] as string[],
        borderWidth: 1,
      },
    ],
  })
  const setChartData = (data: ExerciseTimerResponse[]) => {
    const aggregatedData = data.reduce(
      (acc, log) => {
        const existing = acc.find((item) => item.exerciseType === log.exerciseType)
        if (existing) {
          existing.exerciseTime += log.exerciseTime // 중복 값 합산
        } else {
          acc.push({ exerciseType: log.exerciseType, exerciseTime: log.exerciseTime })
        }
        return acc
      },
      [] as { exerciseType: string; exerciseTime: number }[],
    )

    const labels = aggregatedData.map((log) => log.exerciseType)
    const values = aggregatedData.map((log) => log.exerciseTime)
    const colors = labels.map(() => generateRandomColor())
    const backgroundColors = colors.map((color) => color.backgroundColor)
    const borderColors = colors.map((color) => color.borderColor)

    chartData.value.labels = labels
    chartData.value.datasets[0].data = values
    chartData.value.datasets[0].backgroundColor = backgroundColors
    chartData.value.datasets[0].borderColor = borderColors
  }

  return { chartData, setChartData }
})
