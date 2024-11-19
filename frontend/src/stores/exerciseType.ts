import { ref } from 'vue'
import { defineStore } from 'pinia'

export const useExerciseStore = defineStore('exerciseType', () => {
  const exercise = ref<string>('')

  function handleClickExercise(clickedExercise: string) {
    exercise.value = clickedExercise
  }

  return { exercise, handleClickExercise }
})
