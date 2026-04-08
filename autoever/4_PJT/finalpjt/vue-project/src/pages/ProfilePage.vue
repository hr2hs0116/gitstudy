<template>
  <div class="profile-page">
    <h1>프로필 관리</h1>
    
    <!-- 현재 사용자 정보 -->
    <div class="profile-section">
      <h2>현재 정보</h2>
      <div class="user-info" v-if="userInfo">
        <div class="info-item">
          <label>사용자명:</label>
          <span>{{ userInfo.username }}</span>
        </div>
        <div class="info-item">
          <label>이메일:</label>
          <span>{{ userInfo.email }}</span>
        </div>
        <div class="info-item">
          <label>역할:</label>
          <span>{{ userInfo.role === 'ADMIN' ? '관리자' : '일반 사용자' }}</span>
        </div>
        <div class="info-item">
          <label>가입일:</label>
          <span>{{ formatDate(userInfo.createdAt) }}</span>
        </div>
      </div>
    </div>

    <!-- 회원 정보 수정 -->
    <div class="profile-section">
      <h2>회원 정보 수정</h2>
      <form @submit.prevent="updateProfile" class="profile-form">
        <div class="form-group">
          <label for="username">사용자명</label>
          <input
            type="text"
            id="username"
            v-model="profileForm.username"
            placeholder="새로운 사용자명"
          />
        </div>
        <div class="form-group">
          <label for="email">이메일</label>
          <input
            type="email"
            id="email"
            v-model="profileForm.email"
            placeholder="새로운 이메일"
          />
        </div>
        <button type="submit" :disabled="loading" class="btn btn-primary">
          {{ loading ? '수정 중...' : '정보 수정' }}
        </button>
      </form>
    </div>

    <!-- 비밀번호 변경 -->
    <div class="profile-section">
      <h2>비밀번호 변경</h2>
      <form @submit.prevent="changePassword" class="profile-form">
        <div class="form-group">
          <label for="currentPassword">현재 비밀번호</label>
          <input
            type="password"
            id="currentPassword"
            v-model="passwordForm.currentPassword"
            required
            placeholder="현재 비밀번호"
          />
        </div>
        <div class="form-group">
          <label for="newPassword">새 비밀번호</label>
          <input
            type="password"
            id="newPassword"
            v-model="passwordForm.newPassword"
            required
            placeholder="새 비밀번호 (6자 이상)"
          />
        </div>
        <div class="form-group">
          <label for="confirmNewPassword">새 비밀번호 확인</label>
          <input
            type="password"
            id="confirmNewPassword"
            v-model="passwordForm.confirmNewPassword"
            required
            placeholder="새 비밀번호 확인"
          />
        </div>
        <button type="submit" :disabled="loading" class="btn btn-primary">
          {{ loading ? '변경 중...' : '비밀번호 변경' }}
        </button>
      </form>
    </div>

    <!-- 회원탈퇴 -->
    <div class="profile-section danger-zone">
      <h2>회원탈퇴</h2>
      <p class="warning-text">⚠️ 회원탈퇴 시 모든 데이터가 삭제되며 복구할 수 없습니다.</p>
      <form @submit.prevent="deleteAccount" class="profile-form">
        <div class="form-group">
          <label for="deletePassword">비밀번호 확인</label>
          <input
            type="password"
            id="deletePassword"
            v-model="deleteForm.password"
            required
            placeholder="현재 비밀번호를 입력하세요"
          />
        </div>
        <button type="submit" :disabled="loading" class="btn btn-danger">
          {{ loading ? '탈퇴 중...' : '회원탈퇴' }}
        </button>
      </form>
    </div>

    <!-- 메시지 모달 -->
    <div v-if="message" class="message-modal-overlay" @click="closeMessage">
      <div class="message-modal" @click.stop>
        <div :class="['message-icon', messageType]">
          <span v-if="messageType === 'success'">✓</span>
          <span v-else>✕</span>
        </div>
        <div class="message-content">
          <h3>{{ messageType === 'success' ? '성공' : '오류' }}</h3>
          <p>{{ message }}</p>
        </div>
        <button @click="closeMessage" class="close-btn">확인</button>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, reactive, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import { useAuthStore } from '../stores/auth'
import api from '../utils/api'

const router = useRouter()
const authStore = useAuthStore()

const loading = ref(false)
const message = ref('')
const messageType = ref('success')
const userInfo = ref(null)

const profileForm = reactive({
  username: '',
  email: ''
})

const passwordForm = reactive({
  currentPassword: '',
  newPassword: '',
  confirmNewPassword: ''
})

const deleteForm = reactive({
  password: ''
})

// 사용자 정보 로드
const loadUserInfo = async () => {
  try {
    const response = await api.get('/auth/profile')
    userInfo.value = response.data
    profileForm.username = userInfo.value.username
    profileForm.email = userInfo.value.email
  } catch (error) {
    showMessage('사용자 정보를 불러오는데 실패했습니다.', 'error')
  }
}

// 회원 정보 수정
const updateProfile = async () => {
  loading.value = true
  try {
    const response = await api.put('/auth/profile', {
      username: profileForm.username,
      email: profileForm.email
    })
    
    showMessage('회원 정보가 성공적으로 수정되었습니다.', 'success')
    
    // 로컬 스토리지의 사용자 정보도 업데이트
    if (authStore.user) {
      authStore.user.username = response.data.username
      authStore.user.email = response.data.email
      localStorage.setItem('user', JSON.stringify(authStore.user))
    }
    
    // 폼 초기화
    loadUserInfo()
  } catch (error) {
    showMessage(error.response?.data?.error || '회원 정보 수정에 실패했습니다.', 'error')
  } finally {
    loading.value = false
  }
}

