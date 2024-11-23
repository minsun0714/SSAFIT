import api from '../apiClient'
import type {
  ExerciseGrass,
  FollowInfoResponse,
  MemberInfoResponse,
} from '@/api/interfaces/response'

class OtherMemberService {
  private static path = '/api'

  /**
   * Private: 특정 사용자 정보 조회
   */
  private static async _fetchUserInfo(memberId: string): Promise<MemberInfoResponse> {
    const response = await api.get(`${this.path}/members/${memberId}`)
    return response.data
  }

  private static async _fetchExerciseGrass(memberId: string): Promise<ExerciseGrass[]> {
    const response = await api.get(`${this.path}/exercise-log/grass/${memberId}`)
    return response.data
  }

  private static async _fetchFollowRelations(memberId: string): Promise<FollowInfoResponse> {
    const response = await api.get(`${this.path}/follows/${memberId}`)
    return response.data
  }

  // Public 메서드
  static fetchUserInfo(memberId: string): Promise<MemberInfoResponse> {
    return this._fetchUserInfo(memberId)
  }

  static fetchExerciseGrass(memberId: string): Promise<ExerciseGrass[]> {
    return this._fetchExerciseGrass(memberId)
  }

  static fetchFollowRelations(memberId: string): Promise<FollowInfoResponse> {
    return this._fetchFollowRelations(memberId)
  }
}

export default OtherMemberService
