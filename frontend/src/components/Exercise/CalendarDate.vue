<script lang="ts" setup>
import CalendarCellTrigger from '../ui/calendar/CalendarCellTrigger.vue'

import { type DateValue } from '@internationalized/date'

const { weekDate, month } = defineProps<{
  weekDate: DateValue
  month: DateValue
}>()
</script>
<template>
  <CalendarCellTrigger :day="weekDate" :month="month" />
</template>
