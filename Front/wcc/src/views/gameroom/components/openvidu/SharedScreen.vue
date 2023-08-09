<template>
  <video autoplay/>
</template>

<script>
export default {
  name: 'SharedScreen',

  props: {
    streamManager: Object,
  },

  mounted () {
    this.streamManager.addVideoElement(this.$el);
  },
};
</script>

<style scoped>

</style>

