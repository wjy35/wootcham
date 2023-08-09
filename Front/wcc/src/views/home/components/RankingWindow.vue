<template>
  <div class="content-window shadow">
    <div class="banner"> 
      <img src="@/assets/images/ranking_banner.png" alt="explain_banner">
    </div>

    <div class="content">

      <div class="ranking-card">

      </div>
    </div>

  </div>
</template>

<style scoped>
.content-window {
  background-color: #FFF2EA;
  display: flex;
  flex-direction: column;
  align-items: center;
}

.banner img {
  width: 350px;
  margin-top: -60px;
}


.content-window::before {
  content: '';
  position: absolute;
  top: 78%;
  left: -28px;
  width: 50px; 
  height: 50px; 
  background-image: url('@/assets/images/indicator.png');
  background-size: contain; 
  background-repeat: no-repeat; 
  background-color: transparent;
}
</style>