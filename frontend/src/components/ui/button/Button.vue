<script setup lang="ts">
import type { HTMLAttributes } from 'vue'
import { cn } from '@/lib/utils'
import { Primitive, type PrimitiveProps } from 'radix-vue'
import { type ButtonVariants, buttonVariants } from '.'

interface Props extends PrimitiveProps {
  variant?: ButtonVariants['variant']
  size?: ButtonVariants['size']
  class?: HTMLAttributes['class']
}

const props = withDefaults(defineProps<Props>(), {
  as: 'button',
})
</script>

<template>
  <!-- Primitive 컴포넌트에 스타일 적용 -->
  <Primitive
    :as="as"
    :as-child="asChild"
    :class="cn(buttonVariants({ variant, size }), props.class, 'blue-button')"
  >
    <slot />
  </Primitive>
</template>

<style scoped>
/* 파란색 버튼 스타일 */
.blue-button {
  background-color: #007bff;
  color: white;
  padding: 0.5rem 1rem;
  border-radius: 0.375rem;
  border: none;
  font-weight: bold;
  transition: background-color 0.3s ease;
}

.blue-button:hover {
  background-color: black;
}
</style>
