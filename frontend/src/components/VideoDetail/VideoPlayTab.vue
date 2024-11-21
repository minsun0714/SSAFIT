  <template>
    <div class="video-play">
      <div class="video-container">
        <iframe
        class="video-iframe"
  src="https://www.youtube.com/embed/50WCSpZtdmA"
  frameborder="0"
  allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture"
  allowfullscreen
>
</iframe>
      </div>
    </div>

  <RelatedVideos />
  </template>

  <script setup lang="ts">
import RelatedVideos from "./RelatedVideos.vue";
  </script>

  <style scoped>
  .video-play {
    display: flex;
    flex-direction: column;
    align-items: center;
    margin: 20px;
  }

  .video-container {
  width: 100%;
  max-width: 1000px;
  aspect-ratio: 16 / 9;
  margin-bottom: 20px;
}

.video-iframe {
  width: 100%;
  height: 450px;
}


  .video-details {
    display: flex;
    flex-direction: column;
    align-items: center;
    text-align: center;
  }

  .thumbnail {
    width: 200px;
    height: 120px;
    object-fit: cover;
    border-radius: 4px;
    margin-bottom: 10px;
  }

  .title {
    font-size: 20px;
    font-weight: bold;
  }

  .nickname {
    font-size: 14px;
    color: #555;
  }

  .view-count {
    font-size: 14px;
    color: #888;
  }

  .created-at {
    font-size: 12px;
    color: #aaa;
  }
  </style>
