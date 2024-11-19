import { useMutation, useQuery } from '@tanstack/vue-query'
import ExerciseLogService from '../services/ExerciseLogService'
import { queryClient } from '@/main'

class ExerciseLogApiFacade {
  static useCreateExerciseLog() {
    return useMutation({
      mutationFn: ({ exerciseDate, exerciseType, exerciseTime }: ExerciseTimerRequest) =>
        ExerciseLogService.createExerciseLog({
          exerciseDate,
          exerciseType,
          exerciseTime,
        }),
      onSuccess: () => {
        queryClient.refetchQueries()
      },
      retry: 0,
    })
  }
  static useFetchCardDataList() {
    return useQuery({
      queryKey: ['exerciseCards'],
      queryFn: () => ExerciseLogService.fetchCardDataList(),
      retry: 3,
    })
  }

  static useFetchExerciseLogsByDate(date: string) {
    return useQuery({
      queryKey: ['exerciseLog', date],
      queryFn: () => ExerciseLogService.fetchExerciseLogsByDate(date),
      retry: 0,
    })
  }

  static useDeleteExerciseLog() {
    return useMutation({
      mutationFn: ({exerciseLogId}: {exerciseLogId: number}) => ExerciseLogService.deleteExerciseLog(exerciseLogId),
      onSuccess: () => {
        queryClient.refetchQueries()
      }
    })
  }
}

export default ExerciseLogApiFacade
