<template>
  <div class="content-window shadow">
    <div class="banner">
      <img src="@/assets/images/ranking_banner.png" alt="explain_banner">
    </div>

    <div class="content">

      <div class="ranking-modal shadow">
            <div class="rank-card">
              <div class="rank-card-image">
                <img src="@/assets/images/profile.jpg" alt="">
              </div>
              <div class="rank-card-detail">
                <div class="rank-heading">#1</div>
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
            </div>

            <div class="caption"> * 글로벌 TOP5 랭킹입니다. </div>
            
          </div>

    </div>

  </div>
</template>

<script>
export default {
  data(){
    return {
      username: "username example",
      stats: "5000",
    };
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
.ranking-modal {
  position: relative;
	width: 500px;
	height: 440px;
  
	background-color: #FFF2EA;
	border-radius: 15px;
  padding: 10px;

  display: flex;
  flex-direction: column;
  gap: 10px;
}

.rank-card {
  flex: 1;
  background-color: #F27059;
  border-radius: 15px;

  display: flex;
  align-items: center;
  width: 100%;
}

.rank-card-image img{
  width: 50px;
  height: 50px;
  margin: 0 10px 0 10px;
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
  left: 100px;
  top: 15px;
}

.rank-info .rank-username {
  color: #FAAD1B;
  white-space: normal;
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