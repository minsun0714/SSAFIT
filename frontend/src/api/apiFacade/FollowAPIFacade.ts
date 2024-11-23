import { useMutation, useQuery } from '@tanstack/vue-query'
import { queryClient } from '@/main'
import FollowService from '../services/FollowService'

class FollowApiFacade {
  // 팔로우
  static useFetchFollowRelations() {
    return useQuery({
      queryKey: ['follow'],
      queryFn: () => FollowService.fetchFollowRelations(),
      retry: 0,
    })
  }

  static useFollow() {
    return useMutation({
      mutationFn: (followerId: string) => FollowService.follow(followerId),
      onSuccess: () => {
        queryClient.refetchQueries()
      },
      retry: 0,
    })
  }

  static useUnfollow(followerId: string) {
    return useMutation({ mutationFn: () => FollowService.unfollow(followerId) })
  }
}

export default FollowApiFacade
