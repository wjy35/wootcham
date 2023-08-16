<template>
  <div class="content-window shadow">
    <div class="banner">
      <img src="@/assets/images/shop_banner.png" alt="explain_banner">
    </div>

    <div class="scroll-snap-card">
      <!-- 프로필 이미지 -->
      <div class="slide" v-for="count in (parseInt((profile.length + 2) / 3))" :key="count">
        <div class="heading">Profile Image Items</div>
        <div class="image-products">
          <div class="image-item" @click="toggleBuy(profile[(count - 1) * 3])" v-if="profile.length >= (count - 1) * 3 + 1">
            <div class="coin flex text-shadow">
              <img class='itemImg' :src='profile[(count - 1) * 3].url' style="border-radius: 10px;">
              <img class='coin-img' src="@/assets/images/coin.png" style="width: 20px; height: 20px;">
              <span class="price">{{ profile[(count - 1) * 3].price }}</span>
            </div>
            <span class="desc">{{ profile[(count - 1) * 3].name }}{{ profile[(count - 1) * 3].buy ? '(보유 중)' : '' }}{{ profile[(count - 1) * 3].wear ? '(착용 중)' : '' }}</span>
          </div>
          <div class="image-item" @click="toggleBuy(profile[(count - 1) * 3 + 1])" v-if="profile.length >= (count - 1) * 3 + 2">
            <div class="coin flex text-shadow">
              <img class='itemImg' :src='profile[(count - 1) * 3 + 1].url' style="border-radius: 10px;">
              <img class='coin-img' src="@/assets/images/coin.png" style="width: 20px; height: 20px">
              <span class="price">{{ profile[(count - 1) * 3 + 1].price }}</span>
            </div>
            <span class="desc">{{ profile[(count - 1) * 3 + 1].name }}{{ profile[(count - 1) * 3 + 1].buy ? '(보유 중)' : '' }}{{ profile[(count - 1) * 3 + 1].wear ? '(착용 중)' : '' }}</span> 
          </div>
          <div class="image-item" @click="toggleBuy((profile[(count - 1) * 3 + 2]))" v-if="profile.length >= (count - 1) * 3 + 3">
            <div class="coin flex text-shadow">
              <img class='itemImg' :src='profile[(count - 1) * 3 + 2].url' style="border-radius: 10px;">
              <img class='coin-img' src="@/assets/images/coin.png" style="width: 20px; height: 20px">
              <span class="price">{{ profile[(count - 1) * 3 + 2].price }}</span>
            </div>
            <span class="desc">{{ profile[(count - 1) * 3 + 2].name }}{{ profile[(count - 1) * 3 + 2].buy ? '(보유 중)' : '' }}{{ profile[(count - 1) * 3 + 2].wear ? '(착용 중)' : '' }}</span>
          </div>
        </div>
      </div>
      <!-- 프레임 -->
      <div class="slide" v-for="count in (parseInt((frame.length + 2)/ 3))" :key="count">
        <div class="heading">Profile Frame Items</div>
        <div class="image-products">
          <div class="image-item" @click="toggleBuy(frame[(count - 1) * 3])" v-if="frame.length >= (count - 1) * 3 + 1">
            <div class="coin flex text-shadow">
              <img class='itemImg' :src='myProfile' :style="{ border: '10px outset ' + frame[(count - 1) * 3].url, 'border-radius': '10px'}">
              <img class='coin-img' src="@/assets/images/coin.png" style="width: 20px; height: 20px">
              <span class="price">{{ frame[(count - 1) * 3].price }}</span>
            </div>
            <span class="desc">{{ frame[(count - 1) * 3].name }}{{ frame[(count - 1) * 3].buy ? '(보유 중)' : '' }}{{ frame[(count - 1) * 3].wear ? '(착용 중)' : '' }}</span>
          </div>
          <div class="image-item" @click="toggleBuy(frame[(count - 1) * 3 + 1])" v-if="frame.length >= (count - 1) * 3 + 2">
            <div class="coin flex text-shadow">
              <img class='itemImg' :src='myProfile' :style="{ border: '10px outset ' + frame[(count - 1) * 3 + 1].url, 'border-radius': '10px'}">
              <img class='coin-img' src="@/assets/images/coin.png" style="width: 20px; height: 20px">
              <span class="price">{{ frame[(count - 1) * 3 + 1].price }}</span>
            </div>
            <span class="desc">{{ frame[(count - 1) * 3 + 1].name }}{{ frame[(count - 1) * 3 + 1].buy ? '(보유 중)' : '' }}{{ frame[(count - 1) * 3 + 1].wear ? '(착용 중)' : '' }}</span> 
          </div>
          <div class="image-item" @click="toggleBuy((frame[(count - 1) * 3 + 2]))" v-if="frame.length >= (count - 1) * 3 + 3">
            <div class="coin flex text-shadow">
              <img class='itemImg' :src='myProfile' :style="{ border: '10px outset ' + frame[(count - 1) * 3 + 2].url, 'border-radius': '10px'}">
              <img class='coin-img' src="@/assets/images/coin.png" style="width: 20px; height: 20px">
              <span class="price">{{ frame[(count - 1) * 3 + 2].price }}</span>
            </div>
            <span class="desc">{{ frame[(count - 1) * 3 + 2].name }}{{ frame[(count - 1) * 3 + 2].buy ? '(보유 중)' : '' }}{{ frame[(count - 1) * 3 + 2].wear ? '(착용 중)' : '' }}</span>
          </div>
        </div>
      </div>

      <div class="slide" v-for="count in (parseInt((badge.length + 2) / 3))" :key="count">
        <div class="heading">Profile Badge Items</div>
        <div class="image-products">
          <div class="badge-item" @click="toggleBuy(badge[(count - 1) * 3])" v-if="badge.length >= (count - 1) * 3 + 1">
            <div class="coin flex text-shadow">
              <img class='coin-img' src="@/assets/images/coin.png" style="width: 20px; height: 20px">
              <span class="price">{{ badge[(count - 1) * 3].price }}</span>
              <span class='badge-nickname' :style="{ border: '7px inset ' + badge[(count - 1) * 3].url }">{{ myNickname }}</span>
            </div>
            <span class="desc">{{ badge[(count - 1) * 3].name }}{{ badge[(count - 1) * 3].buy ? '(보유 중)' : '' }}{{ badge[(count - 1) * 3].wear ? '(착용 중)' : '' }}</span>
          </div>
          <div class="badge-item" @click="toggleBuy(badge[(count - 1) * 3 + 1])" v-if="badge.length >= (count - 1) * 3 + 2">
            <div class="coin flex text-shadow">
              <img class='coin-img' src="@/assets/images/coin.png" style="width: 20px; height: 20px">
              <span class="price">{{ badge[(count - 1) * 3 + 1].price }}</span>
              <span class='badge-nickname' :style="{ border: '7px inset ' + badge[(count - 1) * 3 + 1].url }">{{ myNickname }}</span>
            </div>
            <span class="desc">{{ badge[(count - 1) * 3 + 1].name }}{{ badge[(count - 1) * 3 + 1].buy ? '(보유 중)' : '' }}{{ badge[(count - 1) * 3 + 1].wear ? '(착용 중)' : '' }}</span> 
          </div>
          <div class="badge-item" @click="toggleBuy((badge[(count - 1) * 3 + 2]))" v-if="badge.length >= (count - 1) * 3 + 3">
            <div class="coin flex text-shadow">
              <img class='coin-img' src="@/assets/images/coin.png" style="width: 20px; height: 20px">
              <span class="price">{{ badge[(count - 1) * 3 + 2].price }}</span>
              <span class='badge-nickname' :style="{ border: '7px inset ' + badge[(count - 1) * 3 + 2].url }">{{ myNickname }}</span>
            </div>
            <span class="desc">{{ badge[(count - 1) * 3 + 2].name }}{{ badge[(count - 1) * 3 + 2].buy ? '(보유 중)' : '' }}{{ badge[(count - 1) * 3 + 2].wear ? '(착용 중)' : '' }}</span>
          </div>
        </div>
      </div>
      <!-- <div class="slide">
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
      </div> -->
      <!-- <div class="slide">
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
                <span>username</span>
              </div>
            </div>
            <div class="badge-item" @click="toggleBuy">
              <div class="coin flex text-shadow">
                <img src="@/assets/images/coin.png" style="width: 20px; height: 20px">
                1000
                <span>username</span>
              </div>
            </div>

          </div>
      </div> -->
    </div> 

    <!-- 현재 가지고 있는 코인 -->
    <div class="coin-amount shadow">
      <div class="coin flex text-shadow">
        <img src="@/assets/images/coin.png" style="width: 20px; height: 20px">
        {{ myMoney }}
      </div>
    </div>

    <!-- 상품 구매 모달창 -->
    <div v-if="showModal" class="modal">
      <div class="modal-card" v-if="selectedItem.buy && !selectedItem.wear">
        <div class="header">
          <span class="title"><span class="title-name">{{ selectedItem.name }} {{ selectedItem.type }}</span>착용하시겠습니까?</span>
          <p class="message">상품 설명: {{ selectedItem.description }}</p>
          <div class="actions">
            <button class="proceed" type="button" @click="wearItem">착용하기</button>
            <button class="cancel" type="button" @click="toggleBuy(selectedItem)">취소하기</button>
          </div>
        </div>
      </div>
      <div class="modal-card" v-if="!selectedItem.buy && selectedItem.price <= myMoney">
        <div class="header">
          <img src="@/assets/images/shopping-cart.png" alt="">
          <span class="title"><span class="title-name">{{ selectedItem.name }}</span>구매하시겠습니까?</span>
          <p class="message">상품 설명: {{ selectedItem.description }}</p>
          <div class="actions">
            <button class="proceed" type="button" @click="buyItem">구매하기</button>
            <button class="cancel" type="button" @click="toggleBuy(selectedItem)">취소하기</button>
          </div>
        </div>
      </div>
      <div class="modal-card" v-if="!selectedItem.buy && selectedItem.price > myMoney">
        <div class="header">
          <img src="@/assets/images/coin.png" alt="">
          <span class="title">잔액이 부족합니다.</span>
          <div class="actions">
            <button class="cancel" type="button" @click="toggleBuy(selectedItem)">돌아가기</button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import api from '@/api/index'
