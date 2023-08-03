
<template>
    <web-cam></web-cam>
    <game-chat></game-chat>
    <main-content></main-content>
    <!-- <progress-bar></progress-bar> -->
</template>

<script>
import WebCam from '../components/webcam/WebCam.vue'
import GameChat from '../components/chat/GameChat.vue';
import MainContent from '../components/maincontent/MainContent.vue';
// import ProgressBar from '../components/progressbar/ProgressBar.vue';

export default {
    components: {
        WebCam, GameChat, MainContent,
        //  ProgressBar
    }
}
</script>

<style></style>