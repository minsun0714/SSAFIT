import { useQuery } from '@tanstack/vue-query'
import ExerciseTypeService from '../services/ExerciseTypeService'
import { useRoute } from 'vue-router'
import { ref, watch, computed } from 'vue'

class ExerciseTypeApiFacade {
  static useFetchPagedExerciseType(size: number) {
    const route = useRoute()

    const page = computed(() => Number(route.query.page) || 1)
    const exerciseType = ref(route.query.exerciseType || '')

    watch(
      () => route.query.exerciseType,
      (newValue) => {
        exerciseType.value = newValue || ''
      },
      { immediate: true },
    )

    return useQuery({
      queryKey: computed(() => [exerciseType.value, page.value]),
      queryFn: async () => {
        return await ExerciseTypeService.fetchPagedExerciseType(
          page.value,
          size,
          exerciseType.value as string,
        )
      },
      retry: 0,
    })
  }
}

export default ExerciseTypeApiFacade
