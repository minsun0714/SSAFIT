interface SignUpRequest extends Member {
  passwordConfirm: string
}

interface LoginRequest extends Omit<Member, 'name' | 'nickname'> {}

interface MemberInfoRequest extends Omit<Member, 'name'> {
  passwordConfirm: string
  profileImg: string
  isFollowerListOpen: boolean
}

interface ExerciseTimerRequest extends Exercise {}
