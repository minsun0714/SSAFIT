<script setup lang="ts">
import { defineProps, defineEmits } from 'vue'

defineProps<{
  tabs: string[]
  active: number
}>()

const emit = defineEmits(['update:active'])

const setActiveTab = (index: number) => {
  emit('update:active', index)
}
</script>

<template>
  <ul class="h-24 flex justify-center items-center gap-16 text-xs font-bold">
    <li
      v-for="(tab, index) in tabs"
      :key="tab"
      class="cursor-pointer"
      :class="{ 'text-blue-400': index === active, 'text-gray-400': index !== active }"
      @click="setActiveTab(index)"
    >
      {{ tab }}
    </li>
  </ul>
</template>
