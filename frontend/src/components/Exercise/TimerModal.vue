<script setup lang="ts">
import { ref } from 'vue'
import { Dialog, DialogContent, DialogTrigger } from '../../components/ui/dialog'

import BlueButton from '../common/BlueButton.vue'
import TimerModalExerciseTypeTable from './TimerModalExerciseTypeSearch.vue'
import TimerModalTimer from './TimerModalTimer.vue'
import { Button } from 'ant-design-vue'

const currentStep = ref<number>(0)
</script>

<template>
  <div>
    <Dialog class="flex flex-col">
      <DialogTrigger class="border w-[300px] rounded-sm flex justify-center my-10">
        <BlueButton text="운동하기" />
      </DialogTrigger>
      <DialogContent class="flex flex-row flex justify-center items-center pt-6">
        <template v-if="currentStep === 0">
          <div class="">
            <TimerModalExerciseTypeTable />
            <div class="py-3 w-full flex justify-end">
              <Button text="다음" @click="currentStep += 1">다음</Button>
            </div>
          </div>
        </template>
        <template v-else>
          <div>
            <TimerModalTimer />
            <div class="py-3 w-full flex justify-start">
              <Button text="이전" @click="currentStep -= 1">이전</Button>
            </div>
          </div>
        </template>
      </DialogContent>
    </Dialog>
  </div>
</template>
