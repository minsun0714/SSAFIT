import api from '../apiClient'

class ExerciseLogService {
  private static path = '/api/exercise-log'

  // Private 메서드로 API 호출 로직 관리
  private static async _createExerciseLog(
    exerciseLogInfo: ExerciseTimerRequest,
  ): Promise<ExerciseTimerResponse> {
    const response = await api.post(this.path, exerciseLogInfo)
    return response.data
  }

  private static async _fetchCardDataList(): Promise<ExerciseCard[]> {
    const response = await api.get(this.path + '/cards')
    return response.data
  }

  private static async _fetchExerciseLogsByDate(date: Date): Promise<ExerciseTimerResponse[]> {
    const response = await api.get(this.path + `/${date}`)
    return response.data
  }

  private static async _deleteExerciseLog(exerciseLogId: number): Promise<void> {
    await api.delete(this.path + `${exerciseLogId}`)
  }

  // Public 메서드로 외부에서 사용할 수 있도록 제공
  static createExerciseLog(exerciseLogInfo: ExerciseTimerRequest): Promise<ExerciseTimerResponse> {
    return this._createExerciseLog(exerciseLogInfo)
  }

  static fetchCardDataList(): Promise<ExerciseCard[]> {
    return this._fetchCardDataList()
  }

  static fetchExerciseLogsByDate(date: Date): Promise<ExerciseTimerResponse[]> {
    return this._fetchExerciseLogsByDate(date)
  }

  static deleteExerciseLog(exerciseLogId: number): Promise<void> {
    return this._deleteExerciseLog(exerciseLogId)
  }
}

export default ExerciseLogService