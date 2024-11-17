import { useMutation, useQuery } from '@tanstack/vue-query'
import { useRouter } from 'vue-router'
import MemberService from '../services/MemberService'

class MemberApiFacade {
  // 유저, 인증 관련 메서드
  static useFetchUserInfo() {
    return useQuery({
      queryKey: ['user'],
      queryFn: () => MemberService.fetchUserInfo(),
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
        const accessToken = response.accessToken
        localStorage.setItem('token', accessToken)
        router.push('/')
      },
    })
  }

  static useUpdateUser(updatedUserInfo: MemberInfoRequest) {
    return useMutation({
      mutationFn: () => MemberService.updateUserInfo(updatedUserInfo),
    })
  }

  static useDeleteUser() {
    return useMutation({ mutationFn: () => MemberService.deleteUserInfo() })
  }
}

export default MemberApiFacade
