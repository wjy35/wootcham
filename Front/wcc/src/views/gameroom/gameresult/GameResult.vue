<template>
  <div class="inner">
      <!-- HEADER -->
      <header>
          <div class="header-center">게임이 종료되었습니다.</div>
      </header>
      
      <div class="main-container">
          <div class="ranking-modal shadow">
            <div class="rank-card">
                <div class="rank-card-image">
                  <img src="@/assets/images/profile.jpg" alt="">
                  <span class="report-icon" @click="toggleReport">
                    <img src="@/assets/images/report.png" alt="">
                  </span>
                </div>
                <div class="rank-card-detail">
                  <div class="rank-heading">#1</div>
                  <div class="rank-info">
                    <div class="rank-username">verylonglongbigbigusername</div>
                    <div class="rank-pts-earned">+ 250</div>
                  </div>
                </div>
              </div>

              <div class="rank-card">
                <div class="rank-card-image">
                  <img src="@/assets/images/profile.jpg" alt="">
                  <span class="report-icon" @click="toggleReport">
                    <img src="@/assets/images/report.png" alt="">
                  </span>
                </div>
                <div class="rank-card-detail">
                  <div class="rank-heading">#2</div>
                  <div class="rank-info">
                    <div class="rank-username">verylonglongbigbigusername</div>
                    <div class="rank-pts-earned">+ 250</div>
                  </div>
                </div>
              </div>

              <div class="rank-card">
                <div class="rank-card-image">
                  <img src="@/assets/images/profile.jpg" alt="">
                  <span class="report-icon" @click="toggleReport">
                    <img src="@/assets/images/report.png" alt="">
                  </span>
                </div>
                <div class="rank-card-detail">
                  <div class="rank-heading">#3</div>
                  <div class="rank-info">
                    <div class="rank-username">verylonglongbigbigusername</div>
                    <div class="rank-pts-earned">+ 250</div>
                  </div>
                </div>
              </div>

              <div class="rank-card">
                <div class="rank-card-image">
                  <img src="@/assets/images/profile.jpg" alt="">
                  <span class="report-icon" @click="toggleReport">
                    <img src="@/assets/images/report.png" alt="">
                  </span>
                </div>
                <div class="rank-card-detail">
                  <div class="rank-heading">#4</div>
                  <div class="rank-info">
                    <div class="rank-username">verylonglongbigbigusername</div>
                    <div class="rank-pts-earned">+ 250</div>
                  </div>
                </div>
              </div>

              <div class="rank-card">
                <div class="rank-card-image">
                  <img src="@/assets/images/profile.jpg" alt="">
                  <span class="report-icon" @click="toggleReport">
                    <img src="@/assets/images/report.png" alt="">
                  </span>
                </div>
                <div class="rank-card-detail">
                  <div class="rank-heading">#5</div>
                  <div class="rank-info">
                    <div class="rank-username">verylonglongbigbigusername</div>
                    <div class="rank-pts-earned">+ 250</div>
                  </div>
                </div>
              </div>

              <div class="rank-card">
                <div class="rank-card-image">
                  <img src="@/assets/images/profile.jpg" alt="">
                  <span class="report-icon" @click="toggleReport">
                    <img src="@/assets/images/report.png" alt="">
                  </span>
                </div>
                <div class="rank-card-detail">
                  <div class="rank-heading">#6</div>
                  <div class="rank-info">
                    <div class="rank-username">verylonglongbigbigusername</div>
                    <div class="rank-pts-earned">+ 250</div>
                  </div>
                </div>
              </div>

        <!-- 유저 신고 모달창 -->
        <div v-if="showModal" class="modal">
          <div class="modal-card">
            <div class="header">
              <img src="@/assets/images/report.png" alt="">
              <div class="content">
                <span class="title">속임수, 부정행위, 괴롭힘을 저지르는 불건전 행위 플레이어 신고</span>
                <p class="message">선택한 유저를 신고합니다.
                  신고가 이루어지면 웃참클럽 시스템에서 해당 게임을 확인하여
                  페널티 적용 여부를 결정합니다.</p>
              </div>
              <div class="actions">
                <button class="desactivate" type="button">신고하기</button>
                <button class="cancel" type="button" @click="toggleReport">취소하기</button>
              </div>
            </div>
          </div>
        </div>


      </div>

      <div class="result-summary-modal shadow">

        <div class="banner">
          <img src="@/assets/images/game_result_banner.png" alt="explain_banner">
        </div>

        <!-- <div class="confetti">
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
              </div> -->



            <div class="my-result">
              <img class='game-result-icon' src="@/assets/images/profile.jpg" alt="">
              <div class='gauge'>
                <div class="plus-point">+1000</div>
                <div class='gauge-back'>
                  <div class='gauge-data' :style="{ width: pointResult + '%'}"></div>
                  <div class="result-info">2500/5000<br><span class="level">골드1</span></div>
                </div>
              </div>
            </div>

        <div class="coin-result">
          <p>축하합니다! 아래 보상을 획득했습니다.</p>
          <div class="plus-coin">
            <img class='coin' src="@/assets/images/coin.png">
            <span class="coin-count">+150</span>
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

