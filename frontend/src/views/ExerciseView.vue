<script setup lang="ts">
import BlueButton from '../components/common/BlueButton.vue'
import ExerciseGrassRecord from '../components/Exercise/ExerciseGrassRecord.vue'
import MonthlyInfoCardList from '../components/Exercise/MonthlyInfoCardList.vue'
</script>

<template>
  <main>
    <MonthlyInfoCardList />
    <ExerciseGrassRecord />
    <BlueButton :text="'운동하기'" />
  </main>
</template>
