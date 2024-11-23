<script setup lang="ts">
import { ref } from 'vue'
import { Dialog, DialogContent, DialogTrigger } from '../../components/ui/dialog'

import BlueButton from '../common/BlueButton.vue'
import TimerModalExerciseTypeTable from './TimerModalExerciseTypeSearch.vue'
import TimerModalTimer from './TimerModalTimer.vue'
import { Button } from 'ant-design-vue'
import { useExerciseStore } from '@/stores/exerciseType'
import WeightInput from './WeightInput.vue'

const currentStep = ref<number>(0)

const exerciseStore = useExerciseStore()

const handleClickNext = () => {
  if (currentStep.value === 1 && !exerciseStore.exercise) {
    alert('운동을 선택해주세요')
    return
  }
  currentStep.value += 1
}

const handleClickPrevious = () => {
  currentStep.value -= 1
}
</script>

<template>
  <div>
    <Dialog class="flex flex-col">
      <DialogTrigger class="border w-[300px] rounded-sm flex justify-center my-10">
        <BlueButton text="운동하기" />
      </DialogTrigger>
      <DialogContent class="flex flex-row justify-center items-center pt-6">
        <template v-if="currentStep === 0">
          <div class="">
            <div class="py-3 w-full flex justify-end">
              <WeightInput :handle-click-next="handleClickNext" />
            </div>
          </div>
        </template>
        <template v-else-if="currentStep === 1">
          <div class="">
            <TimerModalExerciseTypeTable />
            <div class="py-3 w-full flex justify-end">
              <Button text="이전" @click="handleClickPrevious">이전</Button>
              <Button text="다음" @click="handleClickNext">다음</Button>
            </div>
          </div>
        </template>
        <template v-else>
          <div>
            <TimerModalTimer />
            <div class="py-3 w-full flex justify-start">
              <Button text="이전" @click="handleClickPrevious">이전</Button>
            </div>
          </div>
        </template>
      </DialogContent>
    </Dialog>
  </div>
</template>
