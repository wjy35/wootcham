<template>
  <div class="content-window shadow">
    <div class="banner">
      <img src="@/assets/images/notice_banner.png" alt="notice_banner">
    </div>

    <div class="content">
      </div>
        <div class="flex ">
          <div class="heading">{{ noticeTitle }}</div>
          <div class="para_date">{{ noticeDate }}</div>
        </div>
        <div class="para_content">{{ noticeContent }}</div>
    </div>
        
</template>

<script>
export default {
  data(){
    return {
      noticeTitle: "웃참클럽 1주년 기념 이벤트 공지",
      noticeDate: "2023-07-23",
      noticeContent: "웃참클럽 1주년 기념 이벤트 공지 웃참클럽의 첫 생일을 축하하기 위해 웃참클럽이 생일 아이콘을 쏩니다!더불어 인기 아이템 최대 80% 할인, 저녁 9시부터 11시까지 게임 시 포인트 50% 추가 증정 등 다양한 혜택을 즐기세요! 이벤트 기간 : 2023/7/24 06:00 ~2023/7/27 24:00",
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

.banner img {
  width: 340px;
  margin-top: -60px;
}

.content {

}
</style>