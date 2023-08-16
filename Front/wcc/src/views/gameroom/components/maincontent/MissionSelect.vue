<template>
  <div class="mission-select">
    <p class="heading">
      당신의 미션은 <span>{{ keyword }}</span> 키워드로 <br>
      <span>{{ mission }}</span> 하기 입니다.
    </p>

    <div class="radio-input-container">
        <div class="radio-inputs use-mission">
          <label class="radio">
            <input type="radio" v-model="useMission" value="yes">
            <span class="name">미션 사용하기</span>
          </label>
          <label class="radio">
            <input type="radio" v-model="useMission" value="no">
            <span class="name">미션 사용하지 않기</span>
          </label>
        </div>

        <div class="radio-inputs display-mission">
          <label class="radio">
            <input type="radio" v-model="displayMission" value="yes">
            <span class="name">미션 표시하기</span>
          </label>
          <label class="radio">
            <input type="radio" v-model="displayMission" value="no">
            <span class="name">미션 표시하지 않기</span>
          </label>
        </div>

        <button @click="goMainGame" class="shadow">
          준비 완료!
        </button>
    </div>
      
  </div>
</template>

<script>
export default {
  data() {
    return {
      mission: "삼행시",
      keyword: "노트북",

      useMission: "yes",
      displayMission: "yes"
    };
  }
};
</script>

<style scoped>
.mission-select {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: flex-start;
  
  padding: 8px;
  width: 98%;
  height: 400px;
  margin: 3px 0 3px 1px;
  background-color: #FEE9E1;
  border-radius: 15px;
}

.heading {
  font-size: 2.5em;
  font-weight: 100;
  color: #FFCDAD;
  text-shadow: 1px 1px 2px #FF432A;
}
.heading span {
  color: #f27059;
}

.radio-input-container {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}


.radio-inputs {
  position: relative;
  display: flex;
  flex-wrap: wrap;
  border-radius: 0.5rem;
  background-color: #EEE;
  box-sizing: border-box;
  box-shadow: 0 0 0px 1px rgba(0, 0, 0, 0.06);
  padding: 0.25rem;
  margin: 1em;
  width: 300px;
  font-size: 14px;
}

.radio-inputs .radio {
  flex: 1 1 auto;
  text-align: center;
}

.radio-inputs .radio input {
  display: none;
}

.radio-inputs .radio .name {
  display: flex;
  cursor: pointer;
  align-items: center;
  justify-content: center;
  border-radius: 0.5rem;
  border: none;
  padding: .5rem 0;
  color: rgba(51, 65, 85, 1);
  font-weight: 200;
  transition: all .15s ease-in-out;
}

.radio-inputs .radio input:checked + .name {
  background-color: #fff;
  font-weight: 600;
}

/* 완료 버튼 */
button {
  width: 300px;
  color: #d35400;
  font-size: 20px;
  background-color: #FFCDAD;
  border: 1px inset #f39c12;
  border-radius: 15px;
  padding: 7px;
  box-shadow: 0px 6px 0px #FF432A;
  transition: all .1s;
}

button:active {
 box-shadow: 0px 2px 0px #d35400;
 position: relative;
 top: 2px;
}
</style>