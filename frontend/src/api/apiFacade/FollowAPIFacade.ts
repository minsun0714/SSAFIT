import { useMutation, useQuery } from '@tanstack/vue-query'
import { queryClient } from '@/main'
import FollowService from '../services/FollowService'

class FollowApiFacade {
  // 팔로우
  static useFetchFollowRelations() {
    return useQuery({
      queryKey: ['follow'],
      queryFn: async () => {
        const result = await FollowService.fetchFollowRelations()
        console.log(result)
        return result
      },
    })
  }

  static useFollow() {
    return useMutation({
      mutationFn: (followerId: string) => FollowService.follow(followerId),
      onSuccess: async () => {
        await queryClient.invalidateQueries({
          queryKey: ['follow'],
          refetchType: 'active',
        })
      },
    })
  }

  static useUnfollow() {
    return useMutation({
      mutationFn: (followerId: string) => FollowService.unfollow(followerId),
      onSuccess: async () => {
        await queryClient.invalidateQueries({
          queryKey: ['follow'],
          refetchType: 'active',
        })
      },
    })
  }
}

export default FollowApiFacade
