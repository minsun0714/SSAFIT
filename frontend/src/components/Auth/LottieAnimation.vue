<script lang="ts">
import lottie from 'lottie-web'

export default {
  props: {
    animationPath: {
      type: String,
      required: true,
    },
    loop: {
      type: Boolean,
      default: true,
    },
    autoplay: {
      type: Boolean,
      default: true,
    },
  },
  mounted() {
    lottie.loadAnimation({
      container: this.$refs.lottieContainer,
      renderer: 'svg',
      loop: this.loop,
      autoplay: this.autoplay,
      path: this.animationPath,
    })
  },
}
</script>

<template>
  <div ref="lottieContainer" class="w-full h-full border bg-blue-400"></div>
</template>
