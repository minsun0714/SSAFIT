import { useQuery } from '@tanstack/vue-query'
import ExerciseTypeService from '../services/ExerciseTypeService'

class ExerciseLogApiFacade {
  static useFetchCardDataList(page: number) {
    return useQuery({
      queryKey: ['exerciseType', page],
      queryFn: () => ExerciseTypeService.fetchPagedExerciseType(page),
      retry: 3,
    })
  }
}

export default ExerciseLogApiFacade
