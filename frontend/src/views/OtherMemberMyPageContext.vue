<script setup lang="ts">
import { ref, provide, type Ref } from 'vue'
import type {
  FollowInfoResponse,
  MemberInfoResponse,
  ExerciseGrass,
  LikeResponseVO,
  ReviewResponseVO,
} from '@/api/interfaces/response'
import OtherMemberApiFacade from '@/api/apiFacade/OtherMemberApiFacade'
import LikeVideoApiFacade from '@/api/apiFacade/LikeVideoApiFacade';
import ReviewApiFacade from '@/api/apiFacade/ReviewAPIFacade';

const userInfo: Ref<MemberInfoResponse | undefined> = ref(
  OtherMemberApiFacade.useFetchUserInfo().data,
)
const followInfo: Ref<FollowInfoResponse | undefined> = ref(
  OtherMemberApiFacade.useFetchFollowRelations().data,
)
const grassInfo: Ref<ExerciseGrass[] | undefined> = ref(
  OtherMemberApiFacade.useFetchExerciseGrass().data,
)
const likeVideosInfo: Ref<LikeResponseVO[] | undefined> = ref(LikeVideoApiFacade.useFetchLikesByMember().data)
const reviewsInfo: Ref<ReviewResponseVO | undefined> = ref(ReviewApiFacade.useFetchReviewsByMemberId().data)

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
