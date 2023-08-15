<template>
	<video id="myFace" autoplay />
</template>


<script>
import * as faceapi from 'face-api.js';
import {mapState} from "vuex";

let cam;

export default {
	name: 'MyFace',

	props: {
		streamManager: Object,
	},
  data() {
    return {
      isChecked: false,
    }
  },
  computed: {
    ...mapState(["client"])
  },
	mounted() {
		cam = document.getElementById("myFace");
		Promise.all([
			faceapi.nets.tinyFaceDetector.loadFromUri('/models'),
			faceapi.nets.faceLandmark68Net.loadFromUri('/models'),
			faceapi.nets.faceRecognitionNet.loadFromUri('/models'),
			faceapi.nets.faceExpressionNet.loadFromUri('/models')
		]).then(navigator.mediaDevices
			.getUserMedia({ video: true })
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

	updated() {
		cam = document.getElementById("myFace");
		Promise.all([
			faceapi.nets.tinyFaceDetector.loadFromUri('/models'),
			faceapi.nets.faceLandmark68Net.loadFromUri('/models'),
			faceapi.nets.faceRecognitionNet.loadFromUri('/models'),
			faceapi.nets.faceExpressionNet.loadFromUri('/models')
		]).then(navigator.mediaDevices
			.getUserMedia({ video: true })
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
			const detections = await faceapi.detectSingleFace(cam, new faceapi.TinyFaceDetectorOptions()).withFaceLandmarks().withFaceExpressions()
			if(!this.isChecked){
        if (detections === undefined || detections.expressions.happy > 0.75) {

          this.client.send("/up",JSON.stringify({
            sessionId: localStorage.getItem("sessionId"),
            memberToken: localStorage.getItem("memberToken")
          }));

          this.isChecked = true;
          setTimeout(() => this.isChecked=false, 5000);
        }
      }
		},
		startDetect() {
			setInterval(() => {
				this.detectSmile(cam);
			}, 1000);
		}
	}
};
</script>

<style scoped>
video {
	width: 100%;
	height: auto;
}
</style>