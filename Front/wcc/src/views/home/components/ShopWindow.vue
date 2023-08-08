<template>
  <div class="content-window shadow">
    <div class="banner"> 
      <img src="@/assets/images/shop_banner.png" alt="explain_banner">
    </div>

    <div class="scroll-snap-card">
      <div class="slide red">
          <p class="tip">Scroll On Me</p>
      </div>
      <div class="slide blue">
          <p class="tip">Scroll On Me</p>
      </div>
      <div class="slide green">
          <p class="tip">Scroll On Me</p>
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
  top: 67%;
  left: -28px;
  width: 50px; 
  height: 50px; 
  background-image: url('@/assets/images/indicator.png');
  background-size: contain; 
  background-repeat: no-repeat; 
  background-color: transparent;
}

.content {
  width: 800px;
  height: 700px;
}

.red {
  background-color: #f43f5e;
}

.blue {
  background-color: #3b82f6;
}

.green {
  background-color: #22c55e;
}

.scroll-snap-card {
  height: 100%;
  width: 100%;
  scroll-snap-type: y mandatory;
  overflow: auto;
  border-radius: 10px;
}

.scroll-snap-card .slide {
  width: 100%;
  height: 100%;
  scroll-snap-align: start;
  display: flex;
  align-items: center;
  justify-content: center;
}

.scroll-snap-card .slide p {
  font-size: 1em;
  color: white;
  font-weight: 700;
}
</style>