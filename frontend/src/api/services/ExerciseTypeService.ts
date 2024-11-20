import api from '../apiClient'
import type { PagedExerciseTypeResponse } from '../interfaces/response'

class ExerciseTypeService {
  private static path = '/api/exercise-type'
  // Private 메서드로 API 호출 로직 관리

  private static async _fetchPagedExerciseType(
    page: number,
    exerciseType: string,
  ): Promise<PagedExerciseTypeResponse[]> {
    const response = await api.get(this.path, {
      params: {
        page,
        perPage: 10,
        exerciseType,
      },
    })
    console.log(response.data)
    return response.data
  }

  // Public 메서드로 외부에서 사용할 수 있도록 제공
  static async fetchPagedExerciseType(
    page: number,
    exerciseType: string,
  ): Promise<PagedExerciseTypeResponse[]> {
    return await this._fetchPagedExerciseType(page, exerciseType)
  }
}

export default ExerciseTypeService
