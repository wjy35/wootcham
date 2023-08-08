<template>
    <div class="inner">
        <!-- HEADER -->
        <header>
            <div class="header-center">2라운드 09:30</div>
            <button @click="connectScreen">connect screen</button>
            <button @click="disconnectScreen">stop screen</button>
            <button class="Btn" @click="showModal = true">
                <img src="@/assets/images/getout.png" class="sign" alt="나가기" style="width: 24px;">
                <div class="text">게임 나가기</div>
            </button>
        </header>
        
        <div class="main-container">
            <div class="gridlayout">
                <!-- 1번 박스 === video-one -->
                <div class="main video video-one shadow">
                  <img v-if="publisher === undefined" src="../../../assets/images/WCC_logo.png">
                  <UserVideo v-else :stream-manager="publisher" videoType="myVideo"/>
                  <div v-if="publisher !== undefined" class="video-username">{{ nickname(publisher) }}</div>
                </div>

                <!-- 2, 3, 6, 7번 박스 === Main Content -->
                <div class="main main-content shadow">
                  <div class="main-content-username">username</div>
                  <div id="main-content-video">
                    <!-- 임시로 이미지 넣음-->
                    <img v-if="mainStreamManager === undefined" src="https://media.istockphoto.com/id/518360318/photo/crazy-horse.jpg?s=612x612&w=0&k=20&c=KP27AuWsogbIb1dRSqqwRn_ykPMqguJb7z2q3x9xr5A=" alt="">
                    <UserVideo v-else :stream-manager="mainStreamManager" videoType="screen"/>
                    <!-- Progress Bar -->
                    <div class="loader">
                      <div></div>
                    </div>
                    <!-- 화면 공유 & 턴 종료 버튼 -->
                    <ul class="wrapper">
                      <li @click='connectScreen' class="icon facebook">
                          <span class="tooltip">화면공유</span>
                          <span><i class="fab fa-facebook-f"></i></span>
                      </li>
                      <li @click="disconnectScreen" class="icon instagram">
                          <span class="tooltip">턴 종료</span>
                          <span><i class="fab fa-instagram"></i></span>
                      </li>
                    </ul>
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
                  <img v-if="subscribers[0] === undefined" src="../../../assets/images/WCC_logo.png">
                  <UserVideo v-else :stream-manager="subscribers[0]" videoType="notMyVideo"/>
                  <div v-if="subscribers[0] !== undefined" class="video-username">{{ nickname(subscribers[0]) }}</div>
                </div>

                <!-- 9번 박스 -->
                <div class="main video video-three shadow">
                  <img v-if="subscribers[1] === undefined" src="../../../assets/images/WCC_logo.png">
                  <UserVideo v-else :stream-manager="subscribers[1]" videoType="notMyVideo"/>
                  <div v-if="subscribers[1] !== undefined" class="video-username">{{ nickname(subscribers[1]) }}</div>
                </div>

                <!-- 10번 박스 -->
                <div class="main video video-four shadow">
                  <img v-if="subscribers[2] === undefined" src="../../../assets/images/WCC_logo.png">
                  <UserVideo v-else :stream-manager="subscribers[2]" videoType="notMyVideo"/>
                  <div v-if="subscribers[2] !== undefined" class="video-username">{{ nickname(subscribers[2]) }}</div>
                </div>

                <!-- 11번 박스 -->
                <div class="main video video-five shadow">
                  <img v-if="subscribers[3] === undefined" src="../../../assets/images/WCC_logo.png">
                  <UserVideo v-else :stream-manager="subscribers[3]" videoType="notMyVideo"/>
                  <div v-if="subscribers[3] !== undefined" class="video-username">{{ nickname(subscribers[3]) }}</div>
                </div>

                <!-- 12번 박스 -->
                <div class="main video video-six shadow">
                  <img v-if="subscribers[4] === undefined" src="../../../assets/images/WCC_logo.png">
                  <UserVideo v-else :stream-manager="subscribers[4]" videoType="notMyVideo"/>
                  <div v-if="subscribers[4] !== undefined" class="video-username">{{ nickname(subscribers[4]) }}</div>
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
import axios from "axios";
import { OpenVidu } from "openvidu-browser";
import UserVideo from "../components/webcam/components/UserVideo.vue";

