<script setup lang="ts">
import CommonTab from '@/components/common/CommonTab.vue'
import InfoManagement from '@/components/MyPage/InfoManagement.vue'
import LikedVideoList from '@/components/MyPage/LikedVideoList.vue'
import MyProfile from '@/components/MyPage/MyProfile.vue'
import MyReviews from '@/components/MyPage/MyReviewList.vue'
import { ref } from 'vue'

const activeTab = ref(0)

const tabComponents = [LikedVideoList, MyReviews, InfoManagement]
</script>

<template>
  <main>
    <MyProfile />
    <CommonTab :tabs="['좋아요한 영상', '남긴 후기', '정보관리']" v-model:active="activeTab" />
    <component :is="tabComponents[activeTab]" />
  </main>
</template>
