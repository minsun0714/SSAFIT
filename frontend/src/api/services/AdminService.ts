import api from '../apiClient'
import type { VideoStatus } from '../interfaces/common'
import type { PaginationResponse, VideoResponse } from '../interfaces/response'

class AdminService {
  private static path = '/api/admin/videos'

  private static async _getPendingVideos(
    page: number,
    size: number,
  ): Promise<PaginationResponse<VideoResponse>> {
    const response = await api.get(this.path + '/pending', {
      params: {
        page,
        size,
      },
    })
    return response.data
  }

  private static async _updateVideoStatus(videoId: string, status: VideoStatus): Promise<void> {
    const response = await api.patch(this.path + `/${videoId}`, {
      status,
    })
    return response.data
  }

  static getPendingVideos(page: number, size: number): Promise<PaginationResponse<VideoResponse>> {
    return this._getPendingVideos(page, size)
  }

  static updateVideoStatus(videoId: string, status: VideoStatus): Promise<void> {
    return this._updateVideoStatus(videoId, status)
  }
}

export default AdminService
