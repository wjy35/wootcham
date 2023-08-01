<template>
  <div class="content-window shadow">
    <div class="content">
      <div class="notice-card shadow">
        <div class="notice-card-content">
          <p class="heading">WootCham Club</p>
          <p class="para">게임 설명... <br> 하단 카메라 버튼을 눌러주세요.</p>
        </div>
      </div>
    </div>

      <div class="utility-bar">
        
      </div>
  </div>
</template>

<style>
.content-window {
  background-color: #DEDEDE;
}

/* NOTICE CARD */
.notice-card {
 width: 500px;
 height: 200px;
 background: rgba(255, 255, 255, 0.50);
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
  height: 8%; 
  display: flex;
  justify-content: center;
  align-items: flex-end;
  position: absolute;
  bottom: 0; 
  left: 0;
  right: 0;
  background-color: #63687B;

  border-radius: 0 0 25px 25px; 
}

.camera-btn { 
  color: #212121;
}

</style>