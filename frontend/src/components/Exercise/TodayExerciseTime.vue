<script setup lang="ts">
import ExerciseLogApiFacade from '@/api/apiFacade/ExerciseLogApiFacade'
import TimerModal from './TimerModal.vue'
import { getStringDateFromDate, getTimeFromSecond } from '@/utils/helperFunction';

const { data } = ExerciseLogApiFacade.useFetchExerciseLogsByDate(getStringDateFromDate(new Date()))
console.log(getStringDateFromDate(new Date()))
</script>

<template>
  <div class="w-full flex flex-col justify-center items-center text-white text-5xl p-10 font-bold">
    <p>{{ getTimeFromSecond(data?.dailyTotalExerciseTime || 0) }}</p>
    <TimerModal :text="'운동하기'" />
  </div>
</template>
