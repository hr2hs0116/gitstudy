package com.example.demo.model;

import java.time.LocalDateTime;

/**
 * 차량 정보 모델 클래스
 * 현대오토에버 스마트팩토리 스쿨 강의용
 */
public class Vehicle {
    
    private Integer vehicleId;        // 차량 고유 ID
    private String modelName;         // 차량 모델명
    private String plateNumber;       // 차량 번호판
    private String driverName;        // 운전자 이름
    private LocalDateTime createdAt;  // 등록일시
    private LocalDateTime updatedAt;  // 수정일시
    
    // 기본 생성자
    public Vehicle() {}
    
    // 생성자
    public Vehicle(String modelName, String plateNumber, String driverName) {
        this.modelName = modelName;
        this.plateNumber = plateNumber;
        this.driverName = driverName;
    }
    
    // Getter와 Setter 메서드들
    public Integer getVehicleId() {
        return vehicleId;
    }
    
    public void setVehicleId(Integer vehicleId) {
        this.vehicleId = vehicleId;
    }
    
    public String getModelName() {
        return modelName;
    }
    
    public void setModelName(String modelName) {
        this.modelName = modelName;
    }
    
    public String getPlateNumber() {
        return plateNumber;
    }
    
    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }
    
    public String getDriverName() {
        return driverName;
    }
    
    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
    
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
    
    @Override
    public String toString() {
        return "Vehicle{" +
                "vehicleId=" + vehicleId +
                ", modelName='" + modelName + '\'' +
                ", plateNumber='" + plateNumber + '\'' +
                ", driverName='" + driverName + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
} 