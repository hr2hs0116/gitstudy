<template>
  <div>
    <h1>차량 상세 정보</h1>
    <div v-if="vehicle" class="vehicle-info">
      <div><b>모델명:</b> {{ vehicle.modelName }}</div>
      <div><b>번호판:</b> {{ vehicle.plateNumber }}</div>
      <div><b>운전자:</b> {{ vehicle.driverName }}</div>
      <div class="btns">
        <button @click="startEdit">수정</button>
        <button @click="deleteVehicle" class="delete">삭제</button>
      </div>
    </div>
    <div v-if="editMode" class="edit-form">
      <h3>차량 정보 수정</h3>
      <form @submit.prevent="submitEdit">
        <div class="form-group">
          <label>모델명</label>
          <input v-model="editModelName" required />
        </div>
        <div class="form-group">
          <label>번호판</label>
          <input v-model="editPlateNumber" required />
        </div>
        <div class="form-group">
          <label>운전자</label>
          <input v-model="editDriverName" required />
        </div>
        <button type="submit">저장</button>
        <button type="button" @click="editMode = false">취소</button>
      </form>
    </div>
    <div
      v-if="message"
      :class="{ success: success, error: !success }"
      class="result-msg"
    >
      {{ message }}
    </div>
    <div class="detail-charts-grid" v-if="logs.length > 0">
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
    <div style="margin-top: 2rem">
      <h2>센서 로그</h2>
      <LogTable :vehicleId="vehicleId" />
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted, onUnmounted, computed } from "vue";
import { useRoute, useRouter } from "vue-router";
import api from "../utils/api";
import LogTable from "../common/LogTable.vue";
import SpeedLineChart from "../charts/SpeedLineChart.vue";
import FuelGaugeChart from "../charts/FuelGaugeChart.vue";
import TempBarChart from "../charts/TempBarChart.vue";
import AlertPieChart from "../charts/AlertPieChart.vue";

const route = useRoute();
const router = useRouter();
const vehicleId = Number(route.params.id);

const vehicle = ref(null);
const logs = ref([]);
const latestFuel = computed(() =>
  logs.value.length > 0 ? logs.value[0].fuelPercent : 0
);

// 수정 폼 상태
const editMode = ref(false);
const editModelName = ref("");
const editPlateNumber = ref("");
const editDriverName = ref("");
const message = ref("");
const success = ref(false);

const engineTempLogs = ref([]);
const alertLogs = ref([]);

let intervalId = null;

onMounted(() => {
  fetchData();
  intervalId = setInterval(fetchData, 5000);
});
onUnmounted(() => {
  if (intervalId) clearInterval(intervalId);
});

async function fetchData() {
  // 차량 정보 불러오기
  const vRes = await api.get(`/vehicles/${vehicleId}`);
  vehicle.value = vRes.data;
  // 센서 로그 불러오기
  const logRes = await api.get(`/vehicles/${vehicleId}/logs?start=&end=`);
  logs.value = logRes.data
    .sort((a, b) => b.timestamp.localeCompare(a.timestamp))
    .slice(0, 10);
  // 엔진 온도 로그 추출
  engineTempLogs.value = logs.value.map((l) => ({
    timestamp: l.timestamp,
    engine_temp: l.engineTemp,
  }));
  // 알림 로그 불러오기
  const alertRes = await api.get(`/vehicles/${vehicleId}/alerts`);
  alertLogs.value = alertRes.data;
}

function startEdit() {
  editModelName.value = vehicle.value.modelName;
  editPlateNumber.value = vehicle.value.plateNumber;
  editDriverName.value = vehicle.value.driverName;
  editMode.value = true;
  message.value = "";
}

async function submitEdit() {
  try {
    await api.put(`/vehicles/${vehicleId}`, {
      modelName: editModelName.value,
      plateNumber: editPlateNumber.value,
      driverName: editDriverName.value,
    });
    message.value = "수정이 완료되었습니다.";
    success.value = true;
    editMode.value = false;
    await fetchData();
  } catch (e) {
    message.value = "수정 실패: " + (e.response?.data?.message || e.message);
    success.value = false;
  }
}

async function deleteVehicle() {
  if (!confirm("정말로 이 차량을 삭제하시겠습니까?")) return;
  try {
    await api.delete(`/vehicles/${vehicleId}`);
    message.value = "차량이 삭제되었습니다.";
    success.value = true;
    setTimeout(() => router.push("/"), 1000);
  } catch (e) {
    message.value = "삭제 실패: " + (e.response?.data?.message || e.message);
    success.value = false;
  }
}
</script>

<style scoped>
.vehicle-info {
  background: #f8f9fa;
  padding: 1rem;
  border-radius: 8px;
  margin-bottom: 1.5rem;
}
.charts-area {
  display: flex;
  align-items: center;
  gap: 2rem;
  margin-bottom: 2rem;
}
.btns {
  margin-top: 1rem;
}
.btns button {
  margin-right: 0.7rem;
  padding: 0.4rem 1.1rem;
  border-radius: 5px;
  border: none;
  font-size: 1rem;
  cursor: pointer;
  background: #1976d2;
  color: #fff;
  font-weight: bold;
  transition: background 0.2s;
}
.btns .delete {
  background: #e53935;
}
.edit-form {
  background: #fffde7;
  border: 1px solid #ffe082;
  border-radius: 8px;
  padding: 1rem;
  margin-bottom: 1.2rem;
}
.form-group {
  margin-bottom: 1rem;
}
label {
  display: block;
  margin-bottom: 0.3rem;
  font-weight: bold;
}
input {
  width: 100%;
  padding: 0.5rem;
  border: 1px solid #ccc;
  border-radius: 5px;
  font-size: 1rem;
}
.result-msg {
  margin-top: 1.2rem;
  padding: 0.7rem;
  border-radius: 5px;
  text-align: center;
}
.success {
  background: #e3f2fd;
  color: #1976d2;
}
.error {
  background: #ffebee;
  color: #e53935;
}
.detail-charts-grid {
  margin-bottom: 2rem;
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