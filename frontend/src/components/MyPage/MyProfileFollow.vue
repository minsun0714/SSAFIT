<script setup lang="ts">
import {
  Dialog,
  DialogContent,
  DialogFooter,
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
  () => route.fullPath,
  () => {
    location.reload()
  },
)

const follows = inject<Ref<FollowInfoResponse | undefined>>('followInfo')
console.log(follows?.value)
</script>

<template>
  <Dialog>
    <DialogTrigger as-child>
      <h2 class="w-40 text-center">
        팔로워
        <p class="font-bold">{{ follows?.followerList.length }}</p>
      </h2>
    </DialogTrigger>
    <DialogContent class="sm:max-w-[425px]">
      <DialogHeader>
        <DialogTitle>팔로워 목록</DialogTitle>
      </DialogHeader>
      <ul>
        <li
          v-for="follower in follows?.followerList"
          :key="follower?.memberId"
          class="flex border rounded-lg p-4 cursor-pointer"
          @click="
            router.push({ name: 'others-liked-videos', params: { memberId: follower.memberId } })
          "
        >
          <div>
            <img :src="follower?.profileImg" alt="" />
          </div>
          <div>{{ follower?.name }}</div>
          <div>{{ follower?.nickname }}</div>
        </li>
      </ul>
      <DialogFooter>
        <Button type="submit"> Save changes </Button>
      </DialogFooter>
    </DialogContent>
  </Dialog>
  <Dialog>
    <DialogTrigger as-child>
      <h2 class="w-40 text-center">
        팔로잉
        <p class="font-bold">{{ follows?.followingList.length }}</p>
      </h2>
    </DialogTrigger>
    <DialogContent class="sm:max-w-[425px]">
      <DialogHeader>
        <DialogTitle>팔로워 목록</DialogTitle>
      </DialogHeader>
      <ul>
        <li
          v-for="following in follows?.followingList"
          :key="following?.memberId"
          class="flex border rounded-lg p-4 cursor-pointer"
          @click="
            router.push({ name: 'others-liked-videos', params: { memberId: following.memberId } })
          "
        >
          <div>
            <img :src="following?.profileImg" alt="" />
          </div>
          <div>{{ following?.name }}</div>
          <div>{{ following?.nickname }}</div>
        </li>
      </ul>
      <DialogFooter>
        <Button type="submit"> Save changes </Button>
      </DialogFooter>
    </DialogContent>
  </Dialog>
</template>
