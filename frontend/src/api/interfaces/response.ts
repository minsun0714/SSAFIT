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
type CardType = 'MINUTE' | 'KCAL' | 'KG'
interface ExerciseCard {
  title: string
  cardIconUrl: string
  cardType: CardType
  currentValue: number
  lastValue: number
  up: boolean
}

interface PagedExerciseTypeResponse extends MET {
  currentPage: number
  pageSize: number
  totalPages: number
  totalItems: number
}
