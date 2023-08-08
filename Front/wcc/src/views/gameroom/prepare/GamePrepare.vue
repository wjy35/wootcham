<template>
    <div class="game-room-prepare-background">
        <!-- TIMER -->
        <div class="game-room-timer">
          <game-room-timer style="z-index: 1;"></game-room-timer>
        </div>

         <!-- HEADER -->
         <header>
            <div class="header-center">2라운드 코미디 미션 정하기</div>
            <div class="getout-btn"></div>
        </header>

				<!-- CONTENT -->
        <div class="game-room-prepare-container">
						<div class="game-room-prepare-modal">
							<img 
									src="@/assets/images/game_prepare_banner.png" 
									class="banner_image" 
									alt="Game_Prepare_Banner"
									width="400"
							>
							<img 
									src="@/assets/images/laughter_icon.png" 
									class="laughter_icon" alt="Game_Prepare_Banner" 
									width="300"
							>

							<div class="mission_choose">
									<!-- 미션 대주제 정하기 -->
									<div class="mission_subject">
											<span class="mission_subject_title">분류</span>
											<!-- <b-form-select class="w-25" v-model="selected" :options="options"></b-form-select> -->
											<div class="mission_subject_option">
													<select class="form-select form-select-lg shadow"
															style="font-size: 0.5em; 
																			color:#714538; 
																			width:12em; 
																			text-align: center;
																			border: none;
																			border-radius: 15px;"
															aria-label="Default select example">
															<option selected>분류 선택</option>
															<option value="nonsense">넌센스</option>
															<option value="dance">막춤</option>
															<option value="acrostic_poem">삼행시</option>
															<option value="impression">성대모사</option>
															<option value="impression">프리스타일</option>
													</select>
											</div>
									</div>

									<!-- 미션 키워드 정하기 -->
									<div class="mission_keyword">
											<span class="mission_keyword_title">키워드</span>
											<input 
													type="text" 
													class="mission_keyword_content shadow" 
													:value="selectedKeyword" 
													readonly
													style="border-radius: 15px;"
											>
											<div class="dice-container">
													<span class="dice-tooltip">
															랜덤으로 키워드를 정할 수 있습니다.
													</span>
													<img 
															type="button" 
															@click="rollKeyword" 
															src="@/assets/images/dice.png" 
															class="dice-icon"
															alt="Game_Prepare_Banner" 
															width="100"
													>
											</div>
									</div>

									<button id-="submitButton" @click="goMainGame" class="shadow">
										<span class="now">ENTER ROOM</span>
										<span class="play">READY!</span>
									</button>
											

							</div>
							
						</div>					
        </div>

    </div>
</template>

<script>
import GameRoomTimer from "../components/timer/GameTimer.vue"

export default {
    name: 'GameRoomPrepare',
    components: {
        GameRoomTimer
    },
    data() {
        return {
            // selected: '넌센스',
            // options: [
            //     { value: 'nonsense', text: '넌센스' },
            //     { value: 'dance', text: '막춤' },
            //     { value: 'acrostic_poem', text: '삼행시' },
            //     { value: 'impression', text: '성대모사' }
            // ],
            selectedKeyword: '노트북',
        }
    },
    methods: {
        rollKeyword() {
            const keywords = ['노트북', '싸진남', '마우스', '에어컨', '강아지', '고양이'];
            this.selectedKeyword = keywords[Math.floor(Math.random() * keywords.length)];
        },
        goMainGame(){
            this.$router.push({name:'maingame'})
        }
    },
}
</script>

<style scoped>
.game-room-prepare-background {
	background-color: red;
}

