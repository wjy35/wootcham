<template>

  <div v-if="gameStatus===GameStatus.WAIT_GAME_START">

    <div class="loading-screen">
      <div class="loading-screen-loader">
        Loading
      </div>
      <div class="loadingspinner">
        <div id="square1"></div>
        <div id="square2"></div>
        <div id="square3"></div>
        <div id="square4"></div>
        <div id="square5"></div>
      </div>

      <p class="second">{{second}}</p>
      
    </div>
  
      <!-- <p>{{sessionId}}</p>
      <p>{{memberId}}</p>
      <p>{{memberToken}}</p> -->

  </div>

  <div v-else-if="gameStatus===GameStatus.PICK_TOPIC_KEYWORD">
    <game-room-prepare :round="round" :second="second"></game-room-prepare>
  </div>

  <div v-else>
      <div class="inner">
        <!-- HEADER -->
        <header>
          <div class="header-center">{{ round + 1 }} 라운드 {{ second }}</div>
          <button class="Btn" @click="showModal = true">
            <img src="@/assets/images/getout.png" class="sign" alt="나가기" style="width: 24px;">
            <div class="text">게임 나가기</div>
          </button>
        </header>

        <div class="main-container">
          <div class="gridlayout">

            <!-- 1번 박스 === video-one -->
            <div class="video video-one shadow" :class="{ 'video-effect': isLaugh }">
                <div class="video-card" :class="{'stop':noLaugh}">
                  <UserVideo 
                    :isMyFace="memberToken===memberTokens[0]"  
                    :stream-manager="streamManagers[0]" 
                  />
                </div>
                <!-- <div v-if="streamManagers[0] !== undefined" class="video-username">{{ nickname(publisher) }}</div>-->
            </div>

            <!-- 2, 3, 6, 7번 박스 === Main Content -->
            <div class="main-content shadow">
              <div class="main-content-username">username</div>
              <div id="main-content-video">
                

                <!-- 발표자 화면 -->
                <div v-if="memberToken===tellerToken">            
                    <!-- <UserVideo  :stream-manager="streamManagers[0]" :key="componentKey"/> -->
                    
                    <!-- 주제 선택 화면 -->
                    <!-- <mission-select></mission-select> -->

                    <!-- 카운트다운 화면 -->
                    <count-down></count-down>
                </div>
                <div v-else class="stand-by">

                  <!-- 대기 화면 -->
                  <!-- <stand-by></stand-by> -->


                </div>                
                
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
            <div class="chat-card shadow">
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
            <div class="video video-two shadow">
              <img v-if="streamManagers.length<2" src="@/assets/images/WCC_logo.png">
              <UserVideo v-else :isMyFace="memberToken===memberTokens[1]"   :stream-manager="streamManagers[1]" :key="componentKey" />
              <!--                <div v-if="subscribers[0] !== undefined" class="video-username">{{ nickname(subscribers[0]) }}</div>-->
            </div>

            <!-- 9번 박스 -->
            <div class="video video-three shadow">
              <img v-if="streamManagers.length<3" src="@/assets/images/WCC_logo.png">
              <UserVideo v-else :isMyFace="memberToken===memberTokens[2]"  :stream-manager="streamManagers[2]" :key="componentKey" />
              <!--                <div v-if="subscribers[1] !== undefined" class="video-username">{{ nickname(subscribers[1]) }}</div>-->
            </div>

            <!-- 10번 박스 -->
            <div class="video video-four shadow">
              <img v-if="streamManagers.length<4" src="@/assets/images/WCC_logo.png">
              <UserVideo v-else :isMyFace="memberToken===memberTokens[3]" :stream-manager="streamManagers[3]" :key="componentKey" />
              <!--                <div v-if="subscribers[2] !== undefined" class="video-username">{{ nickname(subscribers[2]) }}</div>-->
            </div>

            <!-- 11번 박스 -->
            <div class="video video-five shadow">
              <img v-if="streamManagers.length<5" src="@/assets/images/WCC_logo.png">
              <UserVideo v-else :isMyFace="memberToken===memberTokens[4]"  :stream-manager="streamManagers[4]" :key="componentKey" />
              <!--                <div v-if="subscribers[3] !== undefined" class="video-username">{{ nickname(subscribers[3]) }}</div>-->
            </div>

            <!-- 12번 박스 -->
            <div class="video video-six shadow">
              <img v-if="streamManagers.length<6" src="@/assets/images/WCC_logo.png">
              <UserVideo v-else :isMyFace="memberToken===memberTokens[5]"  :stream-manager="streamManagers[5]" :key="componentKey" />
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
  </div>
