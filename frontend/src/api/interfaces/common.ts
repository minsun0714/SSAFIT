import type { MemberInfoResponse } from "./response"

interface Member {
  memberId: string
  name: string
  nickname: string
  password: string
}

interface Exercise {
  exerciseDate: Date
  exerciseType: string
  exerciseTime: number
}

export interface Follow {
  followingList: MemberInfoResponse[]
  followerList: MemberInfoResponse[]
}
