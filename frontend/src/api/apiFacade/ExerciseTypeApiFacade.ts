import { useQuery } from '@tanstack/vue-query'
import ExerciseTypeService from '../services/ExerciseTypeService'
import { useRoute } from 'vue-router'

class ExerciseTypeApiFacade {
  static useFetchPagedExerciseType(page: number) {
    const route = useRoute()
    const exerciseType = route.query.exerciseType || ''
    console.log('exerciseType', exerciseType)
    return useQuery({
      queryKey: [exerciseType, page],
      queryFn: () => ExerciseTypeService.fetchPagedExerciseType(page, exerciseType as string),
      retry: 3,
    })
  }
}

export default ExerciseTypeApiFacade
