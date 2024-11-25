<script setup lang="ts">
import { ref, provide, type Ref } from 'vue'
import type {
  FollowInfoResponse,
  MemberInfoResponse,
  ExerciseGrass,
  LikeResponseVO,
  ReviewResponseVO,
} from '@/api/interfaces/response'
import MemberApiFacade from '@/api/apiFacade/MemberApiFacade'
import FollowApiFacade from '@/api/apiFacade/FollowAPIFacade'
import ExerciseLogApiFacade from '@/api/apiFacade/ExerciseLogApiFacade'
import LikeVideoApiFacade from '@/api/apiFacade/LikeVideoApiFacade'
import ReviewApiFacade from '@/api/apiFacade/ReviewAPIFacade'

const userInfo: Ref<MemberInfoResponse | undefined> = ref(MemberApiFacade.useFetchUserInfo().data)
const followInfo: Ref<FollowInfoResponse | undefined> = ref(
  FollowApiFacade.useFetchFollowRelations().data,
)
const grassInfo: Ref<ExerciseGrass[] | undefined> = ref(
  ExerciseLogApiFacade.useFetchExerciseGrass().data,
)
const likeVideosInfo: Ref<LikeResponseVO[] | undefined> = ref(LikeVideoApiFacade.useFetchLikes().data)
const reviewsInfo: Ref<ReviewResponseVO | undefined> = ref(ReviewApiFacade.useFetchReviews().data)

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
