<template>
  <div class="gauge-container" :style="containerStyle">
    <div class="gauge-bar" :style="gaugeStyle"></div>
  </div>
</template>

<script>
export default {
  props: {
    data: {
      type: Number,
      required: true
    }, 
    back: {
      type: String
    },
    bar: {
      type: String
    }
  },
  computed: {
    gaugeStyle() {
      const height = Math.min(100, Math.max(0, this.data)) + '%';
      if (this.bar) {
        return { height: height, backgroundColor: this.bar };
      } else {
        return { height: height };
      }
    }, 
    containerStyle() {
      if (this.back) {
        return { backgroundColor: this.back }
      } else {
        return {}
      }
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
  transition: width 0.3s;
  border-radius: 10px 10px 0 0;
  margin-left: auto;
  margin-right: auto;
}
</style>