<script setup lang="ts">
import { FaStar, FaStarHalf } from 'vue3-icons/fa'
import MyReviewContent from './MyReviewContent.vue'
import { Ref, inject } from 'vue'
const data = inject<Ref<ReviewResponse[] | undefined>>('reviewsInfo')
</script>

<template>
  <div>
    <ul class="flex flex-col justify-center items-center w-full">
      <li
        v-for="review in data"
        :key="review.id"
        class="w-2/3 h-full border-t border-b flex flex-col justify-center items-center text-sm text-gray-600"
      >
        <div class="flex flex-col items-center text-sm w-full pt-6 px-6">
          <div class="flex flex-row justify-between items-center w-full">
            <span class="flex flex-row items-center gap-2">
              <img
                :src="review.user.profileImg"
                alt="댓글 작성자의 프로필 사진"
                class="border rounded-full h-8 w-8"
              />
              <h1>{{ review.user.username }}</h1>
              ·
              <p>{{ review.createdAt }}</p></span
            >
            <div class="grid grid-cols-5 gap-1">
              <span
                class="flex flex-row"
                v-for="(_, idx) in Math.floor(review.evaluation / 2)"
                :key="idx"
              >
                <FaStar color="#ECBA0B" />
              </span>
              <span
                class="flex flex-row"
                v-for="(_, idx) in Math.floor(review.evaluation % 2)"
                :key="idx"
              >
                <FaStarHalf color="#ECBA0B" />
              </span>
            </div>
          </div>
        </div>
        <MyReviewContent :content="review.content" />
      </li>
    </ul>
  </div>
</template>
