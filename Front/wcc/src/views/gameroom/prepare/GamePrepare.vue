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

        <div class="game-room-prepare-container">
            <img 
                src="@/assets/images/game_prepare_banner.png" 
                class="banner_image" 
                alt="Game_Prepare_Banner"
                width="500"
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
                            랜덤으로 키워드 정하기
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
                <button id="submitButton">
                    READY
                </button>
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
        }
    },
}
</script>

<style>
@import url('./GamePrepare.css')
</style>