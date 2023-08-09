<template>
  <div class="mission-select">
    
    <p>미션은 {{ mission }} 으로 {{ keyword }} 하기 입니다.</p>

    <div class="use-mission">
      <p>이 미션 사용하기</p>
      
    </div>
    <div class="display-mission">
      <p>미션을 표시하기</p>
    </div>

    <button>START</button>
    
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
</style>