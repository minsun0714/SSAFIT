<script setup lang="ts">
import { defineProps, ref, watch } from 'vue'
import { useRoute, useRouter } from 'vue-router'

const { tabs, routesMap } = defineProps<{
  tabs: string[]
  routesMap: string[]
}>()

const route = useRoute()
const router = useRouter()

const activeTab = ref(0)

watch(
  () => route.path,
  (newPath) => {
    activeTab.value = routesMap.indexOf(newPath)
  },
  { immediate: true },
)

const handleClickTab = async (index: number) => {
  await router.push({ name: routesMap[index] })
  activeTab.value = index
}
</script>

<template>
  <ul class="h-24 flex justify-center items-center gap-16 text-xs font-bold">
    <li
      v-for="(tab, index) in tabs"
      :key="tab"
      class="cursor-pointer"
      :class="{ 'text-blue-400': index === activeTab, 'text-gray-400': index !== activeTab }"
      @click="handleClickTab(index)"
    >
      {{ tab }}
    </li>
  </ul>
</template>
