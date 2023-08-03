<template>
  <div class="content-window shadow">
    <div class="content">
      <div class="notice-card shadow">
        <div class="notice-card-content">
          <p class="heading">WootCham Club</p>
          <p class="para">카메라를 켜지 않으면 게임을 시작할 수 없습니다. <br> 하단 카메라 버튼을 눌러주세요.</p>
        </div>
      </div>
    </div>

    <div class="gauge">
      <ul class="meter">
        <li class="low"></li>
        <li class="normal"></li>
        <li class="high"></li>
      </ul>

      <div class="dial">
          <div class="inner" :style="dialStyle">
            <div class="arrow">
            </div>
          </div>			
      </div>

      <div class="value">
        {{ gaugeValue }}
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
export default {
  data() {
    return {
      cameraOn: false,
      gaugeValue: 0,
      dialRotation: 0
    };
  },
  methods: {
    toggleCamera() {
      this.cameraOn = !this.cameraOn;
    },
    rotateDial() {
      const value = Math.round(Math.random() * 100);
      const deg = (value * 177.5) / 100;

      this.gaugeValue = value;
      this.dialRotation = deg;
    },
  },
  computed: {
    dialStyle() {
      return {
        transform: `rotate(${this.dialRotation}deg)`,
        msTransform: `rotate(${this.dialRotation}deg)`,
        mozTransform: `rotate(${this.dialRotation}deg)`,
        oTransform: `rotate(${this.dialRotation}deg)`,
        webkitTransform: `rotate(${this.dialRotation}deg)`,
      };
    },
  },
  mounted() {
    setInterval(this.rotateDial, 2000);
  },
};
</script>

<style scoped>
.content-window {
  background-color: #FFF2EA;
  display: flex;
  justify-content: center;
  align-items: center;
}
.content-window::before {
  content: '';
  position: absolute;
  top: 45%;
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

/* ------------ GAUGE ------------- */ 
.gauge {
  position: absolute;
  width: 500px;
  height: 500px;
  top: 30px;
  left: 50%;
  margin-left: -250px;
  border-radius: 100%;
  transform-origin: 50% 50%;
  -webkit-transform-origin: 50% 50%;
  -ms-transform-origin: 50% 50%;
  -webkit-transform: rotate(0deg);
}

.meter
{
  margin: 0;
  padding: 0;
}

.meter > li
{
  width: 250px;
  height: 250px;
  list-style-type: none;
  position: absolute;
  border-top-left-radius: 250px;
  border-top-right-radius: 0px;
  transform-origin:  100% 100%;;
  -webkit-transform-origin:  100% 100%;;
  -ms-transform-origin:  100% 100%;;
  transition-property: -webkit-transform;
  pointer-events: none;
}

.meter .low
{
  transform: rotate(0deg); /* W3C */
  -webkit-transform: rotate(0deg); /* Safari & Chrome */
  -moz-transform: rotate(0deg); /* Firefox */
  -ms-transform: rotate(0deg); /* Internet Explorer */
  -o-transform: rotate(0deg); /* Opera */
  z-index: 8;
  background-color: #09B84F;
}

.meter .normal
{
  transform: rotate(47deg); /* W3C */
  -webkit-transform: rotate(47deg); /* Safari & Chrome */
  -moz-transform: rotate(47deg); /* Firefox */
  -ms-transform: rotate(47deg); /* Internet Explorer */
  -o-transform: rotate(47deg); /* Opera */
  z-index: 7;
  background-color: #FEE62A;
}

.meter .high
{
  transform: rotate(90deg); /* W3C */
  -webkit-transform: rotate(90deg); /* Safari & Chrome */
  -moz-transform: rotate(90deg); /* Firefox */
  -ms-transform: rotate(90deg); /* Internet Explorer */
  -o-transform: rotate(90deg); /* Opera */
  z-index: 6;
  background-color: #FA0E1C;
}


.dial,
.dial .inner
{
  width: 470px;
  height: 470px;
  position: relative;
  top: 10px;
  left: 5px;
  border-radius: 100%;
  border-color: purple;
  z-index: 10;
  transition-property: -webkit-transform;
  transition-duration: 1s;
  transition-timing-function: ease-in-out;
  transform: rotate(0deg); /* W3C */
  -webkit-transform: rotate(0deg); /* Safari & Chrome */
  -moz-transform: rotate(0deg); /* Firefox */
  -ms-transform: rotate(0deg); /* Internet Explorer */
  -o-transform: rotate(0deg); /* Opera */
}

.dial .arrow
{
  width: 0; 
  height: 0; 
  position: absolute;
  top: 214px;
  left: 24px;
  border-left: 5px solid transparent;
  border-right: 5px solid transparent;
  border-bottom: 32px solid #FFFFFF;
  -webkit-transform: rotate(-88deg); /* Safari & Chrome */
  -moz-transform: rotate(88deg); /* Firefox */
  -ms-transform: rotate(88deg); /* Internet Explorer */
  -o-transform: rotate(88deg); /* Opera */

}

.gauge .value
{
  font-family: 'Josefin Slab', serif;
  font-size: 50px;
  color: #ffffff;
  position: absolute;
  top: 142px;
  left: 45%;
  z-index: 11;
}

</style>