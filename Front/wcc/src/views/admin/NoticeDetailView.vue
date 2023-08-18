<template>
    <div>
      <header>
        <a href="/home" class="logo">WootCham Club</a>
        <span class="admin-page">관리자 페이지</span>
        <button @click="logout">로그아웃</button>
      </header>
      <div class='main-container'>
        <SideBar/>
        <NoticeDetail v-if="!update" @update="updateNotice" :notice="notice"/>
        <NoticeForm v-else type="update" :notice="notice"/>
      </div>
    </div>
</template>
<script>
import SideBar from './components/SideBar.vue';
import NoticeDetail from './components/NoticeDetail.vue';
import NoticeForm from './components/NoticeForm.vue';
import api from '@/api/index'
  
export default {
  name: 'NoticeDetailView',
  components: {
    SideBar, 
    NoticeDetail, NoticeForm
  },
  data() {
    return {
      update: false,
      notice: {},
    }
  },
  mounted() {
    const pathName = new URL(document.location).pathname.split("/");
    const id = pathName[pathName.length - 1];
    let token = localStorage.getItem("accessToken");
    api.post('/notice/detail', {'id' : id }, {headers : { 'Authorization': token }}).then(({data}) => {
        this.notice = data.data;
        this.notice.id = id;
    }).catch((err) => console.log(err))
  },
  methods: {
    updateNotice() {
      this.update = true;
    },
    logout() {
      // local storage 제거
      localStorage.removeItem('access_token')
      localStorage.removeItem('refresh_token')
      // Store 초기화
      this.$store.commit('initUserInfo');
      // 로그인 화면으로 리다이렉트
      this.$router.push({name: "login"});
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