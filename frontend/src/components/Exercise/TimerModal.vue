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

const time = ref(0) // 타이머 초기값
let timer: ReturnType<typeof setInterval> | null = null
const isRunning = ref(false) // 타이머 상태 (실행 중인지 여부)

// 재생/일시 정지 기능
const toggleTimer = () => {
  if (isRunning.value) {
    pauseTimer() // 일시정지 함수 호출
  } else {
    startTimer()
  }
}

// 타이머 시작
const startTimer = () => {
  if (!timer) {
    isRunning.value = true
    timer = setInterval(() => {
      time.value += 1
    }, 1000) // 1초마다 증가
  }
}

// 타이머 일시정지 (시간 초기화 없이 멈춤)
const pauseTimer = () => {
  if (timer) {
    clearInterval(timer)
    timer = null
    isRunning.value = false
  }
}

// 타이머 정지 및 초기화
const resetTimer = () => {
  pauseTimer() // 타이머 멈춤
  time.value = 0 // 시간 초기화
}
</script>

<template>
  <div>
    <Dialog>
      <DialogTrigger><BlueButton text="운동하기" /></DialogTrigger>
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
            <!-- 일시정지 / 재생 버튼 -->
            <div
              :class="[
                'bg-blue-400 flex justify-center items-center gap-1 border rounded-full w-16 h-16 cursor-pointer',
                isRunning ? 'pause-icon' : 'play-icon',
              ]"
              @click="toggleTimer"
            >
              <span v-if="isRunning" class="bg-white h-1/3 text-transparent rounded-sm">_</span>
              <span v-if="isRunning" class="bg-white h-1/3 text-transparent rounded-sm">_</span>
              <span
                v-else
                class="ml-1 w-0 h-0 border-t-[10px] border-t-transparent border-b-[10px] border-b-transparent border-l-[20px] border-l-white"
              ></span>
            </div>
            <!-- 정지 버튼 -->
            <div
              class="bg-blue-400 flex justify-center items-center gap-1 border rounded-full w-16 h-16 cursor-pointer"
              @click="resetTimer"
            >
              <span class="bg-white h-1/3 w-1/3 text-transparent rounded-sm"></span>
            </div>
          </div>
        </div>
        <DialogFooter><BlueButton text="저장" /></DialogFooter>
      </DialogContent>
    </Dialog>
  </div>
</template>
