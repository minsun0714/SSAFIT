<script setup lang="ts">
import { ref } from 'vue'

defineProps<{
  tabs: string[]
}>()

const active = ref(0)
</script>
<template>
  <ul class="h-24 flex justify-center items-center gap-16 text-xs font-bold">
    <li
      v-for="(tab, index) in tabs"
      :key="tab"
      class="cursor-pointer"
      :class="index === active && 'text-blue-400'"
      @click="active = index"
    >
      {{ tab }}
    </li>
  </ul>
</template>
