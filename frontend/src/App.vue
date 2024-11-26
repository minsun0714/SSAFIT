<script setup lang="ts">
import { RouterView } from 'vue-router'
import NavigationBar from './components/common/NavigationBar.vue'
import { VueQueryDevtools } from '@tanstack/vue-query-devtools'
import Footer from "./components/common/FooterBar.vue";
</script>

<template>
  <NavigationBar />
  <RouterView />
  <VueQueryDevtools :initial-is-open="false" />
  <Footer />
</template>
