-- 차량 대시보드 데이터베이스 스키마
-- 현대오토에버 스마트팩토리 스쿨 강의용

-- 데이터베이스 생성
CREATE DATABASE IF NOT EXISTS vehicle_dashboard CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE vehicle_dashboard;

-- 사용자 테이블
CREATE TABLE IF NOT EXISTS users (
    user_id INT AUTO_INCREMENT PRIMARY KEY COMMENT '사용자 고유 ID',
    username VARCHAR(50) NOT NULL UNIQUE COMMENT '사용자명',
    email VARCHAR(100) NOT NULL UNIQUE COMMENT '이메일',
    password VARCHAR(255) NOT NULL COMMENT '암호화된 비밀번호',
    role ENUM('USER', 'ADMIN') NOT NULL DEFAULT 'USER' COMMENT '사용자 역할',
    is_active BOOLEAN NOT NULL DEFAULT TRUE COMMENT '계정 활성화 상태',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '가입일시',
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일시'
) COMMENT '사용자 테이블';

-- 차량 정보 테이블
CREATE TABLE IF NOT EXISTS vehicle (
    vehicle_id INT AUTO_INCREMENT PRIMARY KEY COMMENT '차량 고유 ID',
    model_name VARCHAR(100) NOT NULL COMMENT '차량 모델명',
    plate_number VARCHAR(20) NOT NULL UNIQUE COMMENT '차량 번호판',
    driver_name VARCHAR(50) NOT NULL COMMENT '운전자 이름',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '등록일시',
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일시'
) COMMENT '차량 정보 테이블';

-- 차량 센서 로그 테이블 (시계열 데이터)
CREATE TABLE IF NOT EXISTS vehicle_log (
    log_id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '로그 고유 ID',
    vehicle_id INT NOT NULL COMMENT '차량 ID',
    speed_kmh DECIMAL(5,2) NOT NULL COMMENT '속도 (km/h)',
    rpm INT NOT NULL COMMENT '엔진 회전수 (RPM)',
    fuel_percent DECIMAL(5,2) NOT NULL COMMENT '연료 잔량 (%)',
    engine_temp DECIMAL(5,2) NOT NULL COMMENT '엔진 온도 (°C)',
    timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '로그 기록 시간',
    FOREIGN KEY (vehicle_id) REFERENCES vehicle(vehicle_id) ON DELETE CASCADE,
    INDEX idx_vehicle_timestamp (vehicle_id, timestamp)
) COMMENT '차량 센서 로그 테이블';

-- 알림 로그 테이블
CREATE TABLE IF NOT EXISTS alert_log (
    alert_id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '알림 고유 ID',
    vehicle_id INT NOT NULL COMMENT '차량 ID',
    message VARCHAR(500) NOT NULL COMMENT '알림 메시지',
    level ENUM('INFO', 'WARN', 'CRITICAL') NOT NULL DEFAULT 'INFO' COMMENT '알림 레벨',
    timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '알림 발생 시간',
    FOREIGN KEY (vehicle_id) REFERENCES vehicle(vehicle_id) ON DELETE CASCADE,
    INDEX idx_vehicle_level_timestamp (vehicle_id, level, timestamp)
) COMMENT '알림 로그 테이블';

-- 샘플 데이터 삽입
INSERT INTO vehicle (model_name, plate_number, driver_name) VALUES
('현대 아반떼', '12가3456', '김철수'),
('현대 투싼', '34나5678', '이영희'),
('현대 팰리세이드', '56다7890', '박민수'),
('현대 그랜저', '78라1234', '최지영'),
('현대 싼타페', '90마5678', '정현우');

-- 샘플 센서 로그 데이터 (빈값 없이, 각 차량별 2건 이상)
INSERT INTO vehicle_log (vehicle_id, speed_kmh, rpm, fuel_percent, engine_temp, timestamp) VALUES
(1, 65.5, 2500, 75.2, 85.3, DATE_SUB(NOW(), INTERVAL 1 HOUR)),
(1, 70.2, 2800, 74.8, 87.1, DATE_SUB(NOW(), INTERVAL 2 HOUR)),
(2, 80.1, 3200, 68.3, 90.2, DATE_SUB(NOW(), INTERVAL 1 HOUR)),
(2, 75.6, 3000, 67.9, 88.7, DATE_SUB(NOW(), INTERVAL 2 HOUR)),
(3, 45.3, 1800, 82.1, 78.5, DATE_SUB(NOW(), INTERVAL 1 HOUR)),
(3, 50.0, 2000, 80.0, 80.0, DATE_SUB(NOW(), INTERVAL 2 HOUR)),
(4, 90.2, 3500, 45.8, 95.1, DATE_SUB(NOW(), INTERVAL 30 MINUTE)),
(4, 85.0, 3400, 50.0, 92.0, DATE_SUB(NOW(), INTERVAL 1 HOUR)),
(5, 60.7, 2400, 71.4, 84.2, DATE_SUB(NOW(), INTERVAL 1 HOUR)),
(5, 62.0, 2500, 70.0, 85.0, DATE_SUB(NOW(), INTERVAL 2 HOUR));

-- 샘플 알림 로그 데이터 (빈값 없이)
INSERT INTO alert_log (vehicle_id, message, level, timestamp) VALUES
(1, '연료 잔량이 20% 이하입니다.', 'WARN', DATE_SUB(NOW(), INTERVAL 2 HOUR)),
(1, '속도가 100km/h를 초과했습니다.', 'WARN', DATE_SUB(NOW(), INTERVAL 1 HOUR)),
(2, '엔진 온도가 정상 범위를 초과했습니다.', 'CRITICAL', DATE_SUB(NOW(), INTERVAL 1 HOUR)),
(2, '정상 운행 중입니다.', 'INFO', DATE_SUB(NOW(), INTERVAL 30 MINUTE)),
(3, '정상 운행 중입니다.', 'INFO', DATE_SUB(NOW(), INTERVAL 30 MINUTE)),
(3, 'RPM이 비정상적으로 높습니다.', 'WARN', DATE_SUB(NOW(), INTERVAL 15 MINUTE)),
(4, '연료 잔량이 10% 이하입니다. 긴급 주유가 필요합니다.', 'CRITICAL', DATE_SUB(NOW(), INTERVAL 15 MINUTE)),
(4, '엔진 온도가 100도를 초과했습니다.', 'CRITICAL', DATE_SUB(NOW(), INTERVAL 10 MINUTE)),
(5, '속도 제한을 초과했습니다.', 'WARN', DATE_SUB(NOW(), INTERVAL 45 MINUTE)),
(5, '정상 운행 중입니다.', 'INFO', DATE_SUB(NOW(), INTERVAL 20 MINUTE));

-- 테스트용 사용자 데이터 (비밀번호: 123456)
INSERT INTO users (username, email, password, role, is_active, created_at, updated_at) VALUES
('admin', 'admin@example.com', '$2a$10$9tgX3CnemVwFZ.VuBfBMq.pFdule/.1K3tdgS5p6S5m.6nu9PKq/C', 'ADMIN', true, NOW(), NOW()),
('user1', 'user1@example.com', '$2a$10$9tgX3CnemVwFZ.VuBfBMq.pFdule/.1K3tdgS5p6S5m.6nu9PKq/C', 'USER', true, NOW(), NOW()),
('user2', 'user2@example.com', '$2a$10$9tgX3CnemVwFZ.VuBfBMq.pFdule/.1K3tdgS5p6S5m.6nu9PKq/C', 'USER', true, NOW(), NOW()); 