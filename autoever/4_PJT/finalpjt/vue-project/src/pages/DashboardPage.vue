<template>
  <div>
    <h1>차량 대시보드</h1>
    <div class="vehicle-list">
      <VehicleCard v-for="v in vehicles" :key="v.vehicleId" :vehicle="v" />
    </div>
    <div v-if="logs.length > 0" class="dashboard-charts-grid">
      <div class="chart-box">
        <SpeedLineChart :data="logs" />
      </div>
      <div class="chart-box">
        <FuelGaugeChart :value="latestFuel" />
      </div>
      <div class="chart-box">
        <TempBarChart :data="engineTempLogs" />
      </div>
      <div class="chart-box">
        <AlertPieChart :data="alertLogs" />
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted, onUnmounted, computed } from "vue";
import api from "../utils/api";
import VehicleCard from "../common/VehicleCard.vue";
import SpeedLineChart from "../charts/SpeedLineChart.vue";
import FuelGaugeChart from "../charts/FuelGaugeChart.vue";
import TempBarChart from "../charts/TempBarChart.vue";
import AlertPieChart from "../charts/AlertPieChart.vue";

const vehicles = ref([]);
const logs = ref([]);
const engineTempLogs = ref([]);
const alertLogs = ref([]);

const latestFuel = computed(() =>
  logs.value.length > 0 ? logs.value[0].fuelPercent : 0
);

let intervalId = null;

async function fetchData() {
  const res = await api.get("/vehicles");
  vehicles.value = res.data;
  if (vehicles.value.length > 0) {
    const vid = vehicles.value[0].vehicleId;
    const logRes = await api.get(`/vehicles/${vid}/logs?start=&end=`);
    logs.value = logRes.data
      .sort((a, b) => b.timestamp.localeCompare(a.timestamp))
      .slice(0, 10);
    engineTempLogs.value = logs.value.map((l) => ({
      timestamp: l.timestamp,
      engine_temp: l.engineTemp,
    }));
    const alertRes = await api.get(`/vehicles/${vid}/alerts`);
    alertLogs.value = alertRes.data;
  }
}

onMounted(() => {
  fetchData();
  intervalId = setInterval(fetchData, 5000);
});
onUnmounted(() => {
  if (intervalId) clearInterval(intervalId);
});
</script>

<style scoped>
.vehicle-list {
  display: flex;
  flex-wrap: wrap;
  gap: 1rem;
}
.dashboard-charts-grid {
  margin-top: 2rem;
  background: #fff;
  padding: 1.5rem;
  border-radius: 8px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.04);
  display: grid;
  grid-template-columns: 1fr 1fr;
  grid-template-rows: 1fr 1fr;
  gap: 2rem;
  width: 100%;
}
.chart-box {
  flex: 1 1 0;
  width: 100%;
  height: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
}
</style> 