axios.defaults.headers.post["Content-Type"] = "application/json";

const APPLICATION_SERVER_URL = process.env.NODE_ENV === 'production' ? '' : 'http://localhost:5000/';

export default {
    data() {
      return {
        showModal: false,
        chatUsername: "가상의 유저",
        // Camera
        OV: undefined,
        session: undefined,
        publisher: undefined,
        subscribers: [undefined, undefined, undefined, undefined, undefined],
        // Screen
        OVScreen: undefined,
        sessionScreen: undefined,
        // mainContent
        mainStreamManager: undefined,
        // userInfo
        mySessionId: "SessionA",
        myUserName: "Participant" + Math.floor(Math.random() * 100),
        token: '',
        // message
        message: '',
        messageList: [],
      };
    },
    mounted() {
      this.joinSession();
    },
    unmounted() {
      this.disconnectScreen();
      this.leaveSession();
    },
    methods: {
        sendMessage() {
          if (this.message.trim()) {
            const data = { message: this.message, nickname: this.myUserName, streamId: this.publisher.stream.streamId };
            this.publisher.stream.session.signal({
                data: JSON.stringify(data),
                type: 'chat',
            });
            this.message = '';
          }
        },
        nickname(publisher) {
            return JSON.parse(publisher.stream.connection.data).clientData;
        },
        exitGame() {
            // 게임 종료 동작을 수행하는 코드
            this.$router.push('/home');
        },
        joinSession() {
            this.OV = new OpenVidu();
            this.session = this.OV.initSession();
            this.session.on("streamCreated", ({ stream }) => {
                const subscriber = this.session.subscribe(stream);
                if (subscriber.stream.typeOfVideo === "SCREEN") {
                    this.mainStreamManager = subscriber;
                }
                else {
                    for (let i = 0; i <= 4; i++) {
                        if (this.subscribers[i] === undefined) {
                            console.log(i + '번째 유저');
                            this.subscribers[i] = subscriber;
                            break;
                        }
                    }
                }
            });
            this.session.on("streamDestroyed", ({ stream }) => {
                // 스크린 스트림이 파괴될 때 mainStreamManager를 지금 하고 있는 사람 캠으로 전환
                this.mainStreamManager = undefined;
                const index = this.subscribers.indexOf(stream.streamManager, 0);
                if (index >= 0) {
                    this.subscribers[index] = undefined;
                }
            });
            this.session.on("exception", ({ exception }) => {
                console.warn(exception);
            });
            this.session.on("signal:chat", (event) => {
                const data = JSON.parse(event.data);
                let newList = this.messageList;
                newList.push({ connectionId: event.from.connectionId, nickname: data.nickname, message: data.message });
                this.messageList = newList;
            });
            this.getToken(this.mySessionId).then((token) => {
                this.session.connect(token, { clientData: this.myUserName })
                    .then(() => {
                    let publisher = this.OV.initPublisher(undefined, {
                        audioSource: undefined,
                        videoSource: undefined,
                        publishAudio: true,
                        publishVideo: true,
                        resolution: "640x480",
                        frameRate: 30,
                        insertMode: "APPEND",
                        mirror: false, // Whether to mirror your local video or not
                    });
                    this.publisher = publisher;
                    this.session.publish(publisher);
                })
                    .catch((error) => {
                    console.log("There was an error connecting to the session:", error.code, error.message);
                });
            });
        },
        connectScreen() {
            this.OVScreen = new OpenVidu();
            this.sessionScreen = this.OV.initSession();
            this.getToken(this.mySessionId).then((token) => {
                this.sessionScreen.connect(token, { clientData: this.myUserName })
                    .then(() => {
                    let publisher = this.OVScreen.initPublisher("screenShare", {
                        audioSource: undefined,
                        videoSource: "screen",
                        publishAudio: true,
                        publishVideo: true,
                        resolution: "640x480",
                        frameRate: 30,
                        insertMode: "APPEND",
                        mirror: false, // Whether to mirror your local video or not
                    });
                    this.mainStreamManager = publisher;
                    this.sessionScreen.publish(publisher);
                })
                    .catch((error) => {
                    console.log("There was an error connecting to the session:", error.code, error.message);
                });
            });
        },
        disconnectScreen() {
            if (this.sessionScreen) {
                this.sessionScreen.disconnect();
            }
            this.sessionScreen = undefined;
            this.mainStreamManager = undefined;
            this.OVScreen = undefined;
        },
        leaveSession() {
            if (this.session)
                this.session.disconnect();
            this.session = undefined;
            this.mainStreamManager = undefined;
            this.publisher = undefined;
            this.subscribers = [];
            this.OV = undefined;
        },
        async getToken(mySessionId) {
            const sessionId = await this.createSession(mySessionId);
            return await this.createToken(sessionId);
        },
        async createSession(sessionId) {
            const response = await axios.post(APPLICATION_SERVER_URL + 'api/sessions', { customSessionId: sessionId }, {
                headers: { 'Content-Type': 'application/json', },
            });
            return response.data; // The sessionId
        },
        async createToken(sessionId) {
            const response = await axios.post(APPLICATION_SERVER_URL + 'api/sessions/' + sessionId + '/connections', {}, {
                headers: { 'Content-Type': 'application/json', },
            });
            return response.data; // The token
        },
    },
    components: { UserVideo }
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

.video-one {grid-area: 1 / 1 / 2 / 2;}
.main-content {grid-area: 1 / 2 / 4 / 4;}
.chat-card {grid-area: 1 / 4 / 3 / 5;}
.video-two {grid-area: 2 / 1 / 3 / 2;}
.video-three {grid-area: 3 / 1 / 4 / 2;}
.video-four {grid-area: 3 / 2 / 4 / 3;}
.video-five {grid-area: 3 / 3 / 4 / 4;}
.video-six {grid-area: 3 / 4 / 4 / 5;}

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
}

