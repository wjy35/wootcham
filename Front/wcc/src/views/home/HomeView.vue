<template>
    <div id="inner">
        <!-- HEADER -->
        <header>
            <a href="/home" class="logo">WootCham Club</a>

            <div class="header-center">
                <!-- <span @click="handleStartGame">{{ headerText }}</span> -->
                <span v-if="matchStatus === MatchStatus.READY" @click="handleStartGame"> 시작하기 </span>
                
                
                
                    <button v-if="matchStatus === MatchStatus.MATCHING" @click="cancel" >
                          매칭 취소
                    </button>
                
                <template v-if="matchStatus === MatchStatus.MATCHED">
                    <button  @click.once="enter" id="test">수락</button>
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
                @selectShop="selectShop" @selectRanking="selectRanking" @selectInfo="selectInfo" />

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
                <ShopWindow />
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
            client: null,
            groupId: "",
            memberId: "",
            second: "",
            matchStatus: MatchStatus.READY,
            sessionId: "",
            memberToken: "",
            acceptStatus:false,
        };
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
                                    this.memberId

                                    // local storage에 아이템 넣기
                                    localStorage.setItem("memberToken", this.memberToken);
                                    localStorage.setItem("sessionId", this.sessionId);
                                    localStorage.setItem("memberId", this.memberId);

                                    // 게임 페이지로 이동
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
            // local storage 제거
            localStorage.removeItem('access_token')
            localStorage.removeItem('refresh_token')
            // Store 초기화
            this.$store.commit('initUserInfo');
            // 로그인 화면으로 리다이렉트
            this.$router.push({ name: "login" });
        },
        selectProfile() {
            this.selectedScreen = 'ProfileWindowScreen';
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
            console.log("enter called.........")
            document.getElementById("test").disabled = "disabled";
            this.acceptStatus = true;

            this.client.send(`/enter/${this.groupId}/${this.memberId}`);
        },
    },
    computed: {
        MatchStatus() {
            return MatchStatus
        }
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
</style>
  