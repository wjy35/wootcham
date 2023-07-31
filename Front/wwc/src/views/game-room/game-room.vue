<template>
    <div id="game-room">
        <game-room-prepare></game-room-prepare>
        <game-room-background> 
        </game-room-background>
    </div>
</template>

<script>
import GameRoomPrepare from './game-room-prepare/game-room-prepare.vue'
import GameRoomBackground from './game-room-background/game-room-background'

export default {
    name: 'GameRoom',
    components: { GameRoomBackground, GameRoomPrepare },
}
</script>

<style></style>