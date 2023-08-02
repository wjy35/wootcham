<template>
  <div class="sidebar shadow">
    <a @click="selectProfile" class="sidebar-info">
      <!-- Profile Image -->
      <img class="profile-image" src="https://i.stack.imgur.com/YQu5k.png" alt="profile" />
      <!-- Username -->
      <div class="username">{{ username }}</div>
      <!-- Game Point -->
      <div class="game-point">
        <div class="coin">
          <div class="side heads">
            <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="#000000" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><circle cx="10" cy="20.5" r="1"/><circle cx="18" cy="20.5" r="1"/><path d="M2.5 2.5h3l2.7 12.4a2 2 0 0 0 2 1.6h7.7a2 2 0 0 0 2-1.6l1.6-8.4H7.1"/></svg>
          </div>
          <div class="side tails">
            <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="#000000" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><circle cx="10" cy="20.5" r="1"/><circle cx="18" cy="20.5" r="1"/><path d="M2.5 2.5h3l2.7 12.4a2 2 0 0 0 2 1.6h7.7a2 2 0 0 0 2-1.6l1.6-8.4H7.1"/></svg>
          </div>
        </div>
        <div class="point-para">
            {{ gamepoint }}
        </div>
      </div>
    </a>

    <div class="sidebar-menus">
      <ul>
        <li><a @click="selectStart" class="start-button value">게임 시작</a></li>
        <li><a @click="selectNotice" class="notice-button value">공지</a></li>
        <li><a @click="selectShop" class="shop-button value">도감</a></li>
        <li><a @click="selectRanking" class="ranking-button value">랭킹</a></li>
        <li><a @click="selectInfo" class="info-button value">게임 설명</a></li>
      </ul>
    </div>


  </div>
</template>

<script>
export default {
  data(){
    return {
      username: "username",
      gamepoint: "1000"
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
.sidebar {
  width: 275px;
  height: 80%;
  margin: 70px 30px 0;
  
  border-radius: 19.535px;
  border: 5px solid #FFCDAD;
  background: #FFF2EA;

  display: flex;
  flex-direction: column;
}

.sidebar-info {
  flex: 40%; /* 사이드바 높이의 40% 차지 */
  display: flex;
  flex-direction: column;
  align-items: center; 
  justify-content: center;
}
.sidebar-info:hover {
  cursor: pointer;
}

.sidebar-menus {
  flex: 60%; /* 사이드바 높이의 60% 차지 */
  display: flex;
  flex-direction: column;
  align-items: center; 
  justify-content: center; 
  border-radius: 5px
}

.sidebar-menus ul li a:hover {
  cursor: pointer;
}

/* Styles for the individual elements inside the sidebar */
.profile-image {
  width: 100px;
  height: 100px;
  flex-shrink: 0;
  border-radius: 136.458px;
}

.game-point {
  display: flex;
  margin: 15px;
}

/* Points */
.coin {
  font-size: 30px;
  width: 0.1em;
  height: 1em;
  background: linear-gradient(#faa504, #FFF2EA);
  margin: 5px;
  animation: rotate_4001510 7s infinite linear;
  transform-style: preserve-3d;
}
.coin .side, .coin:before, .coin:after {
  content: "";
  position: absolute;
  width: 1em;
  height: 1em;
  overflow: hidden;
  border-radius: 50%;
  right: -0.4em;
  text-align: center;
  line-height: 1;
  transform: rotateY(-90deg);
  -moz-backface-visibility: hidden;
  -webkit-backface-visibility: hidden;
  backface-visibility: hidden;
}
.coin .tails, .coin:after {
  left: -0.4em;
  transform: rotateY(90deg);
}
.coin:before, .coin:after {
  background: linear-gradient(#FFCDAD, #FFF2EA);
  backface-visibility: hidden;
  transform: rotateY(90deg);
}
.coin:after {
  transform: rotateY(-90deg);
}
@keyframes rotate_4001510 {
  100% {
    transform: rotateY(360deg);
  }
}
.svg_back {
  transform: scaleX(-1);
}

.value {
  background-color: #FFCDAD;
  border: none;
  padding: 10px;
  margin: 10px;
  color: #FF7B27;
  display: flex;
  position: relative;
  gap: 5px;
  cursor: pointer;
  border-radius: 4px;
}

.value:not(:active):hover,
.value:focus {
  background-color: #FF7B27;
  color: #ffffff;
}

.value:focus,
.value:active {
  background-color: #ffffff;
  outline: none;
}

.value::before {
  content: "";
  position: absolute;
  top: 5px;
  left: -10px;
  width: 5px;
  height: 80%;
  background-color: #ffffff;
  border-radius: 5px;
  opacity: 0;
}

.value:focus::before,
.value:active::before {
  opacity: 1;
}
</style>