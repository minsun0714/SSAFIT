<script setup lang="ts">
import { inject, Ref } from 'vue'
import MyProfileFollow from './MyProfileFollow.vue'
import { MemberInfoResponse } from '@/api/interfaces/response'
import FollowButton from './FollowButton.vue'
import { useRoute } from 'vue-router'
import MemberApiFacade from '@/api/apiFacade/MemberApiFacade'

const route = useRoute()
const memberId = route?.params?.memberId
const { data: myInfo } = MemberApiFacade.useFetchUserInfo()

const data = inject<Ref<MemberInfoResponse | undefined>>('userInfo')
</script>

<template>
  <div class="flex flex-row w-full border justify-center">
    <div class="p-12">
      <img
        :src="'' + data?.profileImg"
        alt="프로필 사진"
        class="border border-gray-200 min-w-32 min-h-32 rounded-full border"
      />
    </div>
    <div class="flex flex-col justify-center gap-y-6">
      <div class="flex flex-row justify-center items-center">
        <h1 class="w-full">
          <p class="font-bold text-xl">{{ data?.name }}</p>
          <p class="text-sm text-gray-500"># {{ data?.nickname }}</p>
        </h1>
        <MyProfileFollow />
      </div>
      <div v-if="memberId && memberId !== myInfo.memberId">
        <FollowButton />
      </div>
    </div>
  </div>
</template>
