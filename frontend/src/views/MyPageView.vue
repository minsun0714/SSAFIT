<script setup lang="ts">
import CommonTab from '@/components/common/CommonTab.vue'
import MyProfile from '@/components/MyPage/MyProfile.vue'
</script>

<template>
  <main>
    <MyProfile />
    <CommonTab :tabs="['좋아요한 영상', '남긴 후기', '정보관리']" />
  </main>
</template>
