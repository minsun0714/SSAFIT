<template>
  <div class="relative w-full">
    <img
      src="@/assets/Home/1.jpg"
      alt="Exercise background"
      class="w-full h-auto object-cover object-center"
    />

    <div
      class="absolute inset-0 flex flex-col items-center justify-center text-center"
      style="bottom: 50%;"
    >
      <div class="mb-4 text-black">
        <p class="text-5xl font-semibold">Elevate Your Fitness</p>
        <br>
        <p class="text-2xl font-semibold">Capture Your Workout</p>
        <p class="text-2xl font-semibold">With Music in Every Move</p>
        <br>
      </div>

      <button
    class="bg-[#0001c8] text-white hover:text-[#0001c8] hover:bg-white font-bold py-2 px-6 rounded-lg shadow-lg transition-colors duration-300"
    @click="navigateToExercise"
  >
    Start Moving
  </button>
    </div>
  </div>
</template>

<script setup lang="ts">
import { useRouter } from 'vue-router';
import { Routes } from '../../utils/enum';

const router = useRouter();

const navigateToExercise = () => {
  router.push({
    name: Routes.EXERCISE,
  });
};
</script>

<style scoped>
</style>
