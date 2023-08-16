<template>
  <div class="content-window shadow">
    <span class="season"></span>


    <div class="scroll-snap-card">
      <div class = "slide" v-for="(item, index) in this.notices" :key="index">
        <div class = "item">
          <div class="notice-date">{{ item.date }}</div>
          <div class="notice-heading">{{ item.subject }}</div>
          <div class="notice-content">{{ item.content }}</div>
        </div>
      </div>
    </div>

  </div>
</template>

<script>
import api from "@/api"

export default {
  data() {
    return {
      notices:{},
    };
  },
  created(){
    api.defaults.headers["Authorization"] = localStorage.getItem("accessToken");
    api.get('/notice')
    .then(({data}) => {
      console.log(data)
      this.notices = data.data;
    }).catch((error)=>{
      console.log(error);
    })
  }
}
</script>

<style scoped>
.content-window {
  background-color: #FFF2EA;

  display: flex;
  flex-direction: column;
  align-items: center;
}

.content-window::before {
  content: '';
  position: absolute;
  top: 56.5%;
  left: -28px;
  width: 50px;
  height: 50px;
  background-image: url('@/assets/images/indicator.png');
  background-size: contain;
  background-repeat: no-repeat;
  background-color: transparent;
}

/* 메인 콘텐츠 */
.one {
  background-color: #ece8e1;
}

.two {
  background-color: #ece8e1;
}

.three {
  background-color: #ece8e1;
}

.scroll-snap-card {
  height: 100%;
  width: 100%;
  scroll-snap-type: y mandatory;
  overflow: auto;
  border-radius: 10px;
}

.scroll-snap-card .slide {
  width: 100%;
  height: 100%;
  scroll-snap-align: start;
  display: flex;
  align-items: center;
  justify-content: flex-start;

  padding: 20px 50px 0;
}

.scroll-snap-card .slide .item .notice-date {
  font-size: 1em;
  color: black;
  font-weight: 400;
  padding-left: 30px;
}

.scroll-snap-card .slide .item .notice-heading {
  font-size: 3em;
  color: #ff6547;
  font-weight: 400;
  padding: 10px 20px;
  cursor: pointer;
  transition: font-size 0.5s ease, transform 1s ease, color 1s ease;
}

.scroll-snap-card .slide .item .notice-heading:hover {
  transform: scale(1.1); 
  color: #ff6547;
}

.scroll-snap-card .slide .item .notice-content {
  font-family: Noto Sans;
  font-size: 1.2em;
  color: grey;
  font-weight: 700;
  padding: 25px;
  transition: font-size 0.5s ease, transform 1s ease;
}

.scroll-snap-card .slide .item .notice-content:hover {
  transform: scale(1.1);
}

.scroll img {
  position: absolute;
  left: 470px;
  bottom: 15px;
  width: 50px;
  height: 50px;
}
</style>