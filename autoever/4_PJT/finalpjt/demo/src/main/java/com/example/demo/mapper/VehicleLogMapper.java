package com.example.demo.mapper;

import com.example.demo.model.VehicleLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 차량 센서 로그 MyBatis Mapper 인터페이스
 * 현대오토에버 스마트팩토리 스쿨 강의용
 */
@Mapper
public interface VehicleLogMapper {
    
    /**
     * 특정 차량의 센서 로그를 조회합니다.
     * @param vehicleId 차량 ID
     * @param startTime 시작 시간
     * @param endTime 종료 시간
     * @return 센서 로그 목록
     */
    List<VehicleLog> selectVehicleLogsByVehicleId(
        @Param("vehicleId") Integer vehicleId,
        @Param("startTime") LocalDateTime startTime,
        @Param("endTime") LocalDateTime endTime
    );
    
    /**
     * 특정 차량의 최근 센서 로그를 조회합니다.
     * @param vehicleId 차량 ID
     * @param limit 조회할 로그 수
     * @return 센서 로그 목록
     */
    List<VehicleLog> selectRecentVehicleLogs(
        @Param("vehicleId") Integer vehicleId,
        @Param("limit") Integer limit
    );
    
    /**
     * 새로운 센서 로그를 등록합니다.
     * @param vehicleLog 센서 로그 정보
     * @return 등록된 행 수
     */
    int insertVehicleLog(VehicleLog vehicleLog);
    
    /**
     * 특정 차량의 최신 센서 데이터를 조회합니다.
     * @param vehicleId 차량 ID
     * @return 최신 센서 로그
     */
    VehicleLog selectLatestVehicleLog(@Param("vehicleId") Integer vehicleId);
} 