<script setup lang="ts">
import {
  Dialog,
  DialogContent,
  DialogDescription,
  DialogFooter,
  DialogHeader,
  DialogTitle,
  DialogTrigger,
} from '../../components/ui/dialog'

import { pad } from '../../utils/helperFunction'
import BlueButton from '../common/BlueButton.vue'
import { ref } from 'vue'
import { Timer } from '../../utils/helperClass'
import TimerModalSaveButton from './TimerModalSaveButton.vue'

const time = ref(0)
const isRunning = ref(false)
const timer = new Timer(time, isRunning)
</script>

<template>
  <div>
    <Dialog>
      <DialogTrigger class="border w-[300px] rounded-sm flex justify-center my-10"
        ><BlueButton text="운동하기"
      /></DialogTrigger>
      <DialogContent>
        <DialogHeader>
          <DialogTitle>오늘의 운동</DialogTitle>
          <DialogDescription>
            Make changes to your profile here. Click save when you're done.
          </DialogDescription>
        </DialogHeader>
        <div class="flex flex-col justify-center items-center">
          <div class="font-bold text-4xl">
            <span>{{ pad(Math.floor(time / 3600)) }}</span>
            :
            <span>{{ pad(Math.floor((time % 3600) / 60)) }}</span>
            :
            <span>{{ pad(time % 60) }}</span>
          </div>
          <div class="w-full flex flex-row justify-evenly p-6">
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
        <DialogFooter><TimerModalSaveButton /></DialogFooter>
      </DialogContent>
    </Dialog>
  </div>
</template>
