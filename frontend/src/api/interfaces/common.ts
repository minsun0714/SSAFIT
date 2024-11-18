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

interface MET {
  운동명: string
  'MET 계수': number
}
