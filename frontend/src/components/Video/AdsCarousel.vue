<template>
    <div class="ads-carousel">
      <VideoCarousel :ads="ads" />
    </div>
  </template>
  
  <script>
  import VideoCarousel from "@/components/Video/VideoCarousel.vue";
  
  export default {
    components: { VideoCarousel },
    props: {
      ads: {
        type: Array,
        required: true
      }
    }
  };
  </script>
  
  <style scoped>
  .ads-carousel {
    display: flex;
    justify-content: center;
  }
  </style>
  