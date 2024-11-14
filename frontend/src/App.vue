<script setup lang="ts">
import { RouterLink, RouterView } from 'vue-router'
import './assets/index.css'
import { profileImg } from './assets/index'
</script>

<template>
  <header class="border p-10 flex flex-row justify-between items-center">
    <nav class="w-full flex flex-row justify-between items-center h-98 text-xs font-bold">
      <RouterLink :to="{ name: 'home' }">SSAFIT</RouterLink>
      <div class="flex flex-row gap-x-4">
        <RouterLink :to="{ name: 'videos' }">운동영상</RouterLink>
        <RouterLink :to="{ name: 'exercise' }">운동기록</RouterLink>
        <RouterLink :to="{ name: 'createVideo' }">영상등록</RouterLink>
      </div>
      <div class="flex flex-row gap-x-1">
        <img :src="profileImg" alt="auth icon" />
        <RouterLink :to="{ name: 'login' }">Login</RouterLink>
        /
        <RouterLink :to="{ name: 'signup' }">Register</RouterLink>
      </div>
    </nav>
  </header>
  <RouterView />
</template>
