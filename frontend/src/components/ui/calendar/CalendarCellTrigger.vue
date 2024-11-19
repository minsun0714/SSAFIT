<script lang="ts" setup>
import ExerciseLogApiFacade from '@/api/apiFacade/ExerciseLogApiFacade'
import { buttonVariants } from '@/components/ui/button'
import { cn } from '@/lib/utils'
import { pad } from '@/utils/helperFunction'
import { CalendarCellTrigger, type CalendarCellTriggerProps, useForwardProps } from 'radix-vue'
import { computed, type HTMLAttributes } from 'vue'

const props = defineProps<CalendarCellTriggerProps & { class?: HTMLAttributes['class'] }>()

const delegatedProps = computed(() => {
  const { class: _, ...delegated } = props

  return delegated
})

const forwardedProps = useForwardProps(delegatedProps)

const { year, month, day } = props.day

const { data } = ExerciseLogApiFacade.useFetchExerciseLogsByDate(
  year + '-' + pad(month) + '-' + pad(day),
)
const totalExerciseTime = computed(() => {
  const exerciseTimeData = data.value?.map((d) => d.exerciseTime) || [];
  return exerciseTimeData.reduce((a, b) => a + b, 0);
});
</script>

<template>
  <CalendarCellTrigger
    :class="
      cn(
        buttonVariants({ variant: 'ghost' }),
        'h-24 font-normal flex justify-end items-start',
        '[&[data-today]]:border-2 [&[data-today]]:border-blue-400  [&[data-today]]:text-black',
        // Selected
        'w-full',
        // Disabled
        // Unavailable
        'data-[unavailable]:text-destructive-foreground data-[unavailable]:line-through',
        // Outside months
        'data-[outside-view]:text-muted-foreground data-[outside-view]:opacity-50',
        props.class,
      )
    "
    v-bind="forwardedProps"
  >
    <slot />
    <div class="w-full">
      <span class="font-semibold">{{ day }}</span>
      <div v-if="totalExerciseTime > 0" class="text-xs w-full h-full py-7">
        <p class="border w-full">{{ totalExerciseTime }}</p>
      </div>
    </div>
  </CalendarCellTrigger>
</template>
