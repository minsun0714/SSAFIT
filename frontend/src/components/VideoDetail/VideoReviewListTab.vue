<script setup lang="ts">
import { ref, reactive, onMounted, watch } from 'vue'
import { FaStar, FaStarHalf } from 'vue3-icons/fa'
import MyReviewContent from './VideoReviewContent.vue'
import Input from '../ui/input/Input.vue'
import ReviewService from '@/api/services/ReviewService'
import type { PropType } from 'vue'
import type { VideoDetailVO, ReviewResponseVO } from '@/api/interfaces/response'
import type { ReviewRequestDTO } from '@/api/interfaces/request'
import { notification } from 'ant-design-vue'
import { useRouter } from 'vue-router'
import { Routes } from '@/utils/enum'
import MemberApiFacade from '@/api/apiFacade/MemberApiFacade'
import Button from '../ui/button/Button.vue'

const router = useRouter()

// props로 videoData를 수신
const props = defineProps({
  videoData: {
    type: Object as PropType<VideoDetailVO | null>,
    required: true,
  },
})

const { data: memberInfo } = MemberApiFacade.useFetchUserInfo()

// 상태 관리
const reviews = ref<ReviewResponseVO[]>([])
const isLoading = ref(false)
const hasError = ref(false)

// 리뷰 등록 상태
const newReviewContent = ref('')
const newReviewRating = ref<number>(2.5) // 기본값 설정

// 수정 중인 리뷰 상태
const editingReview = reactive({
  reviewId: '',
  content: '',
  rating: 0,
  isEditing: false,
})

// 리뷰 로드 함수
const fetchReviews = async () => {
  if (!props.videoData || !props.videoData.videoId) return

  isLoading.value = true
  hasError.value = false

  try {
    const fetchedReviews = await ReviewService.getReviewsByVideoId(props.videoData.videoId)
    console.log('Fetched Reviews:', fetchedReviews) // 리뷰 데이터 출력
    reviews.value = fetchedReviews
  } catch (error) {
    console.error('Failed to fetch reviews:', error)
    hasError.value = true
  } finally {
    isLoading.value = false
  }
}

// 리뷰 등록 함수
const submitReview = async () => {
  if (!props.videoData || !props.videoData.videoId) return

  const payload: ReviewRequestDTO = {
    videoId: props.videoData.videoId,
    content: newReviewContent.value,
    rating: newReviewRating.value * 2,
  }

  try {
    const newReview = await ReviewService.createReview(payload)
    reviews.value.unshift(newReview) // 새 리뷰를 리스트 상단에 추가
    newReviewContent.value = '' // 입력 필드 초기화
    newReviewRating.value = 2.5 // 평점 초기화
    notification.success({
      message: 'Review Submitted',
      description: 'Your review has been successfully submitted!',
    })
  } catch (error) {
    console.error('Failed to submit review:', error)
    notification.error({
      message: 'Failed to Submit',
      description: 'Could not submit your review. Please try again later.',
    })
  }
}

// 리뷰 수정 시작
const startEditingReview = (review: ReviewResponseVO) => {
  editingReview.reviewId = review.reviewId
  editingReview.content = review.content
  editingReview.rating = review.rating / 2
  editingReview.isEditing = true
}

// 리뷰 수정 완료
const submitEditedReview = async () => {
  if (!editingReview.reviewId) return

  try {
    const payload: ReviewRequestDTO = {
      videoId: props.videoData?.videoId || '',
      content: editingReview.content,
      rating: editingReview.rating * 2,
    }
    const updatedReview = await ReviewService.updateReview(editingReview.reviewId, payload)

    reviews.value = reviews.value.map((review) =>
      review.reviewId === editingReview.reviewId ? updatedReview : review,
    )

    notification.success({
      message: 'Review Updated',
      description: 'Your review has been successfully updated!',
    })
    cancelEditing() // 수정 상태 초기화
  } catch (error) {
    console.error('Failed to update review:', error)
    notification.error({
      message: 'Failed to Update',
      description: 'Could not update your review. Please try again later.',
    })
  }
}

// 리뷰 수정 취소
const cancelEditing = () => {
  editingReview.reviewId = ''
  editingReview.content = ''
  editingReview.rating = 0
  editingReview.isEditing = false
}

