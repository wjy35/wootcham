<template>
    <div class="main-wrapper">
        <!-- <router-view></router-view> -->
        <game-room></game-room>
    </div>
</template>

<script>
import GameRoom from '../gameroom/GameRoom.vue'

export default {
    name: 'MainView',
    components: {
        GameRoom
    }
}
</script>

<style></style>