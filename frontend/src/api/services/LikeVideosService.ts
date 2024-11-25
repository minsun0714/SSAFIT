import api from '../apiClient'
import type { LikeRequestDTO } from '../interfaces/request'
import type { LikeResponseVO } from '../interfaces/response'
import type { LikeVideos } from '../interfaces/common'

class LikeVideosService {
  private static path = '/api/likes'

  // 좋아요 등록
  static async addLike(requestDTO: LikeRequestDTO): Promise<LikeResponseVO> {
    const response = await api.post(this.path, requestDTO)
    return response.data
  }

  // 나의 좋아요 조회
  static async getLikes(): Promise<LikeVideos[]> {
    const response = await api.get(`${this.path}/list`)
    return response.data
  }

  // 특정 멤버의 좋아요 조회
  static async getLikesByMember(memberId: string): Promise<LikeVideos[]> {
    const response = await api.get(`${this.path}/list/${memberId}`)
    return response.data
  }

  // 특정 영상의 좋아요 조회
  static async getLikesByVideo(videoId: string): Promise<LikeVideos[]> {
    const response = await api.get(`${this.path}/${videoId}`)
    return response.data
  }

  // 좋아요 삭제
  static async removeLike(likeId: string): Promise<void> {
    await api.delete(`${this.path}/${likeId}`)
  }
}

export default LikeVideosService
