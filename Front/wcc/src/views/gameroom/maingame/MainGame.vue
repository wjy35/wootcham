<template>
    <div class="inner">
        <!-- HEADER -->
        <header>
            <div class="header-center">2라운드 09:30</div>

            <button class="Btn" @click="showModal = true">
                <img src="@/assets/images/getout.png" class="sign" alt="나가기" style="width: 24px;">
                <div class="text">게임 나가기</div>
            </button>
        </header>
        
        <div class="main-container">
            <div class="gridlayout">
                <!-- 1번 박스 === video-one -->
                <div class="main video video-one shadow">
                  <div class="video-username">username</div>
                </div>

                <!-- 2, 3, 6, 7번 박스 === Main Content -->
                <div class="main main-content shadow">
                    <div class="main-content-username">username</div>
                    <div id="main-content-video">
                      <!-- 임시로 이미지 넣음-->
                      <img src="https://media.istockphoto.com/id/518360318/photo/crazy-horse.jpg?s=612x612&w=0&k=20&c=KP27AuWsogbIb1dRSqqwRn_ykPMqguJb7z2q3x9xr5A=" alt="">
                    </div>
                </div>

                <!-- 4, 8번 박스 === chat-card -->
                <div class="main chat-card shadow">
                    <div class="chat-body">
                        <div class="message incoming">
                             <p><span>{{ chatUsername }}</span>: 받은 메시지</p>
                        </div>
                        <div class="message outgoing">
                            <p><span>나</span>: 보낸 메시지</p>
                        </div>
                    </div>

                    <div class="input-container shadow">
                        <input placeholder="메시지를 입력해주세요." type="text" class="input">
                        <span>Enter</span>
                    </div>
                </div>

                <!-- 5번 박스 -->
                <div class="main video video-two shadow">
                    <div class="video-username">username</div>
                </div>

                <!-- 9번 박스 -->
                <div class="main video video-three shadow">
                  <div class="video-username">username</div>
                </div>

                <!-- 10번 박스 -->
                <div class="main video video-four shadow">
                  <div class="video-username">username</div>
                </div>

                <!-- 11번 박스 -->
                <div class="main video video-five shadow">
                  <div class="video-username">username</div>
                </div>

                <!-- 12번 박스 -->
                <div class=",ain video video-six shadow">
                  <div class="video-username">username</div>
                </div>

                <!-- <img src="@/assets/images/WCC_logo.png"> -->
            </div>

            <!-- 모달 창 -->
            <div v-if="showModal" class="modal">
                <div class="modal-content">
                    <p>게임을 나가시겠습니까?</p>
                    <div class="modal-content-warning">
                        <div>지금 게임을 <span>무단으로 </span> 나가면 <br>
                            <span>100포인트</span>를 잃게 됩니다. 그래도 나가시겠습니까?
                        </div>
                    </div>
                    <div class="modal-content-btn">
                        <div @click="exitGame">나가기</div>
                        <button @click="showModal = false" class="goback-btn">돌아가기</button>
                    </div>
                </div>
            </div>
        </div>


        

    </div>
</template>

<script>
export default {
    data(){
        return{
            showModal: false,
            chatUsername: "가상의 유저"
        };
    },
    methods: {
        exitGame(){
            // 게임 종료 동작을 수행하는 코드
            this.$router.push('/home');
        }
    },
}
</script>

<style scoped>
/* ------------ HEADER --------------- */
header {
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    background-color: #F27059;
    z-index: 999;
		
    display: flex;
    justify-content: space-between;
    align-items: center;

    padding: 10px;
    border-top: 1px solid white;
    border-bottom: 1px solid white;
}

.header-center {
    display: flex;
    align-items: center;
    justify-content: center;
    flex-grow: 1;

    color: #FFF;
    text-shadow: 2px 2px 4px #FF432A;
    font-size: 30px;
    font-style: normal;
    font-weight: 400;
    line-height: normal;
}

/* ------------ 화면 배치 ------------- */
.main-container {
    position: relative;
    height: 100vh;
    overflow: hidden;
    
    padding: 60px 15px 0;
    border: 3px solid #FFCDAD;
    background-color: #FFCDAD;

    display: flex; /* Add this line */
    justify-content: center; /* Add this line */
    align-items: center; /* Add this line */
}

.gridlayout {
    width: 95%;
    height: 97%;
    display: grid;
    grid-template-columns: repeat(4, 1fr);
    grid-template-rows: repeat(3, 1fr);

    gap: 10px;
    border: 3px solid #FFCDAD;
    background-color: #FFCDAD;
}

.video-one {grid-area: 1 / 1 / 2 / 2;}
.main-content {grid-area: 1 / 2 / 4 / 4;}
.chat-card {grid-area: 1 / 4 / 3 / 5;}
.video-two {grid-area: 2 / 1 / 3 / 2;}
.video-three {grid-area: 3 / 1 / 4 / 2;}
.video-four {grid-area: 3 / 2 / 4 / 3;}
.video-five {grid-area: 3 / 3 / 4 / 4;}
.video-six {grid-area: 3 / 4 / 4 / 5;}

/* -------- VIDEO -------- */
.video {
    border: 1px solid #FFF2EA;
    border-radius: 15px;

    position: relative;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    overflow: hidden;
}

.video-username {
  position: absolute;
  bottom: 5px;
  left: 5px;
  z-index: 99;

  color: white;
}


/* -------- MAIN CONTENT --------- */
.main-content{
    height: 66%;
    position: relative;
    
    background-color: gainsboro;
    border-radius: 10px;
    border: 3px solid #FFF2EA;
}

.main-content-username {
  position: absolute;
  top: 5px;
  left: 5px;
  z-index: 99;

  color: white;
}

