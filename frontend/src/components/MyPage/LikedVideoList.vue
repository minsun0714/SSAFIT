<script setup lang="ts">
import VideoCard from '@/components/Video/VideoCard.vue'

const allVideos = [
  {
    thumbnailImgUrl: 'https://img.youtube.com/vi/50WCSpZtdmA/maxresdefault.jpg',
    title: 'Shim EuDdeum 10 Minute Morning Stretch Everydayㅣ2023 Renewal',
    nickname: '힘으뜸',
    viewCount: 1200000,
    createdAt: '2024-11-17',
  },
  {
    thumbnailImgUrl: 'https://img.youtube.com/vi/02K-k6daPb4/maxresdefault.jpg',
    title: '15 MIN Fat Burning Cardio - 서서하는 유산소 - 다이어트 운동',
    nickname: '빅씨스 Bigsis',
    viewCount: 900000,
    createdAt: '2024-11-16',
  },
  {
    thumbnailImgUrl: 'https://img.youtube.com/vi/54tTYO-vU2E/maxresdefault.jpg',
    title: '상체 다이어트 최고의 운동 BEST [팔뚝살/겨드랑이살/등살/가슴어깨라인]',
    nickname: 'ThankyouBUBU',
    viewCount: 800000,
    createdAt: '2024-11-15',
  },
  {
    thumbnailImgUrl: 'https://img.youtube.com/vi/QqqZH3j_vH0/maxresdefault.jpg',
    title: '상체비만 다이어트 최고의 운동 [상체 핵매운맛]',
    nickname: 'ThankyouBUBU',
    viewCount: 950000,
    createdAt: '2024-11-14',
  },
  {
    thumbnailImgUrl: 'https://img.youtube.com/vi/tzN6ypk6Sps/maxresdefault.jpg',
    title: '하체운동이 중요한 이유? 이것만 보고 따라하자 ! [하체운동 교과서]',
    nickname: '김강민',
    viewCount: 1100000,
    createdAt: '2024-11-13',
  },
  // 동일 데이터를 반복하여 총 18개의 항목 구성
  {
    thumbnailImgUrl: 'https://img.youtube.com/vi/02K-k6daPb4/maxresdefault.jpg',
    title: '15 MIN Fat Burning Cardio - 서서하는 유산소 - 다이어트 운동',
    nickname: '빅씨스 Bigsis',
    viewCount: 900000,
    createdAt: '2024-11-16',
  },
  {
    thumbnailImgUrl: 'https://img.youtube.com/vi/54tTYO-vU2E/maxresdefault.jpg',
    title: '상체 다이어트 최고의 운동 BEST [팔뚝살/겨드랑이살/등살/가슴어깨라인]',
    nickname: 'ThankyouBUBU',
    viewCount: 800000,
    createdAt: '2024-11-15',
  },
  {
    thumbnailImgUrl: 'https://img.youtube.com/vi/QqqZH3j_vH0/maxresdefault.jpg',
    title: '상체비만 다이어트 최고의 운동 [상체 핵매운맛]',
    nickname: 'ThankyouBUBU',
    viewCount: 950000,
    createdAt: '2024-11-14',
  },
  {
    thumbnailImgUrl: 'https://img.youtube.com/vi/tzN6ypk6Sps/maxresdefault.jpg',
    title: '하체운동이 중요한 이유? 이것만 보고 따라하자 ! [하체운동 교과서]',
    nickname: '김강민',
    viewCount: 1100000,
    createdAt: '2024-11-13',
  },
  {
    thumbnailImgUrl: 'https://img.youtube.com/vi/50WCSpZtdmA/maxresdefault.jpg',
    title: 'Shim EuDdeum 10 Minute Morning Stretch Everydayㅣ2023 Renewal',
    nickname: '힘으뜸',
    viewCount: 1200000,
    createdAt: '2024-11-17',
  },
  {
    thumbnailImgUrl: 'https://img.youtube.com/vi/02K-k6daPb4/maxresdefault.jpg',
    title: '15 MIN Fat Burning Cardio - 서서하는 유산소 - 다이어트 운동',
    nickname: '빅씨스 Bigsis',
    viewCount: 900000,
    createdAt: '2024-11-16',
  },
  {
    thumbnailImgUrl: 'https://img.youtube.com/vi/54tTYO-vU2E/maxresdefault.jpg',
    title: '상체 다이어트 최고의 운동 BEST [팔뚝살/겨드랑이살/등살/가슴어깨라인]',
    nickname: 'ThankyouBUBU',
    viewCount: 800000,
    createdAt: '2024-11-15',
  },
  {
    thumbnailImgUrl: 'https://img.youtube.com/vi/QqqZH3j_vH0/maxresdefault.jpg',
    title: '상체비만 다이어트 최고의 운동 [상체 핵매운맛]',
    nickname: 'ThankyouBUBU',
    viewCount: 950000,
    createdAt: '2024-11-14',
  },
  {
    thumbnailImgUrl: 'https://img.youtube.com/vi/tzN6ypk6Sps/maxresdefault.jpg',
    title: '하체운동이 중요한 이유? 이것만 보고 따라하자 ! [하체운동 교과서]',
    nickname: '김강민',
    viewCount: 1100000,
    createdAt: '2024-11-13',
  },
  // 동일 데이터를 반복하여 총 18개의 항목 구성
  {
    thumbnailImgUrl: 'https://img.youtube.com/vi/02K-k6daPb4/maxresdefault.jpg',
    title: '15 MIN Fat Burning Cardio - 서서하는 유산소 - 다이어트 운동',
    nickname: '빅씨스 Bigsis',
    viewCount: 900000,
    createdAt: '2024-11-16',
  },
  {
    thumbnailImgUrl: 'https://img.youtube.com/vi/54tTYO-vU2E/maxresdefault.jpg',
    title: '상체 다이어트 최고의 운동 BEST [팔뚝살/겨드랑이살/등살/가슴어깨라인]',
    nickname: 'ThankyouBUBU',
    viewCount: 800000,
    createdAt: '2024-11-15',
  },
  {
    thumbnailImgUrl: 'https://img.youtube.com/vi/QqqZH3j_vH0/maxresdefault.jpg',
    title: '상체비만 다이어트 최고의 운동 [상체 핵매운맛]',
    nickname: 'ThankyouBUBU',
    viewCount: 950000,
    createdAt: '2024-11-14',
  },
  {
    thumbnailImgUrl: 'https://img.youtube.com/vi/tzN6ypk6Sps/maxresdefault.jpg',
    title: '하체운동이 중요한 이유? 이것만 보고 따라하자 ! [하체운동 교과서]',
    nickname: '김강민',
    viewCount: 1100000,
    createdAt: '2024-11-13',
  },
  {
    thumbnailImgUrl: 'https://img.youtube.com/vi/50WCSpZtdmA/maxresdefault.jpg',
    title: 'Shim EuDdeum 10 Minute Morning Stretch Everydayㅣ2023 Renewal',
    nickname: '힘으뜸',
    viewCount: 1200000,
    createdAt: '2024-11-17',
  },
  {
    thumbnailImgUrl: 'https://img.youtube.com/vi/02K-k6daPb4/maxresdefault.jpg',
    title: '15 MIN Fat Burning Cardio - 서서하는 유산소 - 다이어트 운동',
    nickname: '빅씨스 Bigsis',
    viewCount: 900000,
    createdAt: '2024-11-16',
  },
  {
    thumbnailImgUrl: 'https://img.youtube.com/vi/54tTYO-vU2E/maxresdefault.jpg',
    title: '상체 다이어트 최고의 운동 BEST [팔뚝살/겨드랑이살/등살/가슴어깨라인]',
    nickname: 'ThankyouBUBU',
    viewCount: 800000,
    createdAt: '2024-11-15',
  },
  {
    thumbnailImgUrl: 'https://img.youtube.com/vi/QqqZH3j_vH0/maxresdefault.jpg',
    title: '상체비만 다이어트 최고의 운동 [상체 핵매운맛]',
    nickname: 'ThankyouBUBU',
    viewCount: 950000,
    createdAt: '2024-11-14',
  },
  {
    thumbnailImgUrl: 'https://img.youtube.com/vi/tzN6ypk6Sps/maxresdefault.jpg',
    title: '하체운동이 중요한 이유? 이것만 보고 따라하자 ! [하체운동 교과서]',
    nickname: '김강민',
    viewCount: 1100000,
    createdAt: '2024-11-13',
  },
  // 동일 데이터를 반복하여 총 18개의 항목 구성
  {
    thumbnailImgUrl: 'https://img.youtube.com/vi/02K-k6daPb4/maxresdefault.jpg',
    title: '15 MIN Fat Burning Cardio - 서서하는 유산소 - 다이어트 운동',
    nickname: '빅씨스 Bigsis',
    viewCount: 900000,
    createdAt: '2024-11-16',
  },
  {
    thumbnailImgUrl: 'https://img.youtube.com/vi/54tTYO-vU2E/maxresdefault.jpg',
    title: '상체 다이어트 최고의 운동 BEST [팔뚝살/겨드랑이살/등살/가슴어깨라인]',
    nickname: 'ThankyouBUBU',
    viewCount: 800000,
    createdAt: '2024-11-15',
  },
  {
    thumbnailImgUrl: 'https://img.youtube.com/vi/QqqZH3j_vH0/maxresdefault.jpg',
    title: '상체비만 다이어트 최고의 운동 [상체 핵매운맛]',
    nickname: 'ThankyouBUBU',
    viewCount: 950000,
    createdAt: '2024-11-14',
  },
  {
    thumbnailImgUrl: 'https://img.youtube.com/vi/tzN6ypk6Sps/maxresdefault.jpg',
    title: '하체운동이 중요한 이유? 이것만 보고 따라하자 ! [하체운동 교과서]',
    nickname: '김강민',
    viewCount: 1100000,
    createdAt: '2024-11-13',
  },
]
</script>

<template>
  <div class="flex justify-center">
    <ul>
      <li class="max-w-[1200px] flex flex-wrap justify-center items-center gap-6 p-6">
        <VideoCard
          v-for="(video, index) in allVideos"
          :key="index"
          :title="video.title"
          :nickname="video.nickname"
          :viewCount="video.viewCount"
          :createdAt="video.createdAt"
          :thumbnailImgUrl="video.thumbnailImgUrl"
        />
      </li>
    </ul>
  </div>
</template>
