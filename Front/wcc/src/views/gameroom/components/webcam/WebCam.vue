<!-- <template>
    <div id="main-container" class="container">
        <div id="join" v-if="!session">
            <div id="img-div">
                <img src="resources/images/openvidu_grey_bg_transp_cropped.png" />
            </div>
            <div id="join-dialog" class="jumbotron vertical-center">
                <h1>Join a video session</h1>
                <div class="form-group">
                    <p>
                        <label>Participant</label>
                        <input v-model="myUserName" class="form-control" type="text" required />
                    </p>
                    <p>
                        <label>Session</label>
                        <input v-model="mySessionId" class="form-control" type="text" required />
                    </p>
                    <p class="text-center">
                        <button class="btn btn-lg btn-success" @click="joinSession()">
                            Join!
                        </button>
                    </p>
                </div>
            </div>
        </div>

        <div id="session" v-if="session">
            <div id="session-header">
                <h1 id="session-title">{{ mySessionId }}</h1>
                <input class="btn btn-large btn-danger" type="button" id="buttonLeaveSession" @click="leaveSession"
                    value="Leave session" />
            </div>
            <div id="main-video" class="col-md-6">
                <user-video :stream-manager="mainStreamManager" />
            </div>
            <div id="video-container" class="col-md-6">
                <user-video :stream-manager="publisher" @click.enter="updateMainVideoStreamManager(publisher)" />
                <user-video v-for="sub in subscribers" :key="sub.stream.connection.connectionId" :stream-manager="sub"
                    @click.enter="updateMainVideoStreamManager(sub)" />
            </div>
        </div>
    </div>
</template>

<script>
import axios from "axios";
import { OpenVidu } from "openvidu-browser";
import UserVideo from "./components/UserVideo";

axios.defaults.headers.post["Content-Type"] = "application/json";

const APPLICATION_SERVER_URL = process.env.NODE_ENV === 'production' ? '' : 'http://localhost:5000/';

