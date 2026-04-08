import { createRouter, createWebHistory } from "vue-router";
import { useAuthStore } from "../stores/auth";

// 라우터 설정
const routes = [
  {
    path: "/login",
    name: "Login",
    component: () => import("../pages/LoginPage.vue"),
    meta: { requiresGuest: true }
  },
  {
    path: "/register",
    name: "Register",
    component: () => import("../pages/RegisterPage.vue"),
    meta: { requiresGuest: true }
  },
  {
    path: "/",
    name: "Dashboard",
    component: () => import("../pages/DashboardPage.vue"),
    meta: { requiresAuth: true }
  },
  {
    path: "/vehicle/:id",
    name: "VehicleDetail",
    component: () => import("../pages/VehicleDetailPage.vue"),
    props: true,
    meta: { requiresAuth: true }
  },
  {
    path: "/alerts",
    name: "AlertLog",
    component: () => import("../pages/AlertLogPage.vue"),
    meta: { requiresAuth: true }
  },
  {
    path: "/add-vehicle",
    name: "AddVehicle",
    component: () => import("../pages/AddVehiclePage.vue"),
    meta: { requiresAuth: true }
  },
  {
    path: "/profile",
    name: "Profile",
    component: () => import("../pages/ProfilePage.vue"),
    meta: { requiresAuth: true }
  },
  {
    path: "/:pathMatch(.*)*",
    name: "NotFound",
    component: () => import("../pages/NotFoundPage.vue"),
  },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

// 네비게이션 가드
router.beforeEach((to, from, next) => {
  const authStore = useAuthStore()
  
  // 인증이 필요한 페이지
  if (to.meta.requiresAuth && !authStore.isAuthenticated) {
    next('/login')
    return
  }
  
  // 게스트만 접근 가능한 페이지 (로그인, 회원가입)
  if (to.meta.requiresGuest && authStore.isAuthenticated) {
    next('/')
    return
  }
  
  next()
})

export default router;
