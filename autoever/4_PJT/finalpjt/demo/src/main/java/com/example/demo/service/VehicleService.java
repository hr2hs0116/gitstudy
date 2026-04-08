package com.example.demo.service;

import com.example.demo.mapper.VehicleMapper;
import com.example.demo.model.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 차량 정보 서비스 클래스
 * 현대오토에버 스마트팩토리 스쿨 강의용
 */
@Service
public class VehicleService {
    
    @Autowired
    private VehicleMapper vehicleMapper;
    
    /**
     * 모든 차량 목록을 조회합니다.
     * @return 차량 목록
     */
    public List<Vehicle> getAllVehicles() {
        return vehicleMapper.selectAllVehicles();
    }
    
    /**
     * 특정 차량 정보를 조회합니다.
     * @param vehicleId 차량 ID
     * @return 차량 정보
     */
    public Vehicle getVehicleById(Integer vehicleId) {
        return vehicleMapper.selectVehicleById(vehicleId);
    }
    
    /**
     * 새로운 차량을 등록합니다.
     * @param vehicle 차량 정보
     * @return 등록된 차량 정보
     */
    public Vehicle createVehicle(Vehicle vehicle) {
        vehicleMapper.insertVehicle(vehicle);
        return vehicle;
    }
    
    /**
     * 차량 정보를 수정합니다.
     * @param vehicle 차량 정보
     * @return 수정 성공 여부
     */
    public boolean updateVehicle(Vehicle vehicle) {
        return vehicleMapper.updateVehicle(vehicle) > 0;
    }
    
    /**
     * 차량을 삭제합니다.
     * @param vehicleId 차량 ID
     * @return 삭제 성공 여부
     */
    public boolean deleteVehicle(Integer vehicleId) {
        return vehicleMapper.deleteVehicle(vehicleId) > 0;
    }
} 