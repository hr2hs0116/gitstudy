<template>
  <div style="height: 350px; width: 100%">
    <!-- 차량 속도(시계열) 라인 차트 -->
    <v-chart :option="option" style="height: 100%; width: 100%" />
  </div>
</template>

<script setup>
import { ref, watch } from "vue";
import { use } from "echarts/core";
import VChart from "vue-echarts";
import { LineChart } from "echarts/charts";
import {
  TitleComponent,
  TooltipComponent,
  GridComponent,
  LegendComponent,
} from "echarts/components";
import { CanvasRenderer } from "echarts/renderers";

use([
  LineChart,
  TitleComponent,
  TooltipComponent,
  GridComponent,
  LegendComponent,
  CanvasRenderer,
]);

const props = defineProps({
  data: {
    type: Array,
    default: () => [],
  },
});

const option = ref({
  title: { text: "차량 속도" },
  tooltip: { trigger: "axis" },
  xAxis: { type: "category", data: [] },
  yAxis: { type: "value", name: "속도km/h" },
  series: [
    {
      name: "속도",
      type: "line",
      data: [],
    },
  ],
});

watch(
  () => props.data,
  (val) => {
    option.value.xAxis.data = val.map((item) => item.timestamp);
    option.value.series[0].data = val.map((item) => item.speedKmh);
  },
  { immediate: true }
);
</script> 