header {
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    background-color: #F27059;
		z-index: 9999;
		
    display: flex;
    justify-content: space-between;
    align-items: center;

    padding: 10px;
    border-top: 1px solid white;
    border-bottom: 2px solid white;
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

.header-center:hover {
	cursor: pointer;
	transform: scale(0.96);
}

.game-room-prepare-container {
	background-color: #FFCDAD;

	display: flex;
	justify-content: center;
	align-items: center;

	padding: 5px;
	overflow-y: hidden;
	height: 100vh;
}

.game-room-prepare-modal {
	background-color: #FFF2EA;
	width: 800px;
	height: 420px;

	border-radius: 15px;
}

.banner_image {
	position: absolute;
	top: 20%;
	left: 50%;
	transform: translate(-50%, -50%);
}

.mission_choose {
	position: absolute;
	width: 100%;
	height: auto;
	top: 60%;
	left: 50%;
	transform: translate(-50%, -50%);
}


.mission_subject {
	color: rgba(112.62499898672104, 69.4708514213562, 55.84322847425938, 1);
	font-family: "Black Han Sans";
	font-size: 4em;
	width: 40%;
	display: flex;
	/* justify-content: left; */
	margin: 0 auto;
}

.mission_subject_title {
	color: rgba(112.62499898672104, 69.4708514213562, 55.84322847425938, 1);
	font-family: "Black Han Sans";
	width: fit-content;
	font-size: 1em;
	display: flex;
	justify-content: flex-start;
}

.mission_subject_option {
	color: rgba(112.62499898672104, 69.4708514213562, 55.84322847425938, 1);
	width: 20vw;
	height: 8vh;
	font-family: "Black Han Sans";
	text-align: right;
	font-size: 1em;
	display: flex;
	justify-content: flex-end;
	align-items: stretch;
	margin: auto 0 auto auto;
}

.mission_keyword {	
	color: rgba(112.62499898672104, 69.4708514213562, 55.84322847425938, 1);
	font-family: "Black Han Sans";
	font-size: 4em;
	width: 40%;
	display: flex;
	/* justify-content: left; */
	margin: 0 auto;
}

.mission_keyword_title {
	color: rgba(112.62499898672104, 69.4708514213562, 55.84322847425938, 1);
	font-family: "Black Han Sans";
	width: fit-content;
	font-size: 1em;
	display: flex;
	justify-content: flex-start;
}

.mission_keyword_content {
	color: rgba(112.62499898672104, 69.4708514213562, 55.84322847425938, 1);
	background-color: #FFFFFF;
	width: 20vw;
	height: 7.5vh;
	border-radius: 8px;
	font-family: "Black Han Sans";
	text-align: center;
	font-size: 0.5em;
	margin: 1.5rem 0 5rem auto;
}

.mission_keyword_content:focus {
	outline: 2px solid #FF7B27;
}

.laughter_icon {
	position: absolute;
	left: 7%;
	bottom: 10%;
	animation: shake 5s infinite;
}

.laughter_icon:hover {
	transform: scale(1.1);
}

@keyframes shake {
    0%, 100% {
        transform: translateX(0);
    }
    10%, 30%, 50%, 70%, 90% {
        transform: translateX(-5px);
    }
    20%, 40%, 60%, 80% {
        transform: translateX(5px);
    }
}

/* ----------- DICE ----------- */
.dice-containeer {
	position: absolute;
	right: 55%;
	bottom: 50%;
	display: inline-block;
}

.dice-tooltip {
	position: absolute;
	top: -80px;
	left: 50%;

	background-color: rgba(0, 0, 0, 0.8);
	color: white;
	padding: 5px;
	border-radius: 5px;
	font-size: 14px;
	transform: translateX(-50%);
	display: none;
	pointer-events: none;
}

.dice-icon {
	position: absolute;
	right: 25%;
	bottom: 25%;
	cursor: pointer;
}

.dice-container:hover .dice-tooltip {
  display: block;
}


button {
  display: flex;
  align-items: center;
  justify-content: center;

	width: 180px;
	height: 40px;

  gap: 10px;
  padding: 0 10px;
  color: white;
  text-shadow: 2px 2px rgb(116, 116, 116);
  text-transform: uppercase;
  border: solid 2px #FFF2EA;
  letter-spacing: 1px;
  font-weight: 600;
  font-size: 17px;
  background-color: #F27059;
  border-radius: 50px;

  position: absolute;
	left: 44%;
	bottom: -15%;
  overflow: hidden;
  transition: all .5s ease;
}

button:active {
  transform: scale(.9);
  transition: all 100ms ease;
}


.play {
  transition: all .5s ease;
  transition-delay: 300ms;
}

.now {
  position: absolute;
  left: 0;
  transform: translateX(-100%);
  transition: all .5s ease;
  z-index: 2;
}

button:hover .now {
  transform: translateX(10px);
  transition-delay: 300ms;
}

button:hover .play {
  transform: translateX(200%);
  transition-delay: 300ms;
}
</style>