// import axios from 'axios'

export default {
  data() {
    return {
      showModal: false,
      profile: [],
      frame: [],
      badge: [],
      myMoney: 0,
      selectedItem: undefined,
      myProfile: '',
      myNickname: '',
    }
  },
  methods: {
    toggleBuy(item){
      if (!(item.buy && item.wear)) {
        if (!this.showModal) {
          this.showModal = true;
          this.selectedItem = item;
        } else {
          this.showModal = false;
          this.selectedItem = undefined;
        }
      }
    },
    buyItem() {
      let token = localStorage.getItem("accessToken");
      api.post('/collection', {'collection_id' : this.selectedItem.id }, {headers : { 'Authorization': token }}).then(() => {
        alert('구매 성공!');
        this.selectedItem = undefined;
        this.showModal = false;
      }).then(() => {
        this.callItems();
        this.callMoney();
      }).catch((err) => alert('구매 실패' + err));
    },
    wearItem() {
      let token = localStorage.getItem("accessToken");
      api.put('/collection', {'collection_id' : this.selectedItem.id }, {headers : { 'Authorization': token }}).then(() => {
        alert('착용 성공!');
        this.selectedItem = undefined;
        this.showModal = false;
      }).then(() => {
        this.callItems();
        this.callMoney();
      }).catch((err) => {
        alert('착용 실패' + err);
      })
    },
    callItems() {
      let token = localStorage.getItem("accessToken");
      api.get('/collection', {headers : { 'Authorization': token }}).then(({data}) => {
        console.log(data.data);

        let profile = []
        let frame = []
        let badge = []

        for (let i of data.data) {
          if (i.type === '프로필') {
            profile.push(i);
          } else if (i.type === '휘장') {
            badge.push(i);
          } else if (i.type === '테두리') {
            frame.push(i);
          }
        }

        this.profile = profile;
        this.frame = frame;
        this.badge = badge;
      }).catch((err) => console.log(err))
    },
    callMoney() {
      let token = localStorage.getItem("accessToken");
      api.post('/member', {headers : { 'Authorization': token }}).then(({data}) => { 
        console.log(data.data);
        this.myMoney = data.data.money;
        this.myProfile = data.data.profile_img;
        this.myNickname = data.data.nickname;
      })
    }
  },
  created() {
    this.callItems();
    this.callMoney();
  },
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
/* .image-products .image-item:nth-child(1) div {
  background-image: url("@/assets/images/profile_low.jpg");
  background-size: cover;
} */
/* .image-products .image-item:nth-child(1)::before {
  content: 'LUCKY'
} */
/* 두 번째 image-item */
/* .image-products .image-item:nth-child(2) div {
  background-image: url("@/assets/images/profile_mid.jpg");
  background-size: cover;
} */
/* .image-products .image-item:nth-child(2)::before {
  content: "Shady"
} */
/* 세 번째 image-item */
/* .image-products .image-item:nth-child(3) div {
  background-image: url("@/assets/images/profile_high.jpg");
  background-size: cover;
} */
/* .image-products .image-item:nth-child(3)::before {
  content: "Crazy"
} */

/* ---------- FRAME 프로필 테두리 아이템 ------------ */
/* 첫 번째 FRAME-item */
.frame-products .frame-item:nth-child(1) div {
  background-image: url("@/assets/images/profile.jpg");
  background-size: cover;
  border: 10px outset #ab5033;
}
.frame-products .frame-item:nth-child(1)::before {
  content: "BRONZE"
}
/* 두 번째 FRAME-item */
.frame-products .frame-item:nth-child(2) div {
  background-image: url("@/assets/images/profile.jpg");
  background-size: cover;
  border: 10px outset silver;
}
.frame-products .frame-item:nth-child(2)::before {
  content: "SILVER"
}
/* 세 번째 FRAME-item */
.frame-products .frame-item:nth-child(3) div {
  background-image: url("@/assets/images/profile.jpg");
  background-size: cover;
  border: 10px outset gold;
}
.frame-products .frame-item:nth-child(3)::before {
  content: "GOLD"
}

/* ---------- 닉네임 뱃지 아이템 ------------ */
/* 첫 번째 BADGE-item */
.badge-products .badge-item:nth-child(1) span {
  width: 150px;
  height: 50px;
  border: 7px inset tomato;
  border-radius: 15px;
  color: #020715;

  display: flex;
  justify-content: center;
  align-items: center;

  margin-top: 80px;
  margin-left: -15px;
}
.badge-products .badge-item:nth-child(1)::before {
  content: "RED"
}

/* 두 번째 BADGE-item */
.badge-products .badge-item:nth-child(2) span {
  width: 150px;
  height: 50px;
  border: 10px inset goldenrod;
  border-radius: 15px;
  color: #020715;

  display: flex;
  justify-content: center;
  align-items: center;

  margin-top: 80px;
  margin-left: -17px;
}
.badge-products .badge-item:nth-child(2)::before {
  content: "GOLD"
}

/* 세 번째 BADGE-item */
.badge-products .badge-item:nth-child(3) span {
  width: 150px;
  height: 50px;
  border: 10px inset #374151;
  border-radius: 15px;
  color: #020715;

  display: flex;
  justify-content: center;
  align-items: center;

  margin-top: 80px;
  margin-left: -20px;
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
  cursor: pointer;
  transition: transform 0.3s ease-in-out;
}
.coin-amount:hover {
  transform: scale(1.1);
  border: 2px solid white;
}


/* COIN 코인 */
.coin {
  color: white;
  padding: 10px;
  gap: 5px;
  position: relative;
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
  overflow-x: auto;
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

.badge-item div {
  background-color: #FFF2EA;
}

.image-item:hover div, .frame-item:hover div, .badge-item:hover div {
 transform: translateY(-30px);
}

.itemImg {
  width: 100%;
  height: 100%;
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

.desc {
  position: absolute;
  bottom: 1px;
  color: black;
  font-size: 1.2em;
}
.coin-img {
  position: absolute;
  top: 20px;
  left: 20px;
}

.price {
  position: absolute;
  top: 17px;
  left: 45px;
}

.title-name {
  font-size: 2rem;
  font-weight: 400;
  line-height: 1.5rem;
  margin: 10px;
  color: #F27059;
}

.badge-nickname {
  width: 150px;
  height: 50px;
  border-radius: 15px;
  color: #020715;

  display: flex;
  justify-content: center;
  align-items: center;

  margin-top: 80px;
  margin-left: -15px;
  margin-left: auto;
  margin-right: auto;
}
</style>
