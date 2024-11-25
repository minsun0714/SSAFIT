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
import { ref } from 'vue';
import { useRouter } from 'vue-router'
import { Routes } from '../../utils/enum'

const props = defineProps<{
  videoId: string
  nickname: string
  title: string
  viewCount: number
  createdAt: string
  part?: string
  introduceText?: string
  thumbnailImgUrl: string
  videoUrl?: string
  status?: string
  rating?: number
}>()

const router = useRouter();
const loading = ref(false);

const navigateToVideo = async () => {
  loading.value = true;
  try {
    await router.push({
      name: Routes.VIDEO,
      params: { videoId: props.videoId },
    });
  } finally {
    loading.value = false;
  }
};
</script>

<style scoped>
.video-card {
  display: flex;
  flex-direction: column;
  width: 360px;
  padding: 10px;
  background-color: black; /* 배경 검은색 */
  cursor: pointer;
  transition: transform 0.2s ease;
  color: white; /* 글씨 흰색 */
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
  color: white; /* 제목 흰색 */
}

.nickname {
  font-size: 14px;
  color: #bbb; /* 닉네임 약간 밝은 회색 */
}

.details {
  display: flex;
  justify-content: space-between;
  font-size: 12px;
  color: white; /* 기본 텍스트 색 흰색 */
}

.view-count {
  color: #e50914; /* 조회수 빨간색 */
  font-weight: bold;
}

.created-at {
  color: #bbb; /* 생성 날짜 밝은 회색 */
}
</style>
