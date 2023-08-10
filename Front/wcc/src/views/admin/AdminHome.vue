<template>
  <div>
    <header></header>
    <div class='main-content'>
      <SideBar @selectNoticeList="selectNoticeList" @selectUserList="selectUserList"/>
      <NoticeList v-if='currentPage === "Notice"'/>
      <UserList v-if='currentPage === "UserList"'/>
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
      currentPage: 'Notice'
    }
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
  border-top: 1px solid #032c16;
  border-bottom: 2px solid #032c16;
}
</style>