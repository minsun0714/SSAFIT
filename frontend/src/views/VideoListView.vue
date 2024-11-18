<template>
  <div class="video-list-view">
    <Search @search="handleSearch" />
    <Sort @sort="handleSort" />
    
    <div class="video-card-container">
      <VideoCard
        v-for="video in paginatedVideos"
        :key="video.id"
        :title="video.title"
        :nickname="video.nickname"
        :viewCount="video.viewCount"
        :createdAt="video.createdAt"
        :thumbnailImgUrl="video.thumbnailImgUrl"
      />
    </div>

    <Pagination
      :currentPage="currentPage"
      :totalPages="totalPages"
      @changePage="handlePageChange"
    />
  </div>
</template>

<script>
import Search from '@/components/ui/sort/Search.vue';
import Sort from '@/components/ui/sort/Sort.vue';
import VideoCard from '@/components/Video/VideoCard.vue';
import Pagination from '@/components/Video/Pagination.vue';

export default {
  components: {
    Search,
    Sort,
    VideoCard,
    Pagination,
  },
  data() {
    return {
      allVideos: [
      {
          thumbnailImgUrl: "https://img.youtube.com/vi/50WCSpZtdmA/maxresdefault.jpg",
          title: "Shim EuDdeum 10 Minute Morning Stretch Everydayㅣ2023 Renewal",
          nickname: "힘으뜸",
          viewCount: 1200000,
          createdAt: "2024-11-17",
        },
        {
          thumbnailImgUrl: "https://img.youtube.com/vi/02K-k6daPb4/maxresdefault.jpg",
          title: "15 MIN Fat Burning Cardio - 서서하는 유산소 - 다이어트 운동",
          nickname: "빅씨스 Bigsis",
          viewCount: 900000,
          createdAt: "2024-11-16",
        },
        {
          thumbnailImgUrl: "https://img.youtube.com/vi/54tTYO-vU2E/maxresdefault.jpg",
          title: "상체 다이어트 최고의 운동 BEST [팔뚝살/겨드랑이살/등살/가슴어깨라인]",
          nickname: "ThankyouBUBU",
          viewCount: 800000,
          createdAt: "2024-11-15",
        },
        {
          thumbnailImgUrl: "https://img.youtube.com/vi/QqqZH3j_vH0/maxresdefault.jpg",
          title: "상체비만 다이어트 최고의 운동 [상체 핵매운맛]",
          nickname: "ThankyouBUBU",
          viewCount: 950000,
          createdAt: "2024-11-14",
        },
        {
          thumbnailImgUrl: "https://img.youtube.com/vi/tzN6ypk6Sps/maxresdefault.jpg",
          title: "하체운동이 중요한 이유? 이것만 보고 따라하자 ! [하체운동 교과서]",
          nickname: "김강민",
          viewCount: 1100000,
          createdAt: "2024-11-13",
        },
        // 동일 데이터를 반복하여 총 18개의 항목 구성
        {
          thumbnailImgUrl: "https://img.youtube.com/vi/02K-k6daPb4/maxresdefault.jpg",
          title: "15 MIN Fat Burning Cardio - 서서하는 유산소 - 다이어트 운동",
          nickname: "빅씨스 Bigsis",
          viewCount: 900000,
          createdAt: "2024-11-16",
        },
        {
          thumbnailImgUrl: "https://img.youtube.com/vi/54tTYO-vU2E/maxresdefault.jpg",
          title: "상체 다이어트 최고의 운동 BEST [팔뚝살/겨드랑이살/등살/가슴어깨라인]",
          nickname: "ThankyouBUBU",
          viewCount: 800000,
          createdAt: "2024-11-15",
        },
        {
          thumbnailImgUrl: "https://img.youtube.com/vi/QqqZH3j_vH0/maxresdefault.jpg",
          title: "상체비만 다이어트 최고의 운동 [상체 핵매운맛]",
          nickname: "ThankyouBUBU",
          viewCount: 950000,
          createdAt: "2024-11-14",
        },
        {
          thumbnailImgUrl: "https://img.youtube.com/vi/tzN6ypk6Sps/maxresdefault.jpg",
          title: "하체운동이 중요한 이유? 이것만 보고 따라하자 ! [하체운동 교과서]",
          nickname: "김강민",
          viewCount: 1100000,
          createdAt: "2024-11-13",
        },
        {
          thumbnailImgUrl: "https://img.youtube.com/vi/50WCSpZtdmA/maxresdefault.jpg",
          title: "Shim EuDdeum 10 Minute Morning Stretch Everydayㅣ2023 Renewal",
          nickname: "힘으뜸",
          viewCount: 1200000,
          createdAt: "2024-11-17",
        },
        {
          thumbnailImgUrl: "https://img.youtube.com/vi/02K-k6daPb4/maxresdefault.jpg",
          title: "15 MIN Fat Burning Cardio - 서서하는 유산소 - 다이어트 운동",
          nickname: "빅씨스 Bigsis",
          viewCount: 900000,
          createdAt: "2024-11-16",
        },
        {
          thumbnailImgUrl: "https://img.youtube.com/vi/54tTYO-vU2E/maxresdefault.jpg",
          title: "상체 다이어트 최고의 운동 BEST [팔뚝살/겨드랑이살/등살/가슴어깨라인]",
          nickname: "ThankyouBUBU",
          viewCount: 800000,
          createdAt: "2024-11-15",
        },
        {
          thumbnailImgUrl: "https://img.youtube.com/vi/QqqZH3j_vH0/maxresdefault.jpg",
          title: "상체비만 다이어트 최고의 운동 [상체 핵매운맛]",
          nickname: "ThankyouBUBU",
          viewCount: 950000,
          createdAt: "2024-11-14",
        },
        {
          thumbnailImgUrl: "https://img.youtube.com/vi/tzN6ypk6Sps/maxresdefault.jpg",
          title: "하체운동이 중요한 이유? 이것만 보고 따라하자 ! [하체운동 교과서]",
          nickname: "김강민",
          viewCount: 1100000,
          createdAt: "2024-11-13",
        },
        // 동일 데이터를 반복하여 총 18개의 항목 구성
        {
          thumbnailImgUrl: "https://img.youtube.com/vi/02K-k6daPb4/maxresdefault.jpg",
          title: "15 MIN Fat Burning Cardio - 서서하는 유산소 - 다이어트 운동",
          nickname: "빅씨스 Bigsis",
          viewCount: 900000,
          createdAt: "2024-11-16",
        },
        {
          thumbnailImgUrl: "https://img.youtube.com/vi/54tTYO-vU2E/maxresdefault.jpg",
          title: "상체 다이어트 최고의 운동 BEST [팔뚝살/겨드랑이살/등살/가슴어깨라인]",
          nickname: "ThankyouBUBU",
          viewCount: 800000,
          createdAt: "2024-11-15",
        },
        {
          thumbnailImgUrl: "https://img.youtube.com/vi/QqqZH3j_vH0/maxresdefault.jpg",
          title: "상체비만 다이어트 최고의 운동 [상체 핵매운맛]",
          nickname: "ThankyouBUBU",
          viewCount: 950000,
          createdAt: "2024-11-14",
        },
        {
          thumbnailImgUrl: "https://img.youtube.com/vi/tzN6ypk6Sps/maxresdefault.jpg",
          title: "하체운동이 중요한 이유? 이것만 보고 따라하자 ! [하체운동 교과서]",
          nickname: "김강민",
          viewCount: 1100000,
          createdAt: "2024-11-13",
        },
        {
          thumbnailImgUrl: "https://img.youtube.com/vi/50WCSpZtdmA/maxresdefault.jpg",
          title: "Shim EuDdeum 10 Minute Morning Stretch Everydayㅣ2023 Renewal",
          nickname: "힘으뜸",
          viewCount: 1200000,
          createdAt: "2024-11-17",
        },
        {
          thumbnailImgUrl: "https://img.youtube.com/vi/02K-k6daPb4/maxresdefault.jpg",
          title: "15 MIN Fat Burning Cardio - 서서하는 유산소 - 다이어트 운동",
          nickname: "빅씨스 Bigsis",
          viewCount: 900000,
          createdAt: "2024-11-16",
        },
        {
          thumbnailImgUrl: "https://img.youtube.com/vi/54tTYO-vU2E/maxresdefault.jpg",
          title: "상체 다이어트 최고의 운동 BEST [팔뚝살/겨드랑이살/등살/가슴어깨라인]",
          nickname: "ThankyouBUBU",
          viewCount: 800000,
          createdAt: "2024-11-15",
        },
        {
          thumbnailImgUrl: "https://img.youtube.com/vi/QqqZH3j_vH0/maxresdefault.jpg",
          title: "상체비만 다이어트 최고의 운동 [상체 핵매운맛]",
          nickname: "ThankyouBUBU",
          viewCount: 950000,
          createdAt: "2024-11-14",
        },
        {
          thumbnailImgUrl: "https://img.youtube.com/vi/tzN6ypk6Sps/maxresdefault.jpg",
          title: "하체운동이 중요한 이유? 이것만 보고 따라하자 ! [하체운동 교과서]",
          nickname: "김강민",
          viewCount: 1100000,
          createdAt: "2024-11-13",
        },
        // 동일 데이터를 반복하여 총 18개의 항목 구성
        {
          thumbnailImgUrl: "https://img.youtube.com/vi/02K-k6daPb4/maxresdefault.jpg",
          title: "15 MIN Fat Burning Cardio - 서서하는 유산소 - 다이어트 운동",
          nickname: "빅씨스 Bigsis",
          viewCount: 900000,
          createdAt: "2024-11-16",
        },
        {
          thumbnailImgUrl: "https://img.youtube.com/vi/54tTYO-vU2E/maxresdefault.jpg",
          title: "상체 다이어트 최고의 운동 BEST [팔뚝살/겨드랑이살/등살/가슴어깨라인]",
          nickname: "ThankyouBUBU",
          viewCount: 800000,
          createdAt: "2024-11-15",
        },
        {
          thumbnailImgUrl: "https://img.youtube.com/vi/QqqZH3j_vH0/maxresdefault.jpg",
          title: "상체비만 다이어트 최고의 운동 [상체 핵매운맛]",
          nickname: "ThankyouBUBU",
          viewCount: 950000,
          createdAt: "2024-11-14",
        },
        {
          thumbnailImgUrl: "https://img.youtube.com/vi/tzN6ypk6Sps/maxresdefault.jpg",
          title: "하체운동이 중요한 이유? 이것만 보고 따라하자 ! [하체운동 교과서]",
          nickname: "김강민",
          viewCount: 1100000,
          createdAt: "2024-11-13",
        }
      ],
      searchQuery: '',
      sortOption: 'viewCount',
      currentPage: 1,
      itemsPerPage: 9,
    };
  },
  computed: {
    filteredVideos() {
      const query = this.searchQuery.toLowerCase();
      return this.allVideos.filter((video) =>
        video.title.toLowerCase().includes(query)
      );
    },
    sortedVideos() {
      const sorted = [...this.filteredVideos];
      switch (this.sortOption) {
        case 'viewCount':
          return sorted.sort((a, b) => b.viewCount - a.viewCount);
        case 'rating':
          return sorted.sort((a, b) => b.rating - a.rating);
        case 'reviews':
          return sorted.sort((a, b) => b.reviews - a.reviews);
        case 'createdAt':
          return sorted.sort((a, b) => new Date(b.createdAt) - new Date(a.createdAt));
        default:
          return sorted;
      }
    },
    paginatedVideos() {
      const startIndex = (this.currentPage - 1) * this.itemsPerPage;
      const endIndex = startIndex + this.itemsPerPage;
      return this.sortedVideos.slice(startIndex, endIndex);
    },
    totalPages() {
      return Math.ceil(this.sortedVideos.length / this.itemsPerPage);
    },
  },
  methods: {
    handleSearch(query) {
      this.searchQuery = query;
      this.currentPage = 1;
    },
    handleSort(option) {
      this.sortOption = option;
      this.currentPage = 1;
    },
    handlePageChange(page) {
      this.currentPage = page;
    },
  },
};
</script>

<style scoped>
.video-list-view {
  padding: 20px;
}
.video-card-container {
  display: flex;
  flex-wrap: wrap;
  gap: 20px;
  justify-content: center;
}
</style>
