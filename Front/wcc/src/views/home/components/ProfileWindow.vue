<template>
  <div class="content-window shadow">
    <div class="content">

      <!-- 프로파일 화면 -->
      <div class="profile-card">
        <div class="top-section">
          <div class="border"></div>
          <div class="icons">
            <div class="logo">
               {{ username }}
            </div>
            <div class="social-media">
              WootCham
            </div>
          </div>
        </div>
        <div class="bottom-section">
          <span class="title">UNIVERSE OF UI</span>
          <div class="row row1">
            <div class="item">
              <span class="big-text">2626</span>
              <span class="regular-text">UI elements</span>
            </div>
            <div class="item">
              <span class="big-text">100%</span>
              <span class="regular-text">Free for use</span>
            </div>
            <div class="item">
              <span class="big-text">38,631</span>
              <span class="regular-text">Contributers</span>
            </div>
          </div>
        </div>
      </div>

      <!-- 전적 기록 화면 -->
      <div class="profile-record-card">
        <div class="profile-image"></div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data(){
    return {
      username: "username"
    };
  }
}
</script>

<style scoped>
.content-window {
  background-color: #FFF2EA;
}

.content-window::before {
  content: '';
  position: absolute;
  top: 10%;
  left: -28px;
  width: 50px; 
  height: 50px; 
  background-image: url('@/assets/images/indicator.png');
  background-size: contain; 
  background-repeat: no-repeat; 
  background-color: transparent;
}

.content {
  display: flex;
  height: 100%; 
  width: 100%;
}

.profile-card {
  width: 400px;
  border-radius: 20px;
  background: #F27059;
  padding: 5px;
  overflow: hidden;
  box-shadow: #F27059;
  transition: transform 0.5s cubic-bezier(0.175, 0.885, 0.32, 1.275);
}

.profile-card:hover {
  transform: scale(1.05);
}

.profile-card .top-section {
  height: 300px;
  border-radius: 15px;
  display: flex;
  flex-direction: column;
  background: linear-gradient(45deg, #FFF2EA 0%, #F27059 100%);
  position: relative;
}

.profile-card .top-section .border {
  border-bottom-right-radius: 10px;
  height: 30px;
  width: 130px;
  background: white;
  background: #FFF2EA;
  position: relative;
  transform: skew(-40deg);
  box-shadow: -10px -10px 0 0 #FFF2EA;
}

.profile-card .top-section .border::before {
  content: "";
  position: absolute;
  width: 15px;
  height: 15px;
  top: 0;
  right: -15px;
  background: rgba(255, 255, 255, 0);
  border-top-left-radius: 10px;
  box-shadow: -5px -5px 0 2px #F27059;
}

.profile-card .top-section::before {
  content: "";
  position: absolute;
  top: 30px;
  left: 0;
  background: rgba(255, 255, 255, 0);
  height: 15px;
  width: 15px;
  border-top-left-radius: 15px;
  box-shadow: -5px -5px 0 2px #1b233d;
}

.profile-card .top-section .icons {
  position: absolute;
  top: 0;
  width: 100%;
  height: 30px;
  display: flex;
  justify-content: space-between;
}

.profile-card .top-section .icons .logo {
  height: 100%;
  aspect-ratio: 1;
  padding: 7px 0 7px 15px;
  color: white;
}

.profile-card .top-section .icons .logo .top-section {
  height: 100%;
}

.profile-card .top-section .icons .social-media {
  height: 100%;
  padding: 8px 15px;
  display: flex;
  gap: 7px;
}

.profile-card .top-section .icons .social-media .svg {
  height: 100%;
  fill: #1b233d;
}

.profile-card.top-section .icons .social-media .svg:hover {
  fill: white;
}

.profile-card .bottom-section {
  margin-top: 15px;
  padding: 10px 5px;
}

.profile-card.bottom-section .title {
  display: block;
  font-size: 17px;
  font-weight: bolder;
  color: white;
  text-align: center;
  letter-spacing: 2px;
}

.profile-card .bottom-section .row {
  display: flex;
  justify-content: space-between;
  margin-top: 20px;
}

.profile-card .bottom-section .row .item {
  flex: 30%;
  text-align: center;
  padding: 5px;
  color: rgba(170, 222, 243, 0.721);
}

.profile-card .bottom-section .row .item .big-text {
  font-size: 12px;
  display: block;
  color: white;
  font-weight: 700;
}

.profile-card .bottom-section .row .item .regular-text {
  font-size: 9px;
}

.profile-card .bottom-section .row .item:nth-child(2) {
  border-left: 1px solid rgba(255, 255, 255, 0.126);
  border-right: 1px solid rgba(255, 255, 255, 0.126);
}
</style>