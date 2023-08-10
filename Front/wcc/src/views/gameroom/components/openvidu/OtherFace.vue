<template>
  <video autoplay/>
</template>

<script>
export default {
  name: 'OtherFace',

  props: {
    streamManager: Object,
  },

  mounted () {
    this.streamManager.addVideoElement(this.$el);
  },
};
</script>

<style scoped>
	video {
		width: 100%;
		height: auto;
	}
</style>

