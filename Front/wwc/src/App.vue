<template>

  <div>
    <div id="inner">
      <!-- HEADER -->
      <header>
        <div class="header-center">{{ headerText }}</div>
        <div class="logout-btn">
          <a href="" v-if="showLogOutButton">로그아웃</a>
        </div>
      </header>

      <!-- CONTENT -->
      <div class="main-container">
        <SideBar
          @selectProfile="selectProfile"
          @selectStart="selectStart"
          @selectNotice="selectNotice" 
          @selectShop="selectShop" 
          @selectRanking="selectRanking"
          @selectInfo="selectInfo"
        />
        
        <!-- Conditional rendering based on the selectedScreen data -->
        <div v-if="selectedScreen === 'ProfileWindowScreen'">
          <ProfileWindow/>
        </div>
        <div v-if="selectedScreen === 'StartWindowScreen'">
          <StartWindow/>
        </div>
        <div v-else-if="selectedScreen === 'NoticeWindowScreen'">
          <NoticeWindow/>
        </div>
        <div v-else-if="selectedScreen === 'ShopWindowScreen'">
          <ShopWindow/>
        </div>
        <div v-else-if="selectedScreen === 'RankingWindowScreen'">
          <RankingWindow/>
        </div>
        <div v-else-if="selectedScreen === 'InfoWindowScreen'">
          <InfoWindow/>
        </div>
      </div>
    </div>
    
  </div>
</template>

<script>
import SideBar from '/src/views/home/components/SideBar.vue';

import ProfileWindow from '/src/views/home/components/ProfileWindow.vue';
import StartWindow from '/src/views/home/components/StartWindow.vue';
import NoticeWindow from '/src/views/home/components/NoticeWindow.vue';
import ShopWindow from '/src/views/home/components/ShopWindow.vue';
import RankingWindow from '/src/views/home/components/RankingWindow.vue';
import InfoWindow from '/src/views/home/components/InfoWindow.vue';

export default {
  name: 'App',
  components: {
    SideBar,
    ProfileWindow,
    StartWindow,
    NoticeWindow,
    ShopWindow,
    RankingWindow,
    InfoWindow,
  },
  data(){
    return {
      headerText: "게임을 시작하기 위해 카메라를 켜주세요",
      showLogoutButton: false, // 카메라가 켜지기 전까지 logout 버튼은 숨겨진다. 
      selectedScreen: 'StartWindowScreen', // 초기화면은 StartWindowScreen
    };
  },
  methods: {
    // 카메라가 켜졌을 때 실행되는 로직 (카메라 사용 가능 여부 감지)
    handleCameraOn() {
      this.headerText = "게임 시작";
      this.showLogoutButton = true;
    },
    // '게임 시작' 버튼이 누르면 '매칭중입니다' 표시 
    handleStartGame(){
      this.headerText = "매칭중입니다";
    },
    handleLogout(){
      // 로그아웃 로직 
    },
    selectProfile(){
      this.selectedScreen = 'ProfileWindowScreen';
    },
    selectStart(){
      this.selectedScreen = 'StartWindowScreen';
    },
    selectNotice(){
      this.selectedScreen = 'NoticeWindowScreen';
    },
    selectShop(){
      this.selectedScreen = 'ShopWindowScreen';
    },
    selectRanking(){
      this.selectedScreen = 'RankingWindowScreen';
    },
    selectInfo(){
      this.selectedScreen = 'InfoWindowScreen';
    },
  }
};
</script>

<style>
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
  flex-grow:1;

  color: #FFF;
  text-shadow: 2px 2px 4px #FF432A;
  font-size: 30px;
  font-style: normal;
  font-weight: 400;
  line-height: normal;
}

.logout-btn {
  position: absolute;
  top: 0;
  right: 0;
  margin: 10px 20px;
  color: #FF7B27;
  font-size: 25px;
  font-style: normal;
  font-weight: 400;
  line-height: normal;
  text-decoration: none;
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