</template>


<script>
import GameRoomPrepare from "@/views/gameroom/prepare/GamePrepare.vue";
import {GameStatus} from "@/game-status";
import {OpenVidu} from "openvidu-browser";
import {mapState} from "vuex";
import UserVideo from "@/views/gameroom/components/openvidu/UserVideo.vue";

// import StandBy from '@/views/gameroom/components/maincontent/StandBy.vue';
// import MissionSelect from '@/views/gameroom/components/maincontent/MissionSelect.vue';
import CountDown from '@/views/gameroom/components/maincontent/CountDown.vue';

export default {
  name: 'GameRoom',
  components: {
    UserVideo, 
    GameRoomPrepare,
    // StandBy,
    CountDown,
    // MissionSelect
  },
  computed: {
    GameStatus() {
      return GameStatus
    },
    ...mapState(["client"])
  },
  data() {
    return {
      gameStatus:GameStatus.WAIT_GAME_START,
      second:"",
      memberId: localStorage.getItem("memberId"),
      sessionId: localStorage.getItem("sessionId"),
      memberToken: localStorage.getItem("memberToken"),
      OV: undefined,
      session: undefined,
      mainStreamManager: undefined,
      streamManagers:[],
      memberTokens:[],
      flag:false,
      componentKey:0, 
      round: "",
      tellerToken:"",

      noLaugh: false,
      laugh: []
    }
  },
  mounted() {
    this.joinSession();
  },
  methods: {
    joinSession() {
      this.OV = new OpenVidu();
      this.session = this.OV.initSession();

      this.session.on("streamCreated", ({stream}) => {
        let subscribeStreamManager = this.session.subscribe(stream);
        this.streamManagers.push(subscribeStreamManager);
        this.memberTokens.push(JSON.parse(subscribeStreamManager.stream.connection.data).clientData);
      });

      this.session.on("streamDestroyed", ({stream}) => {
        const index = this.subscribers.indexOf(stream.streamManager, 0);
        if (index >= 0) {
          this.subscribers.splice(index, 1);
        }
      });

      this.session.on("exception", ({ exception }) => {
        console.warn(exception);
      });

      this.session.connect(this.memberToken, {
        clientData: this.memberToken
      }).then(() => {
        let publisherStreamManager = this.OV.initPublisher(undefined, {
          audioSource: undefined, // The source of audio. If undefined default microphone
          videoSource: undefined, // The source of video. If undefined default webcam
          publishAudio: true, // Whether you want to start publishing with your audio unmuted or not
          publishVideo: true, // Whether you want to start publishing with your video enabled or not
          resolution: "640x480", // The resolution of your video
          frameRate: 30, // The frame rate of your video
          insertMode: "APPEND", // How the video is inserted in the target element 'video-container'
          mirror: false, // Whether to mirror your local video or not
        });

        this.streamManagers.push(publisherStreamManager);
        this.memberTokens.push(this.memberToken);
        this.session.publish(publisherStreamManager);

        this.client.subscribe(
            `/topic/game/${this.sessionId}`,
            (frame)=>{
              console.log(frame);
              let gameResponse = JSON.parse(frame.body);
              this.gameStatus = gameResponse.gameStatus;
              this.second = gameResponse.second;

              if(this.gameStatus===GameStatus.ORDER_GAMEMEMBER){
                console.log(gameResponse.order);
                console.log(this.memberTokens);

                let orderStreamManagers=[];
                let orderMemberTokens=[];

                for(let orderMemberToken of gameResponse.order){
                  for(let i=0; i<this.memberTokens.length; i++){
                    if(orderMemberToken===this.memberTokens[i]){
                      orderStreamManagers.push(this.streamManagers[i]);
                      orderMemberTokens.push(orderMemberToken);
                    }
                  }
                }
                this.streamManagers = orderStreamManagers;
                this.memberTokens = orderMemberTokens;
                this.round = gameResponse.round;
              }else if(this.gameStatus===GameStatus.PREPARE_PRESENT){
                if(this.flag){
                  this.streamManagers.push(this.streamManagers.shift());
                  this.memberTokens.push(this.memberTokens.shift());
                  this.componentKey += this.componentKey+1;
                  this.flag = false;
                }
                this.tellerToken = gameResponse.tellerToken;
              }else if(this.gameStatus===GameStatus.PRESENT){
                this.flag = true;
              }
              //
              // if(gameResponse.tellerToken){
              //   this.tellerToken = gameResponse.tellerToken;
              // }
              //
              // if(gameResponse.topic){
              //   this.topic = gameResponse.topic;
              // }
            },
            (error)=>{
              console.log(error);
            },
        );

      }).catch((error) => {
            console.log("There was an error connecting to the session:", error.code, error.message);
      });

    }
  },
}
</script>