.video-username {
  position: absolute;
  bottom: 5px;
  left: 5px;
  z-index: 99;

  color: white;
}


/* -------- MAIN CONTENT --------- */
.main-content{
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
  width: 75%;
  height: 25px;
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

/* Screen Share Button and End Term Button */
.wrapper {
  display: inline-flex;
  list-style: none;

  position: absolute;
  bottom: 0px;
  right: 5px;
  height: 25px;

  font-family: "Poppins", sans-serif;
  justify-content: center;
}

.wrapper .icon {
  position: relative;
  background: #fff;
  border-radius: 8px;
  margin: 10px;
  
  width: 50px;
  height: 25px;
  font-size: 18px;
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
  box-shadow: 0 10px 10px rgba(0, 0, 0, 0.1);
  cursor: pointer;
  transition: all 0.2s cubic-bezier(0.68, -0.55, 0.265, 1.55);
}

.wrapper .tooltip {
  position: absolute;
  top: 0;
  font-size: 14px;
  background: #fff;
  color: #fff;
  padding: 5px 8px;
  border-radius: 5px;
  box-shadow: 0 10px 10px rgba(0, 0, 0, 0.1);
  opacity: 0;
  pointer-events: none;
  transition: all 0.3s cubic-bezier(0.68, -0.55, 0.265, 1.55);
}

.wrapper .tooltip::before {
  position: absolute;
  content: "";
  height: 8px;
  width: 8px;
  background: #fff;
  bottom: -3px;
  left: 50%;
  transform: translate(-50%) rotate(45deg);
  transition: all 0.3s cubic-bezier(0.68, -0.55, 0.265, 1.55);
}

.wrapper .icon:hover .tooltip {
  top: -45px;
  opacity: 1;
  visibility: visible;
  pointer-events: auto;
}

.wrapper .icon:hover span,
.wrapper .icon:hover .tooltip {
  text-shadow: 0px -1px 0px rgba(0, 0, 0, 0.1);
}

.wrapper .facebook:hover,
.wrapper .facebook:hover .tooltip,
.wrapper .facebook:hover .tooltip::before {
  background: #1877F2;
  color: #fff;
}

.wrapper .instagram:hover,
.wrapper .instagram:hover .tooltip,
.wrapper .instagram:hover .tooltip::before {
  background: #E4405F;
  color: #fff;
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
  border: 1px solid  rgb(176 190 197);
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
  transform: translate(2px ,2px);
}
</style>