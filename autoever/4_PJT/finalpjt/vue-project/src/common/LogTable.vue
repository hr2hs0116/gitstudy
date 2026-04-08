<template>
  <div>
    <table class="log-table">
      <thead>
        <tr>
          <th>시간</th>
          <th>속도(km/h)</th>
          <th>RPM</th>
          <th>연료(%)</th>
          <th>엔진온도(℃)</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="log in logs" :key="log.log_id">
          <td>{{ log.timestamp }}</td>
          <td>{{ log.speedKmh }}</td>
          <td>{{ log.rpm }}</td>
          <td>{{ log.fuelPercent }}</td>
          <td>{{ log.engineTemp }}</td>
        </tr>
        <tr v-if="logs.length === 0">
          <td colspan="5">센서 로그 데이터가 없습니다.</td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script setup>
// LogTable: 차량 센서 로그 테이블 (snake_case 컬럼 접근 보장)
import { ref, onMounted, watch } from "vue";
import api from "../utils/api";

const props = defineProps({
  vehicleId: {
    type: Number,
    required: true,
  },
});

const logs = ref([]);

async function fetchLogs() {
  const res = await api.get(
    `/vehicles/${props.vehicleId}/logs?start=&end=`
  );
  logs.value = res.data;
}

onMounted(fetchLogs);
watch(() => props.vehicleId, fetchLogs);
</script>

<style scoped>
.log-table {
  width: 100%;
  border-collapse: collapse;
  background: #fff;
}
.log-table th,
.log-table td {
  border: 1px solid #e0e0e0;
  padding: 0.5rem 0.7rem;
  text-align: center;
}
.log-table th {
  background: #f0f2f5;
}
</style> 