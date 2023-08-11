<template>
  <div class="userVideo">
    <MyFace v-if="isMyFace" :stream-manager="streamManager" />
    <OtherFace v-else :stream-manager="streamManager" />
  </div>
</template>

<script>
import MyFace from "@/views/gameroom/components/openvidu/MyFace.vue";
import OtherFace from "@/views/gameroom/components/openvidu/OtherFace.vue";

export default {
  name: 'UserVideo',
  components: {
    OtherFace,
    MyFace,
  },
  props: {
    isMyFace: {
      type: Boolean,
      default: false
    },
    streamManager: Object
  },
};

</script>

<style scoped>
.userVideo {
  width: 100%;
  height: 100%;
}
</style>