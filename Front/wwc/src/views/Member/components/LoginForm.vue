<template>
    <div>
        <form id="loginForm">
            <div>
                <input type="email" placeholder="이메일" v-model="emailInput">
            </div>
            <div>
                <input type="password" placeholder="비밀번호" v-model="pwInput">
            </div>
            <button id="loginButton" @click="login">로그인</button>
            <div id="routes">
                <span>비밀번호를 잊어버렸어요</span>
                <span>회원가입</span>
            </div>
        </form>
    </div>
</template>
<script>
export default {
    name: 'LoginForm',
    data() {
        return {
            emailInput: "",
            pwInput: "",
        }
    },
    methods: {
        login() {
            if (this.emailInput === 'ssafy@ssafy.com' && this.pwInput === 'ssafy') {
                alert('로그인 성공');
            } else if (!this.emailInput.includes('@')) {
                alert('이메일 형식을 지켜주세요');
            } else {
                alert('로그인 실패');
            }
        }
    }
}
</script>
<style scoped>
    #loginForm {
        width: 60%;
        margin: auto;
    }

    #routes {
        display: flex;
        justify-content: space-between;
        text-decoration: underline;
    }

    input {
        width: 100%;
    }

    #loginButton {
        width: 100%;
    }
</style>