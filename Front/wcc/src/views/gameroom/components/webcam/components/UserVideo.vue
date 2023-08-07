<template>
    <div class="userVideo" v-if="streamManager">
        <OvVideo v-if="!isMyVideo" :stream-manager="streamManager"/>
        <MyOvVideo v-else :stream-manager="streamManager"/>
        <!-- <div><p>{{ clientData }}</p></div> -->
    </div>
</template>

<script>
import OvVideo from './OvVideo.vue';
import MyOvVideo from './MyOvVideo.vue';

export default {
    name: 'UserVideo',

    props: {
        videoType: String,
        streamManager: Object,
    },

    data() {
        return {
            isMyVideo: false,
        }
    },

    components: {
        OvVideo, MyOvVideo
    },

    computed: {
        clientData () {
            const { clientData } = this.getConnectionData();
            return clientData;
        },
    },

    mounted() {
        // streamManager가 내 비디오가 아니면 isMyVideo를 false로 바꿈
        if (this.videoType === "myVideo") {
            this.isMyVideo = true;
        }
    },

    methods: {
        getConnectionData () {
            const { connection } = this.streamManager.stream;
            return JSON.parse(connection.data);
        },
    },
};
</script>

<style scoped>
    .userVideo {
        width: 100%;
        height: 100%;
    }
</style>
    