<script setup lang="ts">
import CommonTab from '@/components/common/CommonTab.vue'
import ExerciseGrassRecord from '@/components/MyPage/ExerciseGrassRecord.vue'
import MyProfile from '@/components/MyPage/MyProfile.vue'
import { Routes } from '@/utils/enum'
import OtherMemberMyPageContext from './OtherMemberMyPageContext.vue'

const tabs = ['좋아요한 영상', '남긴 후기']

const routesMap = [Routes.MYPAGE_LIKED_VIDEOS, Routes.MYPAGE_REVIEWS]
</script>

<template>
  <main>
    <OtherMemberMyPageContext>
      <MyProfile />
      <ExerciseGrassRecord />
      <CommonTab :tabs="tabs" :routesMap="routesMap" />
      <RouterView />
    </OtherMemberMyPageContext>
  </main>
</template>
