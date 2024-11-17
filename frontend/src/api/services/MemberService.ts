import api from '../apiClient'

class MemberService {
  private static path = '/api/members'

  // Private 메서드로 API 호출 로직 관리
  private static async _signUp(signUpInfo: SignUpRequest): Promise<SignUpResponse> {
    const response = await api.post(this.path, signUpInfo)
    return response.data
  }

  private  static async _fetchUserInfo(): Promise<MemberInfoResponse> {
    const response = await api.get(this.path)
    return response.data
  }

  private static async _updateUserInfo(updatedUserInfo: MemberInfoRequest): Promise<MemberInfoResponse> {
    const response = await api.put(this.path, updatedUserInfo)
    return response.data
  }

  private static async _deleteUserInfo(): Promise<void> {
    await api.delete(this.path)
  }

  // Public 메서드로 외부에서 사용할 수 있도록 제공
  static signUp(signUpInfo: SignUpRequest): Promise<SignUpResponse> {
    return this._signUp(signUpInfo)
  }

  static fetchUserInfo(): Promise<MemberInfoResponse> {
    return this._fetchUserInfo()
  }

  static updateUserInfo(updatedUserInfo: MemberInfoRequest): Promise<MemberInfoResponse> {
    return this._updateUserInfo(updatedUserInfo)
  }

  static deleteUserInfo(): Promise<void> {
    return this._deleteUserInfo()
  }
}

export default MemberService
