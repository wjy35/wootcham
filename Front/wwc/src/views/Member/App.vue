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
  </div>
</template>

<script>
// import HelloWorld from './components/HelloWorld.vue'
// import LoginForm from './components/LoginForm.vue';
// import SignupForm from './components/SignupForm.vue';
// import WelcomeMessage from './components/WelcomeMessage.vue';
import NicknameForm from './components/NicknameForm.vue';

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

<style scoped>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
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
</style>
