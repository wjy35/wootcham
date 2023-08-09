<template>

  <template v-if="gameStatus===GameStatus.WAIT_GAME_START">
    <div>
      <p> 로딩 화면 입니다</p>
      <p>{{sessionId}}</p>
      <p>{{memberId}}</p>
      <p>{{memberToken}}</p>
      <p>{{second}}</p>
    </div>
  </template>

  <template v-else-if="gameStatus===GameStatus.PICK_TOPIC_KEYWORD || gameStatus===GameStatus.PICK_TOPIC_TYPE">
    <game-room-prepare :second="second"></game-room-prepare>
  </template>

  <template v-else>
    <main-game
        :gameStatus="gameStatus"
        :streamManagers="streamManagers"
        :memberTokens="memberTokens"
        :second="second"
    ></main-game>
  </template>

</template>


<script>
import GameRoomPrepare from "@/views/gameroom/prepare/GamePrepare.vue";
import MainGame from "@/views/gameroom/maingame/MainGame.vue";
import {GameStatus} from "@/game-status";
import {OpenVidu} from "openvidu-browser";
import {mapState} from "vuex";

export default {
  name: 'GameRoom',
  computed: {
    GameStatus() {
      return GameStatus
    },
    ...mapState(["client"])
  },
  components: {MainGame, GameRoomPrepare},
  data() {
    return {
      gameStatus:GameStatus.WAIT_GAME_START,
      second:"",
      memberId: localStorage.getItem("memberId"),
      sessionId: localStorage.getItem("sessionId"),
      memberToken: localStorage.getItem("memberToken"),
      OV: undefined,
      session: undefined,
      mainStreamManager: undefined,
      streamManagers:[],
      memberTokens:[],
      flag:true,
    }
  },
  mounted() {
    this.joinSession();
  },
  methods: {
    joinSession() {
      this.OV = new OpenVidu();
      this.session = this.OV.initSession();

      this.session.on("streamCreated", ({stream}) => {
        let subscribeStreamManager = this.session.subscribe(stream);
        this.streamManagers.push(subscribeStreamManager);
        this.memberTokens.push(JSON.parse(subscribeStreamManager.stream.connection.data).clientData);
      });

      this.session.on("streamDestroyed", ({stream}) => {
        const index = this.subscribers.indexOf(stream.streamManager, 0);
        if (index >= 0) {
          this.subscribers.splice(index, 1);
        }
      });

      this.session.on("exception", ({ exception }) => {
        console.warn(exception);
      });

      this.session.connect(this.memberToken, {
        clientData: this.memberToken
      }).then(() => {
        let publisherStreamManager = this.OV.initPublisher(undefined, {
          audioSource: undefined, // The source of audio. If undefined default microphone
          videoSource: undefined, // The source of video. If undefined default webcam
          publishAudio: true, // Whether you want to start publishing with your audio unmuted or not
          publishVideo: true, // Whether you want to start publishing with your video enabled or not
          resolution: "640x480", // The resolution of your video
          frameRate: 30, // The frame rate of your video
          insertMode: "APPEND", // How the video is inserted in the target element 'video-container'
          mirror: false, // Whether to mirror your local video or not
        });

        this.streamManagers.push(publisherStreamManager);
        this.memberTokens.push(this.memberToken);
        this.session.publish(publisherStreamManager);

        this.client.subscribe(
            `/topic/game/${this.sessionId}`,
            (frame)=>{
              console.log(frame);
              let gameResponse = JSON.parse(frame.body);
              this.gameStatus = gameResponse.gameStatus;
              this.second = gameResponse.second;

              if(this.gameStatus===GameStatus.ORDER_GAMEMEMBER){
                console.log(gameResponse.order);
                console.log(this.memberTokens);

                let orderStreamManagers=[];
                let orderMemberTokens=[];

                for(let orderMemberToken of gameResponse.order){
                  for(let i=0; i<this.memberTokens.length; i++){
                    if(orderMemberToken===this.memberTokens[i]){
                      orderStreamManagers.push(this.streamManagers[i]);
                      orderMemberTokens.push(orderMemberToken);
                    }
                  }
                }
                this.streamManagers = orderStreamManagers;
                this.memberTokens = orderMemberTokens;
              }else if(this.gameStatus===GameStatus.PREPARE_PRESENT){
                if(this.flag){
                  this.streamManagers.push(this.streamManagers.shift());
                  this.memberTokens.push(this.memberTokens.shift());

                  this.flag = false;
                }
              }else if(this.gameStatus===GameStatus.PRESENT){
                this.flag = true;
              }
              //
              // if(gameResponse.tellerToken){
              //   this.tellerToken = gameResponse.tellerToken;
              // }
              //
              // if(gameResponse.topic){
              //   this.topic = gameResponse.topic;
              // }
            },
            (error)=>{
              console.log(error);
            },
        );

      }).catch((error) => {
            console.log("There was an error connecting to the session:", error.code, error.message);
      });

    }
  },
}
</script>

<style scoped>
</style>