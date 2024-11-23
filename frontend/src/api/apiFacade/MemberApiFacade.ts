import { useMutation, useQuery } from '@tanstack/vue-query'
import { useRouter } from 'vue-router'
import MemberService from '../services/MemberService'
import { queryClient } from '@/main'

class MemberApiFacade {
  // 유저, 인증 관련 메서드
  static useFetchUserInfo() {
    return useQuery({
      queryKey: ['user'],
      queryFn: () => MemberService.fetchUserInfo(),
      retry: 0,
    })
  }

  static useSignUp() {
    const router = useRouter()
    return useMutation({
      mutationFn: ({ memberId, password, passwordConfirm, name, nickname }: SignUpRequest) =>
        MemberService.signUp({
          memberId,
          password,
          passwordConfirm,
          name,
          nickname,
        }),
      onSuccess: (response) => {
        queryClient.refetchQueries()
        const accessToken = response.accessToken
        localStorage.setItem('token', accessToken)
        router.push('/')
      },
      retry: 0,
    })
  }

  static useUpdateUser() {
    return useMutation({
      mutationFn: (updatedUserInfo: MemberInfoRequest) =>
        MemberService.updateUserInfo(updatedUserInfo),
      onSuccess: () => {
        queryClient.invalidateQueries()
        queryClient.refetchQueries()
        alert('수정되었습니다.')
      },
    })
  }

  static useUpdateWeight() {
    return useMutation({
      mutationFn: (weight: number) =>
        MemberService.updateWeight(weight),
      onSuccess: () => {
        queryClient.invalidateQueries()
        queryClient.refetchQueries()
      },
    })
  }

  static useDeleteUser() {
    return useMutation({ mutationFn: () => MemberService.deleteUserInfo() })
  }
}

export default MemberApiFacade
