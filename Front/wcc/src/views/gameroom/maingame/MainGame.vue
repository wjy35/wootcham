<template>
  <div class="inner">
    <!-- HEADER -->
    <header>
      <div class="header-center">{{ game - round }} 라운드 {{ second }}</div>
      <button class="Btn" @click="showModal = true">
        <img src="@/assets/images/getout.png" class="sign" alt="나가기" style="width: 24px;">
        <div class="text">게임 나가기</div>
      </button>
    </header>

    <div class="main-container">
      <div class="gridlayout">
        <!-- 1번 박스 === video-one -->
        <div class="main video video-one shadow">
          <img v-if="streamManagers.length < 1" src="../../../assets/images/WCC_logo.png">
          <UserVideo v-else :stream-manager="streamManagers[0]"/>
          <!--                <div v-if="streamManagers[0] !== undefined" class="video-username">{{ nickname(publisher) }}</div>-->
        </div>

<!--         2, 3, 6, 7번 박스 === Main Content -->
                      <div class="main main-content shadow">
                        <div class="main-content-username">username</div>
                        <div id="main-content-video">
                          <!-- 임시로 이미지 넣음-->
                          <!-- <img v-if="mainStreamManager === undefined" src="https://media.istockphoto.com/id/518360318/photo/crazy-horse.jpg?s=612x612&w=0&k=20&c=KP27AuWsogbIb1dRSqqwRn_ykPMqguJb7z2q3x9xr5A=" alt=""> -->
<!--                          <div v-if="streamManagers.length>0">-->
<!--                             <main-content></main-content>-->
<!--                             <mission-select></mission-select>-->
<!--                            <stand-by></stand-by>-->
<!--                          </div>-->
                          <UserVideo  :stream-manager="streamManagers[0]"/>
                          <!-- Progress Bar -->
                          <div class="loader">
                            <div></div>
                          </div>

                          <div class="share-btn screenshare">
                            <div class="sign">
                              <img src="@/assets/images/stream.png" alt="">
                            </div>
                            <div class="text">화면 공유</div>
                          </div>

                          <div class="share-btn endterm">
                            <div class="sign">
                              <img src="@/assets/images/the-end.png" alt="">
                            </div>
                            <div class="text">턴 종료</div>
                          </div>
                        </div>
                      </div>

        <!-- 4, 8번 박스 === chat-card -->
        <div class="main chat-card shadow">
          <div class="chat-body">
            <ul v-for="m in messageList" :key="m.connectionId">
              <li v-if="nickname(publisher) === m.nickname" class="message incoming">
                <p><span>{{ m.nickname }}</span>: {{ m.message }}</p>
              </li>
              <li v-if="nickname(publisher) !== m.nickname" class="message outgoing">
                <p><span>{{ m.nickname }}</span>: {{ m.message }}</p>
              </li>
            </ul>
          </div>

          <div class="input-container shadow">
            <input placeholder="메시지를 입력해주세요." type="text" class="input" v-model="message" @keyup.enter="sendMessage">
            <button @click="sendMessage" class="invite-btn" type="button">
              SEND
            </button>
          </div>
        </div>

        <!-- 5번 박스 -->
        <div class="main video video-two shadow">
          <img v-if="streamManagers.length<2" src="@/assets/images/WCC_logo.png">
          <UserVideo v-else :stream-manager="streamManagers[1]"/>
          <!--                <div v-if="subscribers[0] !== undefined" class="video-username">{{ nickname(subscribers[0]) }}</div>-->
        </div>

        <!-- 9번 박스 -->
        <div class="main video video-three shadow">
          <img v-if="streamManagers.length<3" src="@/assets/images/WCC_logo.png">
          <UserVideo v-else :stream-manager="streamManagers[2]"/>
          <!--                <div v-if="subscribers[1] !== undefined" class="video-username">{{ nickname(subscribers[1]) }}</div>-->
        </div>

        <!-- 10번 박스 -->
        <div class="main video video-four shadow">
          <img v-if="streamManagers.length<4" src="@/assets/images/WCC_logo.png">
          <UserVideo v-else :stream-manager="streamManagers[3]"/>
          <!--                <div v-if="subscribers[2] !== undefined" class="video-username">{{ nickname(subscribers[2]) }}</div>-->
        </div>

        <!-- 11번 박스 -->
        <div class="main video video-five shadow">
          <img v-if="streamManagers.length<5" src="@/assets/images/WCC_logo.png">
          <UserVideo v-else :stream-manager="streamManagers[4]"/>
          <!--                <div v-if="subscribers[3] !== undefined" class="video-username">{{ nickname(subscribers[3]) }}</div>-->
        </div>

        <!-- 12번 박스 -->
        <div class="main video video-six shadow">
          <img v-if="streamManagers.length<6" src="@/assets/images/WCC_logo.png">
          <UserVideo v-else :stream-manager="streamManagers[5]"/>
          <!--                <div v-if="subscribers[3] !== undefined" class="video-username">{{ nickname(subscribers[3]) }}</div>-->
        </div>

        <!-- <img src="@/assets/images/WCC_logo.png"> -->
      </div>

      <!-- 모달 창 -->
      <div v-if="showModal" class="modal">
        <div class="modal-content">
          <p>게임을 나가시겠습니까?</p>
          <div class="modal-content-warning">
            <div>지금 게임을 <span>무단으로 </span> 나가면 <br>
              <span>100포인트</span>를 잃게 됩니다. 그래도 나가시겠습니까?
            </div>
          </div>
          <div class="modal-content-btn">
            <div @click="exitGame">나가기</div>
            <button @click="showModal = false" class="goback-btn">돌아가기</button>
          </div>
        </div>
      </div>
    </div>


  </div>
