<template>
  <div class="content-window shadow">
    <div class="banner">
      <img src="@/assets/images/ranking_banner.png" alt="explain_banner">
    </div>

    <div class="content">

      <div class="ranking-modal-left shadow">
        <div class="rank-card" v-for="(r, i) in rankersLeft" :key="i">
          <div class="rank-card-image">
            <img :src='r.profile_img' alt="" v-if="r.border" :style="{ border: '6px outset ' + r.border }">
            <img :src="r.profile_img" alt="" v-else>
          </div>
          <div class="rank-card-detail">
            <div class="rank-heading" >#{{ i + 1 }}</div>
            <div class="rank-info">
              <div class="rank-username badge-on" v-if="r.badge" :style="{ border: '6px outset ' + r.badge }">{{ r.nickname }}</div>
              <div class="rank-username" v-else>{{ r.nickname }}</div>
              <div class="rank-pts-earned">{{ r.point }}</div>
            </div>
          </div>
        </div>
      </div>
      <div class="ranking-modal-right shadow" v-if="rankersRight.length > 0">
        <div class="rank-card" v-for="(r, i) in rankersRight" :key="i">
          <div class="rank-card-image">
            <img :src='r.profile_img' alt="" v-if="r.border" :style="{ border: '6px outset ' + r.border }">
            <img :src="r.profile_img" alt="" v-else>
          </div>
          <div class="rank-card-detail">
            <div class="rank-heading">#{{ left + i + 1 }}</div>
            <div class="rank-info">
              <div class="rank-username badge-on" v-if="r.badge" :style="{ border: '6px outset ' + r.badge }">{{ r.nickname }}</div>
              <div class="rank-username" v-else>{{ r.nickname }}</div>
              <div class="rank-pts-earned">{{ r.point }}</div>
            </div>
          </div>
        </div>
      </div>

            <!-- <div class="rank-card">
              <div class="rank-card-image">
                <img src="@/assets/images/profile.jpg" alt="">
              </div>
              <div class="rank-card-detail">
                <div class="rank-heading">#2</div>
                <div class="rank-info">
                  <div class="rank-username">{{ username }}</div>
                  <div class="rank-pts-earned">{{ stats }}</div>
                </div>
              </div>
            </div>

            <div class="rank-card">
              <div class="rank-card-image">
                <img src="@/assets/images/profile.jpg" alt="">
              </div>
              <div class="rank-card-detail">
                <div class="rank-heading">#3</div>
                <div class="rank-info">
                  <div class="rank-username">{{ username }}</div>
                  <div class="rank-pts-earned">{{ stats }}</div>
                </div>
              </div>
            </div>

            <div class="rank-card">
              <div class="rank-card-image">
                <img src="@/assets/images/profile.jpg" alt="">
              </div>
              <div class="rank-card-detail">
                <div class="rank-heading">#4</div>
                <div class="rank-info">
                  <div class="rank-username">{{ username }}</div>
                  <div class="rank-pts-earned">{{ stats }}</div>
                </div>
              </div>
            </div>

            <div class="rank-card">
              <div class="rank-card-image">
                <img src="@/assets/images/profile.jpg" alt="">
              </div>
              <div class="rank-card-detail">
                <div class="rank-heading">#5</div>
                <div class="rank-info">
                  <div class="rank-username">{{ username }}</div>
                  <div class="rank-pts-earned">{{ stats }}</div>
                </div>
              </div>
            </div> -->

            <!-- <div class="caption"> * 글로벌 TOP5 랭킹입니다. </div> -->
            
          

    </div>

  </div>
</template>

<script>
import api from '@/api/index'

export default {
  data(){
    return {
      rankersLeft: [],
      rankersRight: [],
      left: 0,
    };
  },
  created() {
    let token = localStorage.getItem("accessToken");
    api.get('/rank', { headers : { 'Authorization': token }}).then(({data}) => {
      console.log(data.data[6].border)
      if (data.data.length < 6) {
        this.rankersLeft = data.data;
      } else {
        let left = parseInt(data.data.length / 2);
        this.left = left;
        for (let i = 0; i < left; i++) {
          this.rankersLeft.push(data.data[i])
        }
        for (let i = left; i < data.data.length; i++) {
          this.rankersRight.push(data.data[i])
        }
      }
    }).catch((err) => console.log(err))
  },
}
</script>

<style scoped>
.content-window {
  background-color: #FFF2EA;
  display: flex;
  flex-direction: column;
  align-items: center;
}
.content {
  display: flex;
  justify-content: center;
  width: 95%;
}
.banner img {
  width: 350px;
  margin-top: -60px;
}

.content-window::before {
  content: '';
  position: absolute;
  top: 78%;
  left: -28px;
  width: 50px;
  height: 50px;
  background-image: url('@/assets/images/indicator.png');
  background-size: contain;
  background-repeat: no-repeat;
  background-color: transparent;
}

/* ------------ 랭킹 ------------ */ 
.ranking-modal-left, .ranking-modal-right {
  position: relative;
	width: 500px;
	height: 440px;
  
	background-color: #FFF2EA;
	border-radius: 15px;
  padding: 10px;

  display: flex;
  flex-direction: column;
  justify-content: space-around;
  gap: 10px;
}

.rank-card {
  /* flex: auto; */
  background-color: #F27059;
  border-radius: 15px;

  display: flex;
  align-items: center;
  width: 100%;
  height: 20%;
  /* margin-bottom: 10px; */
}

.rank-card-image img{
  width: 60px;
  height: 60px;
  margin: 0 10px 0 15px;
  border-radius: 15px;
  flex-shrink: 0;
  transition: filter 0.3s ease;
}

.rank-card-image .report-icon {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  font-size: 20px;
  color: red;
  display: none;
}

.rank-card-image:hover .report-icon {
  display: block; 
  cursor: pointer;
}

.rank-card-detail {
  flex-grow: 1;
  background-color: #FFF2EA;
  border-radius: 50px 20px;
  height: 93%;
  position: relative;

  margin: 5px;
}

.rank-card-detail .rank-heading {
  color: #F27059;
  position: absolute;
  left: 10px;
  top: 5px;
  font-size: 3em;
}

.rank-card-detail .rank-info {
  position: absolute;
  left: 110px;
  top: 10px;
}

.rank-info .rank-username {
  color: #FAAD1B;
  white-space: normal;
}

.badge-on {
  border-radius: 10px;
  padding-left: 1rem;
  padding-right: 1rem;
}

.rank-info .rank-pts-earned {
  color: yellowgreen;
  white-space: normal;
}


.rank-card:hover .rank-card-image,
.rank-card:hover .rank-card-detail {
  transform: scale(0.96);
}

.caption {
  font-family: Noto Sans;
  color: grey;
  font-size: 0.8em;
  font-weight: 800;
}
</style>