<template>
  <div id="inner">
    <!-- HEADER -->
    <header>
      <a href="/home" class="logo">WootCham Club</a>

      <div class="header-center">
        <!-- <span @click="handleStartGame">{{ headerText }}</span> -->
<!--        <span v-if="!ready">카메라를 키고 활짝 웃어주세요!</span>-->
        <span v-if="matchStatus === MatchStatus.READY" @click="handleStartGame"> 시작하기 </span>


        <button v-if="matchStatus === MatchStatus.MATCHING" @click="cancel" class="text-shadow">
          매칭 취소
        </button>

        <template v-if="matchStatus === MatchStatus.MATCHED">
          <button @click.once="enter" v-if='!acceptStatus' class='info ask-accept text-shadow' id="test">수락하기</button>
          <button v-if='acceptStatus' class='info text-shadow'>다른 플레이어의 수락을 기다리는 중</button>
          <!-- <button @click="cancel">거절</button> -->
          <!-- <button v-if="!this.acceptStatus" @click="cancel">수락</button>
              <button v-else @click="cancel" disabled>수락</button>
              {{ second }} -->
          <!-- <button type="button" @click="cancel">거절</button> -->
        </template>
        <div v-if="matchStatus === MatchStatus.MATCHED">
          <span>{{ second }}</span>
        </div>
      </div>
      <div class="logout-btn">
        <div @click="logout">Log Out</div>
      </div>
    </header>

    <!-- CONTENT -->
    <div class="main-container">
      <SideBar @selectProfile="selectProfile" @selectStart="selectStart" @selectNotice="selectNotice"
        @selectShop="selectShop" @selectRanking="selectRanking" @selectInfo="selectInfo" ref="sidebar"/>

      <!-- Conditional rendering based on the selectedScreen data -->
      <div v-if="selectedScreen === 'ProfileWindowScreen'">
        <ProfileWindow />
      </div>
      <div v-if="selectedScreen === 'StartWindowScreen'">
        <StartWindow />
      </div>
      <div v-else-if="selectedScreen === 'NoticeWindowScreen'">
        <NoticeWindow />
      </div>
      <div v-else-if="selectedScreen === 'ShopWindowScreen'">
        <ShopWindow @refreshSidebar = refreshSidebar></ShopWindow>
      </div>
      <div v-else-if="selectedScreen === 'RankingWindowScreen'">
        <RankingWindow />
      </div>
      <div v-else-if="selectedScreen === 'InfoWindowScreen'">
        <InfoWindow />
      </div>
    </div>
  </div>
</template>

<script>
import SideBar from './components/SideBar.vue';
import ProfileWindow from './components/ProfileWindow.vue';
import StartWindow from './components/StartWindow.vue';
import NoticeWindow from './components/NoticeWindow.vue';
import ShopWindow from './components/ShopWindow.vue';
import RankingWindow from './components/RankingWindow.vue';
import InfoWindow from './components/InfoWindow.vue';
import * as Stomp from "webstomp-client";
import { MatchStatus } from '@/match-status';
import {mapMutations, mapState} from "vuex";
import api from "@/api"

