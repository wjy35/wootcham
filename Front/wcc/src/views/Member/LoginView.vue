<template lang="">
    <div id="login">
        <div id="welcome">
            <img id="welcome-img" alt="Vue logo" src="../../assets/images/welcome.png">
        </div>
        <LoginForm id="content"></LoginForm>
    </div>
</template>
<script>
import LoginForm from './components/LoginForm.vue';

export default {
    name: "LoginView",
    components: {
        LoginForm
    }
}
</script>
<style>
    #login {
    font-family: 'Noto Sans KR', sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: center;
    color: #2c3e50;
    display: flex;
    justify-content: left;
    width: 100vw;
    height: 100vh;
    background-color: #FFCDAD
    }

    body {
    margin: 0;
    }

    #welcome {
    width: 60vw;
    display: flex;
    justify-content: left;
    }

    #welcome-img {
    width: 80%;
    height: auto;
    margin: auto;
    }

    #content {
    width: 40vw;
    height: auto;
    margin: auto;
    }

    input {
    margin-top: 0.5rem;
    margin-bottom: 0.5rem;
    width: 100%;
    border: none;
    padding: 1rem;
    box-shadow: 2px 2px 2px gray;
    border-radius: 1rem;
    box-sizing: border-box;
    font-size: 1.5rem;
    }

    input:focus {
    outline: none;
    }

    .warning {
    border: 3px solid red !important;
    }
</style>