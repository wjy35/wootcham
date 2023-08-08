<template>
    <div>
        <div class="card">
            <!-- <div class="chat-header">Chat</div> -->
            <div class="chat-window">
                <ul class="message-list" v-for="m in messageList" :key="m">
                    <li>
                        <p>{{ m.nickname }}</p>
                        <p>{{ m.message }}</p>
                    </li>
                </ul>
            </div>
            <div class="chat-input">
                <input type="text" class="message-input" v-model="message" placeholder="메세지 입력......">
                <button @click='sendMessage' class="send-button">전송</button>
            </div>
        </div>
    </div>
</template>

<script>
import { mapState } from 'vuex';

export default {
    name: 'GameChat',
    data() {
        return {
            message: '',
        }
    },
    computed: {
        ...mapState({ myPublisher : state => state.gameStore.myPublisher, 
            messageList: state => state.gameStore.messageList,
            userName: state => state.gameStore.userName })
    },
    methods: {
        sendMessage() {
            if (this.message.trim()) {
                const data = { message: this.message, nickname: this.userName };
                console.log(data);
                this.myPublisher.stream.session.signal({
                    data: JSON.stringify(data),
                    type: 'chat',
                })
                this.message = '';
            }
        },
    }
}
</script>

<style>
@import url('./GameChat.css');
</style>