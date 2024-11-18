<script setup lang="ts">
import CommonTab from '@/components/common/CommonTab.vue'
import MyProfile from '@/components/MyPage/MyProfile.vue'
import { Routes } from '@/utils/enum'

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
    <MyProfile />
    <CommonTab :tabs="tabs" :routesMap="routesMap" />
    <RouterView />
  </main>
</template>
