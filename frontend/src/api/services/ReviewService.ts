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

  static async getReviews(): Promise<ReviewResponseVO> {
    const response = await api.get(this.path + '/member')
    return response.data
  }

  static async getReviewsByMemberId(memberId: string): Promise<ReviewResponseVO> {
    const response = await api.get(this.path + `/member/${memberId}`)
    console.log('남의 리뷰' + response.data)
    return response.data
  }

  static async getReviewsByMyId(): Promise<ReviewResponseVO> {
    const response = await api.get(this.path + '/video')
    return response.data
  }
 

  static async getReviewsByVideoId(videoId: string): Promise<ReviewResponseVO[]> {
    const response = await api.get(`${this.path}/list/${videoId}`)
    return response.data
  }

  // 리뷰 수정
  static async updateReview(
    reviewId: string,
    reviewRequestDTO: ReviewRequestDTO,
  ): Promise<ReviewResponseVO> {
    const response = await api.put(`${this.path}/${reviewId}`, reviewRequestDTO)
    return response.data
  }

  // 리뷰 삭제
  static async deleteReview(reviewId: string): Promise<void> {
    await api.delete(`${this.path}/${reviewId}`)
  }
}

export default ReviewService
