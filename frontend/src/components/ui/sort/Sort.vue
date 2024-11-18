<template>
    <div class="sort-container">
      <select v-model="selectedOption" @change="emitSort">
        <option value="viewCount">조회순</option>
        <option value="rating">별점순</option>
        <option value="createdAt">최신순</option>
        <option value="reviews">후기순</option>
      </select>
    </div>
  </template>
  
  <script>
  export default {
    data() {
      return {
        selectedOption: 'viewCount', // 기본 정렬 옵션
      };
    },
    methods: {
      emitSort() {
        this.$emit('sort', this.selectedOption);
      },
    },
  };
  </script>
  
  <style scoped>
  .sort-container {
    flex: 1;
    margin-left: 10px;
  }
  select {
    width: 100%;
    padding: 10px;
    border: 1px solid #ccc;
    border-radius: 5px;
  }
  </style>
  