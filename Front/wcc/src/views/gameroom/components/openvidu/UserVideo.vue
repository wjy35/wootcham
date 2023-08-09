<template>
  <div class="userVideo">
    <MyFace v-if="isMyFace" :stream-manager="streamManager"/>
    <SharedScreen v-else :stream-manager="streamManager"/>
  </div>
</template>

<script>
import MyFace from "@/views/gameroom/components/openvidu/MyFace.vue";
import SharedScreen from "@/views/gameroom/components/openvidu/SharedScreen.vue";

export default {
  name: 'UserVideo',
  components: {
    SharedScreen,
    MyFace,
  },
  props: {
    isMyFace: {
      type: Boolean,
      default: true
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