export default {
  name: "GameResult",
  components: {

  },
  data() {
    return {
      showModal: false,
      pointResult: 95,
    }
  },
  methods: {
    toggleReport() {
      this.showModal = !this.showModal;
    },
    report() {
      // 신고 로직
    }
  }
}
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
  width: 500px;
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

.rank-card-image img {
  width: 60px;
  height: 60px;
  margin: 0 10px 0 10px;
  border-radius: 50%;
  flex-shrink: 0;
  transition: filter 0.3s ease;
}

.rank-card-image .report-icon {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  font-size: 20px;
  color: red;
  display: none;
}

.rank-card-image:hover .report-icon {
  display: block;
  cursor: pointer;
}

.rank-card-detail {
  flex-grow: 1;
  background-color: #FFF2EA;
  border-radius: 50px 20px;
  height: 93%;
  position: relative;

  margin: 5px;
}

.rank-card-detail .rank-heading {
  color: #F27059;
  position: absolute;
  left: 10px;
  top: 5px;
  font-size: 3em;
}

.rank-card-detail .rank-info {
  position: absolute;
  left: 100px;
  top: 15px;
}

.rank-info .rank-username {
  color: #FAAD1B;
  white-space: normal;
}

.rank-info .rank-pts-earned {
  color: yellowgreen;
  white-space: normal;
}


.rank-card:hover .rank-card-image,
.rank-card:hover .rank-card-detail {
  transform: scale(0.96);
}

/* -------- 유저 신고 모달창 (User Report Modal) ----------- */
.modal {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.5);
  display: flex;
  align-items: center;
  justify-content: center;
}

.modal-card {
  overflow: hidden;
  position: relative;
  background-color: #ffffff;
  text-align: left;
  border-radius: 0.5rem;
  max-width: 500px;
  box-shadow: 0 20px 25px -5px rgba(0, 0, 0, 0.1), 0 10px 10px -5px rgba(0, 0, 0, 0.04);
}

