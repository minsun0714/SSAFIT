<script lang="ts" setup>
import { CalendarGridHead, type CalendarGridHeadProps } from 'radix-vue'

const props = defineProps<CalendarGridHeadProps>()
</script>

<template>
  <CalendarGridHead v-bind="props" class="border flex justify-evenly bg-gray-100">
    <slot />
  </CalendarGridHead>
</template>
