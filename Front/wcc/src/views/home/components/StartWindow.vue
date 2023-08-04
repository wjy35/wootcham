<template>
  <div class="content-window shadow">
    <div class="content">
      <div class="notice-card shadow flex">
        <div class="notice-card-content">
          <!-- <p class="heading">WootCham Club</p> -->
          <p class="para">카메라를 켜지 않으면 게임을 시작할 수 없습니다. <br> 하단 카메라 버튼을 눌러주세요.</p>
        </div>
          <!-- 실시간 웃음 정도 데이터 -->
          <div class="laugh-o-meter">
            <RealtimeGauge :data="realtimeData"/>
          </div>
      </div>

    </div>

    <div class="utility-bar" @click="toggleCamera">
      <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" :stroke="cameraOn ? '#ffffff' : '#ff0000'" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
        <path v-if="cameraOn" d="M15.6 11.6L22 7v10l-6.4-4.5v-1zM4 5h9a2 2 0 0 1 2 2v10a2 2 0 0 1-2 2H4a2 2 0 0 1-2-2V7c0-1.1.9-2 2-2z" />
        <path v-else d="M2 2l19.8 19.8M15 15.7V17a2 2 0 0 1-2 2H4a2 2 0 0 1-2-2V7c0-1.1.9-2 2-2h.3m5.4 0H13a2 2 0 0 1 2 2v3.3l1 1L22 7v10" />
      </svg>
    </div>
  </div>
</template>

<script>
import RealtimeGauge from './RealtimeGauge.vue';

export default {
  components: {
    RealtimeGauge
  },
  data() {
    return {
      cameraOn: false,
      realtimeData: 50 // 실시간 데이터를 저장할 변수 (초기값 0)
    };
  },
  created(){
    // 실시간 데이터를 받는 로직 (예: WebSocket 등)
    // 데이터가 업데이트될 때마다 this.realtimeData를 업데이트합니다.
  },
  methods: {
    toggleCamera() {
      this.cameraOn = !this.cameraOn;
    },
  },
};
</script>

<style scoped>
.content-window {
  position: relative;
  
  background-color: #FFF2EA;
  display: flex;
  justify-content: center;
}
.content-window::before {
  content: '';
  position: absolute;
  top: 46%;
  left: -28px;
  width: 50px; 
  height: 50px; 
  background-image: url('@/assets/images/indicator.png');
  background-size: contain; 
  background-repeat: no-repeat; 
  background-color: transparent;
}

/* NOTICE CARD */
.notice-card {
  position: absolute;
  bottom: 60px;
  right: 20px;
  
  justify-content: space-around;
  
  width: 500px;
  height: 200px;
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
  
  gap: 1em;
  height: 100%;
  transition: transform 0.4s ease;
}

.notice-card:hover .notice-card-content {
  transform: scale(0.96);
}
.laugh-o-meter {
  padding: 5px 0 3px;
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

</style>