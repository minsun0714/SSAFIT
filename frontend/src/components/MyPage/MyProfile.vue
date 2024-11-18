<script setup lang="ts">
import profileImg from '@/assets/NavigationBar/profileImg.svg'
const data = {
  name: '이민선',
  userId: 'minsun',
  profileImg,
  followerCount: 435,
  followingCount: 416,
  introduceText:
    '운동을 좋아하는 강아지입니다 다들 너무 반갑고 제가 어쩌구저쩌구 운동 맣이좋아합니다. 멍멍멍러러러멍멍멍 멍멍멍러러러멍멍멍 멍멍멍러러러멍멍멍멍멍멍러러러멍멍멍멍멍멍러러러멍멍멍 멍멍멍러러러멍멍멍멍멍멍러러러멍멍',
}
</script>

<template>
  <div class="flex flex-row w-full border justify-center">
    <div class="p-12">
      <img :src="profileImg" alt="프로필 사진" class="border min-w-28 min-h-28 rounded-full" />
    </div>
    <div class="flex flex-col">
      <div class="h-full flex flex-row justify-center items-center">
        <h1 class="w-full">
          <p class="font-bold text-xl">{{ data.name }}</p>
          <p class="text-sm text-gray-500"># {{ data.userId }}</p>
        </h1>
        <h2 class="w-40 text-center">
          팔로워
          <p class="font-bold">{{ data.followerCount }}</p>
        </h2>
        <h2 class="w-40 text-center">
          팔로잉
          <p class="font-bold">{{ data.followingCount }}</p>
        </h2>
      </div>
      <p class="flex justify-center items-center h-full w-96 text-xs text-gray-400">
        {{ data.introduceText }}
      </p>
    </div>
  </div>
</template>
