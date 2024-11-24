import { useQuery } from '@tanstack/vue-query'
import { useRoute } from 'vue-router'
import OtherMemberService from '@/api/services/OtherMemberService'

class OtherMemberApiFacade {
  static useFetchUserInfo() {
    const route = useRoute()
    const memberId = (route.params.memberId as string)
    return useQuery({
      queryKey: ['user', memberId],
      queryFn: async () => {
        const result =  await OtherMemberService.fetchUserInfo(memberId)
        console.log(result)
        return result
      },
      retry: 0,
    })
  }

  static useFetchExerciseGrass() {
    const route = useRoute()
    const memberId = route.params.memberId as string

    return useQuery({
      queryKey: ['exerciseGrass', memberId],
      queryFn: async () => {
        const result =  await OtherMemberService.fetchExerciseGrass(memberId)
        console.log(result)
        return result
      },
    })
  }

  static useFetchFollowRelations() {
    const route = useRoute()
    const memberId = route.params.memberId as string

    return useQuery({
      queryKey: ['follow', memberId],
      queryFn: async () => {
        const result =  await OtherMemberService.fetchFollowRelations(memberId)
        console.log(result)
        return result
      },
    })
  }
}

export default OtherMemberApiFacade
