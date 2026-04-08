<template>
  <div class="alert-item" :class="levelClass">
    <div class="alert-header">
      <span class="level">{{ alert.level }}</span>
      <span class="timestamp">{{ alert.timestamp }}</span>
    </div>
    <div class="message">{{ alert.message }}</div>
  </div>
</template>

<script setup>
// AlertItem: 알림/이상 이벤트 단건 표시 카드
const props = defineProps({
  alert: {
    type: Object,
    required: true,
  },
});

// 알림 레벨별 스타일 클래스 반환
const levelClass =
  {
    INFO: "info",
    WARN: "warn",
    CRITICAL: "critical",
  }[props.alert.level] || "info";
</script>

<style scoped>
.alert-item {
  border-radius: 8px;
  padding: 1rem;
  margin-bottom: 1rem;
  background: #fff;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.04);
  border-left: 6px solid #bdbdbd;
  transition: border-color 0.2s;
}
.alert-item.info {
  border-color: #1976d2;
}
.alert-item.warn {
  border-color: #ffa726;
}
.alert-item.critical {
  border-color: #e53935;
}
.alert-header {
  display: flex;
  justify-content: space-between;
  margin-bottom: 0.5rem;
}
.level {
  font-weight: bold;
  text-transform: uppercase;
}
.level.info {
  color: #1976d2;
}
.level.warn {
  color: #ffa726;
}
.level.critical {
  color: #e53935;
}
.timestamp {
  color: #888;
  font-size: 0.95em;
}
.message {
  font-size: 1.05em;
}
</style> 