// 비밀번호 변경
const changePassword = async () => {
  if (passwordForm.newPassword !== passwordForm.confirmNewPassword) {
    showMessage('새 비밀번호가 일치하지 않습니다.', 'error')
    return
  }
  
  if (passwordForm.newPassword.length < 6) {
    showMessage('새 비밀번호는 6자 이상이어야 합니다.', 'error')
    return
  }
  
  loading.value = true
  try {
    await api.put('/auth/change-password', {
      currentPassword: passwordForm.currentPassword,
      newPassword: passwordForm.newPassword
    })
    
    showMessage('비밀번호가 성공적으로 변경되었습니다.', 'success')
    
    // 폼 초기화
    passwordForm.currentPassword = ''
    passwordForm.newPassword = ''
    passwordForm.confirmNewPassword = ''
  } catch (error) {
    showMessage(error.response?.data?.error || '비밀번호 변경에 실패했습니다.', 'error')
  } finally {
    loading.value = false
  }
}

// 회원탈퇴
const deleteAccount = async () => {
  if (!confirm('정말로 회원탈퇴를 하시겠습니까? 이 작업은 되돌릴 수 없습니다.')) {
    return
  }
  
  loading.value = true
  try {
    await api.delete('/auth/profile', {
      data: { password: deleteForm.password }
    })
    
    showMessage('회원탈퇴가 완료되었습니다.', 'success')
    
    // 로그아웃 처리
    setTimeout(() => {
      authStore.logout()
      router.push('/login')
    }, 2000)
  } catch (error) {
    showMessage(error.response?.data?.error || '회원탈퇴에 실패했습니다.', 'error')
  } finally {
    loading.value = false
  }
}

// 메시지 표시
const showMessage = (msg, type = 'success') => {
  message.value = msg
  messageType.value = type
}

// 메시지 닫기
const closeMessage = () => {
  message.value = ''
}

// 날짜 포맷팅
const formatDate = (dateString) => {
  if (!dateString) return ''
  return new Date(dateString).toLocaleDateString('ko-KR', {
    year: 'numeric',
    month: 'long',
    day: 'numeric',
    hour: '2-digit',
    minute: '2-digit'
  })
}

onMounted(() => {
  loadUserInfo()
})
</script>

<style scoped>
.profile-page {
  max-width: 800px;
  margin: 0 auto;
  padding: 2rem;
}

.profile-section {
  background: white;
  border-radius: 10px;
  padding: 2rem;
  margin-bottom: 2rem;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
}

.profile-section h2 {
  margin-bottom: 1.5rem;
  color: #333;
  border-bottom: 2px solid #667eea;
  padding-bottom: 0.5rem;
}

.user-info {
  display: grid;
  gap: 1rem;
}

.info-item {
  display: flex;
  align-items: center;
  padding: 0.5rem 0;
  border-bottom: 1px solid #eee;
}

.info-item label {
  font-weight: 600;
  min-width: 100px;
  color: #555;
}

.info-item span {
  color: #333;
}

.profile-form {
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

.btn {
  padding: 0.75rem 1.5rem;
  border: none;
  border-radius: 5px;
  font-size: 1rem;
  font-weight: 500;
  cursor: pointer;
  transition: all 0.2s;
}

.btn:disabled {
  opacity: 0.6;
  cursor: not-allowed;
}

.btn-primary {
  background: #667eea;
  color: white;
}

.btn-primary:hover:not(:disabled) {
  background: #5a6fd8;
}

.btn-danger {
  background: #e74c3c;
  color: white;
}

.btn-danger:hover:not(:disabled) {
  background: #c0392b;
}

.danger-zone {
  border: 2px solid #e74c3c;
}

.warning-text {
  color: #e74c3c;
  font-weight: 500;
  margin-bottom: 1rem;
}

.message-modal-overlay {
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background: rgba(0, 0, 0, 0.5);
  display: flex;
  align-items: center;
  justify-content: center;
  z-index: 1000;
}

.message-modal {
  background: white;
  border-radius: 10px;
  padding: 2rem;
  max-width: 400px;
  width: 90%;
  text-align: center;
  box-shadow: 0 4px 20px rgba(0, 0, 0, 0.3);
  animation: modalSlideIn 0.3s ease-out;
}

@keyframes modalSlideIn {
  from {
    opacity: 0;
    transform: translateY(-50px);
  }
  to {
    opacity: 1;
    transform: translateY(0);
  }
}

.message-icon {
  width: 60px;
  height: 60px;
  border-radius: 50%;
  display: flex;
  align-items: center;
  justify-content: center;
  margin: 0 auto 1rem;
  font-size: 2rem;
  font-weight: bold;
}

.message-icon.success {
  background: #d4edda;
  color: #155724;
  border: 3px solid #c3e6cb;
}

.message-icon.error {
  background: #f8d7da;
  color: #721c24;
  border: 3px solid #f5c6cb;
}

.message-content h3 {
  margin: 0 0 0.5rem 0;
  color: #333;
  font-size: 1.2rem;
}

.message-content p {
  margin: 0 0 1.5rem 0;
  color: #666;
  line-height: 1.5;
}

.close-btn {
  background: #667eea;
  color: white;
  border: none;
  padding: 0.75rem 2rem;
  border-radius: 5px;
  font-size: 1rem;
  font-weight: 500;
  cursor: pointer;
  transition: background 0.2s;
}

.close-btn:hover {
  background: #5a6fd8;
}
</style>
