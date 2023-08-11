<template>
  <div class="content-window shadow">
    <div class="banner">
      <img src="@/assets/images/shop_banner.png" alt="explain_banner">
    </div>

    <div class="scroll-snap-card">
      <!-- 프로필 이미지 아이템 3개-->
      <div class="slide">
        <div class="heading">Profile Image Items</div>
        <div class="image-products">
          <div class="image-item" @click="toggleBuy">
            <div class="coin flex text-shadow">
              <img src="@/assets/images/coin.png" style="width: 20px; height: 20px">
              500
            </div>
          </div>
          <div class="image-item" @click="toggleBuy">
            <div class="coin flex text-shadow">
              <img src="@/assets/images/coin.png" style="width: 20px; height: 20px">
              700
            </div>
          </div>
          <div class="image-item" @click="toggleBuy">
            <div class="coin flex text-shadow">
              <img src="@/assets/images/coin.png" style="width: 20px; height: 20px">
              1000
            </div>
          </div>
        </div>
      </div>

      <!-- 프레임 아이템 3개-->
      <div class="slide">
        <div class="heading">Profile Frame Items</div>
          <div class="frame-products">
            <div class="frame-item" @click="toggleBuy">
              <div class="coin flex text-shadow">
                <img src="@/assets/images/coin.png" style="width: 20px; height: 20px">
                500
              </div>
            </div>
            <div class="frame-item" @click="toggleBuy">
              <div class="coin flex text-shadow">
                <img src="@/assets/images/coin.png" style="width: 20px; height: 20px">
                700
              </div>
            </div>
            <div class="frame-item" @click="toggleBuy">
              <div class="coin flex text-shadow">
                <img src="@/assets/images/coin.png" style="width: 20px; height: 20px">
                1000
              </div>
            </div>

         </div>
      </div>

      <!-- 뱃지 아이템 3개 -->
      <div class="slide">
        <div class="heading">Badge Items</div>
          <div class="badge-products">
            <div class="badge-item" @click="toggleBuy">
              <div class="coin flex text-shadow">
                <img src="@/assets/images/coin.png" style="width: 20px; height: 20px">
                500
                <span>username</span>
              </div>
            </div>
            <div class="badge-item" @click="toggleBuy">
              <div class="coin flex text-shadow">
                <img src="@/assets/images/coin.png" style="width: 20px; height: 20px">
                700
              </div>
            </div>
            <div class="badge-item" @click="toggleBuy">
              <div class="coin flex text-shadow">
                <img src="@/assets/images/coin.png" style="width: 20px; height: 20px">
                1000
              </div>
            </div>

          </div>
      </div>
    </div>  

    <!-- 현재 가지고 있는 코인 -->
    <div class="coin-amount shadow">
      <div class="coin flex text-shadow">
        <img src="@/assets/images/coin.png" style="width: 20px; height: 20px">
        40000
      </div>
    </div>

    <!-- 상품 구매 모달창 -->
    <div v-if="showModal" class="modal">
        <div class="modal-card">
          <div class="header">
            <img src="@/assets/images/shopping-cart.png" alt="">
            <span class="title">상품을 구매하시겠습니까?</span>
            <p class="message">구매 내역을 다시 한번 확인해주세요.</p>
            <div class="actions">
              <button class="proceed" type="button">구매하기</button>
              <button class="cancel" type="button" @click="toggleBuy">취소하기</button>
            </div>
          </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      showModal: false,
    }
  },
  methods: {
    toggleBuy(){
      this.showModal = !this.showModal
    }
  }
}
</script>

<style scoped>
.content-window {
  background-color: #FFF2EA;
  display: flex;
  flex-direction: column;
  align-items: center;
  position: relative;
}

.banner img {
  width: 340px;
  margin-top: -80px;
}

.content-window::before {
  content: '';
  position: absolute;
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

/* ---------- IMAGE 프로필 이미지 아이템 ------------ */
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

/* ---------- FRAME 프로필 테두리 아이템 ------------ */
/* 첫 번째 image-item */
.frame-products .frame-item:nth-child(1) div {
  background-image: url("@/assets/images/profile.jpg");
  background-size: cover;
  border: 10px outset #ab5033;
}
.frame-products .frame-item:nth-child(1)::before {
  content: "BRONZE"
}
/* 두 번째 image-item */
.frame-products .frame-item:nth-child(2) div {
  background-image: url("@/assets/images/profile.jpg");
  background-size: cover;
  border: 10px outset silver;
}
.frame-products .frame-item:nth-child(2)::before {
  content: "SILVER"
}
/* 세 번째 image-item */
.frame-products .frame-item:nth-child(3) div {
  background-image: url("@/assets/images/profile.jpg");
  background-size: cover;
  border: 10px outset gold;
}
.frame-products .frame-item:nth-child(3)::before {
  content: "GOLD"
}

/* ---------- 닉네임 뱃지 아이템 ------------ */
/* 첫 번째 image-item */
.badge-products .badge-item:nth-child(1) span {
  border: 10px inset red;
  border-radius: 15px;
}
.badge-products .badge-item:nth-child(1)::before {
  content: "RED"
}
/* 두 번째 image-item */
.badge-products .badge-item:nth-child(2) span {
  border: 10px inset midnightblue;
}
.badge-products .badge-item:nth-child(2)::before {
  content: "BLUE"
}
/* 세 번째 image-item */
.badge-products .badge-item:nth-child(3) span {
  border: 10px inset black;
  border-radius: 15px;
}
.badge-products .badge-item:nth-child(3)::before {
  content: "BLACK"
}


/* ------- COMMON COMPONENTS 공통 컴포넌트 -------- */
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
  flex-direction: column;
  align-items: center;
  justify-content: center;
}

