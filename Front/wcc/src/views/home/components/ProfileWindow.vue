<template>
  <div class="content-window shadow">
    <div class="content">

      <!-- 프로파일 화면 -->
      <div class="card">
        <!-- 프로필 이미지 -->
        <div class="img">
          <img v-if="this.border" class='frameImg' :src='this.profile_img' :style="{ width: '100%', border: '10px outset ' + this.border }"> 
          <img v-else :src="this.profile_img" :style="{ width: '100%', 'border-radius': '15px'}" alt="">
        </div>

        <!-- 유저네임 -->
        <!-- <div v-if="this.badge" class="username badge-nickname" :style="{ border: '7px inset ' + this.badge }" v-text = "this.nickname"></div> -->
        <div class="nickname">
          <div v-if="this.badge" :style="{ border: '7px inset ' + this.badge, 'border-radius': '5px', 'padding': '1px 5px 1px 5px'}"><span>{{ this.nickname }}</span></div>
          <div v-else class="username"><span>{{ this.nickname }}</span></div>
        </div>

        
        <p class="info text-shadow"> 
          <img src="@/assets/images/coin.png" style="width: 20px; height: 20px">
          {{ this.point }} 
        </p>
        <p class="info text-shadow">{{ this.ranking }}위 (상위 {{ this.top }}%)</p>

        <!-- 프로파일 버튼들 -->
        <div class="profile-btns">
          <button @click.prevent="changeNickname" class="shadow">닉네임 수정하기</button>
          <button @click.prevent="changePw" class="shadow">비밀번호 변경하기</button>
          <div @click.prevent="deleteUser">회원 탈퇴하기</div>
        </div>
      </div>

      <!-- 전적 기록 화면 -->
      <div class="profile-record-card">
        <div class="banner">
          <img src="@/assets/images/profile_record_banner.png" alt="profile_record_banner">
        </div>

        <div class="table-container">

            <table class="styled-table">
              <thead>
                <tr>
                  <th>등수</th>
                  <th>시작 시간</th>
                  <th>종료 시간</th>
                  <th>웃은 횟수</th>
                  <th>포인트 변경</th>
                  <th>머니 획득</th>
                </tr>
              </thead>

              <tbody>
                <tr v-for="(match, index) in this.records" :key="index">
                  <td>{{ match.rank }}</td>
                  <td>{{ match.start }}</td>
                  <td>{{ match.end }}</td>
                  <td>{{ match.smileCount }}</td>
                  <td>{{ match.changePoint }}</td>
                  <td>{{ match.changeMoney }}</td>
                </tr>
              </tbody>
            </table>

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
      nickname: "",
      point: "",
      profile_img: "",
      border: "",
      badge: "",
      ranking: "",
      top: "",
      records:{}
    };
  },
  created() {
    api.defaults.headers["Authorization"] = localStorage.getItem("accessToken")
    api.post(`/member`)
    .then(({ data }) => {
      this.point = data.data.point;
      this.nickname = data.data.nickname;
      this.profile_img = data.data.profile_img;
      this.border = data.data.border;
      this.badge = data.data.badge;
      this.ranking = data.data.ranking;
      this.top = data.data.top;
    })
    .catch(error => {
      console.log(error.message)
    })
    api.defaults.headers["Authorization"] = localStorage.getItem("accessToken")
    api.get('/record')
    .then(({data})=> {
      this.records = data.data;
    })
    .catch(({error}) => {
      console.log(error)
    })
  },
  methods:{
    changeNickname(){
      this.$router.push({name: "nicknamechange"})
    },
    changePw(){
      this.$router.push({name: "pwchange"})
    },
    deleteUser(){
      this.$router.push({name: "goodbye"})
    }
  }
}
</script>

<style scoped>
.content-window {
  background-color: #FFF2EA;
}

.content-window::before {
  content: '';
  position: absolute;
  top: 10%;
  left: -28px;
  width: 50px;
  height: 50px;
  background-image: url('@/assets/images/indicator.png');
  background-size: contain;
  background-repeat: no-repeat;
  background-color: transparent;
}

.content {
  display: flex;
  height: 100%;
  width: 80%;
  gap: 40px;
}

.banner img {
  width: 340px;
  margin-top: -80px;
}

/* ------- 프로필 카드 --------- */
.card {
  width: 35em;
  transition: 1s ease-in-out;
  border-top-right-radius: 20px;
  border-bottom-left-radius: 20px;
  display: flex;
  flex-direction: column;
  background-color: #FFCDAD;
}

.card span {
  font-weight: 200;
  color: white;
  text-align: center;
  display: block;
  font-size: 2em;
}

.card .info {
  font-weight: 400;
  color: white;
  text-align: center;
  font-size: 1.2em;
}

.card .img {
  width: 8em;
  height: 8em;
  background: url(@/assets/images/profile.jpg);
  background-size: cover;
  border-radius: 15px;
  margin: 20px auto;
}

.card .nickname {
  display: flex;
  justify-content: center;
  align-items: center;
}

.card .nickname div {
  display: inline-block;
}

.card button {
  width: 200px;
  padding: 10px;
  display: block;
  border-radius: 20px;
  border: none;
  font-weight: 200;
  background: #FF7B27;
  color: #FFFFFF;
  transition: .2s ease-in-out;
}
.card button:hover {
  background: #ffffff;
  color: #FF7B27;
  cursor: pointer;
}

.profile-btns {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  gap: 10px;
}
.profile-btns div {
  font-size: 1em;
  cursor: pointer;
  margin: 10px 0 10px;
  
  color: #FF7B27;
  text-decoration: underline;
  text-decoration-color: #FF7B27;
  text-decoration-style: dashed; /* 라인 종류 변경: solid, dotted, dashed, double 등 */
  text-decoration-thickness: 2px;
}

/* ------- 나의 전적 화면 ----------- */
.profile-record-card {
  border: 2px solid transparent;
}

.banner img {
  width: 340px;
  margin: -80px 0 0 100px;
}

.table-container
.styled-table {
  border-collapse: collapse;
  width: 130%;
}

.styled-table th,
.styled-table td {
  border: 3px solid white;
  padding: 8px;
  text-align: center;
  border-radius: 10px;
}

.styled-table th {
  background-color: #FF7B27;
  color: white;
  font-size: 1.2em;
}

.styled-table tr:nth-child(even) {
  background-color: #FF7B27;
}
</style>