<template>
	<!-- <video autoplay /> -->
	<div>
		<!-- <input class="btn btn-large btn-danger" type="button" id="hi" @click="hi" />
		<input class="btn btn-large btn-danger" type="button" id="startExpressDetection" @click="detection" /> -->
		<video ref="myWebCam" @mouseover="startExpressDetection" :class="{ 'webcam': true }" autoplay playsinline controls="false" />
	</div>
</template>

<script>

import { onMounted, reactive, ref, watch } from 'vue'
// import { ElMessage } from 'element-plus'
// import { useStore } from 'vuex'
import * as faceapi from 'face-api.js'

export default {
	name: 'OvVideo',

	props: {
		streamManager: Object,
	},

	setup(props) {

		console.log("SetUp Called..!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!")

		const myWebCam = ref(null)

		const state = reactive({
			detections: null,
			myEmotion: '',
			webCamWidth: 0,
			webCamHeight: 0,
			hide: false,
			lie: false,
			timerId: 0,
			emotions: {
				angry: 0,
				disgusted: 0,
				fearful: 0,
				happy: 0,
				neutral: 0,
				sad: 0,
				surprised: 0,
			},
		})

		const startExpressDetection = async () => {

			state.timerId = setInterval(async () => {
				state.detections = await faceapi.detectSingleFace(myWebCam.value, new faceapi.TinyFaceDetectorOptions())
					.withFaceExpressions()

				console.log("감정 탐지 시작!!!!!!!!!!!!!!!!!!!!!!!!!!!!")
				console.log(state.detections.expressions)
				// if (state.detections) {
				// 	let maxVal = 0
				// 	let maxEmotion = ''
				// 	for (let emotion in state.detections.expressions) {
				// 		if (state.detections.expressions[emotion] > maxVal) {
				// 			maxVal = state.detections.expressions[emotion]
				// 			maxEmotion = emotion
				// 		}
				// 		console.log(emotion)
				// 	}
				// 	state.emotions[maxEmotion]++
				// }


			}, 250)

		}

		onMounted(async () => {
			props.streamManager.addVideoElement(myWebCam.value)
			await faceapi.nets.tinyFaceDetector.load('/static/models')
			await faceapi.nets.faceExpressionNet.load('/static/models')
		})

		watch(() => state.show, () => {
			if (state.show) {
				setTimeout(() => {
					state.show = false
				}, 3000)
			}
		})

		return { state, myWebCam, startExpressDetection }

	},

};
</script>