</template>

<script>
import MainContent from "../components/maincontent/MainContent.vue"
import MissionSelect from "../components/maincontent/MissionSelect.vue"
import StandBy from "../components/maincontent/StandBy.vue"
import UserVideo from "@/views/gameroom/components/openvidu/UserVideo.vue";


// let interval;

export default {
  name: 'MainGame',
  props: {
    gameStatus: Number,
    streamManagers: Array,
    memberTokens: Array,
    second:Number,
  },

  data() {
    return {
      showModal: false,

      // Screen
      OVScreen: undefined,
      sessionScreen: undefined,

      // mainContent
      mainStreamManager: undefined,

      // message
      message: '',
      messageList: [],
    };
  },
  unmounted() {
    this.disconnectScreen();
    this.leaveSession();
  },
  methods: {
    getMemberToken(streamManager) {
      return JSON.parse(streamManager.stream.connection.data).clientData;
    },
    sendMessage() {

    },
    exitGame() {
      // 게임 종료 동작을 수행하는 코드
      this.$router.push('/home');
    },
    connectScreen() {

    },
    disconnectScreen() {

    },
    leaveSession() {

    },
  },
  components: {
    UserVideo, MainContent,
    MissionSelect,
    StandBy
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

  display: flex; /* Add this line */
  justify-content: center; /* Add this line */
  align-items: center; /* Add this line */
}

.gridlayout {
  width: 95%;
  height: 97%;
  display: grid;
  grid-template-columns: repeat(4, 1fr);
  grid-template-rows: repeat(3, 1fr);

  gap: 10px;
  border: 3px solid #FFCDAD;
  background-color: #FFCDAD;
}

.video-one {
  grid-area: 1 / 1 / 2 / 2;
}

.main-content {
  grid-area: 1 / 2 / 4 / 4;
}

.chat-card {
  grid-area: 1 / 4 / 3 / 5;
}

.video-two {
  grid-area: 2 / 1 / 3 / 2;
}

.video-three {
  grid-area: 3 / 1 / 4 / 2;
}

.video-four {
  grid-area: 3 / 2 / 4 / 3;
}

.video-five {
  grid-area: 3 / 3 / 4 / 4;
}

.video-six {
  grid-area: 3 / 4 / 4 / 5;
}

/* -------- VIDEO -------- */
.video {
  border: 1px solid #FFF2EA;
  border-radius: 15px;

  position: relative;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  overflow: hidden;

  display: flex;
  justify-content: center;
  align-items: center;
}

.video-username {
  position: absolute;
  bottom: 5px;
  left: 5px;
  z-index: 99;

  color: white;
}

.video img {
  width: 250px;
}


/* -------- MAIN CONTENT --------- */
.main-content {
  height: 66%;
  position: relative;

  background-color: gainsboro;
  border-radius: 10px;
  border: 3px solid #FFF2EA;
}

.main-content-username {
  position: absolute;
  top: 5px;
  left: 5px;

  color: white;
}

#main-content-video {
  position: absolute;
  left: 5px;

  width: 100%;
  height: 100%;
  overflow: hidden;
}

