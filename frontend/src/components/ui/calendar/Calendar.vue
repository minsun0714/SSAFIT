<script lang="ts" setup>
import { cn } from '@/lib/utils'
import {
  CalendarRoot,
  type CalendarRootEmits,
  type CalendarRootProps,
  useForwardPropsEmits,
} from 'radix-vue'
import { computed, type HTMLAttributes } from 'vue'
import {
  CalendarCell,
  CalendarGrid,
  CalendarGridBody,
  CalendarGridHead,
  CalendarGridRow,
  CalendarHeadCell,
  CalendarHeader,
  CalendarHeading,
  CalendarNextButton,
  CalendarPrevButton,
} from '.'
import CalendarDate from '@/components/Exercise/CalendarDate.vue'

const props = defineProps<CalendarRootProps & { class?: HTMLAttributes['class'] }>()

const emits = defineEmits<CalendarRootEmits>()

const delegatedProps = computed(() => {
  const { class: _, ...delegated } = props

  return delegated
})

const weekDays = ['SUN', 'MON', 'TUE', 'WED', 'THU', 'FRI', 'SAT']

const forwarded = useForwardPropsEmits(delegatedProps, emits)
</script>

<template>
  <CalendarRoot v-slot="{ grid }" :class="cn('p-3 w-[1000px]', props.class)" v-bind="forwarded">
    <CalendarHeader>
      <CalendarPrevButton />
      <CalendarHeading />
      <CalendarNextButton />
    </CalendarHeader>

    <div class="flex flex-col gap-y-4 mt-4">
      <CalendarGrid v-for="month in grid" :key="month.value.toString()">
        <CalendarGridHead>
          <CalendarGridRow>
            <CalendarHeadCell v-for="day in weekDays" :key="day">
              {{ day }}
            </CalendarHeadCell>
          </CalendarGridRow>
        </CalendarGridHead>
        <CalendarGridBody>
          <CalendarGridRow v-for="(weekDates, index) in month.rows" :key="`weekDate-${index}`">
            <CalendarCell v-for="weekDate in weekDates" :key="weekDate.toString()" :date="weekDate">
              <CalendarDate :week-date="weekDate" :month="month.value" v-bind="forwarded" />

            </CalendarCell>
          </CalendarGridRow>
        </CalendarGridBody>
      </CalendarGrid>
    </div>
  </CalendarRoot>
</template>
