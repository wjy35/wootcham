<template>
    <div>
        <form id="findPasswordForm">
            <p>가입한 이메일을 입력하세요</p>
            <div id="emailInput">
                <input type="email" placeholder="이메일" v-model="emailInput">
                <button id="findPasswordButton" @click="login">인증번호 전송</button>
            </div>
            <div id="routes">
                <span>로그인</span>
                <span>회원가입</span>
            </div>
        </form>
    </div>
</template>
<script>
export default {
    name: 'FindPasswordForm',
    data() {
        return {
            emailInput: "",
        }
    },
    methods: {
        login() {
            if (this.emailInput === 'ssafy@ssafy.com') {
                alert('가입하신 이메일로 새로운 비밀번호를 전송했습니다.');
                // 로그인 페이지로 리다이렉트하기
            } else if (!this.emailInput.includes('@')) {
                alert('이메일 형식을 지켜주세요');
            } else {
                alert('존재하지 않는 이메일입니다');
                this.emailInput = ""
            }
        }
    }
}
</script>
<style scoped>
    #findPasswordForm {
        width: 60%;
        margin: auto;
    }

    input {
        width: 100%;
        height: 100px;
        padding: 0;
    }

    #emailInput {
        position: relative;
    }

    #findPasswordButton {
        position: absolute;
        right: 5%;
        top: 0;
        height: 100px;
    }
</style>