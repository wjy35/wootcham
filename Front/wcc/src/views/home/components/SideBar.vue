<template>
  <div class="sidebar shadow">
    <div class="sidebar-contrast"></div>

    <div @click="selectProfile" class="sidebar-info">

      <!-- 프로필 이미지 & 게임 포인트 -->
      <div class="profile-image-container">
        <div class="profile-image">
          <img src="@/assets/images/profile.png" alt="">
        </div>
        <div class="point-button">
          <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 36 24">
              <path d="m18 0 8 12 10-8-4 20H4L0 4l10 8 8-12z"></path>
          </svg>
          {{ gamepoint }}
        </div>
      </div>

      <div class="username">
        {{ username }}
      </div>
    </div>

    <div class="sidebar-menus">
      <div>
        <div @click="selectStart"
            class="start-button sidebar-menu">게임 시작
        </div>
        <div @click="selectNotice"
          class="-button sidebar-menu">공지
        </div>
        <div @click="selectShop"  
              class="shop-button sidebar-menu">도감
        </div>
        <div @click="selectRanking" 
              class="ranking-button sidebar-menu">랭킹
        </div>
        <div @click="selectInfo" 
              class="info-button sidebar-menu">설명
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data(){
    return {
      username: "username",
      gamepoint: "1000",
      activeMenuItem: ''
    };
  },
  methods: {
    selectProfile(){
      this.$emit('selectProfile');
    },
    selectStart() {
      this.$emit('selectStart');
    },
    selectNotice() {
      this.$emit('selectNotice');
    },
    selectShop() {
      this.$emit('selectShop');
    },
    selectRanking() {
      this.$emit('selectRanking');
    },
    selectInfo() {
      this.$emit('selectInfo');
    },
  },
};
</script>

<style scoped>
/* ------------------------- PROFILE IMAGE --------------------------- */
.profile-image-container {
  width: 155px;
  height: 170px;
  margin: 5x 0 1px;

  background: #FFF2EA;
  border-radius: 40px;
  box-shadow: 0px 0px 30px rgba(0, 0, 0, 0.123);
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: flex-start;
  transition-duration: .3s;
}

.profile-image-container:hover {
  background-color: #FF7B27;
  transition-duration: .5s;
}

.profile-image img {
  width: 150px;
  height: 150px;

  border-radius: 30px 30px 0 0;
  box-shadow: 5px 10px 20px rgba(0, 0, 0, 0.329);
}



/* --------------- PROFILE POINT ------------- */
.point-button {
  width: 150px;
  margin-top: -5px;

  display: flex;
  justify-content: center;
  align-items: center;

  padding: 0.2em 1.5em;
  gap: 0.4rem;
  font-weight: bold;
  border-radius: 0 0 30px 30px;
  text-shadow: 2px 2px 3px rgb(136 0 136 / 50%);
  
  background: linear-gradient(15deg, #880088, #aa2068, #cc3f47, #de6f3d, #f09f33, #de6f3d, #cc3f47, #aa2068, #880088) no-repeat;
  background-size: 300%;
  background-position: left center;
  transition: background .3s ease;
  color: #FFCDAD;
}
.point-button svg {
  width: 18px;
  fill: #f09f33;
  transition: .3s ease;
}

.point-button:hover {
  background-size: 320%;
  background-position: right center;
}

.point-button:hover svg {
  fill: #FF7B27;
}

/* --------------- PROFILE POINT ------------- */
.username {
  margin-top: 20px;
  color: #FF7B27;
    text-shadow: 2px 2px 4px #FFCDAD;
    font-size: 20px;
    font-style: normal;
    font-weight: 400;
    line-height: normal;
}

/* -------------- SIDEBAR SETTINGS ------------- */
.sidebar {
  width: 275px;
  height: 80%;
  margin: 70px 30px 0;
  
  border-radius: 20px;
  border: 5px solid #FFCDAD;
  background: #FFF2EA;

  display: flex;
  flex-direction: column;
}

.sidebar-contrast {
  flex: 20%;
  background-color: #FFD3B7;

  border-radius: 15px 15px 0 0;
}

.sidebar-info {
  flex: 40%; /* 사이드바 높이의 40% 차지 */
  display: flex;
  flex-direction: column;
  align-items: center; 
  justify-content: center;
  cursor: pointer;

  margin-top: -160px;
}

.sidebar-menus {
  flex: 40%; 
  display: flex;
  flex-direction: column;
  align-items: center; 
  justify-content: center; 
}

.sidebar-menu {
  width: 260px;
  border: none;
  padding: 10px;
  margin: 10px;

  color: #FF7B27;
  font-size: 1.2em;

  display: flex;
  justify-content: center;

  cursor: pointer;
  border-radius: 4px;
  background-color: #FFF2EA;

  transition: background-color 0.3s;
}

.sidebar-menu.selected {
  background-color: #FF7B27;
  color: #ffffff;
}

.sidebar-menu:not(:active):hover,
.sidebar-menu:focus {
  background-color: #FF7B27;
  color: #ffffff;
}
.sidebar-menu:focus,
.sidebar-menu:active {
  background-color: #ffffff;
  outline: none;
}
</style>