.scroll-snap-card .slide p {
  font-size: 1em;
  color: white;
  font-weight: 700;
}

.heading {
  font-size: 2.5em;
  font-family: Luckiest Guy;
  color: #F27059;
}

.coin-amount {
  position: absolute;
  right: 32px;

  background-color: #FF7B27;
  border-radius: 15px;
}
/* COIN 코인 */
.coin {
  color: white;
  padding: 10px;
  gap: 5px;
}

/* ------------- 공통 컴포넌트 (상점 아이템) ------------- */
.image-products, .frame-products, .badge-products {
  background-color: #FFCDAD;
  gap: 15px;
  display: flex;
  justify-content: center;
  align-items: center;
  width: 100%;
  height: 100%;
  border-radius: 15px;
}

.image-item, .frame-item {
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

.badge-item {
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

.image-item::before, .frame-item::before, .badge-item::before {
 letter-spacing: 0.2em;
 position: absolute;
 bottom: 5px;
 left: 100px;
 color: rgb(51, 51, 51);

 font-family: Luckiest Guy;
 font-size: 1em;
 font-weight: 700;
}

.image-item div, .frame-item div, .badge-item div {
 width: 100%;
 height: 100%;
 border-radius: 20px;
 box-shadow: 0 0 10px rgba(0, 0, 0, 0.212);
 cursor: pointer;
 z-index: 10;
 transition: all .4s;
 background-color: lightcyan;
}

.image-item:hover div, .frame-item:hover div, .badge-item:hover div {
 transform: translateY(-30px);
}

/* -------- 상품 구매 모달창 (Item Buy Modal) ----------- */
.modal {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.5);
  display: flex;
  align-items: center;
  justify-content: center;
}

.modal-card {
  position: relative;
  background-color: #ffffff;
  text-align: center;
  border-radius: 2em;
  height: 350px;
  box-shadow: 0 20px 25px -5px rgba(0, 0, 0, 0.1), 0 10px 10px -5px rgba(0, 0, 0, 0.04);
}

.modal-card .header {
  padding: 1.25rem 1rem 1rem 1rem;
  background-color: #ffffff;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  margin: 20px;
}

.modal-card .header img {
  width: 5rem;
  height: 5rem;
  border-radius: 9999px;
}

.title {
  color: #111827;
  font-size: 2rem;
  font-weight: 400;
  line-height: 1.5rem;
  margin: 10px;
}

.message {
  margin-top: 1rem;
  color: #6B7280;
  font-size: 1rem;
  line-height: 1.25rem;
}

.actions {
  margin: 0.75rem 1rem;
  background-color: #F9FAFB;
}

.proceed {
  display: inline-flex;
  padding: 0.5rem 1rem;
  background-color: #F27059;
  color: #ffffff;
  font-size: 1rem;
  line-height: 1.5rem;
  font-weight: 500;
  justify-content: center;
  width: 100%;
  border-radius: 0.375rem;
  border-width: 1px;
  border-color: transparent;
  box-shadow: 0 1px 2px 0 rgba(0, 0, 0, 0.05);
}

.cancel {
  display: inline-flex;
  margin-top: 0.75rem;
  padding: 0.5rem 1rem;
  background-color: #ffffff;
  color: #374151;
  font-size: 1rem;
  line-height: 1.5rem;
  font-weight: 500;
  justify-content: center;
  width: 100%;
  border-radius: 0.375rem;
  border: 1px solid #D1D5DB;
  box-shadow: 0 1px 2px 0 rgba(0, 0, 0, 0.05);
}

@keyframes sparkle {
  0%, 100% {
    box-shadow: 0px 0px 20px rgba(255, 215, 0, 0.8);
  }
  50% {
    box-shadow: 0px 0px 10px rgba(255, 215, 0, 0.5);
  }
}
</style>