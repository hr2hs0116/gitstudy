<template>
  <div style="height: 350px; width: 100%">
    <!-- 연료 잔량 게이지 차트 -->
    <v-chart :option="option" style="height: 100%; width: 100%" />
  </div>
</template>

<script setup>
import { ref, watch } from "vue";
import { use } from "echarts/core";
import VChart from "vue-echarts";
import { GaugeChart } from "echarts/charts";
import { TitleComponent, TooltipComponent } from "echarts/components";
import { CanvasRenderer } from "echarts/renderers";

use([GaugeChart, TitleComponent, TooltipComponent, CanvasRenderer]);

const props = defineProps({
  value: {
    type: Number,
    default: 0,
  },
});

const option = ref({
  title: { text: "연료 잔량(%)", left: "center" },
  tooltip: {
    formatter: "{b}: {c}%",
  },
  series: [
    {
      type: "gauge",
      min: 0,
      max: 100,
      detail: { formatter: "{value}%" },
      data: [{ value: 0, name: "연료" }],
    },
  ],
});

watch(
  () => props.value,
  (val) => {
    option.value.series[0].data[0].value = val;
  },
  { immediate: true }
);
</script> 