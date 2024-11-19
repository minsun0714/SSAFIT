<template>
  <a-carousel arrows>
    <template #prevArrow>
      <div class="custom-slick-arrow" style="left: 10px; z-index: 1">
        <left-outlined />
      </div>
    </template>
    <template #nextArrow>
      <div class="custom-slick-arrow" style="right: 10px">
        <right-outlined />
      </div>
    </template>

    <div v-for="(video, index) in videos" :key="video.videoId">
      <img :src="video.thumbnailImgUrl" alt="썸네일" class="thumbnail-image" />
    </div>
  </a-carousel>
</template>

<script lang="ts" setup>
import { LeftOutlined, RightOutlined } from '@ant-design/icons-vue';
import firstThumbnail from '@/assets/Home/1.png';

const videos = [
  {
    thumbnailImgUrl: firstThumbnail,
    videoId: 1
  },
  {
    thumbnailImgUrl: "https://img.youtube.com/vi/02K-k6daPb4/maxresdefault.jpg",
    videoId: 2
  },
  {
    thumbnailImgUrl: "https://img.youtube.com/vi/54tTYO-vU2E/maxresdefault.jpg",
    videoId: 3
  },
  {
    thumbnailImgUrl: "https://img.youtube.com/vi/QqqZH3j_vH0/maxresdefault.jpg",
    videoId: 4
  },
  {
    thumbnailImgUrl: "https://img.youtube.com/vi/tzN6ypk6Sps/maxresdefault.jpg",
    videoId: 5
  }
];
</script>

<style scoped>
/* 슬라이드의 기본 스타일 */
:deep(.slick-slide) {
  text-align: center;
  background: #364d79;
  overflow: hidden;
}

/* 썸네일 이미지 스타일 */
.thumbnail-image {
  width: 100%;
  height: 800px;  /* 썸네일 이미지의 고정된 높이 */
  object-fit: cover;  /* 이미지가 영역을 채우도록 설정 */
}

/* 화살표 스타일 */
:deep(.slick-arrow.custom-slick-arrow) {
  width: 50px;
  height: 50px;
  font-size: 50px;
  color: #fff;
  transition: ease all 0.3s;
  opacity: 0.3;
  z-index: 1;
}
:deep(.slick-arrow.custom-slick-arrow:before) {
  display: none;
}
:deep(.slick-arrow.custom-slick-arrow:hover) {
  color: #fff;
  opacity: 0.5;
}
</style>
