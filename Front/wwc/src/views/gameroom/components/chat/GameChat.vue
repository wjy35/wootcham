<template>
    <div>
        <div class="card">
            <!-- <div class="chat-header">Chat</div> -->
            <div class="chat-window">
                <ul class="message-list"></ul>
            </div>
            <div class="chat-input">
                <input type="text" class="message-input" placeholder="메세지 입력......">
                <button class="send-button">전송</button>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    name: 'GameChat'
}
</script>

<style>
@import url('./GameChat.css');
</style>