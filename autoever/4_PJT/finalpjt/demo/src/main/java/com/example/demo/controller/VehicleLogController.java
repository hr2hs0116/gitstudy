package com.example.demo.controller;

import com.example.demo.model.VehicleLog;
import com.example.demo.service.VehicleLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 차량 센서 로그 REST API 컨트롤러
 * 현대오토에버 스마트팩토리 스쿨 강의용
 */
@RestController
@RequestMapping("/api/vehicles")
@CrossOrigin(origins = "*") // CORS 설정
public class VehicleLogController {
    
    @Autowired
    private VehicleLogService vehicleLogService;
    
    /**
     * 특정 차량의 센서 로그 조회
     * GET /api/vehicles/{id}/logs?start=xxx&end=xxx
     */
    @GetMapping("/{id}/logs")
    public ResponseEntity<List<VehicleLog>> getVehicleLogs(
            @PathVariable("id") Integer vehicleId,
            @RequestParam(value = "start", required = false) 
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startTime,
            @RequestParam(value = "end", required = false) 
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endTime) {
        
        List<VehicleLog> logs = vehicleLogService.getVehicleLogs(vehicleId, startTime, endTime);
        return ResponseEntity.ok(logs);
    }
    
    /**
     * 특정 차량의 최근 센서 로그 조회
     * GET /api/vehicles/{id}/logs/recent?limit=10
     */
    @GetMapping("/{id}/logs/recent")
    public ResponseEntity<List<VehicleLog>> getRecentVehicleLogs(
            @PathVariable("id") Integer vehicleId,
            @RequestParam(value = "limit", defaultValue = "10") Integer limit) {
        
        List<VehicleLog> logs = vehicleLogService.getRecentVehicleLogs(vehicleId, limit);
        return ResponseEntity.ok(logs);
    }
    
    /**
     * 특정 차량의 최신 센서 데이터 조회
     * GET /api/vehicles/{id}/logs/latest
     */
    @GetMapping("/{id}/logs/latest")
    public ResponseEntity<VehicleLog> getLatestVehicleLog(@PathVariable("id") Integer vehicleId) {
        VehicleLog latestLog = vehicleLogService.getLatestVehicleLog(vehicleId);
        if (latestLog != null) {
            return ResponseEntity.ok(latestLog);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    /**
     * 특정 차량의 최근 24시간 센서 로그 조회
     * GET /api/vehicles/{id}/logs/24h
     */
    @GetMapping("/{id}/logs/24h")
    public ResponseEntity<List<VehicleLog>> getLast24HoursLogs(@PathVariable("id") Integer vehicleId) {
        List<VehicleLog> logs = vehicleLogService.getLast24HoursLogs(vehicleId);
        return ResponseEntity.ok(logs);
    }
    
    /**
     * 센서 데이터 수신용 API
     * POST /api/vehicles/{id}/logs
     */
    @PostMapping("/{id}/logs")
    public ResponseEntity<VehicleLog> createVehicleLog(
            @PathVariable("id") Integer vehicleId,
            @RequestBody VehicleLog vehicleLog) {
        
        vehicleLog.setVehicleId(vehicleId);
        VehicleLog createdLog = vehicleLogService.createVehicleLog(vehicleLog);
        return ResponseEntity.ok(createdLog);
    }
} 