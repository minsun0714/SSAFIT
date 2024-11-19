<script setup lang="ts">
import { Dialog, DialogContent, DialogTrigger } from '../../components/ui/dialog'

import BlueButton from '../common/BlueButton.vue'
import TimerModalExerciseTypeTable from './TimerModalExerciseTypeSearch.vue'
import TimerModalTimer from './TimerModalTimer.vue'

</script>

<template>
  <div>
    <Dialog>
      <DialogTrigger class="border w-[300px] rounded-sm flex justify-center my-10"
        ><BlueButton text="운동하기"
      /></DialogTrigger>
      <DialogContent class="flex flex-row">
        <TimerModalExerciseTypeTable />
        <TimerModalTimer />
      </DialogContent>
    </Dialog>
  </div>
</template>
