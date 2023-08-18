<template>
  <div class="start-window shadow">


    <div id="container">
    
        
        <input hidden type="radio" name="carousel-control" id="button_a" checked/>
        <input hidden type="radio" name="carousel-control" id="button_b"/>
        <input hidden type="radio" name="carousel-control" id="button_c"/>
        <input hidden type="radio" name="carousel-control" id="button_d"/>            
                
        
        <div id="carousel">
            <div class="p-wrapper">
                        
                <section class="panel_a">
                    <h1 v-if="!cameraOn">카메라를 키고 웃어주세요!</h1>
                    <h1 v-else-if="!hits">크게 웃어보세요!</h1>
                    <h1 v-else>웃었습니다!</h1>
                    <div>
                      <RealtimeGauge class='laugh-gauge' :data="realtimeData" />
                      <video id="video" autoplay>
                        <div class="laugh-meter"></div>
                      </video>
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
                </section>
                        
                <section class="panel_b">
                    <h1 v-if="!micOn">마이크를 켜주세요!</h1>
                    <h1 v-else>마이크가 켜진 상태입니다</h1>
                    <div class="mic-zone">
                      <img class='mic micoff' @click="toggleMic" v-if="!micOn" src="@/assets/images/micoff.png">
                      <img class='mic' v-else src="@/assets/images/micon.png">
                    </div>
                </section>
                        
                <section class="panel_c">
                    <h1 v-if="!screenOn">화면을 공유해주세요!</h1>
                    <h1 v-else>화면을 공유했습니다</h1>
                    <video id="screen" autoplay></video>
                    <div class="utility-bar" @click="toggleScreen">
                      <img class='stream-icon' src="@/assets/images/stream.png">
                    </div>
                </section>
                        
                <section class="panel_d">
                    <h1>{{ missionFinish }}</h1>
                    <h2 v-if="ready">시작하기를 누르면 게임 매칭을 시작합니다</h2>
                    <img class='startlogo' src="@/assets/logo.png">
                </section>
                        
            </div> <!-- // .p-wrapper -->
        </div> <!-- // #carousel -->            
                
        
        <div id="navigation">
            <label for="button_a" class="label_a"> <span>1</span> </label>
            <label for="button_b" class="label_b"> <span>2</span> </label>
            <label for="button_c" class="label_c"> <span>3</span> </label>
            <label for="button_d" class="label_d"> <span>*</span> </label>
        </div> <!-- // #navigation -->               
                
        
    </div> <!-- // #container -->

    <!-- <div class="home-logo">
      <img src="@/assets/logo.png" alt="" style="width: 300px;">
    </div>
    

    <div v-if='cameraOn' class="notice-card shadow flex">
      <div class="notice-card-content">
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
    </div> -->

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
      cameraOn: false, // 1단계: 카메리 키기
      realtimeData: 50, // 실시간 데이터를 저장할 변수 (초기값 0)
      hits: false, // 1단계: 웃기
      micOn: false, // 2단계: 마이크 키기
      screenOn: false, // 3단계: 화면 키기
      missionFinish: '모든 단계를 완료해주세요!'
    };
  },
  computed: {
    ...mapState(['ready'])
  },
  created() {
    // 실시간 데이터를 받는 로직 (예: WebSocket 등)
    // 데이터가 업데이트될 때마다 this.realtimeData를 업데이트합니다.
  },
  mounted() {
    if (this.ready) {
      this.toggleCamera();
      this.toggleMic();
      this.toggleScreen();
    }
  },
  watch: {
    hits() {
      if (this.hits && this.micOn && this.screenOn) {
        this.missionFinish = '모든 단계가 완료되었습니다'
        this.$store.commit('setReady', true);
      }
    },
    micOn() {
      if (this.hits && this.micOn && this.screenOn) {
        this.missionFinish = '모든 단계가 완료되었습니다'
        this.$store.commit('setReady', true);
      }
    },
    screenOn() {
      if (this.hits && this.micOn && this.screenOn) {
        this.missionFinish = '모든 단계가 완료되었습니다'
        console.log('ready');
        this.$store.commit('setReady', true);
      }
    }
  },
  methods: {
    toggleCamera() {
      let video = document.getElementById('video');

      this.cameraOn = !this.cameraOn

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
          } else {
            this.realtimeData = detections.expressions.happy * 100;
            if (this.realtimeData > 70 && !this.hits) {
              this.hits = true;
            }
          }
        } catch (err) {
          console.log(err);
        }
      }, 500);
    },
    toggleMic() {
      navigator.mediaDevices.getUserMedia({ audio: true }).then(() => {
        this.micOn = true;
      })
    },
    toggleScreen() {
      let screen = document.getElementById('screen');

      navigator.mediaDevices.getDisplayMedia({ video: true })
        .then(function (stream) {
          screen.srcObject = stream;
        }).then(() => {
          this.screenOn = true;
        }).catch((err) => console.log(err))
    }

  }
}