<style scoped>
/* ------------ HEADER --------------- */
header {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  /* background-color: #F27059; */
  background-color: #504C4C; 
  z-index: 999;

  display: flex;
  justify-content: space-between;
  align-items: center;

  padding: 15px;
  border-top: 1px solid white;
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
  border: 3px solid #504C4C;
  /* background-color: #FFCDAD; */
  background-color: #504C4C; /* 배경색 및 투명도 설정 */

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
  border: 3px solid #504C4C;
  background-color: #504C4C;
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
  left: 8px;
  bottom: 5px;
  width: 95%;
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

/* 미션 선택 Mission Select */
.presenter {
  position: relative;

  display: flex;
  justify-content: center;
  align-items: center;  
}

/* ---------- SHARE BTN --------- */
.screenshare {
  position: absolute;
  right: 18px;
  bottom: 30px;
  width: 96%;
  height: 10px;
}

.endterm {
  position: absolute;
  right: 18px;
  bottom: 90px;
  width: 96%;
  height: 10px;
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
  opacity: 0.9;
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
  font-weight: 200;

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
  background-color: #504C4C;
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

/* 로딩창 Loading Screen */
.loading-screen {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.7); /* 배경색 및 투명도 설정 */
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  z-index: 9999; /* 다른 요소들 위에 나타나도록 설정 */
}

.loading-screen-loader {
  font-size: 2.5rem;
  font-family: sans-serif;
  font-variant: small-caps;
  font-weight: 900;
  background: conic-gradient(
    #dff2ae 0 25%,
    #ff904f 25% 50%,
    #feefe7 50% 75%,
    #ffde2b 75%
  );
  background-size: 200% 200%;
  animation: animateBackground 4.5s ease-in-out infinite;
  color: transparent;
  background-clip: text;
  -webkit-background-clip: text;
}

@keyframes animateBackground {
  25% {
    background-position: 0 100%;
  } 

  50% {
    background-position: 100% 100%;
  }

  75% {
    background-position: 100% 0%;
  }

  100% {
    background-position: 0 0;
  }
}

.loading-screen .second {
  color: darkorange;
  font-size: 2em;
  font-family: Luckiest Guy;
}


.loadingspinner {
  --square: 26px;
  --offset: 30px;
  --duration: 2.4s;
  --delay: 0.2s;
  --timing-function: ease-in-out;
  --in-duration: 0.4s;
  --in-delay: 0.1s;
  --in-timing-function: ease-out;
  width: calc( 3 * var(--offset) + var(--square));
  height: calc( 2 * var(--offset) + var(--square));
  padding: 0px;
  margin-left: auto;
  margin-right: auto;
  margin-top: 10px;
  margin-bottom: 30px;
  position: relative;
}

.loadingspinner div {
  display: inline-block;
  background: darkorange;
    /*background: var(--text-color);*/
    /*box-shadow: 1px 1px 1px rgba(0, 0, 0, 0.4);*/
  border: none;
  border-radius: 2px;
  width: var(--square);
  height: var(--square);
  position: absolute;
  padding: 0px;
  margin: 0px;
  font-size: 6pt;
  color: black;
}

.loadingspinner #square1 {
  left: calc( 0 * var(--offset) );
  top: calc( 0 * var(--offset) );
  animation: square1 var(--duration) var(--delay) var(--timing-function) infinite,
               squarefadein var(--in-duration) calc(1 * var(--in-delay)) var(--in-timing-function) both;
}

.loadingspinner #square2 {
  left: calc( 0 * var(--offset) );
  top: calc( 1 * var(--offset) );
  animation: square2 var(--duration) var(--delay) var(--timing-function) infinite,
              squarefadein var(--in-duration) calc(1 * var(--in-delay)) var(--in-timing-function) both;
}

.loadingspinner #square3 {
  left: calc( 1 * var(--offset) );
  top: calc( 1 * var(--offset) );
  animation: square3 var(--duration) var(--delay) var(--timing-function) infinite,
               squarefadein var(--in-duration) calc(2 * var(--in-delay)) var(--in-timing-function) both;
}

