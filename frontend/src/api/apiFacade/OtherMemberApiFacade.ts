import { useQuery, useMutation } from '@tanstack/vue-query';
import { useRoute } from 'vue-router';
import { queryClient } from '@/main';
import OtherMemberService from '@/services/OtherMemberService';

class OtherMemberApiFacade {
  /**
   * 사용자 정보 조회
   * - `memberId`는 URL Path Parameter에서 추출
   */
  static useFetchUserInfo() {
    const route = useRoute();
    const memberId = route.params.memberId as string;

    return useQuery({
      queryKey: ['user', memberId],
      queryFn: () => OtherMemberService.fetchUserInfo(memberId),
      retry: 0, // 요청 실패 시 재시도 하지 않음
      staleTime: 5 * 60 * 1000, // 5분 동안 데이터가 유효
    });
  }

  /**
   * 운동 기록 (Exercise Grass) 조회
   * - 운동 관련 그래프 데이터를 조회
   * - Path Parameter에서 `memberId` 추출
   */
  static useFetchExerciseGrass() {
    const route = useRoute();
    const memberId = route.params.memberId as string;

    return useQuery({
      queryKey: ['exerciseGrass', memberId],
      queryFn: () => OtherMemberService.fetchExerciseGrass(memberId),
      staleTime: 0, // 즉시 최신화
      gcTime: 60 * 1000, // 1분 후 데이터 제거
    });
  }

  /**
   * 팔로우 관계 조회
   * - Path Parameter로 전달된 `memberId`를 기반으로 팔로우 데이터를 조회
   */
  static useFetchFollowRelations() {
    const route = useRoute();
    const memberId = route.params.memberId as string;

    return useQuery({
      queryKey: ['follow', memberId],
      queryFn: () => OtherMemberService.fetchFollowRelations(memberId),
      retry: 0, // 재시도 없음
    });
  }
}

export default OtherMemberApiFacade;
