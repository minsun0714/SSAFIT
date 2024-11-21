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
        <p class="text-lg font-semibold">Capture Your Workout</p>
        <p class="text-lg font-semibold">With Music in Every Move</p>
      </div>

      <button
        class="bg-black hover:bg-blue-400 hover:text-white text-white font-bold py-2 px-6 rounded-lg shadow-lg transition-colors duration-300"
      >
      Start Moving
      </button>
    </div>
  </div>
</template>

<script setup lang="ts">
</script>

<style scoped>
</style>
