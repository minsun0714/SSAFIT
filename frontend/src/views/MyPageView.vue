<script setup lang="ts">
import CommonTab from '@/components/common/CommonTab.vue'
import MyProfile from '@/components/MyPage/MyProfile.vue'

const tabs = ['좋아요한 영상', '남긴 후기', '정보관리', 'Admin']
const routesMap = ['/mypage/liked-videos', '/mypage/reviews', '/mypage/info', '/mypage/admin']
</script>

<template>
  <main>
    <MyProfile />
    <CommonTab :tabs="tabs" :routesMap="routesMap" />
    <RouterView />
  </main>
</template>
