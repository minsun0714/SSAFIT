<script setup lang="ts">
import VideoCard from "../common/VideoCard.vue";
const allVideos = [
  {
    videoId: 1,
    thumbnailImgUrl: 'https://img.youtube.com/vi/50WCSpZtdmA/maxresdefault.jpg',
    title: 'Shim EuDdeum 10 Minute Morning Stretch Everydayㅣ2023 Renewal',
    nickname: '힘으뜸',
    viewCount: 1200000,
    createdAt: '2024-11-17',
    rating: Math.floor(Math.random() * 5) + 1
  },
  {
    videoId: 2,
    thumbnailImgUrl: 'https://img.youtube.com/vi/02K-k6daPb4/maxresdefault.jpg',
    title: '15 MIN Fat Burning Cardio - 서서하는 유산소 - 다이어트 운동',
    nickname: '빅씨스 Bigsis',
    viewCount: 900000,
    createdAt: '2024-11-16',
    rating: Math.floor(Math.random() * 5) + 1
  },
  {
    videoId: 3,
    thumbnailImgUrl: 'https://img.youtube.com/vi/54tTYO-vU2E/maxresdefault.jpg',
    title: '상체 다이어트 최고의 운동 BEST [팔뚝살/겨드랑이살/등살/가슴어깨라인]',
    nickname: 'ThankyouBUBU',
    viewCount: 800000,
    createdAt: '2024-11-15',
    rating: Math.floor(Math.random() * 5) + 1
  },
  {
    videoId: 4,
    thumbnailImgUrl: 'https://img.youtube.com/vi/QqqZH3j_vH0/maxresdefault.jpg',
    title: '상체비만 다이어트 최고의 운동 [상체 핵매운맛]',
    nickname: 'ThankyouBUBU',
    viewCount: 950000,
    createdAt: '2024-11-14',
    rating: Math.floor(Math.random() * 5) + 1
  },
  {
    videoId: 7,
    thumbnailImgUrl: 'https://img.youtube.com/vi/54tTYO-vU2E/maxresdefault.jpg',
    title: '상체 다이어트 최고의 운동 BEST [팔뚝살/겨드랑이살/등살/가슴어깨라인]',
    nickname: 'ThankyouBUBU',
    viewCount: 800000,
    createdAt: '2024-11-15',
    rating: Math.floor(Math.random() * 5) + 1
  }
]
</script>

<template>
  <div class="flex justify-center">
    <ul>
      <li class="max-w-[1200px] flex flex-wrap justify-center items-center gap-6 p-6">
        <VideoCard
          v-for="(video, index) in allVideos"
          :key="index"
          :videoId="video.videoId"
          :title="video.title"
          :nickname="video.nickname"
          :viewCount="video.viewCount"
          :createdAt="video.createdAt"
          :thumbnailImgUrl="video.thumbnailImgUrl"
          :rating="video.rating"
        />
      </li>
    </ul>
  </div>
</template>
