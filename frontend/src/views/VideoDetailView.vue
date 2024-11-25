<script setup lang="ts">
import VideoProfile from "../components/VideoDetail/VideoProfile.vue";
import CommonTab from '@/components/common/CommonTab.vue'
import { Routes } from '@/utils/enum'
import { useRouter, onMounted } from 'vue'

const tabs = ['영상재생', '영상후기']

const routesMap = [
  Routes.VIDEO_PLAY,
  Routes.VIDEO_REVIEWS,
]
const router = useRouter()

onMounted(() => {
  router.push(Routes.VIDEO_PLAY)
})
</script>

<template>
  <main>
    <VideoProfile />
    <CommonTab :tabs="tabs" :routesMap="routesMap" />
    <RouterView />
  </main>
</template>
