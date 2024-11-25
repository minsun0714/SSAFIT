import { useQuery } from '@tanstack/vue-query'
import VideoSearchService from '../services/VideoSearchService'
import type { VideoSortType } from '../interfaces/common'
import { useRoute } from 'vue-router'
import { computed, ref, watch } from 'vue'
import { useDebounce, useThrottle } from '@vueuse/core'

class VideoSearchApiFacade {
  // 페이지네이션
  static useFetchPaginatedAndSortedVideos() {
    const route = useRoute()

    const page = computed(() => Number(route.query.page) || 1)
    const keyword = ref(route.query.keyword || '')
    const sort = computed(() => route.query.sort || 'RECENT')

    const debouncedKeyword = useDebounce(keyword, 300)

    watch(
      () => route.query.keyword,
      (newValue) => {
        keyword.value = newValue || ''
      },
      { immediate: true },
    )

    return useQuery({
      queryKey: ['search', debouncedKeyword, page, sort],
      queryFn: async () => {
        const result = await VideoSearchService.getPaginatedAndSortedVideos(
          debouncedKeyword.value as string,
          page.value,
          9,
          sort.value as VideoSortType,
        )
        console.log(result)
        return result
      },
    })
  }

  static useFetchAutocompleteSuggestions() {
    const route = useRoute()
    const keyword = ref(route.query.keyword || '')
    const throttledKeyword = useThrottle(keyword, 300)

    watch(
      () => route.query.keyword,
      (newValue) => {
        keyword.value = newValue || ''
      },
      { immediate: true },
    )

    return useQuery({
      queryKey: ['autocomplete', throttledKeyword],
      queryFn: async () => {
        const result = await VideoSearchService.getAutocompleteSuggestions(throttledKeyword.value as string)
        console.log(result)
        return result
      },
    })
  }
}

export default VideoSearchApiFacade
