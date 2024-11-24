import api from '../apiClient'
import type { FollowInfoResponse } from '../interfaces/response'

class FollowService {
  private static path = '/api/follows'

  // Private 메서드로 API 호출 로직 관리
  private static async _follow(followerId: string): Promise<void> {
    console.log(followerId)
    const response = await api.post(this.path, null, {
      params: {
        followerId,
      },
    })
    return response.data
  }

  private static async _fetchFollowRelations(): Promise<FollowInfoResponse> {
    const response = await api.get(this.path)
    return response.data
  }

  private static async _unfollow(followerId: string): Promise<void> {
    await api.delete(this.path, {
      params: {
        followerId,
      },
    })
  }

  // Public 메서드로 외부에서 사용할 수 있도록 제공
  static async follow(followerId: string): Promise<void> {
    return await this._follow(followerId)
  }

  static fetchFollowRelations(): Promise<FollowInfoResponse> {
    return this._fetchFollowRelations()
  }

  static async unfollow(followerId: string): Promise<void> {
    return await this._unfollow(followerId)
  }
}

export default FollowService
