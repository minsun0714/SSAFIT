interface SignUpResponse extends Omit<Member, 'password'> {
  profileImg: string
  accessToken: string
}

interface LoginResponse {
  grantType: 'Bearer'
  accessToken: string
}

interface MemberInfoResponse extends Omit<Member, 'password'> {
  profileImg: string
  role: 'ROLE_ADMIN' | 'ROLE_USER'
}

interface ExerciseTimerResponse extends Exercise {
  exerciseLogId: string
  caloriesBurned: number
  fatBurned: number
}

interface ExerciseCard {
  title: string
  cardIconUrl: string
  cardType: 'MINUTE' | 'KCAL' | 'KG'
   currentValue: number
  lastValue: number
  up: boolean
}
