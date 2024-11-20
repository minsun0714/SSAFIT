<template>
  <div class="video-card" @click="navigateToVideo">
    <div class="thumbnail-wrapper">
      <img :src="thumbnailImgUrl" alt="Thumbnail" class="thumbnail" />
    </div>
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
  padding: 10px;
  background-color: white;
  cursor: pointer;
  transition: transform 0.2s ease;
}

.video-card:hover {
  transform: translateY(-5px);
}

.thumbnail-wrapper {
  overflow: hidden;
  border-radius: 8px;
}

.thumbnail {
  width: 100%;
  height: 220px;
  object-fit: cover;
  transition: transform 0.3s ease;
}

.video-card:hover .thumbnail {
  transform: scale(1.05);
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
