<script setup lang="ts">
import {
  Dialog,
  DialogContent,
  DialogDescription,
  DialogFooter,
  DialogHeader,
  DialogTitle,
  DialogTrigger,
} from '@/components/ui/dialog'
import FollowApiFacade from '@/api/apiFacade/FollowAPIFacade'
import { useRoute } from 'vue-router'
import Button from '../ui/button/Button.vue'
import OtherMemberApiFacade from '@/api/apiFacade/OtherMemberApiFacade'
import MemberApiFacade from '@/api/apiFacade/MemberApiFacade'
import { computed, inject, Ref } from 'vue'
import { SlArrowDown } from 'vue3-icons/sl'
import { MemberInfoResponse } from '@/api/interfaces/response'

const route = useRoute()
const memberId = route?.params?.memberId

const { data: memberInfo } = MemberApiFacade.useFetchUserInfo()
const { data: followData, refetch } = OtherMemberApiFacade.useFetchFollowRelations()

const userInfo = inject<Ref<MemberInfoResponse | undefined>>('userInfo')

const myId = computed(() => memberInfo?.value?.memberId || null)

const isFollowing = computed(() => {
  if (!followData?.value?.followerList || !myId.value) return false // 초기값 설정
  return followData.value.followerList.some((follower) => follower?.memberId === myId.value)
})

const { mutate: follow } = FollowApiFacade.useFollow()
const { mutate: unfollow } = FollowApiFacade.useUnfollow()

const handleFollow = () => {
  follow(memberId as string)
  refetch()
  console.log(isFollowing.value)
}

const handleUnfollow = () => {
  unfollow(memberId as string)
  refetch()
  console.log(isFollowing)
}
</script>

<template>
  <Dialog v-if="isFollowing === true">
    <DialogTrigger as-child>
      <Button>팔로잉 <SlArrowDown /> </Button>
    </DialogTrigger>
    <DialogContent class="sm:max-w-[425px]">
      <DialogHeader class="flex flex-col justify-center w-full">
        <DialogTitle class="flex flex-col justify-center items-center p-6"
          ><img :src="userInfo?.profileImg" class="object-cover h-20" /><span class="p-3">
            {{ userInfo?.name }}
          </span></DialogTitle
        >
        <DialogDescription>
          <div @click="handleUnfollow"><button>팔로우 취소</button></div>
        </DialogDescription>
      </DialogHeader>
    </DialogContent>
  </Dialog>
  <Button v-else-if="isFollowing === false" @click="handleFollow">follow</Button>
</template>