</script>

<style scoped>
.start-window {
  position: relative;
  background-color: #FFF2EA;
  display: flex;
  justify-content: center;
  align-items: center;

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



.home-logo img {
  position: absolute;
  right: 350px;
  bottom: 80px;
  animation: shake 2s infinite;
}


/* NOTICE CARD */
.notice-card {
  position: absolute;
  bottom: 150px;
  right: 38px;
  
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
  height: 8%;
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
}

/* 로고 흔드는 효과 */
@keyframes shake {
  0%, 100% {
    transform: translateX(0);
  }
  25% {
    transform: translateX(-5px) rotate(-5deg);
  }
  50% {
    transform: translateX(5px) rotate(5deg);
  }
  75% {
    transform: translateX(-5px) rotate(-5deg);
  }
}

#container {
    display: flex;
    justify-content: center;
    align-items: center;
    min-height: 100vh;
}
            
#carousel, #navigation {
    flex: initial;
    display: block;
}
            
#carousel {
    overflow: hidden;
    width: 600px;
    height: 500px;
    border-radius: 4px;
    background-color: #fff;
}
            
.p-wrapper {
    position: relative;
    width: 100%;
    height: 100%;
    transition: transform 0.4s ease-in-out;
}
            
#button_a:checked ~ #carousel .p-wrapper {
    -webkit-transform: translateY(0);
    transform: translateY(0); 
}
            
#button_b:checked ~ #carousel .p-wrapper {
    -webkit-transform: translateY(-100%);
    transform: translateY(-100%); 
}

#button_c:checked ~ #carousel .p-wrapper {
    -webkit-transform: translateY(-200%);
    transform: translateY(-200%); 
}

#button_d:checked ~ #carousel .p-wrapper {
    -webkit-transform: translateY(-300%);
    transform: translateY(-300%); 
}
            
[class^="panel_"] {
    overflow: auto;
    position: relative;
    width: inherit;
    height: inherit;
    padding: 24px;
    text-align: center;
}
            
#navigation {
    margin-left: 24px;
}

[class^="label_"] {
    display: block;
    cursor: pointer;
    margin: 8px 0;
    width: 32px;
    height: 32px;
    line-height: 32px;
    text-align: center;
    border-radius: 4px;
    color: #aaa;
    background-color: #fff;
    transition: all 0.3s ease;
}
            
[class^="label_"]:hover {
    color: #444;
    background-color: #ffeed9;
}
            
#button_a:checked ~ #navigation .label_a,
#button_b:checked ~ #navigation .label_b,
#button_c:checked ~ #navigation .label_c,
#button_d:checked ~ #navigation .label_d {
     cursor: default;
     color: #fff;
     background-color: #FF7B27;
}

.panel-a {
  display: flex;
  justify-content: center;
}
.laugh-gauge {
  width: 10%;
  height: 100%;
}

#video {
  width: 90%;
  border-radius: 20px;
  height: auto;
  object-fit: cover;
}

.mic-zone {
  display: flex;
  justify-content: center;
  width: 350px;
  height: 350px;
  margin: auto;
}

.mic {
  width: 50%;
  height: 50%;
  margin: auto;
}

.micoff {
  cursor: pointer;
}

#screen {
  width: 90%;
  border-radius: 20px;
  height: auto;
  object-fit: cover;
}
.stream-icon {
  width: 24px;
  height: 24px;
}

.startlogo {
  position: absolute;
  left: 150px;
  bottom: 50px;
  width: 300px;
  height: auto;
  animation: shake 2s infinite;
}
</style>
