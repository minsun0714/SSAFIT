import api from '../apiClient'
import type { VideoRegistDTO } from '../interfaces/request'
import type { VideoRegistVO, VideoDetailVO, VideoCardVO } from '../interfaces/response'
class VideoService {
  private static path = '/api/videos'

  // Private 메서드로 API 호출 로직 관리
  private static async _registerVideo(videoRegistDTO: VideoRegistDTO): Promise<VideoRegistVO> {
    const response = await api.post(this.path, videoRegistDTO)
    return response.data
  }

  private static async _getVideoById(videoId: string): Promise<VideoDetailVO> {
    const response = await api.get(`${this.path}/${videoId}`)
    return response.data
  }

  private static async _getAllVideos(page: number, size: number): Promise<VideoCardVO[]> {
    const response = await api.get(`${this.path}/list`, {
      params: { page, size }
    })
    return response.data
  }

    // Public 메서드로 외부에서 사용할 수 있도록 제공
  static registerVideo(videoRegistDTO: VideoRegistDTO): Promise<VideoRegistVO> {
    return this._registerVideo(videoRegistDTO)
  }

  static getVideoById(videoId: string): Promise<VideoDetailVO> {
    return this._getVideoById(videoId)
  }

  static getAllVideos(page: number = 0, size: number = 9): Promise<VideoCardVO[]> {
    return this._getAllVideos(page, size)
  }
}

export default VideoService
