<script setup lang="ts">
import CommonTab from '@/components/common/CommonTab.vue'
import ExerciseGrassRecord from '@/components/MyPage/ExerciseGrassRecord.vue'
import MyProfile from '@/components/MyPage/MyProfile.vue'
import { Routes } from '@/utils/enum'
import MyPageContext from './MyPageContext.vue'

const tabs = ['좋아요한 영상', '남긴 후기', '정보관리', 'Admin']

const routesMap = [
  Routes.MYPAGE_LIKED_VIDEOS,
  Routes.MYPAGE_REVIEWS,
  Routes.MYPAGE_INFO,
  Routes.MYPAGE_ADMIN,
]
</script>

<template>
  <main>
    <MyPageContext>
      <MyProfile />
      <ExerciseGrassRecord />
      <CommonTab :tabs="tabs" :routesMap="routesMap" />
      <RouterView :key="$route.fullPath"/>
    </MyPageContext>
  </main>
</template>

<style scoped>
main {
  background-color: black;
  color: white;
  min-height: 100vh;
}
</style>
