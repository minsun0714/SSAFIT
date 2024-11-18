<script setup lang="ts">
import { RouterLink, useRouter } from 'vue-router'
import '@/assets/index.css'
import { profileImg } from '../../assets'
import { Routes } from '../../utils/enum'
import MemberApiFacade from '@/api/apiFacade/MemberApiFacade'

const { data: memberInfo, isSuccess, refetch } = MemberApiFacade.useFetchUserInfo()
const router = useRouter()
const logout = () => {
  localStorage.removeItem('token')
  refetch()
  router.push('/')
  alert('로그아웃 되었습니다.')
}
</script>

<template>
  <header class="border p-10 flex flex-row justify-between items-center">
    <nav class="w-full flex flex-row justify-between items-center h-98 text-xs font-bold">
      <RouterLink :to="{ name: Routes.HOME }">SSAFIT</RouterLink>
      <div class="flex flex-row gap-x-4">
        <RouterLink :to="{ name: Routes.VIDEOS }">운동영상</RouterLink>
        <RouterLink :to="{ name: Routes.EXERCISE }">운동기록</RouterLink>
        <RouterLink :to="{ name: Routes.CREATE_VIDEO }">영상등록</RouterLink>
      </div>
      <div v-if="isSuccess" class="flex flex-row gap-x-2 text-blue-400">
        <img :src="profileImg" alt="auth icon" />
        <span>{{ memberInfo?.nickname }}님</span>
        <RouterLink :to="{ name: Routes.MY_PAGE }">마이페이지</RouterLink>
        <button @click="logout">로그아웃</button>
      </div>
      <div class="flex flex-row gap-x-1 text-blue-400" v-else>
        <img :src="profileImg" alt="auth icon" />
        <RouterLink :to="{ name: Routes.LOGIN }">Login</RouterLink>
        /
        <RouterLink :to="{ name: Routes.SIGN_UP }">Register</RouterLink>
      </div>
    </nav>
  </header>
</template>
