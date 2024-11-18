<template>
  <div class="carousel-container">
    <div class="carousel" ref="carousel" :style="{ transform: `translateX(-${currentIndex * 100}%)` }">
      <div class="carousel-item" v-for="(ad, index) in ads" :key="index">
        <div class="carousel-image-container">
          <img :src="ad.thumbnailImgUrl" :alt="ad.title" class="carousel-image" />
          <div class="carousel-caption">{{ ad.title }}</div>
        </div>
      </div>
    </div>

    <button class="carousel-button prev" @click="prevSlide">‹</button>
    <button class="carousel-button next" @click="nextSlide">›</button>
  </div>
</template>

<script>
export default {
  props: {
    ads: Array // 광고 영상 데이터
  },
  data() {
    return {
      currentIndex: 0 // 현재 보이는 슬라이드의 인덱스
    };
  },
  methods: {
    nextSlide() {
      if (this.currentIndex < this.ads.length - 1) {
        this.currentIndex++;
      } else {
        this.currentIndex = 0; // 마지막 슬라이드일 때 처음으로 돌아가기
      }
    },
    prevSlide() {
      if (this.currentIndex > 0) {
        this.currentIndex--;
      } else {
        this.currentIndex = this.ads.length - 1; // 첫 슬라이드일 때 마지막 슬라이드로 돌아가기
      }
    }
  }
};
</script>

<style scoped>
.carousel-container {
  position: relative;
  width: 100%;
  max-width: 1200px;
  margin-bottom: 40px;
  overflow: hidden;
}

.carousel {
  display: flex;
  transition: transform 0.3s ease;
}

.carousel-item {
  min-width: 100%; /* 한 번에 하나의 영상만 보이게 */
  display: flex;
  justify-content: center;
  align-items: center;
}

.carousel-image-container {
  position: relative;
}

.carousel-image {
  width: 100%;
  height: auto;
  max-height: 500px;
}

.carousel-caption {
  position: absolute;
  bottom: 20px;
  left: 20px;
  color: white;
  font-size: 24px;
  background-color: rgba(0, 0, 0, 0.5);
  padding: 10px;
}

.carousel-button {
  position: absolute;
  top: 50%;
  transform: translateY(-50%);
  background: rgba(0, 0, 0, 0.5);
  color: white;
  border: none;
  padding: 10px;
  font-size: 24px;
  cursor: pointer;
}

.carousel-button.prev {
  left: 10px;
}

.carousel-button.next {
  right: 10px;
}
</style>
