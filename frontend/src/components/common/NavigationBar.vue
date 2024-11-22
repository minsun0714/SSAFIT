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
  <header class="p-10 flex flex-row justify-between items-center bg-[#121212]">
    <nav class="w-full flex flex-row justify-between items-center h-98 font-bold">
      <RouterLink :to="{ name: Routes.HOME }" class="logo-text">SSAFIT</RouterLink>
      <div class="flex flex-row gap-x-6">
        <RouterLink :to="{ name: Routes.VIDEOS }" class="nav-link">Video</RouterLink>
        <RouterLink :to="{ name: Routes.EXERCISE }" class="nav-link">Record</RouterLink>
        <RouterLink :to="{ name: Routes.CREATE_VIDEO }" class="nav-link">Regist</RouterLink>
        <a class="nav-link">About</a>
      </div>
      <div v-if="isSuccess" class="flex flex-row gap-x-2 text-blue-400">
        <img :src="profileImg" alt="auth icon" />
        <span>{{ memberInfo?.nickname }}님</span>
        <RouterLink :to="{ name: Routes.MYPAGE_LIKED_VIDEOS }">마이페이지</RouterLink>
        <button @click="logout">로그아웃</button>
      </div>
      <div class="flex flex-row gap-x-1 text-[#E50914]" v-else>
        <!-- <img :src="profileImg" alt="auth icon" /> -->
        <RouterLink :to="{ name: Routes.LOGIN }">Login</RouterLink>
        /
        <RouterLink :to="{ name: Routes.SIGN_UP }">Register</RouterLink>
      </div>
    </nav>
  </header>
</template>

<style scoped>
.logo-text {
  font-size: 2rem;
  font-family: Georgia, 'Times New Roman', Times, serif;
  font-weight: bold;
  color: #E50914;
  text-transform: uppercase;
}

.nav-link {
  font-size: 1.1rem;
  padding: 0.5rem;
  color: white;
  text-transform: capitalize;
  transition: color 0.3s ease;
}

.nav-link:hover {
  color: #E50914;
}

</style>
