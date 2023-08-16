<template>
  <div class="notice-detail">
    <h1>공 지 사 항</h1>
    <div class="notice-zone">
      <p>{{ notice.subject }}</p>
      <p class="content">{{ notice.content }}</p>
      <p class="date">{{ notice.date }}</p>
      <div class="button-area">
        <SubmitButton class='update-button' @click="update" value="수정"></SubmitButton>
        <SubmitButton class='list-button' @click='noticeList' value="목록"></SubmitButton>
      </div>
    </div>
  </div>
</template>

<script>
import SubmitButton from '@/views/Member/components/UI/SubmitButton.vue';
export default {
  name: 'NoticeDetail',
  components: {SubmitButton},
  props: {
    notice: Object,
  },
  methods: {
    update() {
      this.$emit('update');
    },
    noticeList() {
      this.$router.push({name: 'admin'})
    }
  }
}
</script>

<style scoped>
.notice-detail {
  background-color: #FFF2EA;
  text-align: center;
  
  height: calc(100vh - 150px);
  width: 1000px;
  margin: 70px 30px 0;
  border-radius: 25px;
  border: 3px solid #FFCDAD;
  padding: 0;
}

h1 {
  margin-top: 2rem;
}

.notice-zone {
  width: 80%;
  margin-top: 2rem;
  margin-left: auto;
  margin-right: auto;
  padding: 1rem;
  font-size: 1rem;
  font-family: 'Noto Sans KR', sans-serif;
  background-color: #FFCDAD;
  border-left: 5px solid #F8837A;
  clip-path: polygon(0 0, 100% 0, 100% calc(100% - 20px), calc(100% - 20px) 100%, 0 100%);
}

.notice-zone p {
  font-size: 1rem;
  padding: 0.5rem;
  width: 95%;
  border-radius: 0;
  color: black;
  background-color: #FFF2EA;
  border: none;
  outline: none;
  margin-bottom: 20px;
  font-weight: bold;
  transition: all 0.2s ease-in-out;
  border-left: 5px solid #F8837A;
}

.content {
  margin-bottom: -1.5rem;
  height: 9rem;
  overflow: auto;
}
.date {
  margin-top: -1.5rem;
  text-align: end;
}

.button-area {
  margin-top: -1rem;
  display: flex;
  justify-content: end;
}

.update-button {
    width: auto;
    margin-top: 1.5rem;
    height: 3.5rem;
    padding-left: 2rem;
    padding-right: 2rem;
    margin-right: 1rem;
    background-color: #FFF2EA;
    color: #F8837A;
    align-items: center;
}

.list-button {
    width: auto;
    margin-top: 1.5rem;
    height: 3.5rem;
    padding-left: 2rem;
    padding-right: 2rem;
    margin-right: 1rem;
    color: #FFF2EA;
    background-color: #F8837A;
    align-items: center;
}
</style>