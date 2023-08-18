<template lang="">
    <div id="goodbye">
        <div id="goodbye-left">
            <img id="welcome-img" alt="Vue logo" src="../../assets/images/goodbye.png">
        </div>
        <div id='goodbye-right'>
            <div class='goodbye-content'>
                <h1>정말 탈퇴하시겠습니까?</h1>
                <p>회원님께서 탈퇴를 선택하신다면 저희 서비스를 더 이상 이용하실 수 없으며, 모든 혜택과 데이터가 소멸되는 점을 유념하시기 바랍니다. 회원님의 모든 개인정보가 삭제되며, 향후 복구가 불가능하니 신중하게 결정해주세요.</p>
                <SubmitButton class='button back' @click.prevent="goodbye" value="탈퇴하기"></SubmitButton>
                <SubmitButton class='button' @click.prevent="back" value="돌아가기"></SubmitButton>
            </div>
        </div>
    </div>
</template>
<script>
import SubmitButton from './components/UI/SubmitButton.vue';
import api from '@/api'

export default {
    name: "GoodbyeView",
    components: {
        SubmitButton
    },
    methods: {
        goodbye() {
            // 탈퇴
            api.defaults.headers["Authorization"] = localStorage.getItem("accessToken");
            api.delete("/member")
            .then(()=>{
                alert("탈퇴 성공")
                this.$router.push({name: 'login'})
            })
            .catch(()=> {
                alert("일시적 오류입니다. 잠시 후 다시 시도해주세요.")
            })
        }, 
        back() {
            // 뒤로
            this.$router.go(-1);
        }
    }
}
</script>
<style>
    #goodbye {
    font-family: 'Noto Sans KR', sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: center;
    color: #2c3e50;
    display: flex;
    justify-content: left;
    width: 100vw;
    height: 100vh;
    background-color: #F27059;
    }

    body {
    margin: 0;
    }

    #goodbye-left {
    width: 60vw;
    display: flex;
    justify-content: left;
    }

    #welcome-img {
    width: 80%;
    height: auto;
    margin: auto;
    }

    #goodbye-right {
    width: 40vw;
    height: auto;
    margin: auto;
    }

    .goodbye-content {
        background-color: #FFCDAD;
        border-radius: 2rem;
        padding: 2rem;
        width: 80%;
    }

    .button {
        width: 50% !important;
    }
    .back {
        background-color: #FFCDAD !important;
        box-shadow: none !important;
    }

    .back:hover {
        box-shadow: none !important;
        cursor: pointer !important;
    }

    p {
        text-align: left;
        margin: 1rem;
    }

    h1 {
        font-family: 'Black Han Sans', sans-serif;
        color: #F27059;
    }
</style>