.loader {
  position: absolute;
  left: 10px;
  bottom: 5px;
  width: 96%;
  height: 10px;
  border-radius: 8px;
  background-color: #47a7ff44;
}

.loader div {
  height: 100%;
  width: 100%;
  border-radius: 8px;
  background-color: #F27059;
  animation: width7435 60s linear infinite;
  transition: all;
}

@keyframes width7435 {
  from {
    /*width: 0;*/
    transform: scaleX(0);
  }

  to {
    transform: scaleX(1);
  }
}

/* ---------- SHARE BTN --------- */
.screenshare {
  position: absolute;
  right: 18px;
  bottom: 30px;
  width: 96%;
  height: 10px;
  z-index: 99;
}

.endterm {
  position: absolute;
  right: 18px;
  bottom: 90px;
  width: 96%;
  height: 10px;
  z-index: 99;

}

.share-btn {
  display: flex;
  align-items: center;
  justify-content: flex-start;
  width: 45px;
  height: 45px;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  transition-duration: .3s;
  box-shadow: 2px 2px 10px rgba(0, 0, 0, 0.199);
}

/* plus sign */
.share-btn .sign {
  width: 100%;
  transition-duration: .3s;
  display: flex;
  align-items: center;
  justify-content: center;
}

.share-btn .sign img {
  width: 25px;
}

.share-btn .text {
  position: absolute;
  font-family: Noto Sans;

  right: 0%;
  width: 0%;
  opacity: 0;
  color: black;
  font-size: 1em;
  font-weight: 400;
  transition-duration: .3s;
}

/* hover effect on button width */
.share-btn:hover {
  width: 125px;
  border-radius: 5px;
  transition-duration: .3s;
}

.share-btn:hover .sign {
  width: 30%;
  transition-duration: .3s;
  padding-left: 20px;
}

/* hover effect button's text */
.share-btn:hover .text {
  opacity: 1;
  width: 70%;
  transition-duration: .3s;
  padding: 5px 0 5px 10px;
}

/* button click effect*/
.share-btn:active {
  transform: translate(2px, 2px);
}

/* -------- CHAT --------- */
.chat-card {
  padding: 10px;
  display: flex;
  flex-direction: column;
  justify-content: space-between;


  background-color: #fff;
  border: 5px solid #FFF2EA;
  border-radius: 10px;
  font-family: Noto-Sans;

  /* height: 100%; */
  overflow-y: scroll;
}

.chat-body {
  padding: 5px 0 0;
}

.message {
  margin-bottom: 10px;
  padding: 10px;
  border-radius: 5px;
}

.incoming {
  background-color: #e1e1e1;
}

.outgoing {
  background-color: #f2f2f2;
  text-align: right;
}

.message p {
  font-size: 14px;
  color: #333;
  margin: 0;
}

@keyframes chatAnimation {
  0% {
    opacity: 0;
    transform: translateY(10px);
  }

  100% {
    opacity: 1;
    transform: translateY(0);
  }
}

.chat-card .message {
  animation: chatAnimation 0.3s ease-in-out;
  animation-fill-mode: both;
  animation-delay: 0.1s;
}

.chat-card .message:nth-child(even) {
  animation-delay: 0.2s;
}

