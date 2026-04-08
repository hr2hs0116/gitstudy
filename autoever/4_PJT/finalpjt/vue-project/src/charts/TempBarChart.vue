<template>
  <div style="height: 350px; width: 100%">
    <!-- 엔진 온도 막대 차트 -->
    <v-chart :option="option" style="height: 100%; width: 100%" />
  </div>
</template>

<script setup>
import { ref, watch } from "vue";
import { use } from "echarts/core";
import VChart from "vue-echarts";
import { BarChart } from "echarts/charts";
import {
  TitleComponent,
  TooltipComponent,
  GridComponent,
} from "echarts/components";
import { CanvasRenderer } from "echarts/renderers";

use([
  BarChart,
  TitleComponent,
  TooltipComponent,
  GridComponent,
  CanvasRenderer,
]);

const props = defineProps({
  data: {
    type: Array,
    default: () => [],
  },
});

const option = ref({
  title: { text: "엔진 온도(℃) 막대 차트" },
  tooltip: { trigger: "axis" },
  xAxis: { type: "category", data: [] },
  yAxis: { type: "value", name: "℃" },
  series: [
    {
      name: "엔진 온도",
      type: "bar",
      data: [],
      itemStyle: { color: "#ff7043" },
    },
  ],
});

watch(
  () => props.data,
  (val) => {
    option.value.xAxis.data = val.map((item) => item.timestamp);
    option.value.series[0].data = val.map((item) => item.engine_temp);
  },
  { immediate: true }
);
</script> 