<template>
  <div class="countdown">
    <div v-if="topic.useTopic && topic.displayTopic">
      <p class="heading text-shadow">주제 : {{type}}</p>
      <p class="heading text-shadow">단어 : {{topic.keyword}}</p>
    </div>
    <div v-else-if="!topic.useTopic && topic.displayTopic">
      <p class="heading text-shadow">주제 : 자유주제 </p>
    </div>
    <div v-else-if="topic.useTopic && !topic.displayTopic">
      <p class="heading text-shadow">비공개</p>
    </div>
    <div v-else-if="!topic.useTopic && !topic.displayTopic">
      <p class="heading text-shadow">비공개</p>
    </div>
    <p class="heading text-shadow">로 발표 시작합니다!.</p>

    <div class="flex">
      <div class="loader"></div>
      <p class="second text-shadow">{{second}} 초</p>
    </div>
    
  </div>
</template>

<script>
export default{
  name:"CountDown",
  props: {
    second: Number,
    topic: Object,
  },
  data() {
    let topicResponse =  JSON.parse(localStorage.getItem("topics"));
    return {
      type:topicResponse[1][`${this.topic.type}`].name,
    }
  },
};
</script>

<style scoped>
.countdown {
  width: 98%;
  height: 400px;
  margin: 5px 0 5px 2.5px;
  background-color: grey;
  border-radius: 15px;
  display: flex;
  flex-direction: column;

  align-items: center;
  justify-content: center;
}

.countdown .heading {
  margin-top: 20px;
  font-size: 2.5em;
  color: white;
  transition: transform 0.4s ease;
}

.countdown .heading:hover {
  transform: scale(0.96);
}

.second {
  font-size: 2em;
  color: wheat;
}

.loader {
  box-sizing: border-box;
  display: inline-block;
  width: 50px;
  height: 80px;
  border-top: 5px solid #fff;
  border-bottom: 5px solid #fff;
  position: relative;
  background: linear-gradient(#FF3D00 30px, transparent 0) no-repeat;
  background-size: 2px 40px;
  background-position: 50% 0px;
  animation: spinx 5s linear infinite;
}

.loader:before, .loader:after {
  content: "";
  width: 40px;
  left: 50%;
  height: 35px;
  position: absolute;
  top: 0;
  transform: translatex(-50%);
  background: rgba(255, 255, 255, 0.4);
  border-radius: 0 0 20px 20px;
  background-size: 100% auto;
  background-repeat: no-repeat;
  background-position: 0 0px;
  animation: lqt 5s linear infinite;
}

.loader:after {
  top: auto;
  bottom: 0;
  border-radius: 20px 20px 0 0;
  animation: lqb 5s linear infinite;
}

@keyframes lqt {
  0%, 100% {
    background-image: linear-gradient(#FF3D00 40px, transparent 0);
    background-position: 0% 0px;
  }

  50% {
    background-image: linear-gradient(#FF3D00 40px, transparent 0);
    background-position: 0% 40px;
  }

  50.1% {
    background-image: linear-gradient(#FF3D00 40px, transparent 0);
    background-position: 0% -40px;
  }
}

@keyframes lqb {
  0% {
    background-image: linear-gradient(#FF3D00 40px, transparent 0);
    background-position: 0 40px;
  }

  100% {
    background-image: linear-gradient(#FF3D00 40px, transparent 0);
    background-position: 0 -40px;
  }
}

@keyframes spinx {
  0%, 49% {
    transform: rotate(0deg);
    background-position: 50% 36px;
  }

  51%, 98% {
    transform: rotate(180deg);
    background-position: 50% 4px;
  }

  100% {
    transform: rotate(360deg);
    background-position: 50% 36px;
  }
}
</style>