<script setup lang="ts">
import { ref, provide, onMounted, type Ref } from 'vue'
import MemberApiFacade from '@/api/apiFacade/MemberApiFacade'
import FollowApiFacade from '@/api/apiFacade/FollowAPIFacade'
import ExerciseLogApiFacade from '@/api/apiFacade/ExerciseLogApiFacade'
import type {
  FollowInfoResponse,
  MemberInfoResponse,
  ExerciseGrass,
} from '@/api/interfaces/response'

const userInfo: Ref<MemberInfoResponse | undefined> = ref(undefined)
const followInfo: Ref<FollowInfoResponse | undefined> = ref(undefined)
const grassInfo: Ref<ExerciseGrass[] | undefined> = ref(undefined)

provide('userInfo', userInfo)
provide('followInfo', followInfo)
provide('grassInfo', grassInfo)

onMounted(() => {
  const userResponse = MemberApiFacade.useFetchUserInfo()
  const followResponse = FollowApiFacade.useFetchFollowRelations()
  const grassResponse = ExerciseLogApiFacade.useFetchExerciseGrass()

  userInfo.value = userResponse.data.value
  followInfo.value = followResponse.data.value
  grassInfo.value = grassResponse.data.value

  console.log(userInfo, followInfo, grassInfo)
})
</script>

<template>
  <main>
    <slot />
  </main>
</template>
