<template>
  <div class="not-found-page">
    <h1>404</h1>
    <p>존재하지 않는 경로입니다.</p>
    <router-link to="/">대시보드로 이동</router-link>
  </div>
</template>

<script setup>
// NotFoundPage: 존재하지 않는 경로 접근 시 표시되는 페이지
</script>

<style scoped>
.not-found-page {
  padding: 2rem;
  text-align: center;
}
</style> 