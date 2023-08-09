<template>
  <div class="inner">
      <!-- HEADER -->
      <header>
          <div class="header-center">게임이 종료되었습니다.</div>
      </header>
      
      <div class="main-container">
          <div class="ranking-modal shadow">
              <div class="rank-card"></div>
              <div class="rank-card"></div>
              <div class="rank-card"></div>
              <div class="rank-card"></div>
              <div class="rank-card"></div>

              <div class="rank-card">
                <div class="rank-card-image">
                  <img src="@/assets/images/profile.png" alt="">
                </div>
                <div class="rank-card-detail">
                  <div class="rank">#{{ rank }}</div>
                  <div class="username">{{ username }}</div>
                  <div>
                    <div class="pts-earned">{{  }}</div>
                  </div>
                  <div class="report-btn"></div>
                </div>
                

              </div>

            
          </div>

          <div class="result-summary-modal shadow">

            <div class="banner"> 
              <img src="@/assets/images/game_result_banner.png" alt="explain_banner">
            </div>
            <div class="results-summary-container">
              <div class="confetti">
                <div class="confetti-piece"></div>
                <div class="confetti-piece"></div>
                <div class="confetti-piece"></div>
                <div class="confetti-piece"></div>
                <div class="confetti-piece"></div>
                <div class="confetti-piece"></div>
                <div class="confetti-piece"></div>
                <div class="confetti-piece"></div>
                <div class="confetti-piece"></div>
                <div class="confetti-piece"></div>
                <div class="confetti-piece"></div>
                <div class="confetti-piece"></div>
                <div class="confetti-piece"></div>
                <div class="confetti-piece"></div>
                <div class="confetti-piece"></div>
                <div class="confetti-piece"></div>
                <div class="confetti-piece"></div>
                <div class="confetti-piece"></div>
                <div class="confetti-piece"></div>
              </div>
            </div>

            <!-- 버튼 -->
            <div class="gameend-option-btn">
                <div class="gameend-option-one">한 판 더하기</div>
                <di class="gameend-option-two">홈으로 나가기</di>
            </div>

          </div>
      </div>

  </div>
</template>

<script>

</script>

<style scoped>
/* ------------ HEADER --------------- */
header {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  background-color: #F27059;
  z-index: 999;
  
  display: flex;
  justify-content: space-between;
  align-items: center;

  padding: 10px;
  border-top: 1px solid white;
  border-bottom: 1px solid white;
}

.header-center {
  display: flex;
  align-items: center;
  justify-content: center;
  flex-grow: 1;

  color: #FFF;
  text-shadow: 2px 2px 4px #FF432A;
  font-size: 30px;
  font-style: normal;
  font-weight: 400;
  line-height: normal;
}

/* ------------ 화면 배치 ------------- */
.main-container {
  position: relative;
  height: 100vh;
  overflow: hidden;
  
  padding: 60px 15px 0;
  border: 3px solid #FFCDAD;
  background-color: #FFCDAD;

  display: flex; 
  justify-content: center; 
  align-items: center;
  gap: 30px;
}

/* ------------ 랭킹 ------------ */ 
.ranking-modal {
  position: relative;
	width: 400px;
	height: 570px;
  
	background-color: #FFF2EA;
	border-radius: 15px;
  padding: 10px;

  display: flex;
  flex-direction: column;
  gap: 10px;
}

.rank-card {
  flex: 1;
  background-color: #F27059;
  border-radius: 15px;

  display: flex;
  align-items: center;
  width: 100%;
}

.rank-card-image img{
  width: 60px;
  height: 60px;
  margin: 0 25px 0;
  border-radius: 50%;

  flex-shrink: 0;
}

.rank-card-detail {
  flex-grow: 1;
  background-color: #FFF2EA;
  border-radius: 50px 20px;
  margin: 10px 3px 10px 0;
  height: 93%;

  display: flex;
  align-items: center;
  justify-content: center;


}

/* ------- 게임 결과 요약 ------- */ 
.result-summary-modal {
	background-color: #FFF2EA;
	width: 500px;
	height: 570px;

  border: 3px solid #F27059;
	border-radius: 15px;
}

.banner img {
  width: 350px;
  margin-top: -60px;
}

.results-summary-container {
  font-family: Luckiest Guy;
  display: flex;
  flex-direction: column;
  width: 100%;
  gap: 10px;

  border-radius: 10px;
  backface-visibility: hidden;
}

.confetti {
  display: flex;
  justify-content: center;
  align-items: center;
  position: absolute;
  width: 450px;
  height: 50%;
  overflow: hidden;
  z-index: 1000;
}

.confetti-piece {
  position: absolute;
  width: 10px;
  height: 20px;
  background-color: hsl(39, 100%, 56%);
  top: 0;
  opacity: 0;
  animation: makeItRain 3000ms infinite ease-in-out;
}

