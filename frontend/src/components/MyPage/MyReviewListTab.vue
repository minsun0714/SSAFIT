<script setup lang="ts">
import { inject, Ref } from 'vue'
import { FaStar, FaStarHalf } from 'vue3-icons/fa'
import MyReviewContent from './MyReviewContent.vue'
import { ReviewResponseVO } from '@/api/interfaces/response'

const reviews = inject<Ref<ReviewResponseVO[] | undefined>>('reviewsInfo')
</script>

<template>
  <div>
    <ul class="flex flex-col justify-center items-center w-full">
      <li
        v-for="review in reviews"
        :key="review.reviewId"
        class="w-2/3 h-full border-t border-b flex flex-col justify-center items-center text-sm text-gray-600"
      >
        <div class="flex flex-col items-center text-sm w-full pt-6 px-6">
          <div class="flex flex-row justify-between items-center w-full">
            <span class="flex flex-row items-center gap-2">
              <!-- 사용자 프로필 이미지 부분은 주석 처리 -->
              <h1>{{ review.memberId }}</h1>
              ·
              <p>{{ review.createdAt }}</p>
            </span>
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
          </div>
        </div>
        <MyReviewContent :content="review.content" />
        <!-- 리뷰 내용 컴포넌트 -->
      </li>
    </ul>
  </div>
</template>
