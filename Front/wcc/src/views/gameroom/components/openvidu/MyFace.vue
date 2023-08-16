<template>
  <video id="myFace" autoplay/>
</template>


<script>
import * as faceapi from 'face-api.js';
import {mapState} from "vuex";

let cam;
let interval;
export default {
  name: 'MyFace',

  props: {
    streamManager: Object,
  },
  data() {
    return {
      isChecked: false,
      sessionId: localStorage.getItem("sessionId"),
      memberToken: localStorage.getItem("memberToken"),
    }
  },
  computed: {
    ...mapState(["client"])
  },
  unmounted() {
    clearInterval(interval);
  },
  mounted() {
    console.log("mounted!!");
    cam = document.getElementById("myFace");
    Promise.all([
      faceapi.nets.tinyFaceDetector.loadFromUri('/models'),
      faceapi.nets.faceLandmark68Net.loadFromUri('/models'),
      faceapi.nets.faceRecognitionNet.loadFromUri('/models'),
      faceapi.nets.faceExpressionNet.loadFromUri('/models')
    ]).then(navigator.mediaDevices
        .getUserMedia({video: true})
        .then(function (stream) {
          cam.srcObject = stream;
        })
        .catch(function (err) {
          console.log(err);
        })
    )
    this.streamManager.addVideoElement(cam);
    this.startDetect();
  },
  methods: {
    async detectSmile(cam) {
      if(this.isChecked) return;

      const detections = await Promise.race([
        new Promise((resolve) => {
          setTimeout(() => {
            resolve(undefined);
          }, 1500);
        }),
        new Promise((resolve) => {
          resolve(faceapi.detectSingleFace(cam, new faceapi.TinyFaceDetectorOptions()).withFaceLandmarks().withFaceExpressions());
        })
      ]);


      let result = await this.check(detections);
      if(result) {
        await new Promise(resolve => setTimeout(resolve, 3000));
        this.isChecked = false;
      }
    },
    async check(detections){
      if (detections===undefined || detections.expressions.happy > 0.8) {
        this.isChecked = true;
        console.log("check")
        this.client.send("/up", JSON.stringify({
          sessionId: this.sessionId,
          memberToken: this.memberToken
        }));

        return true;
      }

      return false;
    },
    startDetect() {
      // this.detectSmile(cam);
      interval = setInterval(async () => {
        await this.detectSmile(cam);
      }, 1500);
    },
  }
};
</script>

<style scoped>
video {
  width: 100%;
  height: auto;
}
</style>