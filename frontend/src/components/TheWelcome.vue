<template>
  <div class="welcome-container">
    <PopularCarousel />
    <hr>
    <PopularVideos :popularVideos="popularVideos" />
    <hr>
    <LatestVideos :latestVideos="latestVideos" />
  </div>
</template>

<script setup lang="ts">
import { ref, computed } from 'vue';
import PopularVideos from "@/components/Video/PopularVideo.vue";
import LatestVideos from "@/components/Video/LatestVideo.vue";
import PopularCarousel from "@/components/Video/PopularCarousel.vue";

const videoData = ref([
{
      thumbnailImgUrl: "https://img.youtube.com/vi/4oGKJW-BdvY/maxresdefault.jpg",
      title: "내가 러닝 할 때 들으려고 만든 플리 | 40분 조깅 클럽 레츠고!",
      nickname: "비치러너 제이",
      viewCount: 580601,
      createdAt: "2023-11-25",
      videoId: '4oGKJW-BdvY',
      rating: 9,
    },
    {
      thumbnailImgUrl: "https://img.youtube.com/vi/wNpRXdAB-kw/maxresdefault.jpg",
      title: "이거 들으면서 유산소 운동 불태우면 여기가 바로 지중해",
      nickname: "솧디",
      viewCount: 112478,
      createdAt: "2024-08-12",
      videoId: 19,
      rating: 9,
    },
    {
      thumbnailImgUrl: "https://img.youtube.com/vi/HEh8sGqab1E/maxresdefault.jpg",
      title: "💪운동할 때 들으면 아드레날린도 지방도 터지는 팝송 모음",
      nickname: "두둠칫DOODOOMCHEAT",
      viewCount: 250400,
      createdAt: "2022-02-05",
      videoId: 16,
      rating: 7,
    },
    {
      thumbnailImgUrl: "https://img.youtube.com/vi/SGwxU6i5sJ4/maxresdefault.jpg",
      title: "🔥내가 뛰려고 만드는 러닝 음악🏃‍♀️",
      nickname: "음악듣는 하루",
      viewCount: 72500,
      createdAt: "2024-10-21",
      videoId: 24,
      rating: 9,
    },
    {
      thumbnailImgUrl: "https://img.youtube.com/vi/ks0LfhSGCXU/maxresdefault.jpg",
      title: "2022 년 최고의 러닝 뮤직러🏃🏽‍♀️러닝 할때 듣는 음악🏃🏼‍♂️산책시 기분전환 최고의 음악🏃🏼‍♂️",
      nickname: "Running Music Play List",
      viewCount: 120030,
      createdAt: "2023-01-24",
      videoId: 17,
      rating: 7.5,
    },
    {
      thumbnailImgUrl: "https://img.youtube.com/vi/zGuPbsX5UtU/maxresdefault.jpg",
      title: "심장박동RPM🔥, 1시간 유산소&러닝노래 쌉가능, 에피데믹사운드 노래모음",
      nickname: "다식맨 DASICKMAN",
      viewCount: 35000,
      createdAt: "2024-05-05",
      videoId: 18,
      rating: 7,
    },
    {
      thumbnailImgUrl: "https://img.youtube.com/vi/EHYwtYC8Clw/maxresdefault.jpg",
      title: "광고 없는 아침 조깅 음악 모음 Jogging 2 hours running music",
      nickname: "음악레시피",
      viewCount: 67010,
      createdAt: "2024-06-29",
      videoId: 25,
      rating: 8,
    },
    {
      thumbnailImgUrl: "https://img.youtube.com/vi/z77Po-CJpgg/maxresdefault.jpg",
      title: "케이팝 노동요 다 밟을 수수수수퍼노바💫 도파민 터지는 K-Pop Drop Only Mixset",
      nickname: "Dj 6ix9ine",
      viewCount: 480000,
      createdAt: "2024-03-11",
      videoId: 26,
      rating: 7.5,
    },
    {
    thumbnailImgUrl: "https://img.youtube.com/vi/5svlvTirzpg/maxresdefault.jpg",
    title: "노래 부심주의👊🏻유산소는 음악빨, 10km 달리기 핵가능 최애곡들",
    nickname: "인생이시트콤_고구마VLOG",
    viewCount: 4390000,
    createdAt: "2022-08-01",
    videoId: 21,
    rating: 9.5,
  },
  {
    thumbnailImgUrl: "https://img.youtube.com/vi/F6MI5hkLNEk/maxresdefault.jpg",
    title: "진짜 괜찮은 헬스음악 🎶🔥 운동 효과보장! (버릴곡 없음, 광고x)",
    nickname: "somifit",
    viewCount: 5850000,
    createdAt: "2019-11-12",
    videoId: 31,
    rating: 8,
  },
  {
    thumbnailImgUrl: "https://img.youtube.com/vi/HnWMaB5y-_Q/maxresdefault.jpg",
    title: "초인적인 힘이 필요할때 듣는 /헬스 음악",
    nickname: "운동음악[Workout Music]",
    viewCount: 371150,
    createdAt: "2023-04-15",
    videoId: 32,
    rating: 9,
    },
    {
    thumbnailImgUrl: "https://img.youtube.com/vi/6nNMSOuRGe8/maxresdefault.jpg",
    title: "한곡한곡 엄선한, 운동할 때 듣는 신나는 음악💪",
    nickname: "MuscleMania",
    viewCount: 159800,
    createdAt: "2024-07-05",
    videoId: 37,
    rating: 9,
  },
  {
    thumbnailImgUrl: "https://img.youtube.com/vi/05QwNcyPw8w/maxresdefault.jpg",
    title: "'뭐해? 들어' │ GYM 𝐏𝐥𝐚𝐲𝐥𝐢𝐬𝐭",
    nickname: "그누크",
    viewCount: 591022,
    createdAt: "2024-06-16",
    videoId: 33,
    rating: 8,
  },
  {
    thumbnailImgUrl: "https://img.youtube.com/vi/5jWOIKjTgHs/maxresdefault.jpg",
    title: "🎧운동할때 듣는음악 신나는노래🔥 진짜 괜찮은 헬스음악",
    nickname: "운동음악[Workout Music]",
    viewCount: 8199,
    createdAt: "2024-01-07",
    videoId: 34,
    rating: 6,
  },
{
  thumbnailImgUrl: "https://img.youtube.com/vi/Ee3lAu_E4zc/maxresdefault.jpg",
  title: "운동할때 듣는 음악 🔥 최고의 운동 음악 믹스 2024",
  nickname: "워크아웃",
  viewCount: 42000,
  createdAt: "2024-09-29",
  videoId: 40,
  rating: 6,
},
  {
    thumbnailImgUrl: "https://img.youtube.com/vi/aN-57bR21U4/maxresdefault.jpg",
    title: "오늘부터 이거 들으면서 10kg 뺀다🎵 하드 운동에 음악",
    nickname: "라이프유",
    viewCount: 52006,
    createdAt: "2022-12-23",
    videoId: 36,
    rating: 7,
  },

  {
    thumbnailImgUrl: "https://img.youtube.com/vi/I2mo7a9XHnM/maxresdefault.jpg",
    title: "듣자마자 맥그리거 빙의하는 운동 할 때 듣는 노래",
    nickname: "권도지",
    viewCount: 530010,
    createdAt: "2024-04-23",
    videoId: 38,
    rating: 8,
  },
]);

// computed properties
const popularVideos = computed(() => {
  return [...videoData.value]
    .sort((a, b) => b.viewCount - a.viewCount)
    .slice(0, 3);
});

const latestVideos = computed(() => {
  return [...videoData.value]
    .sort((a, b) => new Date(b.createdAt).getTime() - new Date(a.createdAt).getTime()) // 올바른 비교
    .slice(0, 3);
});
</script>

<style scoped>
.welcome-container {
  padding: 20px;
  display: flex;
  flex-direction: column;
  align-items: center;
}

</style>
