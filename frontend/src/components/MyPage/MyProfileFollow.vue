<script setup lang="ts">
import FollowApiFacade from '@/api/apiFacade/FollowAPIFacade'

const { data } = FollowApiFacade.useFetchFollowRelations()
</script>

<template>
  <h2 class="w-40 text-center">
    팔로워
    <p class="font-bold">{{ data?.followerList.length }}</p>
  </h2>
  <h2 class="w-40 text-center">
    팔로잉
    <p class="font-bold">{{ data?.followingList.length }}</p>
  </h2>
</template>
