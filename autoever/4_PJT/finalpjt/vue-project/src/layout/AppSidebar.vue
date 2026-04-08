<template>
  <aside class="app-sidebar">
    <nav>
      <ul>
        <li><router-link to="/">대시보드</router-link></li>
        <li><router-link to="/alerts">알림 로그</router-link></li>
        <li><router-link to="/add-vehicle">차량 등록</router-link></li>
        <!-- 차량별 상세는 동적으로 링크 생성 예정 -->
      </ul>
    </nav>
  </aside>
</template>

<script setup>
// AppSidebar: 좌측(또는 우측) 사이드바(메뉴/네비)
</script>

<style scoped>
.app-sidebar {
  width: 180px;
  background: #f0f2f5;
  border-right: 1px solid #e0e0e0;
  padding: 2rem 1rem;
  min-height: calc(100vh - 60px);
}
ul {
  list-style: none;
  padding: 0;
}
li {
  margin-bottom: 1.2rem;
}
.router-link-active {
  font-weight: bold;
  color: #1976d2;
}
</style> 