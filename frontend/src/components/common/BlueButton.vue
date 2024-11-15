<script setup lang="ts">
const props = defineProps<{
  text: string
  width?: number
  height?: number
  handleClick?: () => void
}>()
</script>

<template>
  <div>
    <button
      :style="{
        width: props.width ? `${props.width}px` : 'auto',
        height: props.height ? `${props.height}px` : 'auto',
      }"
      class="flex justify-center items-center bg-blue-400 text-white font-bold text-sm rounded-md p-4"
      @click="props.handleClick"
    >
      {{ props.text }}
    </button>
  </div>
</template>
