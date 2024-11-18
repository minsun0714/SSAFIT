<template>
    <div class="popular-videos">
      <h2>인기 영상</h2>
      <div class="video-card-container">
        <VideoCard 
          v-for="(video, index) in popularVideos" 
          :key="index" 
          :title="video.title" 
          :nickname="video.nickname" 
          :viewCount="video.viewCount" 
          :createdAt="video.createdAt" 
          :thumbnailImgUrl="video.thumbnailImgUrl" 
        />
      </div>
    </div>
  </template>
  
  <script>
  import VideoCard from "@/components/Video/VideoCard.vue";
  
  export default {
    components: { VideoCard },
    props: {
      popularVideos: {
        type: Array,
        required: true
      }
    }
  };
  </script>
  
  <style scoped>
  .video-card-container {
    display: flex;
    gap: 20px;
    flex-wrap: wrap;
    justify-content: center;
  }
  
  h2 {
    margin-top: 30px;
    font-size: 24px;
    font-weight: bold;
    text-align: center;
  }
  </style>
  