<template>
  <div class="sidebar shadow">
    <div class="sidebar-info">
      <!-- Profile Image -->
      <img class="profile-image" src="https://i.stack.imgur.com/YQu5k.png" alt="profile" />
      <!-- Username -->
      <div class="username">username</div>
      <!-- Game Point -->
      <div class="game-point text-danger">1000 Points</div>
    </div>

    <div class="sidebar-menus">
      <ul>
        <li><a @click="selectStart" class="start-button">게임 시작</a></li>
        <li><a @click="selectNotice" class="notice-button">공지</a></li>
        <li><a @click="selectShop" class="shop-button">도감</a></li>
        <li><a @click="selectRanking" class="ranking-button">랭킹</a></li>
        <li><a @click="selectInfo" class="info-button">게임 설명</a></li>
      </ul>
    </div>


  </div>
</template>

<script>
export default {
  methods: {
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

<style>
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

.sidebar-menus {
  flex: 60%; /* 사이드바 높이의 60% 차지 */
  display: flex;
  flex-direction: column;
  align-items: center; 
  justify-content: center; 
}

.sidebar-menus ul li a {

}

.sidebar-menus ul li a:hover {

}

/* Styles for the individual elements inside the sidebar */
.profile-image {
  width: 121.448px;
  height: 121.448px;
  flex-shrink: 0;
  border-radius: 136.458px;
}

.username {
  /* Add styles for the username here */
}

.game-point {
  /* Add styles for the game point here */
}

</style>