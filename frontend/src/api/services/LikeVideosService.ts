import api from '../apiClient'
import type { LikeRequestDTO } from '../interfaces/request'
import type { LikeResponseVO } from '../interfaces/response'
import type { LikeVideos } from '../interfaces/common'

class LikeVideosService {
  private static path = '/api/likes'

  // 좋아요 등록
  static async addLike(requestDTO: LikeRequestDTO): Promise<LikeResponseVO> {
    try {
      const response = await api.post(this.path, requestDTO)
      return response.data
    } catch (error) {
      console.error('Failed to add like:', error)
      throw new Error('좋아요 등록에 실패했습니다.')
    }
  }

  // 특정 멤버의 좋아요 조회 (인증된 사용자 ID로 조회)
  static async getLikesByMember(): Promise<LikeVideos[]> {
    try {
      // 인증된 사용자의 ID를 자동으로 추출하여 요청
      const response = await api.get(`${this.path}/list`)
      return response.data
    } catch (error) {
      console.error('Failed to get likes for member:', error)
      throw new Error('멤버의 좋아요 조회에 실패했습니다.')
    }
  }

  // 특정 영상의 좋아요 조회
  static async getLikesByVideo(videoId: string): Promise<LikeVideos[]> {
    try {
      const response = await api.get(`${this.path}/${videoId}`)
      return response.data
    } catch (error) {
      console.error(`Failed to get likes for video ${videoId}:`, error)
      throw new Error('영상의 좋아요 조회에 실패했습니다.')
    }
  }

  // 좋아요 삭제
  static async removeLikeByVideoId(videoId: string): Promise<void> {
    try {
      await api.delete(`${this.path}/${videoId}`)
    } catch (error) {
      console.error(`Failed to remove like for video ${videoId}:`, error)
      throw new Error('좋아요 삭제에 실패했습니다.')
    }
  }
}

export default LikeVideosService
