package com.example.demo.service;

import com.example.demo.mapper.AlertLogMapper;
import com.example.demo.model.AlertLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 알림 로그 서비스 클래스
 * 현대오토에버 스마트팩토리 스쿨 강의용
 */
@Service
public class AlertLogService {
    
    @Autowired
    private AlertLogMapper alertLogMapper;
    
    /**
     * 특정 차량의 알림 로그를 조회합니다.
     * @param vehicleId 차량 ID
     * @return 알림 로그 목록
     */
    public List<AlertLog> getAlertLogsByVehicleId(Integer vehicleId) {
        return alertLogMapper.selectAlertLogsByVehicleId(vehicleId);
    }
    
    /**
     * 모든 알림 로그를 조회합니다.
     * @return 알림 로그 목록
     */
    public List<AlertLog> getAllAlertLogs() {
        return alertLogMapper.selectAllAlertLogs();
    }
    
    /**
     * 특정 기간의 알림 로그를 조회합니다.
     * @param startTime 시작 시간
     * @param endTime 종료 시간
     * @return 알림 로그 목록
     */
    public List<AlertLog> getAlertLogsByPeriod(LocalDateTime startTime, LocalDateTime endTime) {
        return alertLogMapper.selectAlertLogsByPeriod(startTime, endTime);
    }
    
    /**
     * 특정 레벨의 알림 로그를 조회합니다.
     * @param level 알림 레벨
     * @return 알림 로그 목록
     */
    public List<AlertLog> getAlertLogsByLevel(AlertLog.AlertLevel level) {
        return alertLogMapper.selectAlertLogsByLevel(level);
    }
    
    /**
     * 새로운 알림 로그를 등록합니다.
     * @param alertLog 알림 로그 정보
     * @return 등록된 알림 로그
     */
    public AlertLog createAlertLog(AlertLog alertLog) {
        // 현재 시간을 설정
        if (alertLog.getTimestamp() == null) {
            alertLog.setTimestamp(LocalDateTime.now());
        }
        alertLogMapper.insertAlertLog(alertLog);
        return alertLog;
    }
    
    /**
     * 알림 로그를 삭제합니다.
     * @param alertId 알림 ID
     * @return 삭제 성공 여부
     */
    public boolean deleteAlertLog(Long alertId) {
        return alertLogMapper.deleteAlertLog(alertId) > 0;
    }
    
    /**
     * 최근 24시간의 알림 로그를 조회합니다.
     * @return 알림 로그 목록
     */
    public List<AlertLog> getLast24HoursAlertLogs() {
        LocalDateTime endTime = LocalDateTime.now();
        LocalDateTime startTime = endTime.minusHours(24);
        return alertLogMapper.selectAlertLogsByPeriod(startTime, endTime);
    }
} 