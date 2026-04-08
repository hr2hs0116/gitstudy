# 차량 대시보드 시스템 with 로그인/회원가입

현대오토에버 스마트팩토리 스쿨 강의용 차량 대시보드 시스템입니다.

## 기술 스택

- **Backend**: Spring Boot 3.4.7
- **Database**: MySQL
- **ORM**: MyBatis
- **Security**: Spring Security + JWT
- **Language**: Java 17

## 주요 기능

### 인증 시스템
- 회원가입 (자동 로그인 포함)
- 로그인
- JWT 토큰 기반 인증
- 비밀번호 암호화 (BCrypt)
- 프로필 관리 (회원정보 수정, 비밀번호 변경, 회원탈퇴)

### 차량 대시보드
- 차량 정보 관리
- 실시간 센서 데이터 모니터링
- 알림 시스템
- 통계 대시보드

## 설치 및 실행

### 1. 데이터베이스 설정

MySQL 데이터베이스를 생성하고 `src/main/resources/schema.sql` 파일을 실행하세요.

```sql
-- MySQL에서 실행
source src/main/resources/schema.sql;
```

### 2. 애플리케이션 설정

`src/main/resources/application.properties`에서 데이터베이스 연결 정보를 수정하세요.

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/vehicle_dashboard?useSSL=false&serverTimezone=UTC&characterEncoding=UTF-8
spring.datasource.username=your_username
spring.datasource.password=your_password
```

### 3. 애플리케이션 실행

```bash
# Maven으로 실행
./mvnw spring-boot:run

# 또는 JAR 파일로 실행
./mvnw clean package
java -jar target/demo-0.0.1-SNAPSHOT.jar
```

애플리케이션은 `http://localhost:8081`에서 실행됩니다.

## API 사용법

### 인증 API

#### 1. 회원가입
```http
POST /api/auth/register
Content-Type: application/json

{
    "username": "newuser",
    "email": "newuser@example.com",
    "password": "123456",
    "confirmPassword": "123456"
}
```

#### 2. 로그인
```http
POST /api/auth/login
Content-Type: application/json

{
    "username": "user1",
    "password": "123456"
}
```

#### 3. 응답 예시
```json
{
    "token": "eyJhbGciOiJIUzI1NiJ9...",
    "type": "Bearer",
    "userId": 1,
    "username": "user1",
    "email": "user1@example.com",
    "role": "USER"
}
```

#### 4. 현재 사용자 정보 조회
```http
GET /api/auth/profile
Authorization: Bearer {token}
```

#### 5. 회원 정보 수정
```http
PUT /api/auth/profile
Authorization: Bearer {token}
Content-Type: application/json

{
    "username": "newusername",
    "email": "newemail@example.com"
}
```

#### 6. 비밀번호 변경
```http
PUT /api/auth/change-password
Authorization: Bearer {token}
Content-Type: application/json

{
    "currentPassword": "oldpassword",
    "newPassword": "newpassword"
}
```

#### 7. 회원탈퇴
```http
DELETE /api/auth/profile
Authorization: Bearer {token}
Content-Type: application/json

{
    "password": "currentpassword"
}
```

### 대시보드 API (인증 필요)

#### 1. 대시보드 요약 정보
```http
GET /api/dashboard/summary
Authorization: Bearer {token}
```

#### 2. 차량별 상세 통계
```http
GET /api/dashboard/vehicle/{id}/stats
Authorization: Bearer {token}
```

#### 3. 차량 정보 조회
```http
GET /api/vehicles
Authorization: Bearer {token}
```

#### 4. 차량 로그 조회
```http
GET /api/vehicle-logs
Authorization: Bearer {token}
```

#### 5. 알림 로그 조회
```http
GET /api/alert-logs
Authorization: Bearer {token}
```

## 테스트 계정

스키마에 포함된 테스트 계정:

| 사용자명 | 이메일 | 비밀번호 | 역할 |
|---------|--------|----------|------|
| admin | admin@example.com | 123456 | ADMIN |
| user1 | user1@example.com | 123456 | USER |
| user2 | user2@example.com | 123456 | USER |

## 보안 설정

- 모든 API 엔드포인트는 인증이 필요합니다 (인증 관련 API 제외)
- JWT 토큰은 24시간 동안 유효합니다
- 비밀번호는 BCrypt로 암호화됩니다
- CORS가 모든 도메인에서 허용됩니다 (개발용)
- 회원탈퇴 시 비밀번호 확인 필요
- 비밀번호 변경 시 현재 비밀번호 확인 필요

## 프로젝트 구조

```
src/main/java/com/example/demo/
├── config/
│   └── SecurityConfig.java          # Spring Security 설정
├── controller/
│   ├── AuthController.java          # 인증 API
│   ├── DashboardController.java     # 대시보드 API
│   ├── VehicleController.java       # 차량 API
│   ├── VehicleLogController.java    # 차량 로그 API
│   └── AlertLogController.java      # 알림 API
├── dto/
│   ├── AuthResponse.java            # 인증 응답 DTO
│   ├── LoginRequest.java            # 로그인 요청 DTO
│   └── RegisterRequest.java         # 회원가입 요청 DTO
├── mapper/
│   ├── UserMapper.java              # 사용자 데이터 접근
│   ├── VehicleMapper.java           # 차량 데이터 접근
│   ├── VehicleLogMapper.java        # 차량 로그 데이터 접근
│   └── AlertLogMapper.java          # 알림 데이터 접근
├── model/
│   ├── User.java                    # 사용자 모델
│   ├── Vehicle.java                 # 차량 모델
│   ├── VehicleLog.java              # 차량 로그 모델
│   └── AlertLog.java                # 알림 모델
├── security/
│   └── JwtAuthenticationFilter.java # JWT 인증 필터
├── service/
│   ├── AuthService.java             # 인증 서비스
│   ├── CustomUserDetailsService.java # 사용자 상세 서비스
│   ├── VehicleService.java          # 차량 서비스
│   ├── VehicleLogService.java       # 차량 로그 서비스
│   ├── AlertLogService.java         # 알림 서비스
│   └── RandomVehicleLogScheduler.java # 랜덤 로그 생성 스케줄러
└── util/
    └── JwtUtil.java                 # JWT 유틸리티
```

## 개발 환경

- Java 17
- Maven 3.6+
- MySQL 8.0+
- Spring Boot 3.4.7

## 라이선스

이 프로젝트는 교육 목적으로 제작되었습니다.
