<script setup lang="ts">
import CommonTab from '@/components/common/CommonTab.vue'
import ExerciseGrassRecord from '@/components/MyPage/ExerciseGrassRecord.vue'
import MyProfile from '@/components/MyPage/MyProfile.vue'
import { Routes } from '@/utils/enum'
import MyPageContext from './MyPageContext.vue'

const tabs = ['좋아요한 영상', '남긴 후기', '정보관리', 'Admin']
const routesMap = [
  { name: Routes.MYPAGE_LIKED_VIDEOS, params: {} },
  { name: Routes.MYPAGE_REVIEWS, params: {} },
  { name: Routes.MYPAGE_INFO, params: {} },
  { name: Routes.MYPAGE_ADMIN, params: {} },
]
</script>

<template>
  <main>
    <MyPageContext>
      <MyProfile />
      <ExerciseGrassRecord />
      <CommonTab :tabs="tabs" :routesMap="routesMap" />
      <RouterView />
    </MyPageContext>
  </main>
</template>

<style scoped>
</style>
