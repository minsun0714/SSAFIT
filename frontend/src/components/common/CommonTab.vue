<script setup lang="ts">
import { defineProps, ref, watch } from 'vue'
import { useRoute, useRouter } from 'vue-router'

const { tabs, routesMap } = defineProps<{
  tabs: string[]
  routesMap: Array<{ name: string; params?: Record<string, string> }>
}>()

const route = useRoute()
const router = useRouter()

const activeTab = ref(0)

// 현재 라우트와 routesMap 동기화
watch(
  () => route.name,
  (newName) => {
    activeTab.value = routesMap.findIndex((route) => route.name === newName)
  },
  { immediate: true },
)

// 탭 클릭 이벤트
const handleClickTab = async (index: number) => {
  await router.push(routesMap[index])
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
