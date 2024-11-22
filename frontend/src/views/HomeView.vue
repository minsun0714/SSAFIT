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
  background-color: black; /* 배경색을 검은색으로 설정 */
  color: white; /* 텍스트 색상을 흰색으로 설정 (선택 사항) */
  min-height: 100vh; /* 화면 전체 높이를 유지 */
}
</style>
