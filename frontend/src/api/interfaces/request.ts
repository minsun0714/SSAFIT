interface SignUpRequest extends Member {
  passwordConfirm: string
}

interface LoginRequest extends Omit<Member, 'name' | 'nickname'> {}

export interface MemberInfoRequest extends Omit<Member, 'name'> {
  passwordConfirm: string
  profileImg: File | null
}

interface ExerciseTimerRequest extends Exercise {}

export interface VideoRegistDTO {
  title: string
  description: string
  videoFile: File
}

