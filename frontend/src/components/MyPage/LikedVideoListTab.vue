<script setup lang="ts">
import { inject, Ref } from 'vue'
import VideoCard from '../common/VideoCard.vue'
import { ReviewResponseVO } from '@/api/interfaces/response'

const data = inject<Ref<ReviewResponseVO[] | undefined>>('likeVideosInfo')
</script>

<template>
  <div class="flex justify-center">
    <ul
      v-if="data?.length"
      class="max-w-[1200px] flex flex-wrap justify-center items-center gap-6 p-6"
    >
      <li v-for="video in data" :key="video?.videoId">
        <VideoCard
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
