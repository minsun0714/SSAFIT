import { useMutation, useQuery } from '@tanstack/vue-query'
import { queryClient } from '@/main'
import AdminService from '../services/AdminService'
import type { VideoStatus } from '../interfaces/common'

class FollowApiFacade {
  // 팔로우
  static useFetchPendingVideos(page: number, size: number) {
    return useQuery({
      queryKey: ['admin', page, size],
      queryFn: async () => {
        const result = await AdminService.getPendingVideos(page, size)
        console.log(result)
        return result
      },
    })
  }

  static useUpdateVideoStatus(videoId: string, status: VideoStatus) {
    return useMutation({
      mutationFn: () => AdminService.updateVideoStatus(videoId, status),
      onSuccess: async () => {
        await queryClient.invalidateQueries({
          queryKey: ['video', videoId, status],
          refetchType: 'active',
        })
      },
    })
  }
}

export default FollowApiFacade
