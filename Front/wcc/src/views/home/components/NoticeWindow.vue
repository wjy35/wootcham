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

.season {
  position: absolute;
  overflow: hidden;
  width: 200px;
  height: 200px;
  top: -10px;
  left: -10px;
  display: flex;
  align-items: center;
  justify-content: center;
}

.season::before {
  position: absolute;
  width: 150%;
  height: 40px;
  background-image: linear-gradient(45deg, #ff6547 0%, #ffb144 51%, #ff7053 100%);
  transform: rotate(-45deg) translateY(-20px);
  display: flex;
  align-items: center;
  justify-content: center;

  content: 'SEASON1';
  font-family: Luckiest Guy;
  color: #fff;
  font-weight: 300;
  letter-spacing: 0.1em;
  box-shadow: 0 5px 10px rgba(0, 0, 0, 0.23);
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
  justify-content: center;

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
  color: black;
  font-weight: 400;
  padding: 20px 30px;
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
</style>