<script setup lang="ts">
import { ref, onMounted } from 'vue'
import { notification } from 'ant-design-vue'
import LikeVideosService from '@/api/services/LikeVideosService'
import VideoCard from '../common/VideoCard.vue'

const data = ref<LikeVideos[] | undefined>([])

onMounted(async () => {
  try {
    // 좋아요한 영상 목록 가져오기
    const likedVideos = await LikeVideosService.getLikesByMember()
    data.value = likedVideos
  } catch (error) {
    console.error('좋아요한 영상 조회 실패:', error)
    notification.error({
      message: '영상 좋아요 조회 실패',
      description: '좋아요한 영상을 가져오는 데 실패했습니다.'
    })
  }
})
</script>

<template>
  <div class="flex justify-center">
    <ul v-if="data?.length">
      <li class="max-w-[1200px] flex flex-wrap justify-center items-center gap-6 p-6">
        <VideoCard
          v-for="video in data"
          :key="video?.videoId"
          :title="video?.title"
          :nickname="video?.channelTitle"
          :viewCount="video?.viewCount"
          :createdAt="video?.publishedAt"
          :thumbnailImgUrl="video?.thumbnailUrl"
          :videoId="video?.videoId"
        />
      </li>
    </ul>
    <div v-else>
      <p>좋아요한 영상이 없습니다.</p>
    </div>
  </div>
</template>
