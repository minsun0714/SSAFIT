<template>
  <footer class="footer">
    <p>Copyright © MinSun&BeomSu. 2024 All Rights Reserved.</p>
  </footer>
</template>

<script setup lang="ts">
/* Footer 컴포넌트에는 현재 동작이나 API 호출이 필요하지 않으므로 setup 내에 추가 코드 없음 */
</script>

<style scoped>
.footer {
  background-color: black; /* 밝은 회색 배경 */
  text-align: center; /* 가운데 정렬 */
  display: flex;
  justify-content: center;
  align-items: center;
  font-size: 14px; /* 글자 크기 */
  color: white; /* 글자 색상 */
  height: 100px;
  border-top: 1px solid #dee2e6; /* 위쪽 경계선 */
}
</style>
