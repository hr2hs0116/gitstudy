package com.example.demo.service;

import com.example.demo.mapper.VehicleLogMapper;
import com.example.demo.model.VehicleLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

/**
 * 차량 센서 로그 서비스 클래스
 * 현대오토에버 스마트팩토리 스쿨 강의용
 */
@Service
public class VehicleLogService {
    
    @Autowired
    private VehicleLogMapper vehicleLogMapper;
    
    /**
     * 특정 차량의 센서 로그를 조회합니다.
     * @param vehicleId 차량 ID
     * @param startTime 시작 시간
     * @param endTime 종료 시간
     * @return 센서 로그 목록
     */
    public List<VehicleLog> getVehicleLogs(Integer vehicleId, LocalDateTime startTime, LocalDateTime endTime) {
        return vehicleLogMapper.selectVehicleLogsByVehicleId(vehicleId, startTime, endTime);
    }
    
    /**
     * 특정 차량의 최근 센서 로그를 조회합니다.
     * @param vehicleId 차량 ID
     * @param limit 조회할 로그 수
     * @return 센서 로그 목록
     */
    public List<VehicleLog> getRecentVehicleLogs(Integer vehicleId, Integer limit) {
        return vehicleLogMapper.selectRecentVehicleLogs(vehicleId, limit);
    }
    
    /**
     * 새로운 센서 로그를 등록합니다.
     * @param vehicleLog 센서 로그 정보
     * @return 등록된 센서 로그
     */
    public VehicleLog createVehicleLog(VehicleLog vehicleLog) {
        // 현재 시간을 설정
        if (vehicleLog.getTimestamp() == null) {
            vehicleLog.setTimestamp(LocalDateTime.now());
        }
        vehicleLogMapper.insertVehicleLog(vehicleLog);
        return vehicleLog;
    }
    
    /**
     * 특정 차량의 최신 센서 데이터를 조회합니다.
     * @param vehicleId 차량 ID
     * @return 최신 센서 로그
     */
    public VehicleLog getLatestVehicleLog(Integer vehicleId) {
        return vehicleLogMapper.selectLatestVehicleLog(vehicleId);
    }
    
    /**
     * 최근 24시간의 센서 로그를 조회합니다.
     * @param vehicleId 차량 ID
     * @return 센서 로그 목록
     */
    public List<VehicleLog> getLast24HoursLogs(Integer vehicleId) {
        LocalDateTime endTime = LocalDateTime.now();
        LocalDateTime startTime = endTime.minusHours(24);
        return vehicleLogMapper.selectVehicleLogsByVehicleId(vehicleId, startTime, endTime);
    }
} 