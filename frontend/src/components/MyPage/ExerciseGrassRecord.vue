<script setup lang="ts">
import { ExerciseGrass } from '@/api/interfaces/response'
import { HoverCard, HoverCardContent, HoverCardTrigger } from '@/components/ui/hover-card'
import { getTimeFromSecond } from '@/utils/helperFunction'
import { inject, Ref } from 'vue'

const data = inject<Ref<ExerciseGrass[] | undefined>>('grassInfo')

const today = new Date()
const oneYearAgo = new Date(today)
oneYearAgo.setFullYear(today.getFullYear() - 1)

const dayOneYearAgo = oneYearAgo.getDay()
</script>

<template>
  <section class="flex flex-col justify-center items-center p-4 border">
    <div class="grid grid-rows-7 grid-flow-col gap-1 w-[1000px] border rounded-xl p-6">
      <div v-for="emptySpace in dayOneYearAgo" :key="'empty-' + emptySpace" class="w-3 h-3"></div>
      <div v-for="item in data" :key="item?.date" class="flex items-center">
        <HoverCard>
          <HoverCardTrigger as-child>
            <span
              class="w-3 h-3 rounded-xs border"
              :class="{
                'bg-blue-100': item?.level === 1,
                'bg-blue-200': item?.level === 2,
                'bg-blue-300': item?.level === 3,
                'bg-blue-400': item?.level === 4,
              }"
            ></span>
          </HoverCardTrigger>
          <HoverCardContent class="w-55">
            <div class="flex justify-between items-center space-x-4 text-gray-600">
              <span>{{ item?.date }}</span>
              <span>{{ getTimeFromSecond(item?.exerciseTime) }}</span>
            </div>
          </HoverCardContent>
        </HoverCard>
      </div>
    </div>
    <div class="w-[1000px] flex justify-end items-center gap-1 text-gray-500">
      <span>Less</span>
      <span class="w-3 h-3 rounded-sm bg-blue-100 border"></span>
      <span class="w-3 h-3 rounded-sm bg-blue-200 border"></span>
      <span class="w-3 h-3 rounded-sm bg-blue-300 border"></span>
      <span class="w-3 h-3 rounded-sm bg-blue-400 border"></span>
      <span>More</span>
    </div>
  </section>
</template>
