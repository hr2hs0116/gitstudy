package com.example.demo.controller;

import com.example.demo.model.AlertLog;
import com.example.demo.service.AlertLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 알림 로그 REST API 컨트롤러
 * 현대오토에버 스마트팩토리 스쿨 강의용
 */
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*") // CORS 설정
public class AlertLogController {
    
    @Autowired
    private AlertLogService alertLogService;
    
    /**
     * 특정 차량의 알림 로그 조회
     * GET /api/vehicles/{id}/alerts
     */
    @GetMapping("/vehicles/{id}/alerts")
    public ResponseEntity<List<AlertLog>> getAlertLogsByVehicleId(@PathVariable("id") Integer vehicleId) {
        List<AlertLog> alerts = alertLogService.getAlertLogsByVehicleId(vehicleId);
        return ResponseEntity.ok(alerts);
    }
    
    /**
     * 모든 알림 로그 조회
     * GET /api/alerts
     */
    @GetMapping("/alerts")
    public ResponseEntity<List<AlertLog>> getAllAlertLogs() {
        List<AlertLog> alerts = alertLogService.getAllAlertLogs();
        return ResponseEntity.ok(alerts);
    }
    
    /**
     * 최근 24시간 알림 로그 조회
     * GET /api/alerts/24h
     */
    @GetMapping("/alerts/24h")
    public ResponseEntity<List<AlertLog>> getLast24HoursAlertLogs() {
        List<AlertLog> alerts = alertLogService.getLast24HoursAlertLogs();
        return ResponseEntity.ok(alerts);
    }
    
    /**
     * 특정 레벨의 알림 로그 조회
     * GET /api/alerts/level/{level}
     */
    @GetMapping("/alerts/level/{level}")
    public ResponseEntity<List<AlertLog>> getAlertLogsByLevel(@PathVariable("level") String level) {
        try {
            AlertLog.AlertLevel alertLevel = AlertLog.AlertLevel.valueOf(level.toUpperCase());
            List<AlertLog> alerts = alertLogService.getAlertLogsByLevel(alertLevel);
            return ResponseEntity.ok(alerts);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    /**
     * 새로운 알림 로그 등록
     * POST /api/alerts
     */
    @PostMapping("/alerts")
    public ResponseEntity<AlertLog> createAlertLog(@RequestBody AlertLog alertLog) {
        AlertLog createdAlert = alertLogService.createAlertLog(alertLog);
        return ResponseEntity.ok(createdAlert);
    }
    
    /**
     * 알림 로그 삭제
     * DELETE /api/alerts/{id}
     */
    @DeleteMapping("/alerts/{id}")
    public ResponseEntity<Void> deleteAlertLog(@PathVariable("id") Long alertId) {
        boolean deleted = alertLogService.deleteAlertLog(alertId);
        if (deleted) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
} 