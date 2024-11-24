<script setup lang="ts">
import { ref, provide, onMounted, type Ref } from 'vue'
import type {
  FollowInfoResponse,
  MemberInfoResponse,
  ExerciseGrass,
} from '@/api/interfaces/response'
import OtherMemberService from '@/api/services/OtherMemberService'
import { useRoute } from 'vue-router'

const userInfo: Ref<MemberInfoResponse | undefined> = ref(undefined)
const followInfo: Ref<FollowInfoResponse | undefined> = ref(undefined)
const grassInfo: Ref<ExerciseGrass[] | undefined> = ref(undefined)

provide('userInfo', userInfo)
provide('followInfo', followInfo)
provide('grassInfo', grassInfo)

const route = useRoute()

onMounted(async () => {
  const memberId = route.params.memberId

  const userResponse = async () => {
    return await OtherMemberService.fetchUserInfo(memberId as string)
  }

  const grassResponse = async () => {
    return await OtherMemberService.fetchExerciseGrass(memberId as string)
  }
  const followResponse = async () => {
    return await OtherMemberService.fetchFollowRelations(memberId as string)
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
