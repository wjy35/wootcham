<template>
    <div id="game-room">
        <div v-if="selectedScreen === 'prepare'">
            <game-room-prepare></game-room-prepare>
            <game-room-background></game-room-background>
            <game-room-header></game-room-header>
        </div>
        <div v-else-if="selectedScreen === 'webcam'">
            <game-room-background></game-room-background>
            <main-game></main-game>
            <game-room-header></game-room-header>
        </div>
        <div class="buttonGroup" style="position:absolute">
            <button @click="clickPrepare">prepare</button>
            <button @click="clickWebcam">webcam</button>
        </div>
    </div>
</template>
<script>
import GameRoomHeader from "./components/header/HeaderBar.vue"
import GameRoomPrepare from './prepare/GamePrepare.vue'
import GameRoomBackground from './components/background/BackGround.vue'
import MainGame from "./maingame/MainGame.vue"

export default {
    name: 'GameRoom',
    components: { GameRoomHeader, GameRoomBackground, GameRoomPrepare, MainGame},
    data() {
        return {
            selectedScreen: 'prepare'
        }
    },
    methods: {
        clickPrepare() {
            console.log('prepare called................................')
            this.selectedScreen = 'prepare';
        },
        clickWebcam() {
            console.log('webcam called................................')
            this.selectedScreen = 'webcam';
        }
    }
}
</script>

<style></style>