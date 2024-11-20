import { useQuery } from '@tanstack/vue-query'
import ExerciseTypeService from '../services/ExerciseTypeService'
import { useRoute } from 'vue-router'
import { ref, watch } from 'vue'

class ExerciseTypeApiFacade {
  static useFetchPagedExerciseType(page: number) {
    const route = useRoute()
    const exerciseType = ref(route.query.exerciseType || '')

    watch(
      () => route.query.exerciseType,
      (newValue) => {
        exerciseType.value = newValue || ''
      },
      { immediate: true },
    )

    return useQuery({
      queryKey: [exerciseType, page],
      queryFn: async () => {
        return await ExerciseTypeService.fetchPagedExerciseType(page, exerciseType.value as string)
      },
      retry: 0,
    })
  }
}

export default ExerciseTypeApiFacade
