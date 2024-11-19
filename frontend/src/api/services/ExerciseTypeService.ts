import api from '../apiClient'

class ExerciseTypeService {
  private static path = '/api/exercise-type'
  // Private 메서드로 API 호출 로직 관리

  private static async _fetchPagedExerciseType(page: number): Promise<PagedExerciseTypeResponse[]> {
    const response = await api.get(this.path, {
      params: {
        page,
      },
    })
    return response.data
  }

  // Public 메서드로 외부에서 사용할 수 있도록 제공
  static async fetchPagedExerciseType(page: number): Promise<PagedExerciseTypeResponse[]> {
    return await this._fetchPagedExerciseType(page)
  }
}

export default ExerciseTypeService
