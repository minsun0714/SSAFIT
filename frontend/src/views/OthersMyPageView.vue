<script setup lang="ts">
import CommonTab from '@/components/common/CommonTab.vue'
import ExerciseGrassRecord from '@/components/MyPage/ExerciseGrassRecord.vue'
import MyProfile from '@/components/MyPage/MyProfile.vue'
import OtherMemberMyPageContext from './OtherMemberMyPageContext.vue'
import { useRoute} from 'vue-router'

const route = useRoute()
const memberId = route.params.memberId as string

const tabs = ['좋아요한 영상', '남긴 후기']
const routesMap = [
  { name: 'others-liked-videos', params: { memberId } },
  { name: 'others-reviews', params: { memberId } },
]
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