export default {
    name: "App",

    components: {
        UserVideo,
    },

    data() {
        return {
            // OpenVidu objects
            OV: undefined,
            session: undefined,
            mainStreamManager: undefined,
            publisher: undefined,
            subscribers: [],

            // Join form
            mySessionId: "SessionA",
            myUserName: "Participant" + Math.floor(Math.random() * 100),
        };
    },

    methods: {
        joinSession() {
            // --- 1) Get an OpenVidu object ---
            this.OV = new OpenVidu();

            // --- 2) Init a session ---
            this.session = this.OV.initSession();

            // --- 3) Specify the actions when events take place in the session ---

            // On every new Stream received...
            this.session.on("streamCreated", ({ stream }) => {
                const subscriber = this.session.subscribe(stream);
                this.subscribers.push(subscriber);
            });

            // On every Stream destroyed...
            this.session.on("streamDestroyed", ({ stream }) => {
                const index = this.subscribers.indexOf(stream.streamManager, 0);
                if (index >= 0) {
                    this.subscribers.splice(index, 1);
                }
            });

            // On every asynchronous exception...
            this.session.on("exception", ({ exception }) => {
                console.warn(exception);
            });

            // --- 4) Connect to the session with a valid user token ---

            // Get a token from the OpenVidu deployment
            this.getToken(this.mySessionId).then((token) => {

                // First param is the token. Second param can be retrieved by every user on event
                // 'streamCreated' (property Stream.connection.data), and will be appended to DOM as the user's nickname
                this.session.connect(token, { clientData: this.myUserName })
                    .then(() => {

                        // --- 5) Get your own camera stream with the desired properties ---

                        // Init a publisher passing undefined as targetElement (we don't want OpenVidu to insert a video
                        // element: we will manage it on our own) and with the desired properties
                        let publisher = this.OV.initPublisher(undefined, {
                            audioSource: undefined, // The source of audio. If undefined default microphone
                            videoSource: undefined, // The source of video. If undefined default webcam
                            publishAudio: true, // Whether you want to start publishing with your audio unmuted or not
                            publishVideo: true, // Whether you want to start publishing with your video enabled or not
                            resolution: "640x480", // The resolution of your video
                            frameRate: 30, // The frame rate of your video
                            insertMode: "APPEND", // How the video is inserted in the target element 'video-container'
                            mirror: false, // Whether to mirror your local video or not
                        });

                        // Set the main video in the page to display our webcam and store our Publisher
                        this.mainStreamManager = publisher;
                        this.publisher = publisher;

                        // --- 6) Publish your stream ---

                        this.session.publish(this.publisher);
                    })
                    .catch((error) => {
                        console.log("There was an error connecting to the session:", error.code, error.message);
                    });
            });

            window.addEventListener("beforeunload", this.leaveSession);
        },

        leaveSession() {
            // --- 7) Leave the session by calling 'disconnect' method over the Session object ---
            if (this.session) this.session.disconnect();

            // Empty all properties...
            this.session = undefined;
            this.mainStreamManager = undefined;
            this.publisher = undefined;
            this.subscribers = [];
            this.OV = undefined;

            // Remove beforeunload listener
            window.removeEventListener("beforeunload", this.leaveSession);
        },

        updateMainVideoStreamManager(stream) {
            if (this.mainStreamManager === stream) return;
            this.mainStreamManager = stream;
        },

        async getToken(mySessionId) {
            const sessionId = await this.createSession(mySessionId);
            return await this.createToken(sessionId);
        },

        async createSession(sessionId) {
            const response = await axios.post(APPLICATION_SERVER_URL + 'api/sessions', { customSessionId: sessionId }, {
                headers: { 'Content-Type': 'application/json', },
            });
            return response.data; // The sessionId
        },

        async createToken(sessionId) {
            const response = await axios.post(APPLICATION_SERVER_URL + 'api/sessions/' + sessionId + '/connections', {}, {
                headers: { 'Content-Type': 'application/json', },
            });
            return response.data; // The token
        },
    },
};
</script> -->
<template>
    <div class="game-room-webcam-container">
        <div class="col1">
            <div class="player player1">
                <img v-if="user[0] === undefined" src="../../../../assets/images/WCC_logo.png">
                <user-video v-else-if="myTurn === 0" :stream-manager="user[0]" videoType="myVideo"></user-video>
                <user-video v-else :stream-manager="user[0]" videoType="notMyVideo"></user-video>
            </div>
            <div class="player player2">
                <img v-if="user[1] === undefined" src="../../../../assets/images/WCC_logo.png">
                <user-video v-else-if="myTurn === 1" :stream-manager="user[1]" videoType="myVideo"></user-video>
                <user-video v-else :stream-manager="user[1]" videoType="notMyVideo"></user-video>
            </div>
        </div>
        <div class="row1">
            <div class="player player3">
                <img v-if="user[2] === undefined" src="../../../../assets/images/WCC_logo.png">
                <user-video v-else-if="myTurn === 2" :stream-manager="user[2]" videoType="myVideo"></user-video>
                <user-video v-else :stream-manager="user[2]" videoType="notMyVideo"></user-video>
            </div>
            <div class="player player4">
                <img v-if="user[3] === undefined" src="../../../../assets/images/WCC_logo.png">
                <user-video v-else-if="myTurn === 3" :stream-manager="user[3]" videoType="myVideo"></user-video>
                <user-video v-else :stream-manager="user[3]" videoType="notMyVideo"></user-video>
            </div>
            <div class="player player5">
                <img v-if="user[4] === undefined" src="../../../../assets/images/WCC_logo.png">
                <user-video v-else-if="myTurn === 4" :stream-manager="user[4]" videoType="myVideo"></user-video>
                <user-video v-else :stream-manager="user[4]" videoType="notMyVideo"></user-video>
            </div>
            <div class="player player6">
                <img v-if="user[5] === undefined" src="../../../../assets/images/WCC_logo.png">
                <user-video v-else-if="myTurn === 5" :stream-manager="user[5]" videoType="myVideo"></user-video>
                <user-video v-else :stream-manager="user[5]" videoType="notMyVideo"></user-video>
                <!-- <user-video v-if="mainStreamManager !== undefined" :stream-manager="mainStreamManager" videoType="screen"></user-video>
                <img v-else src="../../../../assets/images/WCC_logo.png"> -->
            </div>
        </div>
    </div>
</template>

<script>
import axios from "axios";
import { OpenVidu } from "openvidu-browser";
import UserVideo from "./components/UserVideo";

axios.defaults.headers.post["Content-Type"] = "application/json";

const APPLICATION_SERVER_URL = process.env.NODE_ENV === 'production' ? '' : 'http://localhost:5000/';

