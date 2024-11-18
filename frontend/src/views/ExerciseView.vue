<script setup lang="ts">
import ExerciseCalendar from '@/components/Exercise/ExerciseCalendar.vue'
import MonthlyInfoCardList from '../components/Exercise/MonthlyInfoCardList.vue'
import TimerModal from '../components/Exercise/TimerModal.vue'
</script>

<template>
  <main>
    <MonthlyInfoCardList />

    <ExerciseCalendar />
  </main>
</template>
