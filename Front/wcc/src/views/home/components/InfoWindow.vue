<template>
  <div class="content-window shadow">
    <div class="banner">
      <img src="@/assets/images/explain_banner.png" alt="explain_banner">
    </div>

    <div class="scroll-snap-card">
      <div class="slide one">

      </div>
      <div class="slide two">

      </div>
      <div class="slide three">

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

.content-window::before {
  content: '';
  position: absolute;
  top: 88%;
  left: -28px;
  width: 50px;
  height: 50px;
  background-image: url('@/assets/images/indicator.png');
  background-size: contain;
  background-repeat: no-repeat;
  background-color: transparent;
}

.banner img {
  width: 350px;
  margin-top: -60px;
}

/* 메인 콘텐츠 */
.one {
  background: url(@/assets/images/info1.png);
  background-size: contain;
  background-repeat: no-repeat;
}

.two {
  background: url(@/assets/images/introduction_one.png);
  background-size: contain;
  background-repeat: no-repeat;
}

.three {
  background: url(@/assets/images/introduction_one.png);
  background-size: contain;
  background-repeat: no-repeat;
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