.modal-card .header {
  padding: 1.25rem 1rem 1rem 1rem;
  background-color: #ffffff;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}

.modal-card .header img {
  width: 10rem;
  height: 10rem;
  border-radius: 9999px;
}

.modal-card .header .content {
  margin-top: 0.75rem;
  text-align: center;
}

.title {
  color: #111827;
  font-size: 2rem;
  font-weight: 400;
  line-height: 1.5rem;
}

.message {
  margin-top: 1rem;
  color: #6B7280;
  font-size: 1rem;
  line-height: 1.25rem;
}

.actions {
  margin: 0.75rem 1rem;
  background-color: #F9FAFB;
}

.desactivate {
  display: inline-flex;
  padding: 0.5rem 1rem;
  background-color: #DC2626;
  color: #ffffff;
  font-size: 1rem;
  line-height: 1.5rem;
  font-weight: 500;
  justify-content: center;
  width: 100%;
  border-radius: 0.375rem;
  border-width: 1px;
  border-color: transparent;
  box-shadow: 0 1px 2px 0 rgba(0, 0, 0, 0.05);
}

.cancel {
  display: inline-flex;
  margin-top: 0.75rem;
  padding: 0.5rem 1rem;
  background-color: #ffffff;
  color: #374151;
  font-size: 1rem;
  line-height: 1.5rem;
  font-weight: 500;
  justify-content: center;
  width: 100%;
  border-radius: 0.375rem;
  border: 1px solid #D1D5DB;
  box-shadow: 0 1px 2px 0 rgba(0, 0, 0, 0.05);
}

/* ------- 게임 결과 요약 ------- */
.result-summary-modal {
  background-color: #FFF2EA;
  width: 500px;
  height: 570px;

  border: 3px solid #F27059;
  border-radius: 15px;

  display: flex;
  flex-direction: column;
  align-items: center;

  position: relative;
}

.banner img {
  width: 350px;
  margin-top: -60px;
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
  gap: 100px;
  display: flex;
  font-size: 5em;
  margin-top: 3rem;
}

.gameend-option-one {
  width: 150px;
  height: 60px;
  border-radius: 25px;
  background-image: linear-gradient(135deg, #feb692 0%, #ea5455 100%);
  box-shadow: 0 20px 30px -6px rgba(238, 103, 97, 0.5);
  cursor: pointer;

  border: none;
  font-size: 24px;
  color: white;

  display: flex;
  align-items: center;
  justify-content: center;
}

.gameend-option-two {
  transition: all 0.3s ease-in-out;
  width: 150px;
  height: 60px;
  border-radius: 25px;
  background-image: linear-gradient(135deg, #ea5455 0%, #feb692 100%);
  box-shadow: 0 20px 30px -6px rgba(238, 103, 97, 0.5);
  outline: none;
  cursor: pointer;

  border: none;
  font-size: 24px;
  color: white;

  display: flex;
  align-items: center;
  justify-content: center;
}

.gameend-option-two:hover {
  transform: translateY(3px);
  border-radius: 50px;
}

.gameend-option-two:active {
  opacity: 0.5;
}

.my-result {
  width: 80%;
  margin-top: 3rem;
  margin-left: auto;
  margin-right: auto;
  display: flex;
  justify-content: center;
}

.game-result-icon {
  width: 25%;
  height: 100%;
  border-radius: 50%;
}

.gauge {
  width: 70%;
  margin-left: 5%;
  height: 100%;
}

.gauge-back {
  background-color: #FFCDAD;
  height: 40px;
  position: relative;
  border-radius: 10px;
  border-top-left-radius: 0;
  border-bottom-left-radius: 0;
}

.gauge-data {
  background-color: #F27059;
  height: 30px;
  position: absolute;
  left: 0;
  top: 5px;
  border-radius: 10px;
  border-top-left-radius: 0;
  border-bottom-left-radius: 0;
}

.result-info {
  position: absolute;
  right: 5px;
  top: -20px;
  text-align: right;
  color: #F27059;
}

.point-increase {
  width: 60px;
  height: calc(500px * 0.8 * 0.8);
  transform: rotate(90deg);
}

.plus-point {
  color: #F27059;
  text-align: left;
  font-size: 2rem;
  padding: 0;
}



.coin-result {
  margin-top: 3rem;
  font-size: 1.5rem;
  color: #714538;
}

.plus-coin {
  display: flex;
  justify-content: center;
  margin-left: auto;
  margin-right: auto;
  align-items: center;
}

.coin {
  width: auto;
  height: 3rem;
  margin-right: 0.5rem;
}

.coin-count {
  color: #FAAD1B;
  font-size: 2rem;
}

.level {
  color: #714538;
  font-size: 1.5rem;
  margin: 0;
}
</style>