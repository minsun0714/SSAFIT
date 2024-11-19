<template>
  <div class="video-intro">
    <div class="thumbnail">
      <img :src="videoData.thumbnailImgUrl" :alt="videoData.title" />
    </div>
    <div class="details">
      <h1 class="title">{{ videoData.title }}</h1>
      <p class="nickname">{{ videoData.nickname }}</p>
      <p class="view-count">조회수: <span>{{ formattedViewCount }}</span></p>
      <p class="introduce">이 비디오는 아침 스트레칭 루틴으로 활력을 더합니다.</p>
      <Button> 좋아요 </Button>
    </div>
  </div>
</template>

<script setup lang="ts">
import { reactive, computed } from "vue";
import Button from "../ui/button/Button.vue";
const videoData = reactive({
  thumbnailImgUrl: "https://img.youtube.com/vi/50WCSpZtdmA/maxresdefault.jpg",
  title: "Shim EuDdeum 10 Minute Morning Stretch Everydayㅣ2023 Renewal",
  nickname: "힘으뜸",
  viewCount: 1200000,
  createdAt: "2024-11-17",
  videoId:"abc"
});

const formattedViewCount = computed(() => {
  return videoData.viewCount.toLocaleString();
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
