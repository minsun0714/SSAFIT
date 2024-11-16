<script lang="ts" setup>
import { ref, onMounted } from 'vue'
import lottie from 'lottie-web'

const props = defineProps<{
  animationPath: string
  loop?: boolean
  autoplay?: boolean
}>()

const lottieContainer = ref<HTMLElement | null>(null)

onMounted(() => {
  if (lottieContainer.value) {
    lottie.loadAnimation({
      container: lottieContainer.value,
      renderer: 'svg',
      loop: props.loop ?? true,
      autoplay: props.autoplay ?? true,
      path: props.animationPath,
    })
  }
})
</script>

<template>
  <div ref="lottieContainer" class="w-full h-full border bg-blue-400"></div>
</template>
