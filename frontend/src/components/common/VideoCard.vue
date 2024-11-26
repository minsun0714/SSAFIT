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
        <p class="view-count">조회수 {{ viewCount.toLocaleString() }}</p>
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
  display: -webkit-box; /* Flexbox를 기반으로 한 클리핑 */
  -webkit-line-clamp: 2; /* 최대 2줄로 제한 */
  -webkit-box-orient: vertical; /* 수직 방향으로 클리핑 */
  overflow: hidden; /* 초과된 텍스트 숨김 */
  text-overflow: ellipsis; /* ... 처리 */
  min-height: 44px; /* 2줄 높이에 맞게 최소 높이 설정 (16px * 2 + 여백) */
  line-height: 1.4; /* 줄 간격 설정 */
}


.nickname {
  font-size: 14px;
  color: #bbb;
}

.details {
  display: flex;
  justify-content: space-between;
  font-size: 12px;
}

.view-count {
  color: #0001c8;
  font-weight: bold;
}

.created-at {
  color: #bbb
}
</style>
