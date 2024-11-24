import { useQuery } from '@tanstack/vue-query'
import VideoSearchService from '../services/VideoSearchService'
import type { VideoSortType } from '../interfaces/common'
import { useRoute } from 'vue-router'
import { computed, ref } from 'vue'

class VideoSearchApiFacade {
  // 페이지네이션
  static useFetchPaginatedAndSortedVideos() {
    const route = useRoute()

    const page = computed(() => Number(route.query.page) || 1)
    const keyword = ref(route.query.keyword || '')
    const sort = computed(() => route.query.sort || 'RECENT')

    return useQuery({
      queryKey: ['search', keyword, page, sort],
      queryFn: async () => {
        const result = await VideoSearchService.getPaginatedAndSortedVideos(
          keyword.value as string,
          page.value,
          9,
          sort.value as VideoSortType,
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
