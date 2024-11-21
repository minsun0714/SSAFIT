<script setup lang="ts">
import {
  DialogDescription,
  DialogFooter,
  DialogHeader,
  DialogTitle,
} from '../../components/ui/dialog'

import { pad } from '../../utils/helperFunction'
import { ref } from 'vue'
import { Timer } from '../../utils/helperClass'
import TimerModalSaveButton from './TimerModalSaveButton.vue'
import { useExerciseStore } from '@/stores/exerciseType'
import ExerciseLogApiFacade from '@/api/apiFacade/ExerciseLogApiFacade'

const { exercise } = useExerciseStore()

const time = ref(0)
const isRunning = ref(false)
const timer = new Timer(time, isRunning)

const { mutate } = ExerciseLogApiFacade.useCreateExerciseLog()

const handleClickSaveTime = () => {
  if (time.value === 0) return
  mutate({ exerciseDate: new Date(), exerciseType: exercise || '', exerciseTime: time.value })

  Timer.confirmTimerReset()
  time.value = 0
}
</script>

<template>
  <div class="p-8 rounded-md w-full flex flex-col justify-center items-center">
    <DialogHeader class="flex gap-y-6 p-8 w-full border rounded-md">
      <DialogDescription class="text-xs"> 현재 선택한 운동 </DialogDescription>
      <DialogTitle class="text-xl">{{ exercise }}</DialogTitle>
    </DialogHeader>
    <div class="flex flex-col justify-center items-center">
      <div class="font-bold text-4xl">
        <span>{{ pad(Math.floor(time / 3600)) }}</span>
        :
        <span>{{ pad(Math.floor((time % 3600) / 60)) }}</span>
        :
        <span>{{ pad(time % 60) }}</span>
      </div>
      <div class="w-[250px] flex flex-row justify-between p-6">
        <div
          :class="[
            'bg-blue-400 flex justify-center items-center gap-1 border rounded-full w-16 h-16 cursor-pointer',
            isRunning ? 'pause-icon' : 'play-icon',
          ]"
          @click="timer.toggle"
        >
          <span v-if="isRunning" class="bg-white h-1/3 text-transparent rounded-sm">_</span>
          <span v-if="isRunning" class="bg-white h-1/3 text-transparent rounded-sm">_</span>
          <span
            v-else
            class="ml-1 w-0 h-0 border-t-[10px] border-t-transparent border-b-[10px] border-b-transparent border-l-[20px] border-l-white"
          ></span>
        </div>
        <div
          class="bg-blue-400 flex justify-center items-center gap-1 border rounded-full w-16 h-16 cursor-pointer"
          @click="timer.reset"
        >
          <span class="bg-white h-1/3 w-1/3 text-transparent rounded-sm"></span>
        </div>
      </div>
    </div>
    <DialogFooter><TimerModalSaveButton @click="handleClickSaveTime" /></DialogFooter>
  </div>
</template>
