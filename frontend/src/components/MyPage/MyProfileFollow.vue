<script setup lang="ts">
import {
  Dialog,
  DialogContent,
  DialogHeader,
  DialogTitle,
  DialogTrigger,
} from '@/components/ui/dialog'
import { FollowInfoResponse } from '@/api/interfaces/response'
import { inject, Ref, watch } from 'vue'
import { useRoute, useRouter } from 'vue-router'

const route = useRoute()
const router = useRouter()

watch(
  () => route.params.memberId,
  () => {
    location.reload()
  },
)

const follows = inject<Ref<FollowInfoResponse | undefined>>('followInfo')
</script>

<template>
  <Dialog>
    <DialogTrigger as-child>
      <h2 class="w-40 text-center cursor-pointer">
        팔로워
        <p class="font-bold">{{ follows?.followerList.length }}</p>
      </h2>
    </DialogTrigger>
    <DialogContent class="sm:max-w-[425px]">
      <DialogHeader>
        <DialogTitle>팔로워 목록</DialogTitle>
      </DialogHeader>
      <ul class="flex flex-col gap-y-4 border p-4 rounded-lg">
        <li
          v-for="follower in follows?.followerList"
          :key="follower?.memberId"
          class="flex items-center gap-4 border rounded-lg p-4 cursor-pointer"
          @click="
            router.push({ name: 'others-liked-videos', params: { memberId: follower.memberId } })
          "
        >
          <span class="flex border rounded-full w-16 h-16">
            <img
              :src="follower?.profileImg"
              alt=""
              class="w-full h-full rounded-full object-cover"
            />
          </span>
          <div>{{ follower?.name }}</div>
          <div>#{{ follower?.nickname }}</div>
        </li>
      </ul>
    </DialogContent>
  </Dialog>
  <Dialog>
    <DialogTrigger as-child>
      <h2 class="w-40 text-center cursor-pointer">
        팔로잉
        <p class="font-bold">{{ follows?.followingList.length }}</p>
      </h2>
    </DialogTrigger>
    <DialogContent class="sm:max-w-[425px]">
      <DialogHeader>
        <DialogTitle>팔로워 목록</DialogTitle>
      </DialogHeader>
      <ul class="flex flex-col gap-y-4 border p-4 rounded-lg">
        <li
          v-for="following in follows?.followingList"
          :key="following?.memberId"
          class="flex border rounded-lg p-4 cursor-pointer items-center gap-4"
          @click="
            router.push({ name: 'others-liked-videos', params: { memberId: following.memberId } })
          "
        >
          <span class="flex border rounded-full w-16 h-16">
            <img
              :src="following?.profileImg"
              alt=""
              class="w-full h-full rounded-full object-cover"
            />
          </span>
          <div>{{ following?.name }}</div>
          <div>#{{ following?.nickname }}</div>
        </li>
      </ul>
    </DialogContent>
  </Dialog>
</template>
