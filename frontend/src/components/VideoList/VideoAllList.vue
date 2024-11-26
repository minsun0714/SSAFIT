<template>
  <br />
  <div class="relative bg-container">
    <!-- 배경 이미지 위에 중앙 정렬된 텍스트 -->
    <div class="absolute inset-0 flex flex-col items-center justify-center space-y-4 z-10">
      <h1 class="text-3xl font-bold text-white">Video Playlist</h1>
      <div class="text-xl font-semibold animated-gradient-text text-white" :class="{ animate: isAnimating }">
        Make exercise more fun with music!
      </div>

      <!-- Search Bar -->
      <div class="relative w-full max-w-sm mt-4">
        <Input
          id="search"
          type="text"
          placeholder="Search..."
          class="pl-10"
          autocomplete="off"
          @input="handleSearch($event.target.value)"
        />
        <span class="absolute start-0 inset-y-0 flex items-center justify-center px-2">
          <Search class="size-6 text-muted-foreground" />
        </span>
      </div>
    </div>

    <!-- 배경 이미지 오버레이 -->
    <div class="absolute inset-0 bg-overlay"></div>
  </div>

  <br />

  <div>
    <!-- 자동완성 목록 -->
    <ul
      v-if="autoCompletes?.length"
      class="bg-white text-black absolute top-[300px] w-[384px] rounded-md shadow-md z-20"
    >
      <li
        v-for="(s, index) in autoCompletes"
        :key="index"
        class="px-4 py-3 cursor-pointer"
        @click="router.push({ name: Routes.VIDEO, params: { videoId: s.videoId } })"
      >
        {{ s?.title?.length <= 30 ? s : s?.title?.slice(0, 28) + '...' }}
      </li>
    </ul>

    <!-- Video List -->
    <div class="video-list-view">
      <div class="flex justify-between items-center mb-4" style="max-width: 1080px; margin: 0 auto;">
        <!-- Search Results Count -->
        <div class="text-lg font-medium">{{ videos?.totalItems }}개의 결과가 검색되었습니다.</div>

        <!-- Sorting Dropdown -->
        <div class="flex justify-center mb-4">
          <a-dropdown>
            <a class="ant-dropdown-link" @click.prevent>
              {{ SORT_MAPPING[selectedSort as VideoSortType] }} <DownOutlined />
            </a>
            <template #overlay>
              <a-menu @click="handleMenuClick">
                <a-menu-item key="VIEW_CNT">
                  <a href="javascript:;">조회순</a>
                </a-menu-item>
                <a-menu-item key="RECENT">
                  <a href="javascript:;">최신순</a>
                </a-menu-item>
                <a-menu-item key="RATING">
                  <a href="javascript:;">별점순</a>
                </a-menu-item>
              </a-menu>
            </template>
          </a-dropdown>
        </div>
      </div>

      <div
        class="grid grid-cols-3 gap-2 justify-center items-center"
        style="max-width: 1080px; margin: 0 auto;"
      >
        <VideoCard
          v-for="video in videos?.data"
          :key="video?.videoId"
          :title="video?.title"
          :nickname="video?.channelTitle"
          :viewCount="video?.viewCount"
          :createdAt="video?.publishedAt"
          :thumbnailImgUrl="video?.thumbnailUrl"
          :videoId="video?.videoId"
        />
      </div>
    </div>

    <!-- Pagination -->
    <div class="mt-4 flex justify-center">
      <CommonPagination :data="videos" />
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, watch, onMounted } from 'vue'
import { useRouter, useRoute } from 'vue-router'
import VideoCard from '@/components/common/VideoCard.vue'
import { Input } from '@/components/ui/input'
import { Search } from 'lucide-vue-next'
import { DownOutlined } from '@ant-design/icons-vue'
import VideoSearchApiFacade from '@/api/apiFacade/VideoSearchApiFacade'
import CommonPagination from '../common/CommonPagination.vue'
import { VideoSortType } from '@/api/interfaces/common'
import { Routes, SORT_MAPPING } from '@/utils/enum'
import { useThrottle } from '@vueuse/core'

const router = useRouter()
const route = useRoute()

const searchQuery = ref<string>((route.query.keyword as string) || '')
const currentPage = ref<number>(Number(route.query.page) || 1)
const selectedSort = ref<string>((route.query.sort as VideoSortType) || 'RECENT')
const autoCompletes = ref(VideoSearchApiFacade.useFetchAutocompleteSuggestions().data)
const { data: videos } = VideoSearchApiFacade.useFetchPaginatedAndSortedVideos()

function updateQueryString() {
  router.push({
    path: route.path,
    query: {
      keyword: searchQuery.value || undefined,
      page: currentPage.value > 1 ? currentPage.value.toString() : undefined,
      sort: selectedSort.value || undefined,
    },
  })
}

function handleSearch(query: string) {
  searchQuery.value = query
  currentPage.value = 1
  updateQueryString()
}

function handleMenuClick({ key }: { key: string }) {
  selectedSort.value = key
  currentPage.value = 1
  updateQueryString()
}

// URL 쿼리스트링이 변경될 때 반응적으로 처리
watch(
  () => route.query,
  (newQuery) => {
    searchQuery.value = (newQuery.keyword as string) || ''
    currentPage.value = Number(newQuery.page) || 1
    selectedSort.value = (newQuery.sort as VideoSortType) || '조회순'
  },
)

const isAnimating = ref(true)

onMounted(() => {
  // 0.5초 후 애니메이션 멈추기
  setTimeout(() => {
    isAnimating.value = false
  }, 500)
})
</script>

<style scoped>
/* 배경 이미지를 설정하는 스타일 */
.bg-container {
  position: relative;
  width: 100%;
  height: 250px; /* 원하는 높이로 설정 */
  background-image: url('@/assets/Home/runner2.jpg'); /* 배경 이미지 경로 */
  background-size: cover; /* 이미지가 영역을 채우도록 */
  background-position: center; /* 이미지가 중앙에 위치하도록 */
}

/* 배경 위에 반투명 오버레이 추가 */
.bg-overlay {
  background-color: rgba(0, 0, 0, 0.4); /* 반투명 검정색 */
  width: 100%;
  height: 100%;
  z-index: 1;
}

.animated-gradient-text {
  font-size: 1.25rem;
  background: linear-gradient(
    90deg,
    #ffffff,
    #7f80e3,
    #0001c8,
    #7f80e3,
    #ffffff,
    #0001c8,
    #7f80e3,
    #ffffff
  );
  background-size: 200% 100%; /* 그라데이션의 크기를 크게 설정 */
  background-clip: text;
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
  animation: gradient-move 6s ease-in-out infinite; /* 속도를 느리게 조정하고 easing 적용 */
}

@keyframes gradient-move {
  0% {
    background-position: 0% 50%; /* 부드러운 시작 */
  }
  50% {
    background-position: 100% 50%; /* 중간 지점에서 방향 변경 */
  }
  100% {
    background-position: 0% 50%; /* 다시 시작점으로 */
  }
}
</style>
