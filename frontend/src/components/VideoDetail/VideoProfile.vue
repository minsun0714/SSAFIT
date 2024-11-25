<template>
  <br />
  <div class="video-intro" v-if="videoData">
    <div class="thumbnail">
      <img :src="videoData.thumbnailUrl" :alt="videoData.title" />
    </div>
    <div class="details">
      <h1 class="title">{{ videoData.title }}</h1>
      <p class="nickname">{{ videoData.channelTitle }}</p>
      <p class="view-count">
        조회수: <span>{{ formattedViewCount }}</span>
      </p>
      <p class="introduce">{{ videoData.introduceText }}</p>
      <Button> 좋아요 </Button>
    </div>
  </div>
</template>

<script setup lang="ts">
import { computed, PropType } from "vue";
import Button from "../ui/button/Button.vue";
import type { VideoDetailVO } from "@/api/interfaces/response";

// props로 데이터 수신
const props = defineProps({
  videoData: {
    type: Object as PropType<VideoDetailVO | null>,
    required: true,
  },
});

// 조회수 포맷팅
const formattedViewCount = computed(() => {
  return props.videoData?.viewCount?.toLocaleString() || "0";
});
</script>





<style scoped>
.video-intro {
  display: flex;
  gap: 20px;
  align-items: flex-start;
  max-width: 900px;
  margin: auto;
}

.thumbnail img {
  width: 300px;
  height: auto;
  border-radius: 8px;
}

.details {
  flex: 1;
  display: flex;
  flex-direction: column;
  gap: 10px;
}

.title {
  font-size: 1.5rem;
  font-weight: bold;
}

.nickname {
  color: #555;
  font-size: 1rem;
}

.view-count {
  font-size: 1rem;
  color: #333;
}

.view-count span {
  color: #0066ff;
  font-weight: bold;
}

.introduce {
  font-size: 0.9rem;
  color: #777;
  line-height: 1.4;
}
</style>
