<template>
    <div class="latest-videos">
      <h2>최신 영상</h2>
      <div class="video-card-container">
        <VideoCard 
          v-for="(video, index) in latestVideos" 
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
      latestVideos: {
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
  