<template>
  <div class="standby">

    <p>다음 발표자가 미션을 고르고 있습니다.</p>
    <p>잠시만 기다려주세요.</p>
    
  </div>
</template>

<script>
</script>

<style>
.standby {
  width: 98%;
  height: 400px;
  margin: 5px 0 5px 2.5px;
  background-color: grey;
  border-radius: 15px;
  display: flex;
  flex-direction: column ;
}

.standby p {
  margin-top: 20px;
  font-size: 2.5em;
  color: #fff;
  transition: transform 0.4s ease;
}

.standby p:hover {
  transform: scale(0.96);
}
</style>