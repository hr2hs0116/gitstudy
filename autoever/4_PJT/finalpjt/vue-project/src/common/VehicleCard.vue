<template>
  <div class="vehicle-card">
    <div class="title">{{ vehicle.modelName }}</div>
    <div class="plate">{{ vehicle.plateNumber }}</div>
    <div class="driver">운전자: {{ vehicle.driverName }}</div>
    <router-link :to="`/vehicle/${vehicle.vehicleId}`">상세보기</router-link>
  </div>
</template>

<script setup>
// VehicleCard: 차량 정보 카드 (props로 vehicle 객체 전달)
const props = defineProps({
  vehicle: {
    type: Object,
    required: true,
  },
});
</script>

<style scoped>
.vehicle-card {
  border: 1px solid #e0e0e0;
  border-radius: 8px;
  padding: 1rem;
  margin-bottom: 1rem;
  background: #fff;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.03);
}
.title {
  font-size: 1.1rem;
  font-weight: bold;
}
.plate {
  color: #1976d2;
  margin: 0.3rem 0;
}
.driver {
  color: #666;
  margin-bottom: 0.5rem;
}
</style> 