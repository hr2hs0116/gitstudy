package com.example.demo.controller;

import com.example.demo.model.AlertLog;
import com.example.demo.model.Vehicle;
import com.example.demo.model.VehicleLog;
import com.example.demo.service.AlertLogService;
import com.example.demo.service.VehicleLogService;
import com.example.demo.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 대시보드 통계 REST API 컨트롤러
 * 현대오토에버 스마트팩토리 스쿨 강의용
 */
@RestController
@RequestMapping("/api/dashboard")
@CrossOrigin(origins = "*") // CORS 설정
public class DashboardController {
    
    @Autowired
    private VehicleService vehicleService;
    
    @Autowired
    private VehicleLogService vehicleLogService;
    
    @Autowired
    private AlertLogService alertLogService;
    
    /**
     * 전체 차량 상태 요약 (통계용)
     * GET /api/dashboard/summary
     */
    @GetMapping("/summary")
    public ResponseEntity<Map<String, Object>> getDashboardSummary() {
        Map<String, Object> summary = new HashMap<>();
        
        try {
            // 전체 차량 수
            List<Vehicle> vehicles = vehicleService.getAllVehicles();
            summary.put("totalVehicles", vehicles.size());
            
            // 최근 24시간 알림 수
            List<AlertLog> recentAlerts = alertLogService.getLast24HoursAlertLogs();
            summary.put("recentAlerts", recentAlerts.size());
            
            // 알림 레벨별 통계
            Map<String, Long> alertLevelStats = new HashMap<>();
            for (AlertLog alert : recentAlerts) {
                String level = alert.getLevel().toString();
                alertLevelStats.put(level, alertLevelStats.getOrDefault(level, 0L) + 1);
            }
            summary.put("alertLevelStats", alertLevelStats);
            
            // 각 차량별 최신 상태
            Map<Integer, VehicleLog> latestVehicleStatus = new HashMap<>();
            for (Vehicle vehicle : vehicles) {
                VehicleLog latestLog = vehicleLogService.getLatestVehicleLog(vehicle.getVehicleId());
                if (latestLog != null) {
                    latestVehicleStatus.put(vehicle.getVehicleId(), latestLog);
                }
            }
            summary.put("latestVehicleStatus", latestVehicleStatus);
            
            // 평균 연료 잔량
            double avgFuelPercent = latestVehicleStatus.values().stream()
                    .mapToDouble(log -> log.getFuelPercent().doubleValue())
                    .average()
                    .orElse(0.0);
            summary.put("averageFuelPercent", avgFuelPercent);
            
            // 평균 엔진 온도
            double avgEngineTemp = latestVehicleStatus.values().stream()
                    .mapToDouble(log -> log.getEngineTemp().doubleValue())
                    .average()
                    .orElse(0.0);
            summary.put("averageEngineTemp", avgEngineTemp);
            
            return ResponseEntity.ok(summary);
            
        } catch (Exception e) {
            summary.put("error", "데이터 조회 중 오류가 발생했습니다: " + e.getMessage());
            return ResponseEntity.ok(summary);
        }
    }
    
    /**
     * 차량별 상세 통계
     * GET /api/dashboard/vehicle/{id}/stats
     */
    @GetMapping("/vehicle/{id}/stats")
    public ResponseEntity<Map<String, Object>> getVehicleStats(@PathVariable("id") Integer vehicleId) {
        Map<String, Object> stats = new HashMap<>();
        
        try {
            // 차량 정보
            Vehicle vehicle = vehicleService.getVehicleById(vehicleId);
            if (vehicle == null) {
                return ResponseEntity.notFound().build();
            }
            stats.put("vehicle", vehicle);
            
            // 최신 센서 데이터
            VehicleLog latestLog = vehicleLogService.getLatestVehicleLog(vehicleId);
            stats.put("latestSensorData", latestLog);
            
            // 최근 24시간 센서 로그
            List<VehicleLog> last24HoursLogs = vehicleLogService.getLast24HoursLogs(vehicleId);
            stats.put("last24HoursLogs", last24HoursLogs);
            
            // 최근 알림
            List<AlertLog> recentAlerts = alertLogService.getAlertLogsByVehicleId(vehicleId);
            stats.put("recentAlerts", recentAlerts);
            
            return ResponseEntity.ok(stats);
            
        } catch (Exception e) {
            stats.put("error", "데이터 조회 중 오류가 발생했습니다: " + e.getMessage());
            return ResponseEntity.ok(stats);
        }
    }
} 