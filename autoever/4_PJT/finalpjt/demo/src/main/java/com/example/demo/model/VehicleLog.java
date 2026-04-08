package com.example.demo.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 차량 센서 로그 모델 클래스
 * 현대오토에버 스마트팩토리 스쿨 강의용
 */
public class VehicleLog {
    
    private Long logId;                    // 로그 고유 ID
    private Integer vehicleId;             // 차량 ID
    private BigDecimal speedKmh;           // 속도 (km/h)
    private Integer rpm;                   // 엔진 회전수 (RPM)
    private BigDecimal fuelPercent;        // 연료 잔량 (%)
    private BigDecimal engineTemp;         // 엔진 온도 (°C)
    private LocalDateTime timestamp;       // 로그 기록 시간
    
    // 기본 생성자
    public VehicleLog() {}
    
    // 생성자
    public VehicleLog(Integer vehicleId, BigDecimal speedKmh, Integer rpm, 
                     BigDecimal fuelPercent, BigDecimal engineTemp) {
        this.vehicleId = vehicleId;
        this.speedKmh = speedKmh;
        this.rpm = rpm;
        this.fuelPercent = fuelPercent;
        this.engineTemp = engineTemp;
    }
    
    // Getter와 Setter 메서드들
    public Long getLogId() {
        return logId;
    }
    
    public void setLogId(Long logId) {
        this.logId = logId;
    }
    
    public Integer getVehicleId() {
        return vehicleId;
    }
    
    public void setVehicleId(Integer vehicleId) {
        this.vehicleId = vehicleId;
    }
    
    public BigDecimal getSpeedKmh() {
        return speedKmh;
    }
    
    public void setSpeedKmh(BigDecimal speedKmh) {
        this.speedKmh = speedKmh;
    }
    
    public Integer getRpm() {
        return rpm;
    }
    
    public void setRpm(Integer rpm) {
        this.rpm = rpm;
    }
    
    public BigDecimal getFuelPercent() {
        return fuelPercent;
    }
    
    public void setFuelPercent(BigDecimal fuelPercent) {
        this.fuelPercent = fuelPercent;
    }
    
    public BigDecimal getEngineTemp() {
        return engineTemp;
    }
    
    public void setEngineTemp(BigDecimal engineTemp) {
        this.engineTemp = engineTemp;
    }
    
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    
    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
    
    @Override
    public String toString() {
        return "VehicleLog{" +
                "logId=" + logId +
                ", vehicleId=" + vehicleId +
                ", speedKmh=" + speedKmh +
                ", rpm=" + rpm +
                ", fuelPercent=" + fuelPercent +
                ", engineTemp=" + engineTemp +
                ", timestamp=" + timestamp +
                '}';
    }
} 