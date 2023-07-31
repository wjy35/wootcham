<template>
    <div class="main-wrapper">
        <!-- <router-view></router-view> -->
        <game-room></game-room>
    </div>
</template>

<script>
import GameRoom from '../game-room/game-room.vue'

export default {
    name: 'MainView',
    components: {
        GameRoom
    }
}
</script>

<style></style>