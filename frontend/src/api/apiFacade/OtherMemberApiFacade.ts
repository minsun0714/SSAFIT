import { useQuery } from '@tanstack/vue-query'
import { useRoute } from 'vue-router'
import OtherMemberService from '@/api/services/OtherMemberService'

class OtherMemberApiFacade {
  static useFetchUserInfo() {
    const route = useRoute()
    const memberId = route.params.memberId as string

    return useQuery({
      queryKey: ['member', memberId],
      queryFn: () => OtherMemberService.fetchUserInfo(memberId),
    })
  }

  static useFetchExerciseGrass() {
    const route = useRoute()
    const memberId = route.params.memberId as string

    return useQuery({
      queryKey: ['exerciseGrass', memberId],
      queryFn: () => OtherMemberService.fetchExerciseGrass(memberId),
    })
  }

  static useFetchFollowRelations() {
    const route = useRoute()
    const memberId = route.params.memberId as string

    return useQuery({
      queryKey: ['follow', memberId],
      queryFn: () => OtherMemberService.fetchFollowRelations(memberId),
    })
  }
}

export default OtherMemberApiFacade
