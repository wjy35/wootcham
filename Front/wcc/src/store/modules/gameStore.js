// import router from "@/router";
import {} from "@/api/game"
import { OpenVidu } from "openvidu-browser";
import axios from "axios";

axios.defaults.headers.post["Content-Type"] = "application/json";

const APPLICATION_SERVER_URL = process.env.NODE_ENV === 'production' ? '' : 'http://localhost:5000/';

const gameStore = {
  namespaced: true,
  state: {
    // session: undefined,
    myPublisher: undefined,
    mainStreamManager: undefined,
    messageList: [],
    userName: '',
  },
  getters: {},
  mutations: {
    SET_MAIN(state, publisher) {
      state.mainStreamManager = publisher;
    },
    SET_MYPUBLISHER(state, publisher) {
      state.myPublisher = publisher;
    },
    SET_MESSAGELIST(state, messageList) {
      state.messageList = messageList;
    },
    SET_USERNAME(state, userName) {
      console.log('setnickname')
      state.userName = userName;
    }
  },
  actions: {
    // 게임 시작할 때 세션에 들어가서 카메라 publisher를 만들기 
    joinSession({commit}) {
      console.log('joinSession')
      // OpenVidu 객체를 하나 생성
      this.OV = new OpenVidu();
      // this.OV = new OpenVidu();

      console.log(this.OV.initSession());

      // 세션 시작하기
      commit('SET_CAMERASESSION', this.OV.initSession())
      // let session = this.state.OV.initSession();

      // 세션에 이벤트가 발생할 때 일어날 일들

      // 세션에 스트림이 새로 생성되면
      this.state.cameraSession.on("streamCreated", ({ stream }) => {
          const subscriber = this.state.cameraSession.subscribe(stream);
          // 어떤 유저가 화면 공유를 했을 때
          // mainStreamManager에 subscriber 배치
          if (subscriber.stream.typeOfVideo === "SCREEN") {
              commit('SET_MAIN', subscriber);
              // this.mainStreamManager = subscriber;
          }
          // 카메라가 들어왔을 때
          // 순서 따라서 players에 subscriber 배치
          else {
              let players = this.state.players;
              for (let i = 0; i < players.length; i++) {
                if (players[i] === undefined) {
                  players[i] = subscriber;
                  break;
                }
              }
              // subscribers.push(subscriber);
              commit('SET_PLAYERS', players);
              // const index = this.subscribers.indexOf(subscriber, 0);
              // this.user[index + 1] = subscriber;
          }
      });

      // 세션에 스트림이 파괴되면
      // 카메라 스트림 - players의 해당 위치를 undefined로
      // 스크린 스트림 - mainStreamManager를 지금 발표하고 있는 유저로 전환
      this.state.cameraSession.on("streamDestroyed", ({ stream }) => {
          // 스크린 스트림이 파괴될 때 mainStreamManager를 지금 하고 있는 사람 캠으로 전환
          commit('SET_MAIN', undefined);
          // this.mainStreamManager = undefined;
          let players = this.state.players;
          const index = players.indexOf(stream.streamManager, 0);
          if (index >= 0) {
              players[index] = undefined;
              commit('SET_PLAYERS', players);
          }
      })

      // 예외처리 
      this.state.cameraSession.on("exception", ({ exception }) => {
          console.warn(exception);
      });

      // 세션 토큰을 활용해서 세션에 연결하기
      this.getToken(this.state.sessionId).then((token) => {

          // streamCreated 이벤트를 유발함
          this.state.cameraSession.connect(token, { clientData: this.state.userName })
              .then(() => {
                
                  // publisher를 만들기                
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

                  // 유저의 위치에 따라서 배치
                  commit('SET_MYSTREAM', publisher);
                  // this.state.myStreamManager = publisher;

                  // publish하기
                  this.state.cameraSession.publish(publisher);
              })
              .catch((error) => {
                  console.log("There was an error connecting to the session:", error.code, error.message);
              });
      });
    },

    leaveSession() {
      // session의 연결 해제
      if (this.state.cameraSession) this.state.cameraSession.disconnect();

      // 모든 state를 undefined로 만들기
      this.state.cameraSession = undefined;
      this.state.mainStreamManager = undefined;
      this.state.publisher = undefined;
      this.state.subscribers = [];
      this.state.OV = undefined;
    },

    updateMainVideoStreamManager({commit}, stream) {
      if (this.state.mainStreamManager === stream) return;
      commit('SET_MAIN', stream);
      // this.state.mainStreamManager = stream;
    },

    async getToken({commit}, id) {
      const sessionId = await this.createSession(id);
      const sessionToken = await this.createToken(sessionId);
      commit('SET_TOKEN', sessionToken)
      return sessionToken;
      // return await this.createToken(sessionId);
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

    // 화면 공유하기
    connectScreen ({commit}) {
      // 새 OV 객체에서 session init하기
			this.OVScreen = new OpenVidu();
      commit('SET_SCREENSESSION', this.OVScreen.initSession())
			// this.sessionScreen = this.OVScreen.initSession();

      // token을 보내서 session에 connect하기
			this.getToken(this.state.sessionId).then(token => {
				this.screenSession.connect(token, { clientData: this.state.userName + ' 님의 화면공유' })
				.then(() => {
					let publisher = this.OVScreen.initPublisher("sharingvideo", {
						audioSource: false,
						videoSource: "screen",
            publishAudio: true,
            publishVideo: true,
						resolution: "640x480",
						frameRate: 10,
            insertMode: 'APPEND',
            mirror: false
					});
					publisher.once('accessAllowed', () => {
						try {
							// this.isScreenShared=true;
							this.screenSession.signal({
								data: JSON.stringify(status),  // Any string (optional)
								to: [],
								type: 'startScreenSharing'             // The type of message (optional)
							})
							publisher.stream.getMediaStream().getVideoTracks()[0].addEventListener('ended', () => {
								this.screenSession.signal({
									data: JSON.stringify(status),  // Any string (optional)
									to: [],
									type: 'stopScreenSharing'             // The type of message (optional)
								})
								this.disconnectScreen()
								// this.isScreenShared=false;
							});
						} catch (error) {
							console.error('Error applying constraints: ', error);
						}
					});
					publisher.once('accessDenied', () => {
						console.warn('ScreenShare: Access Denied');
					});

					// this.mainStreamManager = publisher;
          commit('SET_MAIN', publisher);

          // publisher를 publish하기
          this.state.screenSession.publish(publisher);
				}).catch((error => {
					console.warn('There was an error connecting to the session:', error.code, error.message);
				}));
			});
		},

    // 화면 공유를 끝내기
    disconnectScreen ({commit}) {
      // 세션을 disconnect
      if (this.state.screenSession) {
          this.state.screenSession.disconnect();
      }
      commit('SET_SCREENSESSION', undefined);
      // this.sessionScreen = undefined;
      // 메인에 보이는 사람을 카메라로 돌려놓거나 없애거나 
      commit('SET_MAIN', undefined);
      // this.mainStreamManager = undefined;
      // this.OVScreen = undefined;
    },

  },
};

export default gameStore;
