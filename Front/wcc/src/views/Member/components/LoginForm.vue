<template>
    <div>
        <form id="loginForm">
            <input type="email" placeholder="이메일" v-model="state.form.emailInput" class="email-input">
            <input type="password" placeholder="비밀번호" v-model="state.form.pwInput" class="password-input">
            <SubmitButton class="loginButton" value="로그인" @click.prevent="login"></SubmitButton>
            <div id="routes">
                <span class="cursor-pointer" @click="forgotPw">비밀번호를 잊어버렸어요</span>
                <span class="cursor-pointer" @click="signup">회원가입</span>
            </div>
        </form>
    </div>
</template>
<script>
import { reactive, watch } from 'vue'
import { useStore } from 'vuex'
import { useRouter } from 'vue-router'
import SubmitButton from './UI/SubmitButton.vue';
import api from '@/api/index'

export default {
    name: 'LoginForm',

    components: {
        SubmitButton
    },

    setup() {

        const store = useStore();       // store 등록
        const router = useRouter()      // router 등록

        const state = reactive({        // state 선언
            form: {
                emailInput: "",
                pwInput: "",
                emailCheck: true,
            }
        })

        // 이메일 유효성 검사
        watch(() => state.form.emailInput, () => {
            if (state.form.emailInput.length > 0 && !state.form.emailInput.includes("@")) {
                state.form.emailInputemailCheck = false;
                document.querySelector(".email-input").classList.add("warning");
            } else {
                state.form.emailInputemailCheck = true;
                document.querySelector(".email-input").classList.remove("warning");
                state.form.emailInputemailCheck = true;
                document.querySelector(".email-input").classList.remove("warning");
            }
        })

        const login = () => {
            // 로그인 요청
            api.post(`/member/login`, {
                email: state.form.emailInput,
                password: state.form.pwInput
            }).then(({ data }) => {
                console.log("data:", data)

                // localStorage에 토큰 저장
                localStorage.setItem("access_token", data.access_token);
                localStorage.setItem("refresh_token", data.refresh_token);

                // store에 토큰 저장
                store.commit('setAccessToken', localStorage.getItem('access_token'));
                console.log("Store AccessToken: ", store.getters['getAccessToken']);

                // 사용자 정보 읽어와서 state에 저장
                api.defaults.headers["access_token"] = localStorage.getItem("access_token");
                api.post(`/member`)
                    .then(({ data }) => {
                        if (data.isSuccess == true) {
                            console.log("회원 정보 조회 성공...............")
                            console.log("data: ", data.data)
                            store.commit('setUserEmail', data.data.email)
                            store.commit('setUserMoney', data.data.money)
                            store.commit('setUserNickname', data.data.nickname)
                            store.commit('setUserPoint', data.data.point)
                        } else {
                            console.log("회원 정보 조회 실패...............")
                        }
                    })
                    .catch(error => {
                        alert(error.message)
                        console.log(error.response)
                    });

                // 홈 화면으로 이동
                router.push({ name: 'homeview' })

            }).catch(error => {
                if (error.response.status == 404) {   // 사용자 정보 없음
                    alert("이메일과 비밀번호를 다시 확인해주세요.")
                } else {
                    alert("잠시 후 다시 시도해주세요.")
                }
            })
        }

        // const forgotPw = () => {
        const forgotPw = () => {
            // FindPassword로 라우팅
            router.push({ name: "findpw" })
        }
        //     router.push({ name: "findpw" })
        // }

        const signup = () => {
            // SignupForm으로 라우팅
            router.push({ name: "signup" })
        }

        return { state, login, forgotPw, signup };
    }

}
</script>
<style scoped>
#loginForm {
    width: 70%;
    margin: auto;
    text-align: center;
}

#loginForm input {
    background-color: #FFF2EA;
}

.email-input {
    color: black;
}

#routes {
    display: flex;
    justify-content: space-between;

    padding: 0 20px;
    text-decoration: underline;
    margin-top: 1rem;
    margin-bottom: 1rem;
}

#routes span:hover {
    color: white;
}
</style>