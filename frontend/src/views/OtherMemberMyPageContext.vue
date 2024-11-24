<script setup lang="ts">
import { ref, provide, type Ref } from 'vue'
import type {
  FollowInfoResponse,
  MemberInfoResponse,
  ExerciseGrass,
} from '@/api/interfaces/response'
import OtherMemberApiFacade from '@/api/apiFacade/OtherMemberApiFacade'

const userInfo: Ref<MemberInfoResponse | undefined> = ref(
  OtherMemberApiFacade.useFetchUserInfo().data,
)
const followInfo: Ref<FollowInfoResponse | undefined> = ref(
  OtherMemberApiFacade.useFetchFollowRelations().data,
)
const grassInfo: Ref<ExerciseGrass[] | undefined> = ref(
  OtherMemberApiFacade.useFetchExerciseGrass().data,
)

provide('userInfo', userInfo)
provide('followInfo', followInfo)
provide('grassInfo', grassInfo)
</script>

<template>
  <main>
    <slot />
  </main>
</template>