export default {
  name: 'HomeView',
  components: {
    SideBar,
    ProfileWindow,
    StartWindow,
    NoticeWindow,
    ShopWindow,
    RankingWindow,
    InfoWindow,
  },
  data() {
    return {
      headerText: "게임 시작하기", // 카메라가 켜지기 전까지 logout 버튼은 숨겨진다.
      selectedScreen: 'StartWindowScreen', // 초기화면은 StartWindowScreen
      previousScreen: 'StartWindowScreen',
      client: null,
      groupId: "",
      memberId: "",
      second: "",
      matchStatus: MatchStatus.READY,
      sessionId: "",
      screenToken:"",
      memberToken: "",
      acceptStatus: false,
    };
  },
  created() {
    console.log("get keyword called......")
    // keyowrds 저장
    api.defaults.headers["Authorization"] = localStorage.getItem('accessToken');
    api.get("/topic")
      .then(({ data }) => {
        localStorage.setItem("topics", JSON.stringify(data.data));
      })
      .catch((error) => {
        console.log("keywords err")
        console.log(error)
      });
  },
  methods: {
    // 카메라가 켜졌을 때 실행되는 로직 (카메라 사용 가능 여부 감지)
    handleCameraOn() {
      this.headerText = "게임 시작";
    },
    // '게임 시작' 버튼이 누르면 '매칭중입니다' 표시
    handleStartGame() {
      // this.headerText = "매칭중입니다";
      this.matchStatus = MatchStatus.MATCHING;

      this.client = Stomp.client(process.env.VUE_APP_MATCH_ENDPOINT_URL);
      this.setClient(this.client);
      this.client.connect(
        {},
        () => {
          this.client.subscribe(
            "/user/queue/match",
            (frame) => {
              let response = JSON.parse(frame.body);
              if (response.success) {
                this.matchStatus = response.matchStatus;

                if (response.matchStatus === MatchStatus.MATCHED) {
                  if (response.second) {
                    this.second = response.second;
                  } else {
                    this.matchStatus = response.matchStatus;
                    this.memberId = response.memberId;
                    this.groupId = response.groupId;
                  }

                } else if (response.matchStatus === MatchStatus.CREATED) {

                  this.memberToken = response.memberToken;
                  this.sessionId = response.sessionId;

                  localStorage.setItem("memberToken", this.memberToken);
                  localStorage.setItem("sessionId", this.sessionId);
                  localStorage.setItem("memberId", this.memberId);
                  localStorage.setItem("screenToken", response.screenToken);
                  this.$router.push({ name: "gameroom" });

                } else if (response.matchStatus === MatchStatus.DESTROYED) {
                  this.matchStatus = MatchStatus.READY;
                  // ToDo 연속 뇌절 금지 만들기   // Destroyed 될때마다 count를 올려주고 일정 count가 되면 일정 시간동안 게임시작 잠그기
                  console.log("");

                }
              }
            },
            (error) => {
              /**
               * ToDo
               * 에러 처리
               * 인터넷 연결, 브라우저 오류 등의 문제로 소켓 연결 실패시 실행되는 callback
               */

              console.log(error);
            });
        },
        (error) => {
          /**
           * ToDo
           * 에러 처리
           * 인터넷 연결, 브라우저 오류 등의 문제로 소켓 연결 실패시 실행되는 callback
           */

          console.log(error)
        },
      )
    },
    logout() {
      api.defaults.headers["Authorization"] = localStorage.getItem('accessToken');
      api.post("/member/logout")
      .then(() => {
        alert("로그아웃 성공!")
        // local storage 제거
        localStorage.removeItem('accessToken')
        localStorage.removeItem('refreshToken')
        // Store 초기화
        this.$store.commit('initUserInfo');
        // 로그인 화면으로 리다이렉트
        this.$router.push({ name: "login" });
      }).catch(error=>{
        console.log(error.response)
        // alert("일시적 오류입니다. 잠시 후 다시 시도해주세요.")
      })
    },
    selectProfile() {
      if(this.selectedScreen == 'ProfileWindowScreen'){
        this.selectedScreen = this.previousScreen;
      }else{
        this.previousScreen = this.selectedScreen;
        this.selectedScreen = 'ProfileWindowScreen';
      }
    },
    selectStart() {
      this.selectedScreen = 'StartWindowScreen';
    },
    selectNotice() {
      this.selectedScreen = 'NoticeWindowScreen';
    },
    selectShop() {
      this.selectedScreen = 'ShopWindowScreen';
    },
    selectRanking() {
      this.selectedScreen = 'RankingWindowScreen';
    },
    selectInfo() {
      this.selectedScreen = 'InfoWindowScreen';
    },
    cancel() {
      this.groupId = "";
      this.memberId = "";
      this.client.disconnect();
      this.matchStatus = MatchStatus.READY;
    },
    enter() {
      /**
       * ToDo
       * 수락 버튼을 누르면 수락 버튼을 꼭 비활성화 해주세요!
       */
      document.getElementById("test").disabled = "disabled";
      this.acceptStatus = true;

      this.client.send(`/enter/${this.groupId}/${this.memberId}`,this.userNickname);
    },
    refreshSidebar(){
      this.$refs.sidebar.refresh();
    },

    ...mapMutations(["setClient"])
  },
  computed: {
    MatchStatus() {
      return MatchStatus
    },
    ...mapState(["userNickname", "ready"])
  },
  beforeUnmount() {
    this.$store.commit('setReady');
  }
};
</script>

<style scoped>
.logo {
  position: absolute;
  left: 125px;

  color: #FF7B27;
  font-family: Luckiest Guy, cursive;
  font-size: 30px;
  font-style: normal;
  font-weight: 400;
  line-height: normal;
  text-decoration: none;
}

.logo:hover {
  color: cornsilk;
}

header {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  background-color: #FFCDAD;
  display: flex;
  justify-content: space-between;
  align-items: center;

  padding: 10px;
  border-top: 1px solid #032c16;
  border-bottom: 2px solid #032c16;
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

.header-center:hover {
  transform: scale(0.96);
}

.header-center span {
  cursor: pointer;
}

.logout-btn {
  position: absolute;
  top: 7px;
  right: 80px;
  margin: 10px 20px;
  color: #F27059;
  font-size: 20px;
  font-style: normal;
  font-weight: 400;
  line-height: normal;
  text-decoration: none;
  cursor: pointer;
}

.logout-btn:hover {
  transform: scale(0.96);
}

.main-container {
  display: flex;
  justify-content: center;
  align-items: center;

  background-color: #F27059;
  padding: 5px;
  overflow-y: hidden;
  height: 100vh;
}

.info {
  margin-right: 25px;
}

.ask-accept {
  animation: beat 1s infinite;
}

.ask-accept::before, .ask-accept::after {
  animation: coldblue 1s infinite;
}

.ask-accept::before {
  transform: rotate(-45deg);
  transform-origin: 0 100%;
}

.ask-accept::after {
  transform: rotate(45deg);
  transform-origin: 100% 100%;
}

@keyframes beat {
  0% {
    transform: scale(1);
  }

  50% {
    transform: scale(1.2);
  }

  100% {
    transform: scale(1);
  }
}
@keyframes coldblue {
  0%, 100% {
    background-color: rgb(255, 255, 255);
  }

  50% {
    background-color: rgb(198, 23, 23);
  }
}
</style>
  
