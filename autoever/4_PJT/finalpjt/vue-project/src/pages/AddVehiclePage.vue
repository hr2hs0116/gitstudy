<template>
  <div class="add-vehicle-page">
    <h1>차량 등록</h1>
    <form @submit.prevent="submit">
      <div class="form-group">
        <label>모델명</label>
        <input v-model="modelName" required placeholder="예: 현대 아반떼" />
      </div>
      <div class="form-group">
        <label>번호판</label>
        <input v-model="plateNumber" required placeholder="예: 12가3456" />
      </div>
      <div class="form-group">
        <label>운전자</label>
        <input v-model="driverName" required placeholder="예: 김철수" />
      </div>
      <button type="submit">차량 등록</button>
    </form>
    <div
      v-if="message"
      :class="{ success: success, error: !success }"
      class="result-msg"
    >
      {{ message }}
    </div>
  </div>
</template>

<script setup>
// 차량 등록 폼 페이지
import { ref } from "vue";
import api from "../utils/api";
import { useRouter } from "vue-router";

const modelName = ref("");
const plateNumber = ref("");
const driverName = ref("");
const message = ref("");
const success = ref(false);
const router = useRouter();

const submit = async () => {
  message.value = "";
  try {
    await api.post("/vehicles", {
      modelName: modelName.value,
      plateNumber: plateNumber.value,
      driverName: driverName.value,
    });
    message.value = "차량이 성공적으로 등록되었습니다!";
    success.value = true;
    // 1초 후 대시보드로 이동
    setTimeout(() => router.push("/"), 1000);
  } catch (e) {
    message.value = "등록 실패: " + (e.response?.data?.message || e.message);
    success.value = false;
  }
};
</script>

<style scoped>
.add-vehicle-page {
  max-width: 400px;
  margin: 2rem auto;
  background: #fff;
  padding: 2rem;
  border-radius: 10px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.06);
}
.form-group {
  margin-bottom: 1.2rem;
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
button {
  width: 100%;
  padding: 0.7rem;
  background: #1976d2;
  color: #fff;
  border: none;
  border-radius: 5px;
  font-size: 1.1rem;
  font-weight: bold;
  cursor: pointer;
  margin-top: 1rem;
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
</style> 