<template>
    <div id="inner">
        <!-- HEADER -->
        <header>
            <a href="/home" class="logo">WootCham Club</a>

            <div class="header-center">
                <span @click="handleStartGame">{{ headerText }}</span>
            </div>

            <div class="logout-btn">
                <div @click="logout">Log Out</div>
                <!-- 로그아웃 기능 추가하여야 함 -->
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
import { mapState } from 'vuex';

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
            showCameraModel: false,
            cameraOn: false
        };
    },
    methods: {
        // '게임 시작' 버튼이 누르면 '매칭중입니다' 표시 
        async handleStartGame() {
            const stream = await navigator.mediaDevices.getUserMedia({ video: true });
            stream.getTracks().forEach(track => track.stop()); // Release the camera
            
            this.cameraOn = true;
            this.headerText = "매칭중입니다";
            // Navigate to gameroom
            this.$router.push({ name: "homeview" });
        },
        toggleCameraModal() {
            this.showCameraModal = !this.showCameraModal;
        },
        logout() {
            this.$router.push({name: "login"});
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
    },
    computed: {
        ...mapState(['cameraOn', 'gameReady'])
    },
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
  