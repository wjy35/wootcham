<template>
    <div>
        <form id="loginForm">
            <input type="email" placeholder="이메일" v-model="emailInput" class="emailInput">
            <input type="password" placeholder="비밀번호" v-model="pwInput">
            <SubmitButton class="loginButton" value="로그인" @click.prevent="login"></SubmitButton>
            <div id="routes">
                <span @click="forgotPw">비밀번호를 잊어버렸어요</span>
                <span @click="signup">회원가입</span>
            </div>
        </form>
    </div>
</template>
<script>
// import { useStore } from 'vuex'
// import * as memberApi from '@/api/member';
import SubmitButton from './UI/SubmitButton.vue';
import api from '@/api/http'

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
            api.post(`/member/login`, {
                email: this.emailInput,
                password: this.pwInput
            })
                .then(({ data }) => {
                    if (data.isSuccess == true) {
                        // localStorage에 토큰 저장
                        localStorage.setItem("access_token", data.access_token);
                        localStorage.setItem("refresh_token", data.refresh_token);

                        // store에 토큰 저장
                        this.$store.commit('setAccessToken', localStorage.getItem('access_token'));

                        // 사용자 정보 읽어와서 state에 저장
                        // api.defaults.headers["access-token"] = localStorage.getItem("access_token");
                        // api.post(`/member`)
                        //     .then(({ data }) => {
                        //         if (data.isSuccess == true) {
                        //             console.log("회원 정보 조회 성공...............")
                        //         } else {
                        //             console.log("회원 정보 조회 실패...............")
                        //         }
                        //     })
                        //     .catch(error => {
                        //         alert(error.message)
                        //         console.log(error.response)
                        //     });

                        // 홈 화면으로 이동
                        this.$router.push({ name: 'homeview' })
                    } else {
                        alert("아이디와 비밀번호를 다시 확인해주세요.")
                    }
                })
                .catch(error => {
                    alert("잠시 후 다시 시도해주세요.")
                    console.log(error.message)
                });

        },

        forgotPw() {
            // FindPassword로 라우팅
            this.$router.push({ name: "findpw" })
        },

        signup() {
            // SignupForm으로 라우팅
            this.$router.push({ name: "signup" })
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