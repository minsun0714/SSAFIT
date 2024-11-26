<template>
  <div class="latest-videos">
    <br>
    <h2>Latest Videos</h2>
    <h3>Check out the newest content for fresh inspiration!</h3>
    <br>
    <div class="video-card-container">
      <VideoCard
        v-for="(video, index) in latestVideos"
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

interface Video {
  title: string;
  nickname: string;
  viewCount: number;
  createdAt: string;
  thumbnailImgUrl: string;
  videoId: number;
  rating: number;
}

defineProps<{
  latestVideos: Video[];
}>();

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
