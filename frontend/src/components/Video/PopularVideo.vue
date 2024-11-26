<template>
  <div class="popular-videos">
    <br>
    <h2>Most Viewed Videos</h2>
    <h3>Discover the highest-rated videos, loved by our community.</h3>
    <br>
    <br>
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
  <br>
</template>

<script setup lang="ts">
import VideoCard from "@/components/common/VideoCard.vue";

type Video = {
  title: string;
  nickname: string;
  viewCount: number;
  createdAt: string;
  thumbnailImgUrl: string;
  videoId: string;
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
  gap: 40px;
  flex-wrap: wrap;
  justify-content: center;
}

h2 {
  margin-top: 30px;
  font-size: 24px;
  font-weight: bold;
  text-align: center;
}
h3 {
  text-align: center;
  margin-top: 10px;
  font-size: 18px;
  color: #555;
}
.custom-line {
  width: 100%; /* 전체 너비 */
  height: 1px; /* 줄 두께 */
  background-color: var(--line-color, black); /* 기본값은 검정색, 변경 가능 */
  margin-bottom: 20px; /* 줄과 아래 콘텐츠 간격 */
}
</style>
