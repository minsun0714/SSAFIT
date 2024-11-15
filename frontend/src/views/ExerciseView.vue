<script setup lang="ts">
import ExerciseGrassRecord from '../components/Exercise/ExerciseGrassRecord.vue'
import MonthlyInfoCardList from '../components/Exercise/MonthlyInfoCardList.vue'
import TimerModal from '../components/Exercise/TimerModal.vue'
</script>

<template>
  <main>
    <MonthlyInfoCardList />
    <ExerciseGrassRecord />
    <TimerModal :text="'운동하기'" />
  </main>
</template>
