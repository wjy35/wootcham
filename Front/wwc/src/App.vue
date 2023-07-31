<template>

  <div>
    <!-- HEADER -->
    <header>
      header에용.
      <div class="func_btn">button</div>
    </header>

    <!-- CONTENT -->
    <div class="head-container">
      <img alt="Vue logo" src="./assets/logo.png">
      <HelloWorld msg="Welcome to Your Vue.js App"/>
    </div>
    
  </div>
  
</template>

<script>
import HelloWorld from './components/HelloWorld.vue'


export default {
  name: 'App',
  components: {
    HelloWorld
  }
}
</script>

<style>
.head-container {
  background-color: #F27059;
}

header {
  position: fixed;
  top: 0;
  width: 100%;
  height: 60px;
  background-color: #FFCDAD;
  display: flex;
  justify-content: space-between;
  align-items: center;
  border-top: 1px solid #032c16;
  border-bottom: 2px solid #032c16;
  z-index: 9;
}

.header_menu {
  position: fixed;
  left: 130px;
  width: 380px;
  display: flex;
  align-items: center;
  justify-content: space-between;
}
</style>
