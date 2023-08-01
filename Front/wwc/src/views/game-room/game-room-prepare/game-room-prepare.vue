<template>
    <div class="game-room-prepare-background">
        <div class="game-room-prepare-container">
            <img src="../../../assets/images/Game_Prepare_Banner.png" class="banner_image" alt="Game_Prepare_Banner"
                width="800">
            <game-room-timer style="z-index: 1;"></game-room-timer>
            <img src="../../../assets/images/laughter_icon_2_removebg_preview_2.png" class="laughter_icon"
                alt="Game_Prepare_Banner" width="300">
                <div class="mission_choose">
                    <div class="mission_subject">
                        <span class="mission_subject_title">분류</span>
                    <!-- <b-form-select class="w-25" v-model="selected" :options="options"></b-form-select> -->
                    <div class="mission_subject_option">
                        <select class="form-select form-select-lg"
                        style="font-size: 0.5em; color:#714538; width:12em; text-align: center;"
                        aria-label="Default select example">
                        <option selected>분류 선택</option>
                        <option value="nonsense">넌센스</option>
                        <option value="dance">막춤</option>
                        <option value="acrostic_poem">삼행시</option>
                        <option value="impression">성대모사</option>
                    </select>
                </div>
            </div>
            <div class="mission_keyword">
                <span class="mission_keyword_title">키워드</span>
                <input type="text" class="mission_keyword_content" aria-label="Sizing example input"
                aria-describedby="inputGroup-sizing-lg" value="노트북" readonly>
                <img type="button" src="../../../assets/images/gameDie.png" class="dice_icon" alt="Game_Prepare_Banner"
                    width="100">
                </div>
                <button id="submitButton">READY</button>
            </div>
        </div>

    </div>
</template>

<script>
import GameRoomTimer from "../components/game-room-timer/game-room-timer.vue"

export default {
    name: 'GameRoomPrepare',
    components: {
        GameRoomTimer
    },
    data() {
        return {
            selected: '넌센스',
            options: [
                { value: 'nonsense', text: '넌센스' },
                { value: 'dance', text: '막춤' },
                { value: 'acrostic_poem', text: '삼행시' },
                { value: 'impression', text: '성대모사' }
            ]
        }
    }
}
</script>

<style>
@import url('./game-room-prepare.css')
</style>