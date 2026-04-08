<template>
  <div class="register-container">
    <div class="register-card">
      <h2>회원가입</h2>
      <form @submit.prevent="handleRegister" class="register-form">
        <div class="form-group">
          <label for="username">사용자명</label>
          <input
            type="text"
            id="username"
            v-model="registerForm.username"
            required
            placeholder="3자 이상 50자 이하"
          />
        </div>
        <div class="form-group">
          <label for="email">이메일</label>
          <input
            type="email"
            id="email"
            v-model="registerForm.email"
            required
            placeholder="이메일을 입력하세요"
          />
        </div>
        <div class="form-group">
          <label for="password">비밀번호</label>
          <input
            type="password"
            id="password"
            v-model="registerForm.password"
            required
            placeholder="6자 이상"
          />
        </div>
        <div class="form-group">
          <label for="confirmPassword">비밀번호 확인</label>
          <input
            type="password"
            id="confirmPassword"
            v-model="registerForm.confirmPassword"
            required
            placeholder="비밀번호를 다시 입력하세요"
          />
        </div>
        <button type="submit" :disabled="loading" class="register-btn">
          {{ loading ? '가입 중...' : '회원가입' }}
        </button>
        <div v-if="error" class="error-message">
          {{ error }}
        </div>
      </form>
      <div class="login-link">
        이미 계정이 있으신가요? 
        <router-link to="/login">로그인</router-link>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, reactive } from 'vue'
import { useRouter } from 'vue-router'
import { useAuthStore } from '../stores/auth'

const router = useRouter()
const authStore = useAuthStore()

const loading = ref(false)
const error = ref('')

const registerForm = reactive({
  username: '',
  email: '',
  password: '',
  confirmPassword: ''
})

const handleRegister = async () => {
  loading.value = true
  error.value = ''
  
  // 비밀번호 확인
  if (registerForm.password !== registerForm.confirmPassword) {
    error.value = '비밀번호가 일치하지 않습니다.'
    loading.value = false
    return
  }
  
  try {
    await authStore.register(registerForm)
    // 회원가입 성공 시 자동으로 로그인되어 대시보드로 이동
    router.push('/')
  } catch (err) {
    error.value = err.message || '회원가입에 실패했습니다.'
  } finally {
    loading.value = false
  }
}
</script>

<style scoped>
.register-container {
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 100vh;
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
}

.register-card {
  background: white;
  padding: 2rem;
  border-radius: 10px;
  box-shadow: 0 10px 25px rgba(0, 0, 0, 0.1);
  width: 100%;
  max-width: 400px;
}

.register-card h2 {
  text-align: center;
  margin-bottom: 2rem;
  color: #333;
}

.register-form {
  display: flex;
  flex-direction: column;
  gap: 1rem;
}

.form-group {
  display: flex;
  flex-direction: column;
  gap: 0.5rem;
}

.form-group label {
  font-weight: 500;
  color: #555;
}

.form-group input {
  padding: 0.75rem;
  border: 1px solid #ddd;
  border-radius: 5px;
  font-size: 1rem;
}

.form-group input:focus {
  outline: none;
  border-color: #667eea;
  box-shadow: 0 0 0 2px rgba(102, 126, 234, 0.2);
}

.register-btn {
  background: #667eea;
  color: white;
  padding: 0.75rem;
  border: none;
  border-radius: 5px;
  font-size: 1rem;
  font-weight: 500;
  cursor: pointer;
  transition: background-color 0.2s;
}

.register-btn:hover:not(:disabled) {
  background: #5a6fd8;
}

.register-btn:disabled {
  background: #ccc;
  cursor: not-allowed;
}

.error-message {
  color: #e74c3c;
  text-align: center;
  font-size: 0.9rem;
}

.login-link {
  text-align: center;
  margin-top: 1rem;
  color: #666;
}

.login-link a {
  color: #667eea;
  text-decoration: none;
  font-weight: 500;
}

.login-link a:hover {
  text-decoration: underline;
}
</style>
