# 스마트 차량 대시보드 - 프론트엔드

현대오토에버 스마트팩토리 스쿨 강의용 차량 대시보드 시스템의 프론트엔드입니다.

## 기술 스택

- **Framework**: Vue 3 (Composition API)
- **Build Tool**: Vite
- **State Management**: Pinia
- **Router**: Vue Router 4
- **HTTP Client**: Axios
- **UI**: Custom CSS (반응형 디자인)
- **Authentication**: JWT 토큰 기반

## 주요 기능

### 🔐 인증 시스템
- **회원가입**: 사용자명, 이메일, 비밀번호로 계정 생성 (자동 로그인 포함)
- **로그인**: JWT 토큰 기반 인증
- **자동 로그인**: 페이지 새로고침 시에도 로그인 상태 유지
- **프로필 관리**: 
  - 현재 사용자 정보 조회
  - 회원정보 수정 (사용자명, 이메일)
  - 비밀번호 변경
  - 회원탈퇴 (비밀번호 확인 필요)

### 📊 대시보드
- **실시간 차량 모니터링**: 속도, RPM, 연료량, 엔진 온도
- **차량 목록**: 등록된 모든 차량 정보
- **차량 상세 정보**: 개별 차량의 상세 통계 및 로그
- **알림 시스템**: 차량 상태 이상 알림 로그
- **차량 추가**: 새로운 차량 등록

### 🎨 사용자 인터페이스
- **반응형 디자인**: 모바일, 태블릿, 데스크톱 지원
- **모던 UI**: 깔끔하고 직관적인 인터페이스
- **모달 알림**: 작업 결과를 화면 중앙에 모달로 표시
- **네비게이션 가드**: 인증 상태에 따른 페이지 접근 제어

## 설치 및 실행

### 1. 의존성 설치
```bash
npm install
```

### 2. 개발 서버 실행
```bash
npm run dev
```

애플리케이션은 `http://localhost:5173`에서 실행됩니다.

### 3. 프로덕션 빌드
```bash
npm run build
```

## 프로젝트 구조

```
src/
├── assets/                 # 정적 자산 (CSS, 이미지)
├── charts/                 # 차트 컴포넌트
│   ├── AlertPieChart.vue   # 알림 파이 차트
│   ├── FuelGaugeChart.vue  # 연료 게이지 차트
│   ├── SpeedLineChart.vue  # 속도 라인 차트
│   └── TempBarChart.vue    # 온도 바 차트
├── common/                 # 공통 컴포넌트
│   ├── AlertItem.vue       # 알림 아이템
│   ├── LogTable.vue        # 로그 테이블
│   └── VehicleCard.vue     # 차량 카드
├── layout/                 # 레이아웃 컴포넌트
│   ├── AppHeader.vue       # 헤더 (네비게이션, 사용자 메뉴)
│   └── AppSidebar.vue      # 사이드바
├── pages/                  # 페이지 컴포넌트
│   ├── LoginPage.vue       # 로그인 페이지
│   ├── RegisterPage.vue    # 회원가입 페이지
│   ├── DashboardPage.vue   # 대시보드 페이지
│   ├── VehicleDetailPage.vue # 차량 상세 페이지
│   ├── AlertLogPage.vue    # 알림 로그 페이지
│   ├── AddVehiclePage.vue  # 차량 추가 페이지
│   ├── ProfilePage.vue     # 프로필 관리 페이지
│   └── NotFoundPage.vue    # 404 페이지
├── router/                 # 라우터 설정
│   └── index.js           # 라우트 정의 및 네비게이션 가드
├── stores/                 # 상태 관리
│   └── auth.js            # 인증 상태 관리 (Pinia)
├── utils/                  # 유틸리티
│   └── api.js             # Axios 설정 및 인터셉터
├── App.vue                 # 루트 컴포넌트
└── main.js                 # 애플리케이션 진입점
```

## 주요 컴포넌트 설명

### 인증 시스템
- **LoginPage**: 사용자 로그인 폼
- **RegisterPage**: 회원가입 폼 (자동 로그인)
- **ProfilePage**: 프로필 관리 (정보 수정, 비밀번호 변경, 회원탈퇴)

