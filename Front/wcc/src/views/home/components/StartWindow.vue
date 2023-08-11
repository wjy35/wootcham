<template>
  <div class="start-window shadow">

    <RealtimeGauge :data="realtimeData" />

    <video id="video" autoplay>
      <!-- 실시간 웃음 정도 데이터 -->
      <div class="laugh-meter"></div>
    </video>

    <div v-if='!cameraOn' class="notice-card shadow flex">
      <div class="notice-card-content">
        <!-- <p class="heading">WootCham Club</p> -->
        <p class="para">하단 카메라 버튼을 눌러서 웃음 감지가 잘 작동하는지 확인해주세요.</p>
        <p class="para-important">좌측 게이지에 정보가 표시됩니다.</p>
      </div>
    </div>

    <div class="utility-bar" @click="toggleCamera">
      <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none"
        :stroke="cameraOn ? '#ffffff' : '#ff0000'" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
        <path v-if="cameraOn"
          d="M15.6 11.6L22 7v10l-6.4-4.5v-1zM4 5h9a2 2 0 0 1 2 2v10a2 2 0 0 1-2 2H4a2 2 0 0 1-2-2V7c0-1.1.9-2 2-2z" />
        <path v-else
          d="M2 2l19.8 19.8M15 15.7V17a2 2 0 0 1-2 2H4a2 2 0 0 1-2-2V7c0-1.1.9-2 2-2h.3m5.4 0H13a2 2 0 0 1 2 2v3.3l1 1L22 7v10" />
      </svg>
    </div>

  </div>
</template>

<script>
import RealtimeGauge from './RealtimeGauge.vue';
import * as faceapi from 'face-api.js';
import { mapState } from 'vuex';

export default {
  components: {
    RealtimeGauge
  },
  data() {
    return {
      // cameraOn: false,
      realtimeData: 50, // 실시간 데이터를 저장할 변수 (초기값 0)
      warning: '프레임에서 벗어났습니다.',
      showWarning: false,
      laughing: false,
    };
  },
  computed: {
    ...mapState('gameStore', ['ready'])
  },
  created() {
    // 실시간 데이터를 받는 로직 (예: WebSocket 등)
    // 데이터가 업데이트될 때마다 this.realtimeData를 업데이트합니다.
  },
  mounted() {
    if (this.ready) {
      this.toggleCamera();
    }
  },
  methods: {
    toggleCamera() {
      console.log(this.ready)
      this.$store.commit('gameStore/SET_READY');
      let video = document.getElementById('video');

      if (!navigator.mediaDevices || !navigator.mediaDevices.getUserMedia) {
        alert('사용 가능한 카메라가 없습니다.');
        return;
      }
      Promise.all([
        faceapi.nets.tinyFaceDetector.loadFromUri('/models'),
        faceapi.nets.faceLandmark68Net.loadFromUri('/models'),
        faceapi.nets.faceRecognitionNet.loadFromUri('/models'),
        faceapi.nets.faceExpressionNet.loadFromUri('/models')
      ]).then(navigator.mediaDevices
        .getUserMedia({ video: true })
        .then(function (stream) {
          video.srcObject = stream;
        })
        .catch(function (err) {
          console.log(err);
        }))
      setInterval(async () => {
        try {
          const detections = await faceapi.detectSingleFace(video, new faceapi.TinyFaceDetectorOptions()).withFaceLandmarks().withFaceExpressions()
          if (detections === undefined) {
            // this.showWarning = true;
          } else {
            // this.showWarning = false;
            this.realtimeData = detections.expressions.happy * 100;
          }
        } catch (err) {
          console.log(err);
        }
      }, 500);
    },

  }
}

</script>

<style scoped>
.start-window {
  position: relative;
  background-color: #FFF2EA;
  display: flex;
  justify-content: center;

  height: calc(100vh - 150px);
  width: 1000px;
  margin: 70px 30px 0;
  border-radius: 25px;
  border: 3px solid #FFCDAD;
  padding: 0;
}

.start-window::before {
  content: '';
  position: absolute;
  top: 46%;
  left: -31px;
  width: 50px;
  height: 50px;
  background-image: url('@/assets/images/indicator.png');
  background-size: contain;
  background-repeat: no-repeat;
  background-color: transparent;
}

#video {
  border-radius: 0 25px 25px 0;
  width: 100%;
  height: 100%;
  object-fit: cover;
  position: relative;
}

/* NOTICE CARD */
.notice-card {
  position: absolute;
  bottom: 70px;
  right: 10px;

  align-items: center;
  justify-content: center;

  width: 300px;
  height: 150px;
  background: #FFF2EA;
  border: 5px solid #FFF2EA;
  border-radius: 10px;
  transition: border-radius 0.5s cubic-bezier(0.175, 0.885, 0.32, 1.275);
}

.notice-card-content {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;

  height: 100%;
  transition: transform 0.4s ease;
}

.notice-card:hover .notice-card-content {
  transform: scale(0.96);
}


.heading {
  color: #FF7B27;
  font-family: Luckiest Guy, cursive;
  font-size: 60px;
  font-style: normal;
  font-weight: 400;
  line-height: normal;
}

.para {
  color: #714538;
  font-family: Noto Sans;
  font-size: 15px;
  font-style: normal;
  font-weight: 700;
  text-align: center;
}

.para-important {
  color: #FF7B27;
  font-family: Noto Sans;
  font-size: 15px;
  font-style: normal;
  font-weight: 1000;
  text-align: center;
}

/* UTILITY BAR */
.utility-bar {
  height: 7%;
  display: flex;
  justify-content: center;
  align-items: flex-end;
  position: absolute;
  bottom: 0;
  left: 0;
  right: 0;

  padding: 0.6em 1.1em;
  background-color: #63687B;

  border-top: 1px solid white;
  border-radius: 0 0 25px 25px;
  gap: 0.4rem;

  font-weight: bold;
  text-shadow: 2px 2px 3px rgb(136 0 136 / 50%);

  background: linear-gradient(15deg, #880088, #aa2068, #cc3f47, #de6f3d, #f09f33, #de6f3d, #cc3f47, #aa2068, #880088) no-repeat;
  background-size: 300%;
  background-position: left center;
  transition: background .3s ease;
  color: #fff;
}

.utility-bar:hover {
  background-size: 320%;
  background-position: right center;
  cursor: pointer;
}

.utility-bar:hover svg {
  fill: #fff;
}

.utility-bar svg {
  width: 23px;
  fill: #aa2068;
  transition: .3s ease;
}

.upper {
  z-index: -1;
}</style>