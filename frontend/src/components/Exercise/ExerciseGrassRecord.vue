<script setup lang="ts">
import ExerciseLogApiFacade from '@/api/apiFacade/ExerciseLogApiFacade'
import { HoverCard, HoverCardContent, HoverCardTrigger } from '@/components/ui/hover-card'
const { data } = ExerciseLogApiFacade.useFetchExerciseGrass()
console.log(data)
</script>

<template>
  <div class="flex justify-center border">
    {{ data }}
    <!-- <div class="grid grid-flow-col auto-cols-max gap-1">
      <div v-for="(month, monthIdx) in data" :key="monthIdx" class="flex flex-col items-center">
        <div
          :class="`grid gap-1`"
          :style="{ gridTemplateColumns: `repeat(${month.calendar.length}, minmax(0, 1fr))` }"
        >
          <div v-for="(week, weekIdx) in month.calendar" :key="weekIdx" class="flex flex-col gap-1">
            <HoverCard>
              <HoverCardTrigger>
                <div
                  v-for="(day, dayIdx) in week"
                  :key="dayIdx"
                  class="w-4 h-4 rounded-sm"
                  :class="{
                    'bg-gray-200': day === 0,
                    'bg-blue-100': day === 1,
                    'bg-blue-300': day === 2,
                    'bg-blue-500': day === 3,
                    'bg-blue-700': day === 4,
                  }"
                ></div>
              </HoverCardTrigger>
              <HoverCardContent>
                {{ month.month + '-' + weekIdx }}
              </HoverCardContent>
            </HoverCard>
          </div>
        </div>
        <h3 class="text-xs text-gray-400 mb-2">{{ month.month }}</h3>
      </div>
    </div> -->
  </div>
</template>
