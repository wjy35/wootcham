<template>
  <div id="app">
    <div id="welcome">
      <img id="welcome-img" alt="Vue logo" src="./assets/welcome.png">
    </div>
    <!-- <HelloWorld id="content" msg="Welcome to Your Vue.js App"/> -->
    <!-- <LoginForm id="content"></LoginForm> -->
    <!-- <SignupForm id="content"></SignupForm> -->
    <!-- <WelcomeMessage :isSignup=true :nickname="nickname" id="content"></WelcomeMessage> -->
    <NicknameForm id="content"></NicknameForm>
    <!-- <FindPasswordForm id="content"></FindPasswordForm> -->
  </div>
</template>

<script>
// import LoginForm from './components/LoginForm.vue';
// import SignupForm from './components/SignupForm.vue';
// import WelcomeMessage from './components/WelcomeMessage.vue';
import NicknameForm from './components/NicknameForm.vue';
// import FindPasswordForm from './components/FindPasswordForm.vue';

export default {
  name: 'App',
  components: {
    NicknameForm
  },
  data() {
    return {
      nickname: "yaong",
    }
  }
}
</script>

<style>
#app {
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
