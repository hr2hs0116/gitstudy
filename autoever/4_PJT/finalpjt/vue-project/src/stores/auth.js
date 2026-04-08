import { defineStore } from 'pinia'
import { ref, computed } from 'vue'

export const useAuthStore = defineStore('auth', () => {
  // 상태
  const user = ref(null)
  const token = ref(localStorage.getItem('token') || null)
  const isAuthenticated = computed(() => !!token.value)

  // API 기본 URL
  const API_BASE_URL = 'http://localhost:8081/api'

  // 로그인
  const login = async (credentials) => {
    try {
      const response = await fetch(`${API_BASE_URL}/auth/login`, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(credentials),
      })

      const data = await response.json()

      if (!response.ok) {
        throw new Error(data.error || '로그인에 실패했습니다.')
      }

      // 토큰과 사용자 정보 저장
      token.value = data.token
      user.value = {
        id: data.userId,
        username: data.username,
        email: data.email,
        role: data.role
      }

      // 로컬 스토리지에 토큰 저장
      localStorage.setItem('token', data.token)
      localStorage.setItem('user', JSON.stringify(user.value))

      return data
    } catch (error) {
      console.error('Login error:', error)
      throw error
    }
  }

  // 회원가입
  const register = async (userData) => {
    try {
      const response = await fetch(`${API_BASE_URL}/auth/register`, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(userData),
      })

      const data = await response.json()

      if (!response.ok) {
        throw new Error(data.error || '회원가입에 실패했습니다.')
      }

      // 회원가입 성공 시 토큰과 사용자 정보 저장 (자동 로그인)
      token.value = data.token
      user.value = {
        id: data.userId,
        username: data.username,
        email: data.email,
        role: data.role
      }

      // 로컬 스토리지에 토큰 저장
      localStorage.setItem('token', data.token)
      localStorage.setItem('user', JSON.stringify(user.value))

      return data
    } catch (error) {
      console.error('Register error:', error)
      throw error
    }
  }

  // 로그아웃
  const logout = () => {
    token.value = null
    user.value = null
    localStorage.removeItem('token')
    localStorage.removeItem('user')
  }

  // 토큰으로 사용자 정보 복원
  const restoreUser = () => {
    const savedToken = localStorage.getItem('token')
    const savedUser = localStorage.getItem('user')
    
    if (savedToken && savedUser) {
      token.value = savedToken
      user.value = JSON.parse(savedUser)
    }
  }

  // 인증된 요청을 위한 헤더 생성
  const getAuthHeaders = () => {
    return {
      'Authorization': `Bearer ${token.value}`,
      'Content-Type': 'application/json',
    }
  }

  // 초기화 시 사용자 정보 복원
  restoreUser()

  return {
    user,
    token,
    isAuthenticated,
    login,
    register,
    logout,
    getAuthHeaders,
    restoreUser
  }
})
