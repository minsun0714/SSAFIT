import api from '../apiClient'
import type { MemberInfoRequest } from '../interfaces/request'

class MemberService {
  private static path = '/api/members'

  // Private 메서드로 API 호출 로직 관리
  private static async _signUp(signUpInfo: SignUpRequest): Promise<SignUpResponse> {
    const response = await api.post(this.path, signUpInfo)
    return response.data
  }

  private static async _fetchUserInfo(): Promise<MemberInfoResponse> {
    const response = await api.get(this.path)
    return response.data
  }

  private static async _updateUserInfo(
    updatedUserInfo: MemberInfoRequest,
  ): Promise<MemberInfoResponse> {
    // FormData 생성
    const formData = new FormData()

    // JSON 데이터 추가 (key: 'memberInfo', value: JSON 문자열)
    const { profileImg, ...info } = updatedUserInfo // profileImg를 제외한 나머지 데이터 분리
    formData.append('memberInfo', new Blob([JSON.stringify(info)], { type: 'application/json' }))

    // 파일 추가
    if (profileImg) {
      formData.append('profileImg', profileImg)
    }

    // 디버깅용: FormData 확인
    formData.forEach((value, key) => {
      console.log(key, value)
    })

    // FormData 전송
    const response = await api.put(this.path, formData)

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
