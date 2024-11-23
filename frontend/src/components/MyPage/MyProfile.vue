<script setup lang="ts">
import MemberApiFacade from '@/api/apiFacade/MemberApiFacade'
import MyProfileFollow from './MyProfileFollow.vue'
const { data } = MemberApiFacade.useFetchUserInfo()

</script>

<template>
  <div class="flex flex-row w-full border justify-center">
    <div class="p-12">
      <img
        :src="'' + data?.profileImg"
        alt="프로필 사진"
        class="border min-w-28 min-h-28 rounded-full"
      />
    </div>
    <div class="flex flex-col">
      <div class="h-full flex flex-row justify-center items-center">
        <h1 class="w-full">
          <p class="font-bold text-xl">{{ data.name }}</p>
          <p class="text-sm text-gray-500"># {{ data.nickname }}</p>
        </h1>
        <MyProfileFollow />
      </div>
      <p class="flex justify-center items-center h-full w-96 text-xs text-gray-400">
        {{ data.introduceText }}
      </p>
    </div>
  </div>
</template>
