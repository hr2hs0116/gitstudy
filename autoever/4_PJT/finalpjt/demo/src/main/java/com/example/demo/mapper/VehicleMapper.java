package com.example.demo.mapper;

import com.example.demo.model.Vehicle;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 차량 정보 MyBatis Mapper 인터페이스
 * 현대오토에버 스마트팩토리 스쿨 강의용
 */
@Mapper
public interface VehicleMapper {
    
    /**
     * 모든 차량 목록을 조회합니다.
     * @return 차량 목록
     */
    List<Vehicle> selectAllVehicles();
    
    /**
     * 특정 차량 정보를 조회합니다.
     * @param vehicleId 차량 ID
     * @return 차량 정보
     */
    Vehicle selectVehicleById(@Param("vehicleId") Integer vehicleId);
    
    /**
     * 새로운 차량을 등록합니다.
     * @param vehicle 차량 정보
     * @return 등록된 행 수
     */
    int insertVehicle(Vehicle vehicle);
    
    /**
     * 차량 정보를 수정합니다.
     * @param vehicle 차량 정보
     * @return 수정된 행 수
     */
    int updateVehicle(Vehicle vehicle);
    
    /**
     * 차량을 삭제합니다.
     * @param vehicleId 차량 ID
     * @return 삭제된 행 수
     */
    int deleteVehicle(@Param("vehicleId") Integer vehicleId);
} 