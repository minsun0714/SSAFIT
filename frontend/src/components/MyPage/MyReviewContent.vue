<script setup lang="ts">
import { ref } from 'vue'

defineProps<{
  content: string
}>()

const isReviewOpen = ref(false)

const toggleReviewOpen = () => {
  isReviewOpen.value = !isReviewOpen.value
}
</script>

<template>
  <div class="px-6 py-8 h-full w-full">
    <p class="w-4/5" :class="!isReviewOpen && 'h-20 text-ellipsis overflow-hidden'">
      {{ content.length < 300 ? content : isReviewOpen ? content : content.slice(0, 300) }}
    </p>
    <div v-if="content.length >= 300" class="w-4/5 pt-4 flex justify-end text-blue-400">
      <button v-if="!isReviewOpen" @click="toggleReviewOpen">...Read more</button>
      <button v-else @click="toggleReviewOpen">close</button>
    </div>
  </div>
</template>
