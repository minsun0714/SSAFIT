<script setup lang="ts">
import {
  Pagination,
  PaginationFirst,
  PaginationLast,
  PaginationList,
  PaginationListItem,
  PaginationNext,
  PaginationPrev,
} from '@/components/ui/pagination'

import Button from '../ui/button/Button.vue'
import { useRouter, useRoute } from 'vue-router'
import { computed } from 'vue'
import { PagedExerciseTypeResponse } from '@/api/interfaces/response'

defineProps<{
  data?: PagedExerciseTypeResponse
}>()

const router = useRouter()
const route = useRoute()

const currentPage = computed({
  get: () => Number(route.query.page) || 1,
  set: (page) => {
    router.push({ query: { ...route.query, page } })
  },
})

const handlePageChange = (page: number) => {
  currentPage.value = page
}
</script>

<template>
  <Pagination
    v-slot="{ page }"
    :total="data?.totalItems"
    :sibling-count="2"
    :default-page="currentPage"
    @change="handlePageChange"
  >
    <PaginationList v-slot="{ items }" class="flex justify-center items-center gap-1 p-4">
      <PaginationFirst />
      <PaginationPrev />
      <template v-for="(item, index) in items">
        <PaginationListItem v-if="item.type === 'page'" :key="index" :value="item.value" as-child>
          <Button
            class="w-10 h-10 p-0"
            :variant="item.value === page ? 'default' : 'outline'"
            @click="() => handlePageChange(item.value)"
          >
            {{ item.value }}
          </Button>
        </PaginationListItem>
      </template>
      <PaginationNext />
      <PaginationLast />
    </PaginationList>
  </Pagination>
</template>
