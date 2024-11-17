import api from '../apiClient'

class MemberService {
  private static path = '/api/auth/login'

  private static async _login(loginInfo: LoginRequest): Promise<LoginResponse> {
    const response = await api.post(this.path, loginInfo)
    return response.data
  }

  static login(loginInfo: LoginRequest): Promise<LoginResponse> {
    return this._login(loginInfo)
  }
}

export default MemberService
