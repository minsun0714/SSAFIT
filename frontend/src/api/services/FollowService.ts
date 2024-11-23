import api from '../apiClient'
import type { MemberInfoRequest } from '../interfaces/request'
import type { FollowInfoResponse } from '../interfaces/response'

class FollowService {
  private static path = '/api/follows'

  // Private 메서드로 API 호출 로직 관리
  private static async _follow(followerId: string): Promise<void> {
    const response = await api.post(this.path, {
      params: {
        follower_id: followerId,
      },
    })
    return response.data
  }

  private static async _fetchFollowRelations(): Promise<FollowInfoResponse> {
    const response = await api.get(this.path)
    console.log(response.data)
    return response.data
  }

  private static async _unfollow(followerId: string): Promise<void> {
    await api.delete(this.path, {
      params: {
        follower_id: followerId,
      },
    })
  }

  // Public 메서드로 외부에서 사용할 수 있도록 제공
  static follow(followerId: string): Promise<void> {
    return this._follow(followerId)
  }

  static fetchFollowRelations(): Promise<FollowInfoResponse> {
    return this._fetchFollowRelations()
  }

  static unfollow(followerId: string): Promise<void> {
    return this._unfollow(followerId)
  }
}

export default FollowService
