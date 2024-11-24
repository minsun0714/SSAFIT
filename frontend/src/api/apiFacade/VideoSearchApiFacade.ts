import { useQuery } from '@tanstack/vue-query'
import VideoSearchService from '../services/VideoSearchService'
import type { VideoSortType } from '../interfaces/common'

class VideoSearchApiFacade {
  // 페이지네이션
  static useFetchPaginatedAndSortedVideos(
    keyword: string,
    page: number,
    size: number,
    sort: VideoSortType,
  ) {
    return useQuery({
      queryKey: ['search', keyword, page, size, sort],
      queryFn: async () => {
        const result = await VideoSearchService.getPaginatedAndSortedVideos(
          keyword,
          page,
          size,
          sort,
        )
        console.log(result)
        return result
      },
    })
  }

  static useFetchAutocompleteSuggestions(keyword: string) {
    return useQuery({
      queryKey: ['autocomplete', keyword],
      queryFn: async () => {
        const result = await VideoSearchService.getAutocompleteSuggestions(keyword)
        console.log(result)
        return result
      },
    })
  }
}

export default VideoSearchApiFacade
