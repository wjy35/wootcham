<template>
  <div class="content-window shadow">
    <span class="season"></span>

    <div class="banner">
      <img src="@/assets/images/notice_banner.png" alt="notice_banner">
    </div>


    <div class="scroll-snap-card">
      <div class="slide one">
          <div class="item">
            <div class="notice-date">{{ noticeDate }}</div>
            <div class="notice-heading">{{ noticeTitle }}</div>
            <div class="notice-content">{{ noticeContent }}</div>
          </div>
      </div>
      <div class="slide two">
          <p class="tip">Scroll On Me</p>
      </div>
      <div class="slide three">
          <p class="tip">Scroll On Me</p>
      </div>
    </div>

    </div>
        
</template>

<script>
export default {
  data(){
    return {
      noticeTitle: "웃참클럽 1주년 기념 이벤트 공지",
      noticeDate: "2023-07-23",
      noticeContent: "웃참클럽 1주년 기념 이벤트 공지 웃참클럽의 첫 생일을 축하하기 위해 웃참클럽이 생일 아이콘을 쏩니다!더불어 인기 아이템 최대 80% 할인, 저녁 9시부터 11시까지 게임 시 포인트 50% 추가 증정 등 다양한 혜택을 즐기세요!",
    };
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
  background-image: linear-gradient(45deg, #ff6547 0%, #ffb144  51%, #ff7053  100%);
  transform: rotate(-45deg) translateY(-20px);
  display: flex;
  align-items: center;
  justify-content: center;

  content: 'SEASON1';
  font-family: Luckiest Guy;
  color: #fff;
  font-weight: 300;
  letter-spacing: 0.1em;
  box-shadow: 0 5px 10px rgba(0,0,0,0.23);
}

.banner img {
  width: 340px;
  margin-top: -60px;
}


/* 메인 콘텐츠 */
.one {
  background-color: #ece8e1;
}

.two {
  background-color: #3b82f6;
}

.three {
  background-color: #22c55e;
}

.scroll-snap-card {
  height: 100%;
  width: 100%;
  scroll-snap-type: y mandatory;
  overflow: auto;
  border-radius: 10px;
  margin-top: -10px;
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
  transition: font-size 0.5s ease, transform 1s ease;
}

.scroll-snap-card .slide .item .notice-heading:hover {
  transform: scale(1.1); 
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