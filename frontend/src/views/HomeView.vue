<script setup lang="ts">
import FirstPicture from "@/components/Video/FirstPicture.vue";
import TheWelcome from '@/components/TheWelcome.vue'
</script>

<template>
  <main>
    <FirstPicture />
    <TheWelcome />
  </main>
</template>

<style scoped>
main {
  background-color: black;
  color: white;
  min-height: 100vh;
}
</style>
