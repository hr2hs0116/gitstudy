<template>
  <div class="alert-log-page">
    <h1>알림 로그</h1>
    <p>전체 차량의 알림/이상 이벤트를 확인할 수 있습니다.</p>
    <table>
      <thead>
        <tr>
          <th>차량ID</th>
          <th>레벨</th>
          <th>메시지</th>
          <th>시간</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="log in alertLogs" :key="log.alertId">
          <td>{{ log.vehicleId }}</td>
          <td>{{ log.level }}</td>
          <td>{{ log.message }}</td>
          <td>{{ log.timestamp }}</td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script setup>
import { ref, onMounted, onUnmounted } from "vue";
import api from "../utils/api";

const alertLogs = ref([]);
let intervalId = null;

async function fetchData() {
  const res = await api.get("/alerts");
  alertLogs.value = res.data;
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
.alert-log-page {
  padding: 2rem;
}
table {
  width: 100%;
  border-collapse: collapse;
  margin-top: 1.5rem;
}
th,
td {
  border: 1px solid #ddd;
  padding: 0.6rem 1rem;
  text-align: left;
}
th {
  background: #f5f5f5;
}
</style> 