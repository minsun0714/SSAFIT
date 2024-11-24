<script setup lang="ts">
import { ref, provide, onMounted, type Ref } from 'vue'
import type {
  FollowInfoResponse,
  MemberInfoResponse,
  ExerciseGrass,
} from '@/api/interfaces/response'
import MemberService from '@/api/services/MemberService'
import ExerciseLogService from '@/api/services/ExerciseLogService'
import FollowService from '@/api/services/FollowService'

const userInfo: Ref<MemberInfoResponse | undefined> = ref(undefined)
const followInfo: Ref<FollowInfoResponse | undefined> = ref(undefined)
const grassInfo: Ref<ExerciseGrass[] | undefined> = ref(undefined)

provide('userInfo', userInfo)
provide('followInfo', followInfo)
provide('grassInfo', grassInfo)

onMounted(async () => {
  const userResponse = async () => {
    return await MemberService.fetchUserInfo()
  }

  const grassResponse = async () => {
    return await ExerciseLogService.fetchExerciseGrass()
  }
  const followResponse = async () => {
    return await FollowService.fetchFollowRelations()
  }

  userInfo.value = await userResponse()
  followInfo.value = await followResponse()
  grassInfo.value = await grassResponse()
})
</script>

<template>
  <main>
    <slot />
  </main>
</template>
