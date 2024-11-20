<script setup lang="ts">
import { ref } from 'vue'
import { Dialog, DialogContent, DialogTrigger } from '../../components/ui/dialog'

import BlueButton from '../common/BlueButton.vue'
import TimerModalExerciseTypeTable from './TimerModalExerciseTypeSearch.vue'
import TimerModalTimer from './TimerModalTimer.vue'

// 현재 표시할 컴포넌트를 상태로 관리
const currentStep = ref<number>(0)
</script>

<template>
  <div>
    <Dialog class="flex flex-col">
      <DialogTrigger class="border w-[300px] rounded-sm flex justify-center my-10">
        <BlueButton text="운동하기" />
      </DialogTrigger>
      <DialogContent class="flex flex-row flex justify-center items-center pt-6">
        <!-- currentStep에 따라 렌더링할 컴포넌트 전환 -->
        <template v-if="currentStep === 0">
          <div class="">
            <TimerModalExerciseTypeTable />
            <div class="py-3 w-full flex justify-end">
              <BlueButton text="다음" @click="currentStep += 1" />
            </div>
          </div>
        </template>
        <template v-else>
          <TimerModalTimer />
          <BlueButton text="이전" @click="currentStep -= 1" />
        </template>
      </DialogContent>
    </Dialog>
  </div>
</template>