.confetti-piece:nth-child(1) {
  left: 7%;
  transform: rotate(-10deg);
  animation-delay: 182ms;
  animation-duration: 2000ms;
}

.confetti-piece:nth-child(2) {
  left: 14%;
  transform: rotate(20deg);
  animation-delay: 161ms;
  animation-duration: 2076ms;
}

.confetti-piece:nth-child(3) {
  left: 21%;
  transform: rotate(-51deg);
  animation-delay: 481ms;
  animation-duration: 2103ms;
}

.confetti-piece:nth-child(4) {
  left: 28%;
  transform: rotate(61deg);
  animation-delay: 334ms;
  animation-duration: 1008ms;
}

.confetti-piece:nth-child(5) {
  left: 35%;
  transform: rotate(-52deg);
  animation-delay: 302ms;
  animation-duration: 1776ms;
}

.confetti-piece:nth-child(6) {
  left: 42%;
  transform: rotate(38deg);
  animation-delay: 180ms;
  animation-duration: 1168ms;
}

.confetti-piece:nth-child(7) {
  left: 49%;
  transform: rotate(11deg);
  animation-delay: 395ms;
  animation-duration: 1200ms;
}

.confetti-piece:nth-child(8) {
  left: 56%;
  transform: rotate(49deg);
  animation-delay: 14ms;
  animation-duration: 1887ms;
}

.confetti-piece:nth-child(9) {
  left: 63%;
  transform: rotate(-72deg);
  animation-delay: 149ms;
  animation-duration: 1805ms;
}

.confetti-piece:nth-child(10) {
  left: 70%;
  transform: rotate(10deg);
  animation-delay: 351ms;
  animation-duration: 2059ms;
}

.confetti-piece:nth-child(11) {
  left: 77%;
  transform: rotate(4deg);
  animation-delay: 307ms;
  animation-duration: 1132ms;
}

.confetti-piece:nth-child(12) {
  left: 84%;
  transform: rotate(42deg);
  animation-delay: 464ms;
  animation-duration: 1776ms;
}

.confetti-piece:nth-child(13) {
  left: 91%;
  transform: rotate(-72deg);
  animation-delay: 429ms;
  animation-duration: 1818ms;
}

.confetti-piece:nth-child(14) {
  left: 94%;
  transform: rotate(-72deg);
  animation-delay: 429ms;
  animation-duration: 818ms;
}

.confetti-piece:nth-child(15) {
  left: 96%;
  transform: rotate(-72deg);
  animation-delay: 429ms;
  animation-duration: 2818ms;
}

.confetti-piece:nth-child(16) {
  left: 98%;
  transform: rotate(-72deg);
  animation-delay: 429ms;
  animation-duration: 2818ms;
}

.confetti-piece:nth-child(17) {
  left: 50%;
  transform: rotate(-72deg);
  animation-delay: 429ms;
  animation-duration: 2818ms;
}

.confetti-piece:nth-child(18) {
  left: 60%;
  transform: rotate(-72deg);
  animation-delay: 429ms;
  animation-duration: 1818ms;
}

.confetti-piece:nth-child(odd) {
  background-color: hsl(0, 100%, 67%);
}

.confetti-piece:nth-child(even) {
  z-index: 1;
}

.confetti-piece:nth-child(4n) {
  width: 6px;
  height: 14px;
  animation-duration: 4000ms;
  background-color: #c33764;
}

.confetti-piece:nth-child(5n) {
  width: 3px;
  height: 10px;
  animation-duration: 4000ms;
  background-color: #b06ab3;
}

.confetti-piece:nth-child(3n) {
  width: 4px;
  height: 12px;
  animation-duration: 2500ms;
  animation-delay: 3000ms;
  background-color: #dd2476;
}

.confetti-piece:nth-child(3n-7) {
  background-color: hsl(166, 100%, 37%);
}

@keyframes makeItRain {
  from {
    opacity: 0;
  }

  50% {
    opacity: 1;
  }

  to {
    transform: translateY(200px);
  }
}

.gameend-option-btn {
    gap: 50px;
    display: flex;
    align-items: center;
    justify-content: center;

    font-size: 2em;
}

.gameend-option-one {
    color: #FF7B27;
    cursor: pointer;
    font-size: 1em;
}

.modal-content-btn div:hover {
    transform: translateY(3px);
}

.gameend-option-two {
    transition: all 0.3s ease-in-out;
    width: 150px;
    height: 60px;
    border-radius: 25px;
    background-image: linear-gradient(135deg, #feb692 0%, #ea5455 100%);
    box-shadow: 0 20px 30px -6px rgba(238, 103, 97, 0.5);
    outline: none;
    cursor: pointer;
    
    border: none;
    font-size: 24px;
    color: white;
}

.gameend-option-two:hover {
  transform: translateY(3px);
  border-radius: 25px;
}

.gameend-option-two:active {
  opacity: 0.5;
}

</style>