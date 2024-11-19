<script setup lang="ts">
import { Carousel, CarouselContent, CarouselItem, CarouselNext, CarouselPrevious } from '@/components/ui/carousel'
import Autoplay from 'embla-carousel-autoplay'

// 비디오 데이터 배열
const videos = [
  {
    thumbnailImgUrl: "https://img.youtube.com/vi/50WCSpZtdmA/maxresdefault.jpg",
    title: "Shim EuDdeum 10 Minute Morning Stretch Everydayㅣ2023 Renewal",
    videoId: 1
  },
  {
    thumbnailImgUrl: "https://img.youtube.com/vi/02K-k6daPb4/maxresdefault.jpg",
    title: "15 MIN Fat Burning Cardio - 서서하는 유산소 - 다이어트 운동",
    videoId: 2
  },
  {
    thumbnailImgUrl: "https://img.youtube.com/vi/54tTYO-vU2E/maxresdefault.jpg",
    title: "상체 다이어트 최고의 운동 BEST [팔뚝살/겨드랑이살/등살/가슴어깨라인]",
    videoId: 3
  },
  {
    thumbnailImgUrl: "https://img.youtube.com/vi/QqqZH3j_vH0/maxresdefault.jpg",
    title: "상체비만 다이어트 최고의 운동 [상체 핵매운맛]",
    videoId: 4
  },
  {
    thumbnailImgUrl: "https://img.youtube.com/vi/tzN6ypk6Sps/maxresdefault.jpg",
    title: "하체운동이 중요한 이유? 이것만 보고 따라하자 ! [하체운동 교과서]",
    videoId: 5
  }
]

// Autoplay 설정
const plugin = Autoplay({
  delay: 2000,
  stopOnMouseEnter: true,
  stopOnInteraction: false,
})
</script>

<template>
  <div class="flex justify-center">
    <Carousel
      class="relative w-full max-w-lg"
      :plugins="[plugin]"
      @mouseenter="plugin.stop"
      @mouseleave="[plugin.reset(), plugin.play(), console.log('Running')]"
      >
      <CarouselContent>
        <CarouselItem v-for="video in videos" :key="video.videoId">
          <div class="flex flex-col items-center">
            <!-- 이미지 크기 조정 및 제목 하단 위치 -->
            <img :src="video.thumbnailImgUrl" alt="Video Thumbnail" class="w-full h-60 object-cover rounded-md" />
            <h3 class="text-xl font-semibold mt-2 text-center">{{ video.title }}</h3>
          </div>
        </CarouselItem>
      </CarouselContent>
      <CarouselPrevious />
      <CarouselNext />
    </Carousel>
  </div>
</template>

<style scoped>
.carousel-container {
  display: flex;
  justify-content: center;
}</style>
