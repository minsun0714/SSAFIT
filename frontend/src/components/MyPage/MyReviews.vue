<script setup lang="ts">
import { profileImg } from '@/assets'
import { FaStar, FaStarHalf } from 'vue3-icons/fa'
const data = [
  {
    id: 0,
    user: {
      username: '이민선',
      userId: 'minsun',
      profileImg,
    },
    createdAt: new Date(),
    content:
      "KaiB was phenomenal with our dog, Max!  We were first-time users of a pet-sitting service and were quite nervous. Kai's professionalism and warmth immediately put us at ease. She visited twice a day for a week and provided us with detailed updates and adorable pictures. Max was happy, healthy, and well-exercised throughout our trip. Kai's genuine love for animals shines through in her work. We will definitely book her again and highly recommend her services!",
    evaluation: 10,
  },
  {
    id: 1,
    user: {
      username: '이민선',
      userId: 'minsun',
      profileImg,
    },
    createdAt: new Date(),
    content:
      "KaiB was phenomenal with our dog, Max!  We were first-time users of a pet-sitting service and were quite nervous. Kai's professionalism and warmth immediately put us at ease. She visited twice a day for a week and provided us with detailed updates and adorable pictures. Max was happy, healthy, and well-exercised throughout our trip. Kai's genuine love for animals shines through in her work. We will definitely book her again and highly recommend her services!",
    evaluation: 9,
  },
  {
    id: 2,
    user: {
      username: '이민선',
      userId: 'minsun',
      profileImg,
    },
    createdAt: new Date(),
    content:
      "KaiB was phenomenal with our dog, Max!  We were first-time users of a pet-sitting service and were quite nervous. Kai's professionalism and warmth immediately put us at ease. She visited twice a day for a week and provided us with detailed updates and adorable pictures. Max was happy, healthy, and well-exercised throughout our trip. Kai's genuine love for animals shines through in her work. We will definitely book her again and highly recommend her services!",
    evaluation: 8,
  },
]
</script>

<template>
  <div class="flex justify-center">
    <ul>
      <li
        v-for="review in data"
        :key="review.id"
        class="w-[1000px] border-t border-b flex flex-col justify-between items-center text-sm text-gray-600"
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
        <div class="p-6">
          <p class="w-4/5">{{ review.content }}</p>
        </div>
      </li>
    </ul>
  </div>
</template>
