import api from '../apiClient'
import type {
  ExerciseGrass,
  ExerciseLogResponse,
  ExerciseTimerResponse,
} from '../interfaces/response'

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

  private static async _fetchExerciseLogsByDate(date: string): Promise<ExerciseLogResponse> {
    const response = await api.get(this.path + `/${date}`)
    return response.data
  }

  private static async _fetchExerciseGrass(): Promise<ExerciseGrass[]> {
    const response = await api.get(this.path + '/grass')
    console.log(response.data)
    return response.data
  }

  private static async _deleteExerciseLog(exerciseLogId: number): Promise<void> {
    await api.delete(this.path + `/${exerciseLogId}`)
  }

  // Public 메서드로 외부에서 사용할 수 있도록 제공
  static async createExerciseLog(
    exerciseLogInfo: ExerciseTimerRequest,
  ): Promise<ExerciseTimerResponse> {
    return await this._createExerciseLog(exerciseLogInfo)
  }

  static async fetchCardDataList(): Promise<ExerciseCard[]> {
    return await this._fetchCardDataList()
  }

  static async fetchExerciseLogsByDate(date: string): Promise<ExerciseLogResponse> {
    return await this._fetchExerciseLogsByDate(date)
  }

  static async fetchExerciseGrass(): Promise<ExerciseGrass[]> {
    return await this._fetchExerciseGrass()
  }

  static async deleteExerciseLog(exerciseLogId: number): Promise<void> {
    return await this._deleteExerciseLog(exerciseLogId)
  }
}

export default ExerciseLogService
