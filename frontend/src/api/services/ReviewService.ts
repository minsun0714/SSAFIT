import api from '../apiClient'
import type { ReviewRequestDTO } from '../interfaces/request'
import type { ReviewResponseVO } from '../interfaces/response'

class ReviewService {
  private static path = '/api/reviews'

  // 리뷰 생성
  static async createReview(reviewRequestDTO: ReviewRequestDTO): Promise<ReviewResponseVO> {
    const response = await api.post(this.path, reviewRequestDTO)
    return response.data
  }

  // 특정 영상에 대한 리뷰 조회
  static async getReviewsByVideoId(videoId: string): Promise<ReviewResponseVO[]> {
    const response = await api.get(`${this.path}/video/${videoId}`)
    return response.data
  }

  // 리뷰 수정
  static async updateReview(reviewId: string, reviewRequestDTO: ReviewRequestDTO): Promise<ReviewResponseVO> {
    const response = await api.put(`${this.path}/${reviewId}`, reviewRequestDTO)
    return response.data
  }

  // 리뷰 삭제
  static async deleteReview(reviewId: string): Promise<void> {
    await api.delete(`${this.path}/${reviewId}`)
  }
}

export default ReviewService
