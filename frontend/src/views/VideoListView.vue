<template>
  <div class="video-list-view">
    <Search @search="handleSearch" />
    <Sort @sort="handleSort" />

    <div class="video-card-container">
      <VideoCard
        v-for="video in paginatedVideos"
        :key="video.id"
        :video-id="video.id"
        :title="video.title"
        :nickname="video.nickname"
        :viewCount="video.viewCount"
        :createdAt="video.createdAt"
        :thumbnailImgUrl="video.thumbnailImgUrl"
        :rating="video.rating"
      />
    </div>

    <Pagination
      :currentPage="currentPage"
      :totalPages="totalPages"
      @changePage="handlePageChange"
    />
  </div>
</template>

<script setup lang="ts">
import { ref, computed } from 'vue';
import VideoCard from '@/components/common/VideoCard.vue';

const allVideos = ref([
  {
    id: 1,
    thumbnailImgUrl: "https://img.youtube.com/vi/50WCSpZtdmA/maxresdefault.jpg",
    title: "Shim EuDdeum 10 Minute Morning Stretch Everydayㅣ2023 Renewal",
    nickname: "힘으뜸",
    viewCount: 1200000,
    createdAt: "2024-11-17",
    rating: 7
  },
  {
    id: 2,
    thumbnailImgUrl: "https://img.youtube.com/vi/02K-k6daPb4/maxresdefault.jpg",
    title: "15 MIN Fat Burning Cardio - 서서하는 유산소 - 다이어트 운동",
    nickname: "빅씨스 Bigsis",
    viewCount: 900000,
    createdAt: "2024-11-16",
    rating: 7
  }
  // More video data
]);

const searchQuery = ref('');
const sortOption = ref('viewCount');
const currentPage = ref(1);
const itemsPerPage = ref(9);

const filteredVideos = computed(() => {
  const query = searchQuery.value.toLowerCase();
  return allVideos.value.filter(video =>
    video.title.toLowerCase().includes(query)
  );
});

const sortedVideos = computed(() => {
  const sorted = [...filteredVideos.value];
  switch (sortOption.value) {
    case 'viewCount':
      return sorted.sort((a, b) => b.viewCount - a.viewCount);
    case 'rating':
      // Placeholder for rating sorting logic if needed
      return sorted.sort((a, b) => b.rating - a.rating); // Example if rating exists
    case 'createdAt':
      return sorted.sort((a, b) => new Date(b.createdAt).getTime() - new Date(a.createdAt).getTime());
    default:
      return sorted;
  }
});

const paginatedVideos = computed(() => {
  const start = (currentPage.value - 1) * itemsPerPage.value;
  const end = start + itemsPerPage.value;
  return sortedVideos.value.slice(start, end);
});

const totalPages = computed(() => {
  return Math.ceil(filteredVideos.value.length / itemsPerPage.value);
});

function handleSearch(query: string) {
  searchQuery.value = query;
  currentPage.value = 1; // Reset to page 1 when search is applied
}

function handleSort(option: string) {
  sortOption.value = option;
}

function handlePageChange(page: number) {
  currentPage.value = page;
}
</script>

<style scoped>
</style>
