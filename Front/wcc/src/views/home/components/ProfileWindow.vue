<template>
  <div class="content-window shadow">
    <div class="content">

      <!-- 프로파일 화면 -->
      <div class="card shadow">
        <!-- 프로필 이미지 -->
        <div class="img"><img :src="this.profile_img" style="width:100%; border-radius: 10px;"></div>

        <!-- 유저네임 -->
        <span class="text-shadow">{{ this.nickname }}</span>

        <p class="info"> {{ this.point }} p</p>
        <p class="info">32위 (상위 15%)</p>

        <div class="profile-btns">
          <button @click.prevent="changeNickname">닉네임 수정하기</button>
          <button @click.prevent="changePw">비밀번호 변경하기</button>
          <div @click.prevent="deleteUser">회원 탈퇴하기</div>
        </div>


      </div>

      <!-- 전적 기록 화면 -->
      <div class="profile-record-card">
        <div class="banner">
          <img src="@/assets/images/profile_record_banner.png" alt="profile_record_banner">
        </div>

        <table>
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
</template>
<script>
import api from "@/api"

export default {
  data() {
    return {
      nickname: "",
      point: "",
      profile_img: "",
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

  gap: 100px;
}


/* ------- 프로필 카드 --------- */
.card {
  width: 31em;
  height: 31em;
  background: #FFCDAD;
  transition: 1s ease-in-out;
  clip-path: polygon(30px 0%, 100% 0, 100% calc(100% - 30px), calc(100% - 30px) 100%, 0 100%, 0% 30px);
  border-top-right-radius: 20px;
  border-bottom-left-radius: 20px;
  display: flex;
  flex-direction: column;
}

.card span {
  font-weight: bold;
  color: white;
  text-align: center;
  display: block;
  font-size: 2em;
}

.card .info {
  font-weight: 400;
  color: white;
  display: block;
  text-align: center;
  font-size: 1.3em;
}

.card .img {
  width: 8em;
  height: 8em;
  background-size: cover;
  border-radius: 15px;
  margin: auto;

  border: 5px inset gold;
}

.card a {
  color: white;
  transition: .4s ease-in-out;
}

.card a:hover {
  color: red;
}

.profile-btns {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  gap: 10px;
}

.card button {
  width: 200px;
  padding: 10px;
  display: block;
  border-radius: 20px;
  border: none;
  font-weight: 200;
  background: #FF7B27;
  color: #FFCDAD;
  transition: .2s ease-in-out;
}
.card button:hover {
  background: #ffffff;
  color: #FF7B27;
  cursor: pointer;
}

.profile-btns div {
  font-size: 1.2em;
  cursor: pointer;
  margin: 10px 0 20px;

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
  margin-top: -80px;
}

table {
  width: 140%;
  border-collapse: collapse;
}

th, td {
  border: 1px solid #ddd;
  border-radius: 1px;
  padding: 10px;
  text-align: center;
}
</style>
