interface SignUpRequest extends Member {
  passwordConfirm: string
}

interface LoginRequest extends Omit<Member, 'name' | 'nickname'> {}

export interface MemberInfoRequest extends Omit<Member, 'name'> {
  passwordConfirm: string
  profileImg: File | null
}

interface ExerciseTimerRequest extends Exercise {}

type Part = 'RUN' | 'STRENGTH' | 'RELAX'
export interface VideoRegistDTO {
  videoUrl: string
  introduceText: string
  part: Part
}

export interface ReviewRequestDTO{
  memberId: string;
  videoId: string;
  content: string;
  rating: number;
}
