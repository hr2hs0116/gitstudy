<template>
  <div style="height: 350px; width: 100%">
    <!-- 알림/이상 유형별 비율 파이 차트 -->
    <v-chart :option="option" style="height: 100%; width: 100%" />
  </div>
</template>

<script setup>
// ECharts 파이 차트 샘플 (vue-echarts)
import { ref, watch } from "vue";
import { use } from "echarts/core";
import VChart from "vue-echarts";
import { PieChart } from "echarts/charts";
import {
  TitleComponent,
  TooltipComponent,
  LegendComponent,
} from "echarts/components";
import { CanvasRenderer } from "echarts/renderers";

use([
  PieChart,
  TitleComponent,
  TooltipComponent,
  LegendComponent,
  CanvasRenderer,
]);

// props: 알림 로그 데이터 (예: [{level: 'INFO'}, ...])
const props = defineProps({
  data: {
    type: Array,
    default: () => [],
  },
});

// 알림 레벨별 카운트 집계
function getPieData(logs) {
  const counts = { INFO: 0, WARN: 0, CRITICAL: 0 };
  logs.forEach((log) => {
    if (counts[log.level]) counts[log.level]++;
    else counts[log.level] = 1;
  });
  return Object.entries(counts).map(([level, value]) => ({
    name: level,
    value,
  }));
}

const option = ref({
  title: { text: "이상 비율", left: "center" },
  tooltip: { trigger: "item", formatter: "{b}: {c}건 ({d}%)" },
  legend: { orient: "vertical", left: "left" },
  series: [
    {
      name: "알림 유형",
      type: "pie",
      radius: "60%",
      data: [],
      emphasis: {
        itemStyle: {
          shadowBlur: 10,
          shadowOffsetX: 0,
          shadowColor: "rgba(0,0,0,0.2)",
        },
      },
    },
  ],
});

watch(
  () => props.data,
  (val) => {
    option.value.series[0].data = getPieData(val);
  },
  { immediate: true }
);
</script> 