### 대시보드
- **DashboardPage**: 메인 대시보드 (차량 목록, 통계)
- **VehicleDetailPage**: 차량 상세 정보 및 로그
- **AlertLogPage**: 알림 로그 목록
- **AddVehiclePage**: 새 차량 등록

### 공통 컴포넌트
- **AppHeader**: 네비게이션 및 사용자 메뉴
- **AppSidebar**: 사이드바 네비게이션
- **VehicleCard**: 차량 정보 카드
- **LogTable**: 로그 데이터 테이블

## 상태 관리 (Pinia)

### Auth Store (`stores/auth.js`)
```javascript
// 상태
- user: 현재 로그인한 사용자 정보
- token: JWT 토큰
- isAuthenticated: 로그인 상태

// 액션
- login(credentials): 로그인
- register(userData): 회원가입 (자동 로그인)
- logout(): 로그아웃
- restoreUser(): 토큰으로 사용자 정보 복원
```

## API 통신

### Axios 설정 (`utils/api.js`)
- **Base URL**: `http://localhost:8081/api`
- **요청 인터셉터**: JWT 토큰 자동 추가
- **응답 인터셉터**: 401 에러 시 자동 로그아웃

### 주요 API 엔드포인트
- `POST /auth/login`: 로그인
- `POST /auth/register`: 회원가입
- `GET /auth/profile`: 사용자 정보 조회
- `PUT /auth/profile`: 회원정보 수정
- `PUT /auth/change-password`: 비밀번호 변경
- `DELETE /auth/profile`: 회원탈퇴
- `GET /dashboard/summary`: 대시보드 요약
- `GET /vehicles`: 차량 목록
- `GET /vehicle-logs`: 차량 로그
- `GET /alert-logs`: 알림 로그

## 라우팅

### 인증이 필요한 페이지
- `/`: 대시보드
- `/vehicle/:id`: 차량 상세
- `/alerts`: 알림 로그
- `/add-vehicle`: 차량 추가
- `/profile`: 프로필 관리

### 게스트만 접근 가능한 페이지
- `/login`: 로그인
- `/register`: 회원가입

### 네비게이션 가드
- 인증되지 않은 사용자는 자동으로 로그인 페이지로 리다이렉트
- 이미 로그인한 사용자는 로그인/회원가입 페이지 접근 시 대시보드로 리다이렉트

## 환경 설정

### 개발 환경
- **Node.js**: 16+
- **npm**: 8+
- **Vue**: 3.3+
- **Vite**: 4.0+

### 백엔드 연결
- **API URL**: `http://localhost:8081/api`
- **포트**: 5173 (개발 서버)

## 사용자 경험 (UX)

### 로그인 플로우
1. 회원가입 → 자동 로그인 → 대시보드
2. 로그인 → 대시보드
3. 페이지 새로고침 → 자동 로그인 상태 복원

### 프로필 관리
- 모달 형태의 결과 알림
- 실시간 폼 유효성 검사
- 확인 대화상자 (회원탈퇴)

### 반응형 디자인
- 모바일: 사이드바 숨김, 카드 레이아웃
- 태블릿: 적응형 그리드
- 데스크톱: 전체 레이아웃

## 개발 가이드

### 새 컴포넌트 추가
1. `src/pages/` 또는 `src/components/`에 Vue 파일 생성
2. `src/router/index.js`에 라우트 추가 (필요시)
3. 네비게이션 메뉴에 링크 추가 (필요시)

### API 호출
```javascript
import api from '../utils/api'

// GET 요청
const response = await api.get('/endpoint')

// POST 요청
const response = await api.post('/endpoint', data)

// PUT 요청
const response = await api.put('/endpoint', data)

// DELETE 요청
const response = await api.delete('/endpoint')
```

### 상태 관리
```javascript
import { useAuthStore } from '../stores/auth'

const authStore = useAuthStore()

// 상태 접근
console.log(authStore.user)
console.log(authStore.isAuthenticated)

// 액션 호출
await authStore.login(credentials)
```

## 라이선스

이 프로젝트는 교육 목적으로 제작되었습니다.
