<script setup lang="ts">
import { FollowInfoResponse } from '@/api/interfaces/response'
import { inject, Ref } from 'vue'

const follows = inject<Ref<FollowInfoResponse | undefined>>('followInfo')
console.log('follow' + follows)
</script>

<template>
  <h2 class="w-40 text-center">
    팔로워
    <p class="font-bold">{{ follows?.followerList.length }}</p>
  </h2>
  <h2 class="w-40 text-center">
    팔로잉
    <p class="font-bold">{{ follows?.followingList.length }}</p>
  </h2>
</template>