// 리뷰 삭제
const deleteReview = async (reviewId: string) => {
  try {
    await ReviewService.deleteReview(reviewId)
    reviews.value = reviews.value.filter((review) => review.reviewId !== reviewId)
    notification.success({
      message: 'Review Deleted',
      description: 'Your review has been successfully deleted!',
    })
  } catch (error) {
    console.error('Failed to delete review:', error)
    notification.error({
      message: 'Failed to Delete',
      description: 'Could not delete your review. Please try again later.',
    })
  }
}

// videoData 변경 시 리뷰를 로드
watch(
  () => props.videoData,
  (newVideoData) => {
    if (newVideoData?.videoId) fetchReviews()
  },
  { immediate: true },
)

// 페이지 진입 시에도 리뷰를 로드
onMounted(() => {
  fetchReviews() // 페이지 진입 시에도 리뷰를 로드
})
</script>

<template>
  <!-- 리뷰 등록 -->
  <div class="w-full flex flex-col items-center mb-8">
    <Input v-model="newReviewContent" placeholder="리뷰를 입력해주세요" class="w-2/3 mb-4" />
    <a-rate v-model:value="newReviewRating" allow-half class="mb-4" />
    <button
      @click="submitReview"
      class="bg-blue-500 text-white px-4 py-2 rounded hover:bg-blue-600"
    >
      리뷰 등록
    </button>
  </div>

  <!-- 리뷰 리스트 -->
  <div>
    <!-- 로딩 상태 -->
    <p v-if="isLoading" class="text-gray-500 text-sm">Loading reviews...</p>

    <!-- 오류 상태 -->
    <p v-if="hasError" class="text-sm">Failed to load reviews. Please try again.</p>

    <ul
      v-if="reviews.length && !isLoading"
      class="flex flex-col justify-center items-center w-full"
    >
      <li
        v-for="review in reviews"
        :key="review.reviewId"
        class="w-2/3 h-full border-t border-b flex flex-col justify-center items-center text-sm text-gray-600"
      >
        <!-- 수정 중인 리뷰 -->
        <div
          v-if="editingReview.isEditing && editingReview.reviewId === review.reviewId"
          class="w-full p-4"
        >
          <textarea
            v-model="editingReview.content"
            placeholder="리뷰 내용을 수정하세요"
            class="w-full p-2 rounded mb-2 border"
          ></textarea>
          <a-rate v-model:value="editingReview.rating" allow-half class="mb-2" />
          <div class="flex gap-2 justify-end">
            <Button
              @click="submitEditedReview"
              class="bg-blue-500 text-white px-4 py-2 rounded hover:bg-green-600"
            >
              저장
            </Button>
            <Button
              @click="cancelEditing"
              class="bg-gray-500 text-white px-4 py-2 rounded hover:bg-gray-600"
            >
              취소
            </Button>
          </div>
        </div>

        <!-- 일반 리뷰 -->
        <div v-else class="w-full">
          <div class="flex flex-col justify-center items-center text-sm w-full">
            <div class="flex flex-row justify-between items-center w-full border-b p-4">
              <span
                class="flex flex-row items-center gap-2 cursor-pointer"
                @click="
                  router.push({
                    name: 'others-liked-videos',
                    params: { memberId: review.memberId },
                  })
                "
              >
                <img
                  :src="review.profileImg"
                  alt="프로필 이미지"
                  class="border rounded-full h-8 w-8"
                />
                <h1>{{ review.memberId }}</h1>
                ·
                <p>{{ new Date(review.createdAt).toLocaleDateString() }}</p>
              </span>
              <div class="flex">
                <div class="grid grid-cols-5 gap-1">
                  <span
                    class="flex flex-row"
                    v-for="(_, idx) in Math.floor(review.rating / 2)"
                    :key="idx"
                  >
                    <FaStar color="#ECBA0B" />
                  </span>
                  <span
                    class="flex flex-row"
                    v-for="(_, idx) in Math.floor(review.rating % 2)"
                    :key="idx"
                  >
                    <FaStarHalf color="#ECBA0B" />
                  </span>
                </div>
                <div class="flex gap-2" v-if="memberInfo.memberId === review.memberId">
                  <button @click="startEditingReview(review)" class="text-blue-500 text-sm">
                    수정
                  </button>
                  <button @click="deleteReview(review.reviewId)" class="text-red-500 text-sm">
                    삭제
                  </button>
                </div>
              </div>
            </div>
          </div>
          <MyReviewContent :content="review.content" />
        </div>
      </li>
    </ul>

    <!-- 리뷰가 없는 경우 -->
    <p v-else-if="!isLoading" class="text-gray-500 text-sm">No reviews available for this video.</p>
  </div>
</template>