#main-content-video {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  overflow: hidden;
}

#main-content-video img {
  width: 100%;
  height: 100%;
  object-fit: cover;

  border-radius: 10px;
}

/* -------- CHAT --------- */
.chat-card {
    padding: 10px;
    display: flex;
    flex-direction: column;
    justify-content: space-between;


    background-color: #fff;
    border: 5px solid #FFF2EA;
    border-radius: 10px;
    font-family: Noto-Sans;

    /* height: 100%; */
    overflow-y: scroll;
}

.chat-body {
  padding: 5px 0 0;
}

.message {
  margin-bottom: 10px;
  padding: 10px;
  border-radius: 5px;
}

.incoming {
  background-color: #e1e1e1;
}

.outgoing {
  background-color: #f2f2f2;
  text-align: right;
}

.message p {
  font-size: 14px;
  color: #333;
  margin: 0;
}

@keyframes chatAnimation {
  0% {
    opacity: 0;
    transform: translateY(10px);
  }

  100% {
    opacity: 1;
    transform: translateY(0);
  }
}

.chat-card .message {
  animation: chatAnimation 0.3s ease-in-out;
  animation-fill-mode: both;
  animation-delay: 0.1s;
}

.chat-card .message:nth-child(even) {
  animation-delay: 0.2s;
}

.chat-card .message:nth-child(odd) {
  animation-delay: 0.3s;
}

/* 메시지 입력창 */
.input-container {
  position: relative;
  display: flex;
  width: 100%;
  max-width: 500px;
}

.input-container > span,
.input-container .input {
  white-space: nowrap;
  display: block;
}

.input-container > span,
.input-container .input:first-child {
  border-radius: 6px 0 0 6px;
}

.input-container > span,
.input-container .input {
  border-radius: 0 6px 6px 0;
}

.input-container > span,
.input-container .input {
  margin-left: -1px;
}

.input-container .input {
  position: relative;
  z-index: 1;
  flex: 1 1 auto;
  width: 1%;
  margin-top: 0;
  margin-bottom: 0;
}

.input-container span {
  text-align: center;
  padding: 8px 12px;
  font-size: 14px;
  line-height: 25px;
  color: #6b7385;
  background:  #FFF2EA;
  border: 1px solid #FFF2EA;
  font-weight: bold;
  transition: background 0.3s ease, border 0.3s ease, color 0.3s ease;
}

.input-container:focus-within > span {
  color: #FFF2EA;
  background-color: #F8837A;
  border-color: #F8837A;
}

.input {
  display: block;
  width: 100%;
  padding: 8px 16px;
  line-height: 25px;
  font-size: 14px;
  font-weight: 500;
  font-family: inherit;
  border-radius: 6px;
  color: black;
  border: 1px solid  #FFF2EA;
  background: #fff;
  transition: border 0.3s ease;
}

.input::placeholder {
  color: #CBD1DC;
}

.input:focus {
  outline: none;
  border-color: #F8837A;
}

/* ---------- MODAL ---------- */
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

.modal-content {
  background-color: #F27059;
  margin: 100px 400px 50px;
  padding: 20px;

  gap: 40px;
  border: 3px solid #FFF2EA;
  border-radius: 15px;
  text-align: center;
}

.modal-content p {
    color: white;
    font-size: 3em;
}

.modal-content-warning {
    background-color: #FFF2EA;
    border-radius: 12px;

    font-family: Noto-Sans;
    color: #714538;
    padding: 10px;

    border: 3px solid brown;
}

.modal-content-warning:hover {
  border: 5px solid white;
}

.modal-content-warning span {
    color: #F27059;
    font-size: 1.2em;
}

.modal-content-btn {
    gap: 50px;
    display: flex;
    align-items: center;
    justify-content: center;

    font-size: 2em;
}

.modal-content-btn div {
    color: white;
    cursor: pointer;
}

.modal-content-btn div:hover {
    transform: translateY(3px);
}

.goback-btn {
    transition: all 0.3s ease-in-out;
    width: 150px;
    height: 60px;
    border-radius: 25px;
    background-image: linear-gradient(135deg, #feb692 0%, #ea5455 100%);
    box-shadow: 0 20px 30px -6px rgba(238, 103, 97, 0.5);
    outline: none;
    cursor: pointer;
    
    border: none;
    font-size: 24px;
    color: white;
}

.goback-btn:hover {
  transform: translateY(3px);
  border-radius: 20px;
}

.goback-btn:active {
  opacity: 0.5;
}

/* -------------- 나가기 버튼 --------------- */
.Btn {
  display: flex;
  align-items: center;
  justify-content: flex-start;
  width: 45px;
  height: 45px;
  border: none;
  border-radius: 5px;
  cursor: pointer;

  position: absolute;
  right: 40px;
  overflow: hidden;
  transition-duration: .3s;
  background-color: #F27059;
}

.sign {
  width: 100%;
  transition-duration: .3s;
  display: flex;
  align-items: center;
  justify-content: center;
}
/* text */
.text {
  position: absolute;
  right: 0%;
  width: 0%;
  opacity: 0;
  font-size: 1.2em;
  font-weight: 200;
  transition-duration: .3s;
}
/* hover effect on button width */
.Btn:hover {
  width: 150px;
  border-radius: 5px;
  transition-duration: .3s;
}

.Btn:hover .sign {
  width: 30%;
  transition-duration: .3s;
  padding-left: 20px;
}
/* hover effect button's text */
.Btn:hover .text {
  opacity: 1;
  width: 100%;
  transition-duration: .7s;
  padding-right: 10px;

  color: white;
}
/* button click effect*/
.Btn:active {
  transform: translate(2px ,2px);
}
</style>