<script setup lang="ts">
const data = [
  {
    month: 12,
    calendar: [
      [0, 0, 0, 0, 0, 0, 0],
      [0, 0, 0, 0, 0, 0, 0],
      [0, 0, 0, 0, 0, 0, 0],
      [0, 0, 0, 0, 0, 0, 0],
      [0, 0, 0, 0, 0, 0, 0],
    ],
  },
  {
    month: 1,
    calendar: [
      [0, 0, 0, 0, 0, 0, 0],
      [0, 0, 0, 2, 0, 0, 0],
      [0, 0, 0, 0, 1, 0, 0],
      [0, 0, 0, 0, 0, 0, 0],
    ],
  },
  {
    month: 2,
    calendar: [
      [0, 0, 0, 0, 0, 0, 0],
      [0, 0, 0, 2, 0, 0, 0],
      [0, 0, 0, 0, 1, 0, 0],
      [0, 0, 0, 0, 0, 0, 0],
      [0, 0, 0, 0, 0, 0, 0],
    ],
  },
  {
    month: 3,
    calendar: [
      [0, 0, 0, 0, 0, 0, 0],
      [0, 0, 0, 2, 0, 0, 0],
      [0, 0, 0, 0, 1, 0, 0],
      [0, 0, 0, 0, 0, 0, 0],
    ],
  },
  {
    month: 4,
    calendar: [
      [0, 0, 0, 0, 0, 0, 0],
      [0, 0, 0, 2, 0, 0, 0],
      [0, 0, 0, 0, 1, 0, 0],
      [0, 0, 0, 0, 0, 0, 0],
    ],
  },
  {
    month: 5,
    calendar: [
      [0, 0, 0, 0, 0, 0, 0],
      [0, 0, 0, 2, 0, 0, 0],
      [0, 0, 0, 0, 1, 0, 0],
      [0, 0, 0, 0, 0, 0, 0],
    ],
  },
  {
    month: 6,
    calendar: [
      [0, 0, 0, 0, 0, 0, 0],
      [0, 0, 0, 2, 0, 0, 0],
      [0, 0, 0, 0, 1, 0, 0],
      [0, 0, 0, 0, 0, 0, 0],
    ],
  },
  {
    month: 7,
    calendar: [
      [0, 0, 0, 0, 0, 0, 0],
      [0, 0, 0, 2, 0, 0, 0],
      [0, 0, 0, 0, 1, 0, 0],
      [0, 0, 0, 0, 0, 0, 0],
    ],
  },
  {
    month: 8,
    calendar: [
      [0, 0, 0, 0, 0, 0, 0],
      [0, 0, 0, 2, 0, 0, 0],
      [0, 0, 0, 0, 1, 0, 0],
      [0, 0, 0, 0, 0, 0, 0],
    ],
  },
  {
    month: 9,
    calendar: [
      [0, 0, 0, 0, 0, 0, 0],
      [0, 0, 0, 2, 0, 0, 0],
      [0, 0, 0, 0, 1, 0, 0],
      [0, 0, 0, 0, 0, 0, 0],
    ],
  },
  {
    month: 10,
    calendar: [
      [0, 0, 0, 0, 0, 0, 0],
      [0, 0, 0, 2, 0, 0, 0],
      [0, 0, 0, 0, 1, 0, 0],
      [0, 0, 0, 0, 0, 0, 0],
    ],
  },
  {
    month: 11,
    calendar: [
      [0, 0, 0, 0, 0, 0, 0],
      [0, 0, 0, 2, 0, 0, 0],
      [0, 0, 0, 0, 1, 0, 0],
      [0, 0, 0, 0, 0, 0, 0],
    ],
  },
]
</script>

<template>
  <div class="flex justify-center border">
    <div class="grid grid-flow-col auto-cols-max gap-1">
      <div v-for="(month, monthIdx) in data" :key="monthIdx" class="flex flex-col items-center">
        <div :class="`grid gap-1`"  :style="{ gridTemplateColumns: `repeat(${month.calendar.length}, minmax(0, 1fr))` }">
          <div v-for="(week, weekIdx) in month.calendar" :key="weekIdx" class="flex flex-col gap-1">
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
          </div>
        </div>
        <h3 class="text-xs text-gray-400 mb-2">{{ month.month }}</h3>
      </div>
    </div>
  </div>
</template>
