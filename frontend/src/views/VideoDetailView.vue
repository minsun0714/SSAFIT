<script setup lang="ts">
import { ref, onMounted, watch } from "vue";
import { useRoute } from "vue-router";
import CommonTab from "@/components/common/CommonTab.vue";
import VideoProfile from "@/components/VideoDetail/VideoProfile.vue";
import VideoService from "@/api/services/VideoService";
import { Routes } from "@/utils/enum";
import type { VideoDetailVO } from "@/api/interfaces/response";

// 비디오 데이터를 저장할 상태
const videoData = ref<VideoDetailVO | null>(null);
const route = useRoute();

const tabs = ["영상재생", "영상후기"];
const routesMap = [Routes.VIDEO_PLAY, Routes.VIDEO_REVIEWS];

// 디버깅용: videoData 변경 추적
watch(videoData, (newVal) => {
  console.log("videoData updated:", newVal);
});

// 비디오 데이터 로드
onMounted(async () => {
  const videoId = route.params.videoId as string;

  console.log("Fetching video data for videoId:", videoId); // 요청 전 로그

  try {
    videoData.value = await VideoService.getVideoById(videoId);
    console.log("Loaded video data:", videoData.value); // 요청 후 데이터 확인
  } catch (error) {
    console.error("Failed to load video details:", error); // 오류 발생 시 로그
    alert("Failed to load video details.");
  }
});
</script>

<template>
  <main>
    <VideoProfile :video-data="videoData" />
    <CommonTab :tabs="tabs" :routesMap="routesMap" />
    <!-- RouterView로 데이터 전달 -->
    <RouterView :video-data="videoData" />
    <!-- 디버깅용 상태 확인
    <pre>{{ videoData }}</pre> -->
  </main>
</template>

<style scoped>
</style>
