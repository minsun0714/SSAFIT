import api from '../apiClient'
import type { VideoSortType } from '../interfaces/common'
import type { PaginationResponse, VideoResponse } from '../interfaces/response'

class VideoSearchService {
  private static path = '/api/video/search'

  // Private 메서드로 API 호출 로직 관리
  private static async _getPaginatedAndSortedVideos(
    keyword: string,
    page: number,
    size: number,
    sort: VideoSortType,
  ): Promise<PaginationResponse<VideoResponse>> {
    const response = await api.get(this.path, {
      params: {
        keyword,
        page,
        size,
        sort,
      },
    })
    return response.data
  }

  private static async _getAutocompleteSuggestions(keyword: string): Promise<string[]> {
    const response = await api.get(this.path + '/autocomplete', {
      params: {
        keyword,
      },
    })
    return response.data
  }

  // Public 메서드로 외부에서 사용할 수 있도록 제공
  static async getPaginatedAndSortedVideos(
    keyword: string,
    page: number,
    size: number,
    sort: VideoSortType,
  ): Promise<PaginationResponse<VideoResponse>> {
    return await this._getPaginatedAndSortedVideos(keyword, page, size, sort)
  }

  static getAutocompleteSuggestions(keyword: string): Promise<string[]> {
    return this._getAutocompleteSuggestions(keyword)
  }
}

export default VideoSearchService