export default {
    name: "WebCam",

    components: {
        UserVideo,
    },

    data() {
        return {
            // OpenVidu objects
            OV: undefined,
            session: undefined,
            mainStreamManager: undefined,
            publisher: undefined,
            subscribers: [],

            // Join form
            mySessionId: "SessionA",
            myUserName: "Participant" + Math.floor(Math.random() * 100),

            myTurn: 0,
            user: [undefined, undefined, undefined, undefined, undefined, undefined]
        };
        
    },

    mounted() {
        this.joinSession();
    },

    methods: {
        joinSession() {
            // --- 1) Get an OpenVidu object ---
            this.OV = new OpenVidu();

            // --- 2) Init a session ---
            this.session = this.OV.initSession();

            // --- 3) Specify the actions when events take place in the session ---

            // On every new Stream received...
            this.session.on("streamCreated", ({ stream }) => {
                const subscriber = this.session.subscribe(stream);
                if (subscriber.stream.typeOfVideo === "SCREEN") {
                    this.mainStreamManager = subscriber;
                }
                else {
                    this.subscribers.push(subscriber);
                    const index = this.subscribers.indexOf(subscriber, 0);
                    this.user[index + 1] = subscriber;
                }
            });

            // On every Stream destroyed...
            this.session.on("streamDestroyed", ({ stream }) => {
                // 스크린 스트림이 파괴될 때 mainStreamManager를 지금 하고 있는 사람 캠으로 전환
                this.mainStreamManager = undefined;
                const index = this.subscribers.indexOf(stream.streamManager, 0);
                if (index >= 0) {
                    this.subscribers.splice(index, 1);
                    this.user[index + 1] = undefined;
                }
            })

            // On every asynchronous exception...
            this.session.on("exception", ({ exception }) => {
                console.warn(exception);
            });

            // --- 4) Connect to the session with a valid user token ---

            // Get a token from the OpenVidu deployment
            this.getToken(this.mySessionId).then((token) => {

                // First param is the token. Second param can be retrieved by every user on event
                // 'streamCreated' (property Stream.connection.data), and will be appended to DOM as the user's nickname
                this.session.connect(token, { clientData: this.myUserName })
                    .then(() => {

                        // --- 5) Get your own camera stream with the desired properties ---

                        // Init a publisher passing undefined as targetElement (we don't want OpenVidu to insert a video
                        // element: we will manage it on our own) and with the desired properties
                        let publisher = this.OV.initPublisher(undefined, {
                            audioSource: undefined, // The source of audio. If undefined default microphone
                            videoSource: undefined, // The source of video. If undefined default webcam
                            publishAudio: true, // Whether you want to start publishing with your audio unmuted or not
                            publishVideo: true, // Whether you want to start publishing with your video enabled or not
                            resolution: "640x480", // The resolution of your video
                            frameRate: 30, // The frame rate of your video
                            insertMode: "APPEND", // How the video is inserted in the target element 'video-container'
                            mirror: false, // Whether to mirror your local video or not
                        });

                        // Set the main video in the page to display our webcam and store our Publisher
                        // this.mainStreamManager = publisher;
                        this.publisher = publisher;
                        console.log(this.publisher);

                        // --- 6) Publish your stream ---

                        this.session.publish(this.publisher);
                        this.addUser();
                        console.log(this.user[this.myTurn]);
                    })
                    .catch((error) => {
                        console.log("There was an error connecting to the session:", error.code, error.message);
                    });
            });

            window.addEventListener("beforeunload", this.leaveSession);
        },

        leaveSession() {
            // --- 7) Leave the session by calling 'disconnect' method over the Session object ---
            if (this.session) this.session.disconnect();

            // Empty all properties...
            this.session = undefined;
            this.mainStreamManager = undefined;
            this.publisher = undefined;
            this.subscribers = [];
            this.OV = undefined;

            // Remove beforeunload listener
            window.removeEventListener("beforeunload", this.leaveSession);
        },

        addUser() {
            // 서버에서 주어진 순서에 따라 user 배치
            // 나(==publisher) 배치
            this.user[this.myTurn] = this.publisher;
            // subscribers 배치
        },

        updateMainVideoStreamManager(stream) {
            if (this.mainStreamManager === stream) return;
            this.mainStreamManager = stream;
        },

        async getToken(mySessionId) {
            const sessionId = await this.createSession(mySessionId);
            return await this.createToken(sessionId);
        },

        async createSession(sessionId) {
            const response = await axios.post(APPLICATION_SERVER_URL + 'api/sessions', { customSessionId: sessionId }, {
                headers: { 'Content-Type': 'application/json', },
            });
            return response.data; // The sessionId
        },

        async createToken(sessionId) {
            const response = await axios.post(APPLICATION_SERVER_URL + 'api/sessions/' + sessionId + '/connections', {}, {
                headers: { 'Content-Type': 'application/json', },
            });
            return response.data; // The token
        },
    },
};
</script>

<style>
@import url('./WebCam.css');
</style>