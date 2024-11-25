import type { Follow } from "./common"

interface SignUpResponse extends Omit<Member, 'password'> {
  profileImg: string
  accessToken: string
}

interface LoginResponse {
  grantType: 'Bearer'
  accessToken: string
}

export interface MemberInfoResponse extends Omit<Member, 'password'> {
  profileImg: string
  role: 'ROLE_ADMIN' | 'ROLE_USER'
}

export interface FollowInfoResponse extends Follow {}

export interface ExerciseTimerResponse extends Exercise {
  exerciseLogId: number
  caloriesBurned: number
  fatBurned: number
}

export interface ExerciseLogResponse {
  dailyTotalExerciseTime: number
  exerciseLogVO: ExerciseTimerResponse[]
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

export interface ExerciseType {
  운동명: string
  'MET 계수': number
}

export interface PaginationResponse<T> {
  currentPage: number
  pageSize: number
  totalPages: number
  totalItems: number
  data: T[]
}

export interface ExerciseGrass {
  date: string
  level: 0 | 1 | 2 | 3 | 4
  exerciseTime: number
}

export interface VideoResponse extends Video {}

type Part = 'Run' | 'STRENGTH' | 'RELAX'
export interface VideoRegistVO {
  videoUrl: string
  part: Part
  introductUrl: string
}

export interface VideoDetailVO {
  id: string
  title: string
  description: string
  videoUrl: string
  createdAt: string
  views: number
  likes: number
}

export interface VideoCardVO {
  id: string
  title: string
  description: string
  videoUrl: string
  createdAt: string
}
