<template>
  <div class="popular-videos">
    <h2>인기 영상</h2>
    <div class="video-card-container">
      <VideoCard
        v-for="(video, index) in popularVideos"
        :key="index"
        :title="video.title"
        :nickname="video.nickname"
        :viewCount="video.viewCount"
        :createdAt="video.createdAt"
        :thumbnailImgUrl="video.thumbnailImgUrl"
        :videoId="video.videoId"
        :rating="video.rating"
      />
    </div>
  </div>
</template>

<script setup lang="ts">
import VideoCard from "@/components/common/VideoCard.vue";

type Video = {
  title: string;
  nickname: string;
  viewCount: number;
  createdAt: string;
  thumbnailImgUrl: string;
  videoId: number;
  rating: number;
};

defineProps({
  popularVideos: {
    type: Array as () => Video[],
    required: true,
  },
});
</script>

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
