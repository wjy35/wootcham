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
                localStorage.setItem("accessToken", data.accessToken);
                localStorage.setItem("refreshToken", data.refreshToken);

                // store에 토큰
                store.commit('setAccessToken', localStorage.getItem('accessToken'));

                console.log("localStorage AccessToken: ", localStorage.getItem('accessToken'));
                console.log("Store AccessToken: ", store.getters['getAccessToken']);

                // 0이면 관리자 페이지로 이동
                if (data.admin == 0) {
                    router.push({ name: 'admin' })
                }
                // 1이면 일반 사용자 -> 홈 화면으로 이동
                else {
                    api.defaults.headers["Authorization"] = localStorage.getItem('accessToken');
                    api.get("/topic")
                    .then(({data}) => {
                        store.commit('setKeywords', data.data);
                    })
                    .catch((error)=>{
                        console.log(error)
                    });
                    router.push({ name: 'homeview' })
                }
            }).catch((error) => {
                console.log("error: ", error)
                if (error.status === 400) {   // 비밀번호 틀림
                    alert("비밀번호를 다시 확인해주세요.")
                } else if (error.status === 403) { // 정지된 사용자
                    alert("정지된 사용자입니다.")
                } else if (error.status === 404) { // 존재하지 않는 사용자
                    alert("이메일을 다시 확인해주세요.")
                } else {
                    alert("일시적 오류입니다. 잠시 후 다시 시도해주세요.")
                }
            })
        }

        const forgotPw = () => {
            // FindPassword로 라우팅
            router.push({ name: "findpw" })
        }

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