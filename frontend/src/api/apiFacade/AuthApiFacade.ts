import { useMutation } from '@tanstack/vue-query'
import AuthService from '../services/AuthService'
import { useRouter } from 'vue-router'

class AuthApiFacade {
  // 유저, 인증 관련 메서드

  static useLogin() {
    const router = useRouter()
    return useMutation({
      mutationFn: ({ memberId, password }: LoginRequest) =>
        AuthService.login({ memberId, password }),
      onSuccess: (response) => {
        const accessToken = response.accessToken
        localStorage.setItem('token', accessToken)
        router.push('/')
      },
    })
  }
}

export default AuthApiFacade
