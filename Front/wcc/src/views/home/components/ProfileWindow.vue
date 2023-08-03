<template>
  <div class="content-window shadow">
    <div class="content">
      <div class="profile-info-card">
        프로파일 인포
        <div id="editBtn">닉네임 수정하기</div>
        <div id="editBtn">비밀번호 변경하기</div>
      </div>
      <div class="profile-record-card">
        <div class="profile-image"></div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.content-window {
  background-color: #FFF2EA;
}

.content {
  display: flex;
  height: 100%; 
  width: 100%;
}

.profile-info-card,
.profile-record-card {
  flex-grow: 0;
  flex-shrink: 0;
  box-sizing: border-box;
  border: 1px solid #ccc;
  padding: 10px;
}

.profile-info-card {
  background-color: #f2f2f2;
  border: 10px solid #FF7B27;
  border-radius: 10px;
  flex-basis: 40%; 
  height: 100%; 
}

.profile-info-card:hover {
  box-shadow: 4px 4px 0 1px rgba(0,0,0);
}

.profile-record-card {
  flex-basis: 60%; 
  height: 100%;
}

.editBtn {
  font-family: 'Black Han Sans', sans-serif;
  width: 100%;
  background-color: #FF7926;
  color: white;
  font-size: 2rem; 
  border: none;
  border-radius: 1rem;
  box-shadow: 5px 5px 5px gray;
  margin-top: 1rem;
  margin-bottom: 1rem;
  padding: 0.5rem;
}

#editBtn:hover {
  box-shadow: 5px 5px 5px black;
  cursor: pointer;
}
</style>