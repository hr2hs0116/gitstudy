<template>
  <header class="app-header">
    <div class="logo-area">
      <!-- <img src="/logo.svg" alt="로고" class="logo" /> -->
      <span class="title">스마트 차량 대시보드</span>
    </div>
    <nav class="nav-area">
      <router-link to="/">대시보드</router-link>
      <router-link to="/alerts">알림 로그</router-link>
    </nav>
    <div class="user-area">
      <template v-if="authStore.isAuthenticated">
        <span class="username">{{ authStore.user?.username }}</span>
        <router-link to="/profile" class="profile-link">프로필</router-link>
        <button @click="handleLogout" class="logout-btn">로그아웃</button>
      </template>
      <template v-else>
        <router-link to="/login" class="login-link">로그인</router-link>
      </template>
    </div>
  </header>
</template>

<script setup>
import { useAuthStore } from '../stores/auth'
import { useRouter } from 'vue-router'

const authStore = useAuthStore()
const router = useRouter()

const handleLogout = () => {
  authStore.logout()
  router.push('/login')
}
</script>

<style scoped>
.app-header {
  display: flex;
  align-items: center;
  justify-content: space-between;
  background: #2d3a4b;
  color: #fff;
  padding: 0.5rem 2rem;
  height: 60px;
}
.logo-area {
  display: flex;
  align-items: center;
}
.logo {
  width: 36px;
  height: 36px;
  margin-right: 1rem;
}
.title {
  font-size: 1.3rem;
  font-weight: bold;
}
.nav-area a {
  color: #fff;
  margin-left: 1.5rem;
  text-decoration: none;
  font-weight: 500;
}
.nav-area a.router-link-active {
  text-decoration: underline;
}
.user-area {
  display: flex;
  align-items: center;
  gap: 1rem;
}
.username {
  color: #fff;
  font-weight: 500;
}
.logout-btn {
  background: transparent;
  color: #fff;
  border: 1px solid #fff;
  padding: 0.5rem 1rem;
  border-radius: 5px;
  cursor: pointer;
  font-size: 0.9rem;
  transition: all 0.2s;
}
.logout-btn:hover {
  background: #fff;
  color: #2d3a4b;
}
.login-link {
  color: #fff;
  text-decoration: none;
  font-weight: 500;
  padding: 0.5rem 1rem;
  border: 1px solid #fff;
  border-radius: 5px;
  transition: all 0.2s;
}
.login-link:hover {
  background: #fff;
  color: #2d3a4b;
}
.profile-link {
  color: #fff;
  text-decoration: none;
  font-weight: 500;
  padding: 0.5rem 1rem;
  border: 1px solid #fff;
  border-radius: 5px;
  transition: all 0.2s;
}
.profile-link:hover {
  background: #fff;
  color: #2d3a4b;
}
</style> 