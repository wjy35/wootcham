<template>
    <div class="welcome">
        <p class="nickname">{{ nickname }}님,</p>
        <p class="welcomeMessage" v-if="isSignup">웃참클럽에 가입하신 것을</p>
        <p class="welcomeMessage">환영합니다.</p>
        <p>로딩중...</p>
    </div>
</template>
<script>
export default {
    name: "WelcomeMessage",
    props: {
        nickname: String,
        isSignup: Boolean,
    }, 
}
</script>
<style scoped>
    .welcome {
        font-family: 'Black Han Sans', sans-serif;
        color: #FF7926;
        font-size: 2rem;
    }

    .nickname {
        color: #F27059;
        font-size: 3rem;
        margin: 1rem;
    }
    .welcomeMessage {
        margin: 0;
    }
</style>