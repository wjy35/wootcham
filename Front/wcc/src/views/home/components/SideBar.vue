<template>
  <div class="sidebar shadow">
    <div class="sidebar-contrast"></div>

    <div @click="selectProfile" class="sidebar-info">

      <!-- 프로필 이미지 & 게임 포인트 -->
      <div class="profile-image-container">
        <div class="profile-image">
          <svg viewBox="0 0 128 128"><circle r="60" fill="transparent" cy="64" cx="64"></circle><circle r="48" fill="transparent" cy="64" cx="64"></circle><path fill="#191919" d="m64 14a32 32 0 0 1 32 32v41a6 6 0 0 1 -6 6h-52a6 6 0 0 1 -6-6v-41a32 32 0 0 1 32-32z"></path><path opacity="1" fill="#191919" d="m62.73 22h2.54a23.73 23.73 0 0 1 23.73 23.73v42.82a4.45 4.45 0 0 1 -4.45 4.45h-41.1a4.45 4.45 0 0 1 -4.45-4.45v-42.82a23.73 23.73 0 0 1 23.73-23.73z"></path><circle r="7" fill="#fbc0aa" cy="65" cx="89"></circle><path fill="#4bc190" d="m64 124a59.67 59.67 0 0 0 34.69-11.06l-3.32-9.3a10 10 0 0 0 -9.37-6.64h-43.95a10 10 0 0 0 -9.42 6.64l-3.32 9.3a59.67 59.67 0 0 0 34.69 11.06z"></path><path opacity=".3" fill="#356cb6" d="m45 110 5.55 2.92-2.55 8.92a60.14 60.14 0 0 0 9 1.74v-27.08l-12.38 10.25a2 2 0 0 0 .38 3.25z"></path><path opacity=".3" fill="#356cb6" d="m71 96.5v27.09a60.14 60.14 0 0 0 9-1.74l-2.54-8.93 5.54-2.92a2 2 0 0 0 .41-3.25z"></path><path fill="#fff" d="m57 123.68a58.54 58.54 0 0 0 14 0v-25.68h-14z"></path><path stroke-width="14" stroke-linejoin="round" stroke-linecap="round" stroke="#fbc0aa" fill="none" d="m64 88.75v9.75"></path><circle r="7" fill="#fbc0aa" cy="65" cx="39"></circle><path fill="#ffd8ca" d="m64 91a25 25 0 0 1 -25-25v-16.48a25 25 0 1 1 50 0v16.48a25 25 0 0 1 -25 25z"></path><path fill="#191919" d="m91.49 51.12v-4.72c0-14.95-11.71-27.61-26.66-28a27.51 27.51 0 0 0 -28.32 27.42v5.33a2 2 0 0 0 2 2h6.81a8 8 0 0 0 6.5-3.33l4.94-6.88a18.45 18.45 0 0 1 1.37 1.63 22.84 22.84 0 0 0 17.87 8.58h13.45a2 2 0 0 0 2.04-2.03z"></path><path style="fill:none;stroke-linecap:round;stroke:#fff;stroke-miterlimit:10;stroke-width:2;opacity:.1" d="m62.76 36.94c4.24 8.74 10.71 10.21 16.09 10.21h5"></path><path style="fill:none;stroke-linecap:round;stroke:#fff;stroke-miterlimit:10;stroke-width:2;opacity:.1" d="m71 35c2.52 5.22 6.39 6.09 9.6 6.09h3"></path><circle r="3" fill="#515570" cy="62.28" cx="76"></circle><circle r="3" fill="#515570" cy="62.28" cx="52"></circle><ellipse ry="2.98" rx="4.58" opacity=".1" fill="#f85565" cy="69.67" cx="50.42"></ellipse><ellipse ry="2.98" rx="4.58" opacity=".1" fill="#f85565" cy="69.67" cx="77.58"></ellipse><g stroke-linejoin="round" stroke-linecap="round" fill="none"><path stroke-width="4" stroke="#fbc0aa" d="m64 67v4"></path><path stroke-width="2" stroke="#515570" opacity=".2" d="m55 56h-9.25"></path><path stroke-width="2" stroke="#515570" opacity=".2" d="m82 56h-9.25"></path></g><path opacity=".4" fill="#f85565" d="m64 84c5 0 7-3 7-3h-14s2 3 7 3z"></path><path fill="#f85565" d="m65.07 78.93-.55.55a.73.73 0 0 1 -1 0l-.55-.55c-1.14-1.14-2.93-.93-4.27.47l-1.7 1.6h14l-1.66-1.6c-1.34-1.4-3.13-1.61-4.27-.47z"></path></svg>
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

.profile-image {
  background: linear-gradient(to right, #FFF2EA, #FFCDAD);
  margin: 0 10px;
  width: 150px;
  height: 150px;
  border-radius: 30px 30px 0 0;
  box-shadow: 5px 10px 20px rgba(0, 0, 0, 0.329);
}
.profile-image-container:hover {
  background-color: #FF7B27;
  transition-duration: .5s;
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