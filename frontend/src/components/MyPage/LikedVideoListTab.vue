<script setup lang="ts">
import { Ref, inject } from 'vue'
import VideoCard from '../common/VideoCard.vue'
const data = inject<Ref<LikeVideosResponse[] | undefined>>('likeVideosInfo')
</script>

<template>
  <div class="flex justify-center">
    <ul v-if="data?.length">
      <li class="max-w-[1200px] flex flex-wrap justify-center items-center gap-6 p-6">
        <VideoCard
          v-for="(video, index) in data"
          :key="index"
          :videoId="video?.videoId"
          :title="video?.title"
          :nickname="video?.nickname"
          :viewCount="video?.viewCount"
          :createdAt="video?.createdAt"
          :thumbnailImgUrl="video?.thumbnailImgUrl"
          :rating="video?.rating"
        />
      </li>
    </ul>
    <div>
      <p>좋아요한 영상이 없습니다.</p>
    </div>
  </div>
</template>
