<script setup lang="ts">
import { ref, onMounted, inject, Ref } from 'vue'
import { notification } from 'ant-design-vue'
import LikeVideosService from '@/api/services/LikeVideosService'
import VideoCard from '../common/VideoCard.vue'
import { ReviewResponseVO } from '@/api/interfaces/response'

const data = inject<Ref<ReviewResponseVO[] | undefined>>('likeVideosInfo')
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
