<script setup lang="ts">
import CommonTab from '@/components/common/CommonTab.vue'
import MyProfile from '@/components/MyPage/MyProfile.vue'
import { ref, watch } from 'vue'
import { useRoute, useRouter } from 'vue-router'

const route = useRoute()
const router = useRouter()

const routesMap = ['/mypage/liked-videos', '/mypage/reviews', '/mypage/info', '/mypage/admin']

const activeTab = ref(0)

watch(
  () => route.path,
  (newPath) => {
    activeTab.value = routesMap.indexOf(newPath)
  },
  { immediate: true },
)

const handleClickTab = () => {
  router.push(routesMap[activeTab.value])
}
</script>

<template>
  <main>
    <MyProfile />
    <CommonTab
      :tabs="['좋아요한 영상', '남긴 후기', '정보관리', 'Admin']"
      v-model:active="activeTab"
      @click="handleClickTab"
    />
    <RouterView />
  </main>
</template>
