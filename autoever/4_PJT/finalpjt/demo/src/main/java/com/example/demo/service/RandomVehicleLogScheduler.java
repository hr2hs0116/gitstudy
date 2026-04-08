package com.example.demo.service;

import com.example.demo.model.VehicleLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Random;

@Component
public class RandomVehicleLogScheduler {
    @Autowired
    private VehicleLogService vehicleLogService;

    private final Random random = new Random();

    @Scheduled(fixedRate = 5000)
    public void addRandomVehicleLog() {
        VehicleLog log = new VehicleLog();
        log.setVehicleId(1); // 1번 차량
        log.setTimestamp(LocalDateTime.now());
        log.setSpeedKmh(BigDecimal.valueOf(30 + random.nextDouble() * 90)); // 30~120km/h
        log.setRpm(800 + random.nextInt(5200)); // 800~6000
        log.setFuelPercent(BigDecimal.valueOf(10 + random.nextDouble() * 90)); // 10~100%
        log.setEngineTemp(BigDecimal.valueOf(70 + random.nextDouble() * 50)); // 70~120도
        vehicleLogService.createVehicleLog(log);
        System.out.println("[랜덤 센서로그 추가] " + log);
    }
} 