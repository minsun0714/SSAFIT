import { useMutation } from '@tanstack/vue-query'
import AuthService from '../services/AuthService'
import { useRouter } from 'vue-router'
import { queryClient } from '@/main'

class AuthApiFacade {
  // 유저, 인증 관련 메서드

  static useLogin() {
    const router = useRouter()
    return useMutation({
      mutationFn: ({ memberId, password }: LoginRequest) =>
        AuthService.login({ memberId, password }),
      onSuccess: (response) => {
        const accessToken = response.accessToken
        queryClient.refetchQueries()
        localStorage.setItem('token', accessToken)
        router.push('/')
        alert('로그인되었습니다.')
      },
    })
  }
}

export default AuthApiFacade
