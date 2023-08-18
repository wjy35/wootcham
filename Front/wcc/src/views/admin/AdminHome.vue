<template>
  <div>
    <header>
      <a href="/home" class="logo">WootCham Club</a>
      <span class="admin-page">관리자 페이지</span>
      <button @click="logout">로그아웃</button>
    </header>
    <div class='main-container'>
      <SideBar/>
      <NoticeList/>
    </div>
  </div>
</template>
<script>
import SideBar from './components/SideBar.vue';
import NoticeList from './components/NoticeList.vue';

export default {
  name: 'AdminHome',
  components: {
    SideBar, NoticeList
  },
  data() {
    return {
    }
  },
  methods: {
    logout() {
      // local storage 제거
      localStorage.removeItem('access_token')
      localStorage.removeItem('refresh_token')
      // Store 초기화
      this.$store.commit('initUserInfo');
      // 로그인 화면으로 리다이렉트
      this.$router.push({ name: "login" });
    },
  }
}
</script>
<style scoped>
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
  /* border-top: 1px solid #032c16;
  border-bottom: 2px solid #032c16; */
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

.logo {
  /* position: absolute; */
  /* left: 125px; */

  color: #FF7B27;
  font-family: Luckiest Guy, cursive;
  font-size: 30px;
  font-style: normal;
  font-weight: 400;
  line-height: normal;
  text-decoration: none;
}

.admin-page {
  color: #FFF;
  text-shadow: 2px 2px 4px #FF432A;
  font-size: 30px;
  font-style: normal;
  font-weight: 400;
  line-height: normal;
}
</style>