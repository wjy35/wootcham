<template>
  <div class="mission-select">
    
    <p>미션은 {{ mission }} 으로 {{ keyword }} 하기 입니다.</p>

    <div class="use-mission">
      <p>이 미션 사용하기</p>

      <div class="wrapper">
        <div class="option">
          <input checked="" value="option1" name="btn" type="radio" class="input">
          <div class="btn">
            <span class="span">사용</span>
          </div>
        </div>
        <div class="option">
          <input value="option2" name="btn" type="radio" class="input">
          <div class="btn">
            <span class="span">미사용</span>
          </div>
        </div>
      </div>
      
    </div>
    <div class="display-mission">
      <p>미션을 표시하기</p>

      <div class="wrapper">
        <div class="option">
          <input checked="" value="option1" name="btn" type="radio" class="input">
          <div class="btn">
            <span class="span">예</span>
          </div>
        </div>
        <div class="option">
          <input value="option2" name="btn" type="radio" class="input">
          <div class="btn">
            <span class="span">아니오</span>
          </div>  
        </div>
      </div>
      
    </div>

    <button id-="submitButton" @click="goMainGame" class="shadow">
      <span class="now">입장하기</span>
      <span class="play">준비 완료!</span>
    </button>
    
  </div>
</template>

<script>
export default {
  data(){
    return {
      mission: "노트북",
      keyword: "삼행시"
    }
  }
}
</script>

<style>
.mission-select {
  width: 100%;
  height: 100%;
}

.use-mission, .display-mission {
  display: flex;
}

.wrapper {
  --font-color-dark: #fefefe;
  --font-color-light: #111;
  --bg-color: #111;
  --main-color: #fefefe;
  position: relative;
  width: 250px;
  height: 36px;
  background-color: var(--bg-color);
  border: 2px solid var(--main-color);
  border-radius: 34px;
  display: flex;
  flex-direction: row;
  box-shadow: 4px 4px var(--main-color);
}

.option {
  width: 80.5px;
  height: 28px;
  position: relative;
  top: 2px;
  left: 2px;
}

.input {
  width: 100%;
  height: 100%;
  position: absolute;
  left: 0;
  top: 0;
  appearance: none;
  cursor: pointer;
}

.btn {
  width: 100%;
  height: 100%;
  background-color: var(--bg-color);
  border-radius: 50px;
  display: flex;
  justify-content: center;
  align-items: center;
}

.span {
  color: var(--font-color-dark);
}

.input:checked + .btn {
  background-color: var(--main-color);
}

.input:checked + .btn .span {
  color: var(--font-color-light);
}

/* 플레이 버튼 */ 
button {
  display: flex;
  align-items: center;
  justify-content: center;

    width: 180px;
    height: 40px;

  gap: 10px;
  padding: 0 10px;
  color: white;
  text-shadow: 2px 2px rgb(116, 116, 116);
  text-transform: uppercase;
  border: solid 2px #FFF2EA;
  letter-spacing: 1px;
  font-weight: 600;
  font-size: 17px;
  background-color: #F27059;
  border-radius: 50px;

  position: absolute;
    left: 44%;
    bottom: -15%;
  overflow: hidden;
  transition: all .5s ease;
}

button:active {
  transform: scale(.9);
  transition: all 100ms ease;
}

.play {
  transition: all .5s ease;
  transition-delay: 300ms;
}

.now {
  position: absolute;
  left: 0;
  transform: translateX(-100%);
  transition: all .5s ease;
  z-index: 2;
}

button:hover .now {
  transform: translateX(10px);
  transition-delay: 300ms;
}

button:hover .play {
  transform: translateX(200%);
  transition-delay: 300ms;
}
</style>