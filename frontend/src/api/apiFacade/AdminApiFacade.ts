import { useMutation, useQuery } from '@tanstack/vue-query'
import { queryClient } from '@/main'
import AdminService from '../services/AdminService'
import type { VideoStatus } from '../interfaces/common'
import { useRoute } from 'vue-router'
import { computed } from 'vue'

class AdminApiFacade {
  // 팔로우
  static useFetchPendingVideos() {
    const route = useRoute()

    const page = computed(() => Number(route.query.page) || 1)

    return useQuery({
      queryKey: ['admin', page],
      queryFn: async () => {
        const result = await AdminService.getPendingVideos(page.value, 10)
        console.log(result)
        return result
      },
    })
  }

  static useUpdateVideoStatus() {
    return useMutation({
      mutationFn: ({ videoId, status }: { videoId: string; status: VideoStatus }) =>
        AdminService.updateVideoStatus(videoId, status),
      onSuccess: async () => {
        await queryClient.invalidateQueries({
          queryKey: ['video'],
          refetchType: 'active',
        })
      },
    })
  }
}

export default AdminApiFacade
