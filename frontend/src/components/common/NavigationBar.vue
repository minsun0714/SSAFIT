<script setup lang="ts">
import { RouterLink } from 'vue-router'
import '@/assets/index.css'
import { profileImg } from '../../assets'
import { Routes } from '../../utils/enum'
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
      <div class="flex flex-row gap-x-1">
        <img :src="profileImg" alt="auth icon" />
        <RouterLink :to="{ name: Routes.LOGIN }">Login</RouterLink>
        /
        <RouterLink :to="{ name: Routes.SIGN_UP }">Register</RouterLink>
      </div>
    </nav>
  </header>
</template>
