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
      <button
        :class="['like-button', { 'liked': isLiked }]"
        @click="toggleLike"
      >
        {{ isLiked ? '좋아요 취소' : '좋아요' }}
      </button>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, computed, PropType } from "vue";
import LikeVideosService from "@/api/services/LikeVideosService";
import { notification } from "ant-design-vue";
import type { VideoDetailVO } from "@/api/interfaces/response";
import type { LikeRequestDTO } from "@/api/interfaces/request";

// props로 데이터 수신
const props = defineProps({
  videoData: {
    type: Object as PropType<VideoDetailVO | null>,
    required: true,
  },
});

// 상태 관리
const isLiked = ref(false);
const likeId = ref<number | null>(null);

// 조회수 포맷팅
const formattedViewCount = computed(() => {
  return props.videoData?.viewCount?.toLocaleString() || "0";
});

// 좋아요 상태 토글
const toggleLike = async () => {
  if (!props.videoData?.videoId) return;

  try {
    if (isLiked.value) {
      // 좋아요 취소
      if (likeId.value) {
        await LikeVideosService.removeLike(likeId.value.toString());
        notification.success({ message: "좋아요 취소됨" });
        isLiked.value = false;
        likeId.value = null;
      }
    } else {
      // 좋아요 등록
      const payload: LikeRequestDTO = {
        memberId: "currentUser123", // 실제 로그인 사용자 ID
        videoId: props.videoData.videoId,
      };
      const response = await LikeVideosService.addLike(payload);
      notification.success({ message: "좋아요 추가됨" });
      isLiked.value = true;
      likeId.value = response.likeId;
    }
  } catch (error) {
    console.error("Failed to toggle like:", error);
    notification.error({ message: "작업 실패", description: "좋아요 처리에 실패했습니다." });
  }
};
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

/* 좋아요 버튼 스타일 */
.like-button {
  padding: 8px 16px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  font-weight: bold;
  color: white;
  background-color: #0066ff;
  transition: background-color 0.3s;
}

.like-button:hover {
  background-color: #004db3;
}

.like-button.liked {
  background-color: gray;
}

.like-button.liked:hover {
  background-color: darkgray;
}
</style>
