<script setup lang="ts">
import TheWelcome from '@/components/TheWelcome.vue'
import VideoCarousel from "@/components/Video/VideoCarousel.vue";
import NewCarousel from "@/components/Video/NewCarousel.vue";
</script>

<template>
  <main>
    <NewCarousel />
    <VideoCarousel />
    <TheWelcome />
  </main>
</template>
