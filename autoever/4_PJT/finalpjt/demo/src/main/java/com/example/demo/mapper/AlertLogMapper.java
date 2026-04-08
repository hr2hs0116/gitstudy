package com.example.demo.mapper;

import com.example.demo.model.AlertLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 알림 로그 MyBatis Mapper 인터페이스
 * 현대오토에버 스마트팩토리 스쿨 강의용
 */
@Mapper
public interface AlertLogMapper {
    
    /**
     * 특정 차량의 알림 로그를 조회합니다.
     * @param vehicleId 차량 ID
     * @return 알림 로그 목록
     */
    List<AlertLog> selectAlertLogsByVehicleId(@Param("vehicleId") Integer vehicleId);
    
    /**
     * 모든 알림 로그를 조회합니다.
     * @return 알림 로그 목록
     */
    List<AlertLog> selectAllAlertLogs();
    
    /**
     * 특정 기간의 알림 로그를 조회합니다.
     * @param startTime 시작 시간
     * @param endTime 종료 시간
     * @return 알림 로그 목록
     */
    List<AlertLog> selectAlertLogsByPeriod(
        @Param("startTime") LocalDateTime startTime,
        @Param("endTime") LocalDateTime endTime
    );
    
    /**
     * 특정 레벨의 알림 로그를 조회합니다.
     * @param level 알림 레벨
     * @return 알림 로그 목록
     */
    List<AlertLog> selectAlertLogsByLevel(@Param("level") AlertLog.AlertLevel level);
    
    /**
     * 새로운 알림 로그를 등록합니다.
     * @param alertLog 알림 로그 정보
     * @return 등록된 행 수
     */
    int insertAlertLog(AlertLog alertLog);
    
    /**
     * 알림 로그를 삭제합니다.
     * @param alertId 알림 ID
     * @return 삭제된 행 수
     */
    int deleteAlertLog(@Param("alertId") Long alertId);
} 