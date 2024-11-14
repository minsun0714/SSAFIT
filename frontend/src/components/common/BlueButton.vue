<script setup lang="ts">
defineProps<{
  text: string
}>()
</script>
<template>
  <div>
    <button class="bg-blue-400 text-white font-bold text-sm rounded-md p-4">{{ text }}</button>
  </div>
</template>
