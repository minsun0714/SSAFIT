<script setup lang="ts">
import { ref, provide, type Ref, watchEffect } from 'vue'
import type {
  FollowInfoResponse,
  MemberInfoResponse,
  ExerciseGrass,
  LikeResponseVO,
  ReviewResponseVO,
  VideoDetailVO,
} from '@/api/interfaces/response'
import OtherMemberApiFacade from '@/api/apiFacade/OtherMemberApiFacade'
import LikeVideoApiFacade from '@/api/apiFacade/LikeVideoApiFacade'
import ReviewApiFacade from '@/api/apiFacade/ReviewAPIFacade'

const userInfo: Ref<MemberInfoResponse | undefined> = ref(
  OtherMemberApiFacade.useFetchUserInfo().data,
)
const followInfo: Ref<FollowInfoResponse | undefined> = ref(
  OtherMemberApiFacade.useFetchFollowRelations().data,
)
const grassInfo: Ref<ExerciseGrass[] | undefined> = ref(
  OtherMemberApiFacade.useFetchExerciseGrass().data,
)
const likeVideosInfo: Ref<VideoDetailVO[] | undefined> = ref(
  LikeVideoApiFacade.useFetchLikesByMember().data,
)
const reviewsInfo: Ref<ReviewResponseVO | undefined> = ref(
  ReviewApiFacade.useFetchReviewsByMemberId().data,
)

watchEffect(() => {
  console.log('Updated ikes Info:', likeVideosInfo.value)
})

provide('userInfo', userInfo)
provide('followInfo', followInfo)
provide('grassInfo', grassInfo)
provide('likeVideosInfo', likeVideosInfo)
provide('reviewsInfo', reviewsInfo)
</script>

<template>
  <main>
    <slot />
  </main>
</template>
