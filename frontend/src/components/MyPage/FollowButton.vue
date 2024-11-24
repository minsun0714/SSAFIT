<script setup lang="ts">
import FollowApiFacade from '@/api/apiFacade/FollowAPIFacade'
import { useRoute } from 'vue-router'
import Button from '../ui/button/Button.vue'
import OtherMemberApiFacade from '@/api/apiFacade/OtherMemberApiFacade'
import MemberApiFacade from '@/api/apiFacade/MemberApiFacade'
import { computed } from 'vue'

const route = useRoute()
const memberId = route?.params?.memberId

const { data: memberInfo } = MemberApiFacade.useFetchUserInfo()
const { data: followData, refetch } = OtherMemberApiFacade.useFetchFollowRelations()

const myId = computed(() => memberInfo?.value?.memberId || null);

const isFollowing = computed(() => {
  if (!followData?.value?.followerList || !myId.value) return false; // 초기값 설정
  return followData.value.followerList.some((follower) => follower?.memberId === myId.value);
});


console.log('isFollowing' + isFollowing.value)
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
  {{
    followData?.followingList.forEach((f) => console.log(f.name)) +
    ' ' +
    followData?.followerList.forEach((f) => console.log(f.name))
    + ' ' + isFollowing
  }}
  <Button v-if="isFollowing === true" @click="handleUnfollow">unfollow</Button>
  <Button v-else-if="isFollowing === false" @click="handleFollow">follow</Button>
</template>
