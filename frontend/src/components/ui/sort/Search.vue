<template>
    <div class="search-container">
      <input 
        type="text" 
        v-model="query" 
        @input="emitSearch" 
        placeholder="검색어를 입력하세요" 
      />
    </div>
  </template>
  
  <script>
  export default {
    data() {
      return {
        query: '',
      };
    },
    methods: {
      emitSearch() {
        this.$emit('search', this.query);
      },
    },
  };
  </script>
  
  <style scoped>
  .search-container {
    flex: 1;
    margin-right: 10px;
  }
  input {
    width: 100%;
    padding: 10px;
    border: 1px solid #ccc;
    border-radius: 5px;
  }
  </style>
  