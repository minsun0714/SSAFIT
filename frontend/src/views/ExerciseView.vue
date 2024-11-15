<script setup lang="ts">
import ExerciseCalendar from '@/components/Exercise/ExerciseCalendar.vue'
import ExerciseGrassRecord from '../components/Exercise/ExerciseGrassRecord.vue'
import MonthlyInfoCardList from '../components/Exercise/MonthlyInfoCardList.vue'
import TimerModal from '../components/Exercise/TimerModal.vue'
</script>

<template>
  <main>
    <MonthlyInfoCardList />
    <ExerciseGrassRecord />
    <TimerModal :text="'운동하기'" />
    <ExerciseCalendar />
  </main>
</template>
