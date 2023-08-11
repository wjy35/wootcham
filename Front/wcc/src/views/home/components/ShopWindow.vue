<template>
  <div class="content-window shadow">
    <div class="banner"> 
      <img src="@/assets/images/shop_banner.png" alt="explain_banner">
    </div>

    <div class="scroll-snap-card">
      <!-- 프로필 이미지 아이템 3개-->
      <div class="slide image-products">
        <div class="heading"></div>
        <div class="image-item">
          <div class="coin flex text-shadow">
            <img src="@/assets/images/coin.png" style="width: 20px; height: 20px">
            500
          </div>
        </div>
        <div class="image-item">
          <div class="coin flex text-shadow">
            <img src="@/assets/images/coin.png" style="width: 20px; height: 20px">
            700
          </div>
        </div>
        <div class="image-item">
          <div class="coin flex text-shadow">
            <img src="@/assets/images/coin.png" style="width: 20px; height: 20px">
            1000
          </div>
        </div>
      </div>

      <!-- 프레임 아이템 3개-->
      <div class="slide frame-products">


      </div>

      <!-- 휘장 아이템 3개 -->
      <div class="slide naming-products">
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

.image-products .image-item {
 width: 250px;
 height: 250px;
 box-shadow: 0 10px 10px rgba(0, 0, 0, 0.212);
 background: #fff;
 display: flex;
 border-radius: 20px;
 align-items: center;
 justify-content: center;
 position: relative;
 transition: all .4s;
}

.image-products .image-item::before {
 letter-spacing: 0.2em;
 position: absolute;
 bottom: 5px;
 left: 100px;
 color: rgb(51, 51, 51);

 font-family: Luckiest Guy;
 font-size: 1em;
 font-weight: 700;
}

.image-products .image-item div {
 width: 100%;
 height: 100%;
 border-radius: 20px;
 box-shadow: 0 0 10px rgba(0, 0, 0, 0.212);
 cursor: pointer;
 z-index: 10;
 transition: all .4s;
 background-color: lightcyan;
}

.image-products .image-item:hover div {
 transform: translateY(-30px);
}

/* 첫 번째 image-item */
.image-products .image-item:nth-child(1) div {
  background-image: url("@/assets/images/profile_low.jpg");
  background-size: cover;
}

.image-products .image-item:nth-child(1)::before {
  content: "Lucky"
}

/* 두 번째 image-item */
.image-products .image-item:nth-child(2) div {
  background-image: url("@/assets/images/profile_mid.jpg");
  background-size: cover;
}

.image-products .image-item:nth-child(2)::before {
  content: "Shady"
}

/* 세 번째 image-item */
.image-products .image-item:nth-child(3) div {
  background-image: url("@/assets/images/profile_high.jpg");
  background-size: cover;
}

.image-products .image-item:nth-child(3)::before {
  content: "Crazy"
}

/* ------------- 프레임 상점 ------------- */
.frame-products {
  background-color: #3b82f6;
  gap: 15px;
}

/* ------------- 휘장 상점 ------------- */
.naming-products {
  background-color: #22c55e;
  gap: 15px;
}

/* COMMON COMPONENTS 공통 컴포넌트 */
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

/* COIN 코인 */
.coin {
  color: white;
  padding: 10px;
  gap: 5px;
}
</style>