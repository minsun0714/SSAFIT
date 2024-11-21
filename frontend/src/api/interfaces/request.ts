interface SignUpRequest extends Member {
  passwordConfirm: string
}

interface LoginRequest extends Omit<Member, 'name' | 'nickname'> {}

interface MemberInfoRequest extends Omit<Member, 'name'> {
  passwordConfirm: string
  profileImg: string
}

interface ExerciseTimerRequest extends Exercise {}