.loadingspinner #square4 {
  left: calc( 2 * var(--offset) );
  top: calc( 1 * var(--offset) );
  animation: square4 var(--duration) var(--delay) var(--timing-function) infinite,
               squarefadein var(--in-duration) calc(3 * var(--in-delay)) var(--in-timing-function) both;
}

.loadingspinner #square5 {
  left: calc( 3 * var(--offset) );
  top: calc( 1 * var(--offset) );
  animation: square5 var(--duration) var(--delay) var(--timing-function) infinite,
               squarefadein var(--in-duration) calc(4 * var(--in-delay)) var(--in-timing-function) both;
}

@keyframes square1 {
  0% {
    left: calc( 0 * var(--offset) );
    top: calc( 0 * var(--offset) );
  }

  8.333% {
    left: calc( 0 * var(--offset) );
    top: calc( 1 * var(--offset) );
  }

  100% {
    left: calc( 0 * var(--offset) );
    top: calc( 1 * var(--offset) );
  }
}

@keyframes square2 {
  0% {
    left: calc( 0 * var(--offset) );
    top: calc( 1 * var(--offset) );
  }

  8.333% {
    left: calc( 0 * var(--offset) );
    top: calc( 2 * var(--offset) );
  }

  16.67% {
    left: calc( 1 * var(--offset) );
    top: calc( 2 * var(--offset) );
  }

  25.00% {
    left: calc( 1 * var(--offset) );
    top: calc( 1 * var(--offset) );
  }

  83.33% {
    left: calc( 1 * var(--offset) );
    top: calc( 1 * var(--offset) );
  }

  91.67% {
    left: calc( 1 * var(--offset) );
    top: calc( 0 * var(--offset) );
  }

  100% {
    left: calc( 0 * var(--offset) );
    top: calc( 0 * var(--offset) );
  }
}

@keyframes square3 {
  0%,100% {
    left: calc( 1 * var(--offset) );
    top: calc( 1 * var(--offset) );
  }

  16.67% {
    left: calc( 1 * var(--offset) );
    top: calc( 1 * var(--offset) );
  }

  25.00% {
    left: calc( 1 * var(--offset) );
    top: calc( 0 * var(--offset) );
  }

  33.33% {
    left: calc( 2 * var(--offset) );
    top: calc( 0 * var(--offset) );
  }

  41.67% {
    left: calc( 2 * var(--offset) );
    top: calc( 1 * var(--offset) );
  }

  66.67% {
    left: calc( 2 * var(--offset) );
    top: calc( 1 * var(--offset) );
  }

  75.00% {
    left: calc( 2 * var(--offset) );
    top: calc( 2 * var(--offset) );
  }

  83.33% {
    left: calc( 1 * var(--offset) );
    top: calc( 2 * var(--offset) );
  }

  91.67% {
    left: calc( 1 * var(--offset) );
    top: calc( 1 * var(--offset) );
  }
}

@keyframes square4 {
  0% {
    left: calc( 2 * var(--offset) );
    top: calc( 1 * var(--offset) );
  }

  33.33% {
    left: calc( 2 * var(--offset) );
    top: calc( 1 * var(--offset) );
  }

  41.67% {
    left: calc( 2 * var(--offset) );
    top: calc( 2 * var(--offset) );
  }

  50.00% {
    left: calc( 3 * var(--offset) );
    top: calc( 2 * var(--offset) );
  }

  58.33% {
    left: calc( 3 * var(--offset) );
    top: calc( 1 * var(--offset) );
  }

  100% {
    left: calc( 3 * var(--offset) );
    top: calc( 1 * var(--offset) );
  }
}

@keyframes square5 {
  0% {
    left: calc( 3 * var(--offset) );
    top: calc( 1 * var(--offset) );
  }

  50.00% {
    left: calc( 3 * var(--offset) );
    top: calc( 1 * var(--offset) );
  }

  58.33% {
    left: calc( 3 * var(--offset) );
    top: calc( 0 * var(--offset) );
  }

  66.67% {
    left: calc( 2 * var(--offset) );
    top: calc( 0 * var(--offset) );
  }

  75.00% {
    left: calc( 2 * var(--offset) );
    top: calc( 1 * var(--offset) );
  }

  100% {
    left: calc( 2 * var(--offset) );
    top: calc( 1 * var(--offset) );
  }
}

@keyframes squarefadein {
  0% {
    transform: scale(0.75);
    opacity: 0.0;
  }

  100% {
    transform: scale(1.0);
    opacity: 1.0;
  }
}
</style>