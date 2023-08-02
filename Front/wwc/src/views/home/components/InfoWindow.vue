<template>
  <div class="content-window shadow">
    <div class="content">
        <img src="/src/assets/images/explain_banner.png" alt="explain_banner">
    </div>

  </div>
</template>

<style scoped>
.content-window {
  background-color: #FFF2EA;
}

.content-window::before {
  content: '';
  position: absolute;
  top: 83%;
  left: -28px;
  width: 50px; 
  height: 50px; 
  background-image: url('@/assets/images/indicator.png');
  background-size: contain; 
  background-repeat: no-repeat; 
  background-color: transparent;
}
</style>