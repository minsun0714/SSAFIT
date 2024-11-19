<script setup lang="ts">
import { Card, CardContent } from '@/components/ui/card'
import { Carousel, CarouselContent, CarouselItem, CarouselNext, CarouselPrevious } from '@/components/ui/carousel'
import VideoCard from '@/components/common/VideoCard.vue'
import { ref } from 'vue'

const videoData = ref([
  {
    thumbnailImgUrl: "https://img.youtube.com/vi/50WCSpZtdmA/maxresdefault.jpg",
    title: "Shim EuDdeum 10 Minute Morning Stretch Everydayㅣ2023 Renewal",
    nickname: "힘으뜸",
    viewCount: 1200000,
    createdAt: "2024-11-17",
    videoId: 1,
    rating: 5
  },
  {
    thumbnailImgUrl: "https://img.youtube.com/vi/02K-k6daPb4/maxresdefault.jpg",
    title: "15 MIN Fat Burning Cardio - 서서하는 유산소 - 다이어트 운동",
    nickname: "빅씨스 Bigsis",
    viewCount: 900000,
    createdAt: "2024-11-16",
    videoId: 2,
    rating: 5
  },
  {
    thumbnailImgUrl: "https://img.youtube.com/vi/54tTYO-vU2E/maxresdefault.jpg",
    title: "상체 다이어트 최고의 운동 BEST [팔뚝살/겨드랑이살/등살/가슴어깨라인]",
    nickname: "ThankyouBUBU",
    viewCount: 800000,
    createdAt: "2024-11-15",
    videoId: 3,
    rating: 5
  },
  {
    thumbnailImgUrl: "https://img.youtube.com/vi/QqqZH3j_vH0/maxresdefault.jpg",
    title: "상체비만 다이어트 최고의 운동 [상체 핵매운맛]",
    nickname: "ThankyouBUBU",
    viewCount: 950000,
    createdAt: "2024-11-14",
    videoId: 4,
    rating: 5
  },
  {
    thumbnailImgUrl: "https://img.youtube.com/vi/tzN6ypk6Sps/maxresdefault.jpg",
    title: "하체운동이 중요한 이유? 이것만 보고 따라하자 ! [하체운동 교과서]",
    nickname: "김강민",
    viewCount: 1100000,
    createdAt: "2024-11-13",
    videoId: 5,
    rating: 5
  }
])
</script>

<template>
  <Carousel
    class="relative w-full max-w-sm"
    :opts="{
      align: 'start',
    }"
  >
    <CarouselContent class="-ml-1">
      <CarouselItem
        v-for="(video, index) in videoData"
        :key="video.videoId"
        class="pl-1 md:basis-1/2 lg:basis-1/3"
      >
        <div class="p-1">
          <Card>
            <CardContent class="flex aspect-square items-center justify-center p-6">
              <!-- VideoCard Component Rendering -->
              <VideoCard
                :title="video.title"
                :nickname="video.nickname"
                :viewCount="video.viewCount"
                :createdAt="video.createdAt"
                :thumbnailImgUrl="video.thumbnailImgUrl"
                :videoId="video.videoId"
                :rating="video.rating"
              />
            </CardContent>
          </Card>
        </div>
      </CarouselItem>
    </CarouselContent>
    <CarouselPrevious />
    <CarouselNext />
  </Carousel>
</template>

<style scoped>
.video-card-container {
  display: flex;
  gap: 20px;
  flex-wrap: wrap;
  justify-content: center;
}

h2 {
  margin-top: 30px;
  font-size: 24px;
  font-weight: bold;
  text-align: center;
}
</style>
