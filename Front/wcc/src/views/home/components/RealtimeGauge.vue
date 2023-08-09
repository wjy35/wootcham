<template>
  <div class="gauge-container">
    <div class="gauge-bar" :style="gaugeStyle"></div>
  </div>
</template>

<script>
export default {
  props: {
    data: {
      type: Number,
      required: true
    }
  },
  computed: {
    gaugeStyle() {
      const height = Math.min(100, Math.max(0, this.data)) + '%';
      return {
        height: height
      };
    }
  }
};
</script>

<style>
.gauge-container {
  height: 100%;
  display: flex;
  align-items: flex-end;;

  background-color: #f0f0f0;
  border-radius: 10px;
  overflow: hidden;
}

.gauge-bar {
  width: 40px;

  background-color: #42b883;
  transition: width 0.1s;
  border-radius: 10px 10px 0 0;
}
</style>