.chat-card .message:nth-child(odd) {
  animation-delay: 0.3s;
}

/* 메시지 입력창 */
.input-container {
  position: relative;

  display: flex;
  justify-content: center;
  align-items: center;

  height: 2.8rem;
  background-color: #fff;
  border-radius: 8px;
  box-shadow: 20px 20px 30px rgba(0, 0, 0, .05);
}

.input-container input {
  height: 100%;
  width: 100%;
  border-radius: 8px;
  border: 1px solid rgb(176 190 197);
  background-color: transparent;
  padding: 0.625rem 70px 0.625rem 0.75rem;
  font-size: 0.875rem;
  line-height: 1.25rem;
  font-weight: 400;
  color: rgb(69 90 100);
  outline: none;
  transition: all .15s cubic-bezier(0.4, 0, 0.2, 1);
}

.input-container input:focus {
  border: 1px solid #F27059;
}

.invite-btn {
  position: absolute;
  width: 65px;
  right: 4px;
  top: 4px;
  bottom: 4px;
  z-index: 10;
  border-radius: 4px;
  background-color: #F27059;
  color: #fff;
  padding-top: .25rem;
  padding-bottom: .25rem;
  padding-left: 0.5rem;
  padding-right: 0.5rem;
  text-align: center;
  vertical-align: middle;
  font-size: 12px;
  font-weight: 600;
  text-transform: uppercase;
  border: none;
  transition: .6s ease;
}

.invite-btn:hover {
  right: 2px;
  top: 2px;
  bottom: 2px;
  border-radius: 8px;
}

.input-container input:placeholder-shown ~ .invite-btn {
  pointer-events: none;
  background-color: gray;
  opacity: 0.5;
}

/* ---------- MODAL ---------- */
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

.modal-content {
  background-color: #F27059;
  margin: 100px 400px 50px;
  padding: 20px;

  gap: 40px;
  border: 3px solid #FFF2EA;
  border-radius: 15px;
  text-align: center;
}

.modal-content p {
  color: white;
  font-size: 3em;
}

.modal-content-warning {
  background-color: #FFF2EA;
  border-radius: 12px;

  font-family: Noto-Sans;
  color: #714538;
  padding: 10px;

  border: 3px solid brown;
}

.modal-content-warning:hover {
  border: 5px solid white;
}

.modal-content-warning span {
  color: #F27059;
  font-size: 1.2em;
}

.modal-content-btn {
  gap: 50px;
  display: flex;
  align-items: center;
  justify-content: center;

  font-size: 2em;
}

.modal-content-btn div {
  color: white;
  cursor: pointer;
}

.modal-content-btn div:hover {
  transform: translateY(3px);
}

.goback-btn {
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

.goback-btn:hover {
  transform: translateY(3px);
  border-radius: 20px;
}

.goback-btn:active {
  opacity: 0.5;
}

/* -------------- 나가기 버튼 --------------- */
.Btn {
  display: flex;
  align-items: center;
  justify-content: flex-start;
  width: 45px;
  height: 45px;
  border: none;
  border-radius: 5px;
  cursor: pointer;

  position: absolute;
  right: 40px;
  overflow: hidden;
  transition-duration: .3s;
  background-color: #F27059;
}

.sign {
  width: 100%;
  transition-duration: .3s;
  display: flex;
  align-items: center;
  justify-content: center;
}

/* text */
.text {
  position: absolute;
  right: 0%;
  width: 0%;
  opacity: 0;
  font-size: 1.2em;
  font-weight: 200;
  transition-duration: .3s;
}

/* hover effect on button width */
.Btn:hover {
  width: 150px;
  border-radius: 5px;
  transition-duration: .3s;
}

.Btn:hover .sign {
  width: 30%;
  transition-duration: .3s;
  padding-left: 20px;
}

/* hover effect button's text */
.Btn:hover .text {
  opacity: 1;
  width: 100%;
  transition-duration: .7s;
  padding-right: 10px;

  color: white;
}

/* button click effect*/
.Btn:active {
  transform: translate(2px, 2px);
}
</style>