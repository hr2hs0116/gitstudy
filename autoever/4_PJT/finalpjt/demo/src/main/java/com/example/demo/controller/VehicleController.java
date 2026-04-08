package com.example.demo.controller;

import com.example.demo.model.Vehicle;
import com.example.demo.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 차량 정보 REST API 컨트롤러
 * 현대오토에버 스마트팩토리 스쿨 강의용
 */
@RestController
@RequestMapping("/api/vehicles")
@CrossOrigin(origins = "*") // CORS 설정
public class VehicleController {
    
    @Autowired
    private VehicleService vehicleService;
    
    /**
     * 등록된 차량 목록 조회
     * GET /api/vehicles
     */
    @GetMapping
    public ResponseEntity<List<Vehicle>> getAllVehicles() {
        List<Vehicle> vehicles = vehicleService.getAllVehicles();
        return ResponseEntity.ok(vehicles);
    }
    
    /**
     * 특정 차량 기본 정보 조회
     * GET /api/vehicles/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<Vehicle> getVehicleById(@PathVariable("id") Integer vehicleId) {
        Vehicle vehicle = vehicleService.getVehicleById(vehicleId);
        if (vehicle != null) {
            return ResponseEntity.ok(vehicle);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    /**
     * 차량 등록
     * POST /api/vehicles
     */
    @PostMapping
    public ResponseEntity<Vehicle> createVehicle(@RequestBody Vehicle vehicle) {
        Vehicle createdVehicle = vehicleService.createVehicle(vehicle);
        return ResponseEntity.ok(createdVehicle);
    }
    
    /**
     * 차량 정보 수정
     * PUT /api/vehicles/{id}
     */
    @PutMapping("/{id}")
    public ResponseEntity<Vehicle> updateVehicle(@PathVariable("id") Integer vehicleId, 
                                               @RequestBody Vehicle vehicle) {
        vehicle.setVehicleId(vehicleId);
        boolean updated = vehicleService.updateVehicle(vehicle);
        if (updated) {
            return ResponseEntity.ok(vehicle);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    /**
     * 차량 삭제
     * DELETE /api/vehicles/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVehicle(@PathVariable("id") Integer vehicleId) {
        boolean deleted = vehicleService.deleteVehicle(vehicleId);
        if (deleted) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
} 