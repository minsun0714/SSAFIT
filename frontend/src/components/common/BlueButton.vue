<script setup lang="ts">
defineProps<{
  text: string
  width?: number
  handleClick?: () => void
}>()
</script>
<template>
  <div>
    <button class="bg-blue-400 text-white font-bold text-sm rounded-md p-4" @click="handleClick">
      {{ text }}
    </button>
  </div>
</template>
