<script setup lang="ts">
import { Calendar } from '@/components/ui/calendar'
import { type DateValue, getLocalTimeZone, today } from '@internationalized/date'
import { type Ref, ref } from 'vue'
const value = ref(today(getLocalTimeZone())) as Ref<DateValue>
</script>

<template>
  <div class="flex justify-center">
    <Calendar v-model="value" :weekday-format="'short'" class="rounded-md border" />
  </div>
</template>
