<script setup lang="ts">
import { RouterLink, RouterView } from 'vue-router'
import { profileImg } from './assets/index'
</script>

<template>
  <header>
    <div class="wrapper">
      <nav>
        <span>SSAFIT</span>
        <RouterLink :to="{ name: 'videos' }">운동영상</RouterLink>
        <RouterLink :to="{ name: 'exercise' }">운동기록</RouterLink>
        <RouterLink :to="{ name: 'createVideo' }">영상등록</RouterLink>
        <img :src="profileImg" alt="auth icon" />
        <span>Login</span>
        /
        <span>Register</span>
      </nav>
    </div>
  </header>
  <RouterView />
</template>
