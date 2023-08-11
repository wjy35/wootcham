<template>
  <div class="content-window shadow">
    <div class="banner">
      <img src="@/assets/images/shop_banner.png" alt="explain_banner">
    </div>

    <div class="scroll-snap-card">

      <!-- 프로필 이미지 아이템 3개-->
      <div class="slide image-products">
        <div class="image-item">
          <div></div>
        </div>

        <div class="image-item">
          <div></div>
        </div>

        <div class="image-item">
          <div></div>
        </div>


      </div>
      <div class="slide frame-products">
        <p class="tip">Scroll On Me</p>
      </div>
      <div class="slide naming-products">
        <p class="tip">Scroll On Me</p>
      </div>

    </div>
  </div>
</template>

<style scoped>
.content-window {
  background-color: #FFF2EA;
  display: flex;
  flex-direction: column;
  align-items: center;
}

.banner img {
  width: 340px;
  margin-top: -110px;
}

.content-window::before {
  content: '';
  position: absolut-e;
  top: 67%;
  left: -28px;
  width: 50px;
  height: 50px;
  background-image: url('@/assets/images/indicator.png');
  background-size: contain;
  background-repeat: no-repeat;
  background-color: transparent;
}

.content {
  width: 800px;
  height: 700px;
}

/* ------------- 프로필 이미지 상점 ------------- */
.image-products {
  background-color: #f43f5e;
  gap: 15px;
}

.image-item {
  width: 250px;
  height: 250px;
  box-shadow: 0 10px 10px rgba(0, 0, 0, 0.212);
  background: #fff;
  display: flex;
  border-radius: 20px;
  justify-content: center;
  position: relative;
  transition: all .4s;
}

.image-item::before {
  content: '---content---';
  letter-spacing: 0.2em;
  position: absolute;
  bottom: 8px;
  left: 20px;
  color: rgb(51, 51, 51);
  font-size: 0.8em;
  font-weight: 700;
}

.image-item div {
  width: 100%;
  height: 100%;
  border-radius: 20px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.212);
  cursor: pointer;
  z-index: 10;
  transition: all .4s;
  background-color: #fff;
}

.image-item:hover div {
  transform: translateY(-30px);
}

/* ------------- 프레임 상점 ------------- */
.frame-products {
  background-color: #3b82f6;
}

/* ------------- 휘장 상점 ------------- */
.naming-products {
  background-color: #22c55e;
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
}

.scroll-snap-card .slide p {
  font-size: 1em;
  color: white;
  font-weight: 700;
}
</style>