<template>
    <div id="game-room">
        <!-- <game-room-prepare></game-room-prepare>
        <game-room-background></game-room-background>
        <game-room-header></game-room-header> -->
        <game-room-webcam></game-room-webcam>
    </div>
</template>

<script>
// import GameRoomHeader from "./components/game-room-header/game-room-header.vue"
// import GameRoomPrepare from './game-room-prepare/game-room-prepare.vue'
// import GameRoomBackground from './game-room-background/game-room-background'
import GameRoomWebcam from './components/game-room-webcam/game-room-webcam.vue'

export default {
    name: 'GameRoom',
    // components: { GameRoomHeader, GameRoomBackground, GameRoomPrepare },
    components:{
        GameRoomWebcam
    }
}
</script>

<style></style>