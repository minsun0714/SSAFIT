import { useQuery, useMutation, useQueryClient } from '@tanstack/vue-query'
import ReviewService from '@/api/services/ReviewService'
import { useRoute } from 'vue-router'
import { computed } from 'vue'

class ReviewApiFacade {
  // 특정 영상에 대한 리뷰 조회
  static useFetchReviewsByVideoId(videoId: string) {
    return useQuery({
      queryKey: ['reviews', videoId],
      queryFn: async () => {
        if (!videoId) throw new Error('videoId is missing')
        const result = await ReviewService.getReviewsByVideoId(videoId)
        return result
      },
      enabled: !!videoId,
      staleTime: 1000 * 60 * 5,
    })
  }

  // 나의 리뷰 조회
  static useFetchReviews() {
    return useQuery({
      queryKey: ['reviews'],
      queryFn: async () => {
        const result = await ReviewService.getReviews()
        return result
      },
      staleTime: 1000 * 60 * 5,
    })
  }

  // 다른 사람 리뷰 조회
  static useFetchReviewsByMemberId() {
    const route = useRoute()
    const memberId = computed(() => route.params.memberId)
    return useQuery({
      queryKey: ['reviews', memberId],
      queryFn: async () => {
        const result = await ReviewService.getReviewsByMemberId(memberId.value as string)
        return result
      },
    })
  }

  // 리뷰 생성
  static useCreateReview() {
    const queryClient = useQueryClient()
    return useMutation({
      mutationFn: async (reviewRequestDTO: ReviewRequestDTO) => {
        const result = await ReviewService.createReview(reviewRequestDTO)
        return result
      },
      onSuccess: async () => {
        await queryClient.invalidateQueries({
          queryKey: ['reviews'],
          refetchType: 'active',
        })
      },
    })
  }

  // 리뷰 수정
  static useUpdateReview() {
    const queryClient = useQueryClient()
    return useMutation({
      mutationFn: async ({
        reviewId,
        reviewRequestDTO,
      }: {
        reviewId: string
        reviewRequestDTO: ReviewRequestDTO
      }) => {
        const result = await ReviewService.updateReview(reviewId, reviewRequestDTO)
        return result
      },
      onSuccess: async () => {
        await queryClient.invalidateQueries({
          queryKey: ['reviews'],
          refetchType: 'active',
        })
      },
    })
  }

  // 리뷰 삭제
  static useDeleteReview() {
    const queryClient = useQueryClient()
    return useMutation({
      mutationFn: async (reviewId: string) => {
        await ReviewService.deleteReview(reviewId)
      },
      onSuccess: async () => {
        await queryClient.invalidateQueries({
          queryKey: ['reviews'],
          refetchType: 'active',
        })
      },
    })
  }
}

export default ReviewApiFacade
