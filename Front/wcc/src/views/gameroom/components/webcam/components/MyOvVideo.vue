<template>
	<div>
		<video :id="streamManager.stream.connection.connectionId" autoplay/>
		<!-- <button @click="stopDetect">stop</button>
		<button @click="startDetect">start</button> -->
	</div>
</template>

<script>
import * as faceapi from 'face-api.js';

let cam;
let interval;

export default {
	name: 'MyOvVideo',

	props: {
		streamManager: Object,
	},

	data() {
		return {
			laughCount: 0,
			outOfFrame: 0,
		}
	},

	mounted () {
		cam = document.getElementById(this.streamManager.stream.connection.connectionId);
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

	updated () {
		console.log(this.streamManager)
		cam = document.getElementById(this.streamManager.stream.connection.connectionId);
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

	unmounted() {
		console.log('destroyed');
		this.stopDetect();
	},

	methods: {
		async detectLaugh(id, cam) {
			console.log('detecting ' + id);
			const detections = await faceapi.detectSingleFace(cam, new faceapi.TinyFaceDetectorOptions()).withFaceLandmarks().withFaceExpressions()
			if (detections === undefined) {
				this.outOfFrame += 1;
				console.log("out of frame")
			} else {
				if (detections.expressions.happy > 0.7) {
					console.log("laugh")
					this.laughCount += 1;
				} else {
					console.log('neutral')
				}
			}
		},
		stopDetect() {
			console.log('stop')
			clearInterval(interval);
		},
		startDetect() {
			console.log('start')
			interval = setInterval(() => {
				this.detectLaugh(this.streamManager.stream.connection.connectionId, cam);
			}, 1000);
		}
	}
};
</script>
<style scoped>
</style>
