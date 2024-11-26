<template>
  <div class="video-play" v-if="props.videoData">
    <div class="w-2/3">
      <iframe
        class="video-iframe"
        :src="props.videoData.embeddingUrl"
        frameborder="0"
        allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture"
        allowfullscreen
      >
      </iframe>
      <h2 class="relative py-4">{{ props.videoData.description }}</h2>
    </div>
  </div>
  >
  <RelatedVideos />
</template>

<script setup lang="ts">
import RelatedVideos from './RelatedVideos.vue'
import type { PropType } from 'vue'
import type { VideoDetailVO } from '@/api/interfaces/response'
import { onMounted, watch } from 'vue'

// props로 데이터 수신
const props = defineProps({
  videoData: {
    type: Object as PropType<VideoDetailVO | null>,
    required: true,
  },
})

onMounted(() => {
  console.log('Received videoData:', props.videoData)
})

watch(
  () => props.videoData,
  (newVal) => {
    console.log('Updated videoData:', newVal)
  },
)
</script>

<style scoped>
.video-play {
  display: flex;
  justify-content: center;
  flex-direction: column;
  align-items: center;
}

.video-container {
  width: 100%;
  max-width: 1000px;
  aspect-ratio: 16 / 9;
  margin-bottom: 20px;
}

.video-iframe {
  width: 100%;
  height: 450px;
}

.video-details {
  display: flex;
  flex-direction: column;
  align-items: center;
  text-align: center;
}

.thumbnail {
  width: 200px;
  height: 120px;
  object-fit: cover;
  border-radius: 4px;
  margin-bottom: 10px;
}

.title {
  font-size: 20px;
  font-weight: bold;
}

.nickname {
  font-size: 14px;
  color: #555;
}

.view-count {
  font-size: 14px;
  color: #888;
}

.created-at {
  font-size: 12px;
  color: #aaa;
}
</style>
