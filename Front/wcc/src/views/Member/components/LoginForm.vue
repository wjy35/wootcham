<template>
    <div>
        <form id="loginForm">
            <input type="email" placeholder="이메일" v-model="emailInput" class="emailInput">
            <input type="password" placeholder="비밀번호" v-model="pwInput">
            <SubmitButton class="loginButton" value="로그인" @click="login"></SubmitButton>
            <div id="routes">
                <span @click="forgotPw">비밀번호를 잊어버렸어요</span>
                <span @click="signup">회원가입</span>
            </div>
        </form>
    </div>
</template>
<script>
import SubmitButton from './UI/SubmitButton.vue';

export default {
    name: 'LoginForm',
    data() {
        return {
            emailInput: "",
            pwInput: "",
            emailCheck: true,
        }
    },

    components: {
        SubmitButton
    },

    watch: {
        emailInput() {
            if (this.emailInput.length > 0 && !this.emailInput.includes("@")) {
                this.emailCheck = false;
                document.querySelector(".emailInput").classList.add("warning");
            } else {
                this.emailCheck = true;
                document.querySelector(".emailInput").classList.remove("warning");
            }
        }
    },

    methods: {
        login() {
            if (this.emailInput === 'ssafy@ssafy.com' && this.pwInput === 'ssafy') {
                alert('로그인 성공');
                this.$router.push({name: "welcome"})
            } else if (!this.emailInput.includes('@')) {
                alert('이메일 형식을 지켜주세요');
            } else {
                alert('로그인 실패');
            }
        },

        forgotPw() {
            // FindPassword로 라우팅
            this.$router.push({name: "findpw"})
        },

        signup() {
            // SignupForm으로 라우팅
            this.$router.push({name: "signup"})
        }
    }
}
</script>
<style scoped>
    #loginForm {
        width: 60%;
        margin: auto;
        text-align: center;
    }

    #routes {
        display: flex;
        justify-content: space-between;
        text-decoration: underline;
        margin-top: 1rem;
        margin-bottom: 1rem;
    }
    
</style>