package com.example.demo.model;

import java.time.LocalDateTime;

/**
 * 알림 로그 모델 클래스
 * 현대오토에버 스마트팩토리 스쿨 강의용
 */
public class AlertLog {
    
    private Long alertId;              // 알림 고유 ID
    private Integer vehicleId;         // 차량 ID
    private String message;            // 알림 메시지
    private AlertLevel level;          // 알림 레벨 (INFO/WARN/CRITICAL)
    private LocalDateTime timestamp;   // 알림 발생 시간
    
    // 알림 레벨 열거형
    public enum AlertLevel {
        INFO,       // 정보
        WARN,       // 경고
        CRITICAL    // 심각
    }
    
    // 기본 생성자
    public AlertLog() {}
    
    // 생성자
    public AlertLog(Integer vehicleId, String message, AlertLevel level) {
        this.vehicleId = vehicleId;
        this.message = message;
        this.level = level;
    }
    
    // Getter와 Setter 메서드들
    public Long getAlertId() {
        return alertId;
    }
    
    public void setAlertId(Long alertId) {
        this.alertId = alertId;
    }
    
    public Integer getVehicleId() {
        return vehicleId;
    }
    
    public void setVehicleId(Integer vehicleId) {
        this.vehicleId = vehicleId;
    }
    
    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
    
    public AlertLevel getLevel() {
        return level;
    }
    
    public void setLevel(AlertLevel level) {
        this.level = level;
    }
    
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    
    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
    
    @Override
    public String toString() {
        return "AlertLog{" +
                "alertId=" + alertId +
                ", vehicleId=" + vehicleId +
                ", message='" + message + '\'' +
                ", level=" + level +
                ", timestamp=" + timestamp +
                '}';
    }
} 