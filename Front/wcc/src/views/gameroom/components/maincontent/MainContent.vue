<template>
    <div>
        <div class="main-content">
            <!-- <span>Main Content</span> -->
            <user-video v-if="mainStreamManager !== undefined" :stream-manager="mainStreamManager" videoType="screen"></user-video>
            <img v-else src="../../../../assets/images/WCC_logo.png">
            <button @click="connectScreen">connect screen</button>
            <button @click="stopScreen">stop screen</button>
        </div>
    </div>
</template>

<script>
import axios from "axios";
import { OpenVidu } from "openvidu-browser";
import UserVideo from "../webcam/components/UserVideo.vue";

axios.defaults.headers.post["Content-Type"] = "application/json";

const APPLICATION_SERVER_URL = process.env.NODE_ENV === 'production' ? '' : 'http://localhost:5000/';

export default {
    name: 'MainContent',
    components: {
        UserVideo
    },
    data() {
        return {
            OVScreen: undefined,
            sessionScreen: undefined,
            mainStreamManager: undefined,

            // Join form
            mySessionId: "SessionA",
            myUserName: "Participant" + Math.floor(Math.random() * 100),

            isScreenShared: false,
        }
    },

    methods: {
        connectScreen () {
			this.OVScreen = new OpenVidu();

			this.sessionScreen = this.OVScreen.initSession();
			this.getToken(this.mySessionId).then(token => {
				this.sessionScreen.connect(token, { clientData: this.myUserName + ' 님의 화면공유' })
				.then(() => {
					let publisher = this.OVScreen.initPublisher("sharingvideo", {
						audioSource: false,
						videoSource: "screen",
            publishVideo: true,
						resolution: "640x480",
						frameRate: 10,
            insertMode: 'APPEND',
            mirror: false
					});
					publisher.once('accessAllowed', () => {
						try {
							this.isScreenShared=true;
							this.session.signal({
								data: JSON.stringify(status),  // Any string (optional)
								to: [],
								type: 'startScreenSharing'             // The type of message (optional)
							})
							publisher.stream.getMediaStream().getVideoTracks()[0].addEventListener('ended', () => {
								this.session.signal({
									data: JSON.stringify(status),  // Any string (optional)
									to: [],
									type: 'stopScreenSharing'             // The type of message (optional)
								})
								this.leaveScreenSession()
								this.isScreenShared=false;
							});
						} catch (error) {
							console.error('Error applying constraints: ', error);
						}
					});
					publisher.once('accessDenied', () => {
						console.warn('ScreenShare: Access Denied');
					});
          this.sessionScreen.publish(publisher);
					this.mainStreamManager = publisher;
				}).catch((error => {
					console.warn('There was an error connecting to the session:', error.code, error.message);
				}));
			});
			// window.addEventListener('beforeunload', this.leaveSessionForScreenSharing)
		},

        stopScreen() {
            this.isScreenShared = false;
            this.leaveScreenSession()
        },

        leaveScreenSession () {
            if (this.sessionScreen) {
                this.sessionScreen.disconnect();
            }
            this.sessionScreen = undefined;
            this.mainStreamManager = undefined;
            this.OVScreen = undefined;
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
    }
}
</script>

<style>
@import url('./MainContent.css');
</style>