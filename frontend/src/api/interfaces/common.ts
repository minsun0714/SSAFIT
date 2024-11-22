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

interface Follow {
  followingList: Member[]
  followerList: Member[]
}
