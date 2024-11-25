import { useQuery, useMutation } from '@tanstack/vue-query'
import LikeVideosService from '@/api/services/LikeVideosService'

class LikeVideoApiFacade {
  // 나의 좋아요 리스트 조회
  static useFetchLikes() {
    return useQuery({
      queryKey: ['likes'],
      queryFn: async () => {
        const result = await LikeVideosService.getLikes()
        return result
      },
      staleTime: 1000 * 60 * 5, // 5분 동안 캐시 유지
    })
  }

  // 특정 멤버의 좋아요 리스트 조회
  static useFetchLikesByMember(memberId: string) {
    return useQuery({
      queryKey: ['likes', memberId],
      queryFn: async () => {
        const result = await LikeVideosService.getLikesByMember(memberId)
        return result
      },
      enabled: !!memberId, // memberId가 존재할 때만 실행
    })
  }

  // 특정 영상의 좋아요 조회
  static useFetchLikesByVideo(videoId: string) {
    return useQuery({
      queryKey: ['likes', videoId],
      queryFn: async () => {
        const result = await LikeVideosService.getLikesByVideo(videoId)
        return result
      },
      enabled: !!videoId, // videoId가 존재할 때만 실행
    })
  }

  // 좋아요 등록
  static useAddLike() {
    return useMutation({
      mutationFn: async (requestDTO: LikeRequestDTO) => {
        const result = await LikeVideosService.addLike(requestDTO)
        return result
      },
    })
  }

  // 좋아요 삭제
  static useRemoveLike() {
    return useMutation({
      mutationFn: async (likeId: string) => {
        await LikeVideosService.removeLike(likeId)
      },
      onSuccess: () => {},
    })
  }
}

export default LikeVideoApiFacade
