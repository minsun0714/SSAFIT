<template>
  <div class="video-card" @click="navigateToVideo">
    <img :src="thumbnailImgUrl" alt="Thumbnail" class="thumbnail" />
    <div class="info">
      <h3 class="title">{{ title }}</h3>
      <p class="nickname">{{ nickname }}</p>
      <div class="details">
        <p class="created-at">{{ createdAt }}</p>
        <p class="view-count">조회수 {{ viewCount }}</p>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { useRouter } from 'vue-router';
import { Routes } from '../../utils/enum';

const props = defineProps<{
  title: string,
  nickname: string,
  viewCount: number,
  createdAt: string,
  thumbnailImgUrl: string,
  videoId: number,
  rating: number
}>()


const router = useRouter();

const navigateToVideo = () => {
  router.push({
    name: Routes.VIDEO,
    params: { id: props.videoId }
  });
};
</script>

<style scoped>
.video-card {
  display: flex;
  flex-direction: column;
  width: 360px;
  border: 1px solid #ddd;
  border-radius: 8px;
  padding: 10px;
  background-color: white;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
  cursor: pointer;
  transition: box-shadow 0.2s ease;
}

.video-card:hover {
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.2);
}

.thumbnail {
  width: 100%;
  height: 220px;
  object-fit: cover;
  border-radius: 4px;
}

.info {
  margin-top: 10px;
}

.title {
  font-size: 16px;
  font-weight: bold;
  margin: 0;
}

.nickname {
  font-size: 14px;
  color: #555;
}

.details {
  display: flex;
  justify-content: space-between;
  font-size: 12px;
  color: #888;
}

.view-count {
  color: #0066ff;
  font-weight: bold;
}

.created-at {
  color: #888;
}
</style>
