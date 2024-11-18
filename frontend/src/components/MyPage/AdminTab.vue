<script setup lang="ts">
import { profileImg } from '@/assets'
import { FaStar, FaStarHalf } from 'vue3-icons/fa'
import MyReviewContent from './MyReviewContent.vue'
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
  {
    id: 2,
    user: {
      username: '이민선',
      userId: 'minsun',
      profileImg,
    },
    createdAt: new Date(),
    content:
      's through in her work. We will definitely book her again and highly recommend her services!',
    evaluation: 8,
  },
]
</script>

<template>
  <div>Admin</div>
</template>
