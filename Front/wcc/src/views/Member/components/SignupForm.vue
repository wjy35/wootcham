<template>
    <div>
        <form id="signupForm">
            <div class="emailInput">
                <input type="email" placeholder="이메일" v-model="emailInput">
                <SubmitButton class="innerButton" @click="emailCheck" value="중복 확인"></SubmitButton>
            </div>
            <div class="authInput">
                <input type="text" placeholder="인증 코드" v-model="emailAuth">
                <SubmitButton class="innerButton" @click="authorize" value="인증"></SubmitButton>
            </div>
            <div class="pwInput">
                <input class="pw" type="password" placeholder="비밀번호" v-model="pwInput">
                <span @click.prevent="togglePwPattern" class="pwRule">{{ pwWarning }}</span>
                <p class="pwCondition" v-if="showPwPattern && pwWarning !== '사용 가능'">비밀번호는 8~16자의 영문 대소문자와 숫자 특수문자를 사용하며,</p>
                <p class="pwCondition" v-if="showPwPattern && pwWarning !== '사용 가능'">특수문자는 한 개 이상 포함되어야 합니다.</p>
            </div>
            <div class="pwCheckInput">
                <input type="password" placeholder="비밀번호 확인" v-model="pwCheck">
                <span class="pwCheckWarning">{{ pwCheckWarning }}</span>
            </div>
            <div class="ruleInput">
                <input id="ruleCheck" class="ruleCheck" type="checkbox" v-model="agree">
                <label for="ruleCheck"></label>
                <span class="agree"><a class="toggleRules" @click.prevent="toggleRules">이용약관</a>에 동의합니다.</span>
            </div>
            
            <SubmitButton value="회원가입" @click="signup"></SubmitButton>
        </form>
        <div v-if="showRules" id="rules">
            <p>이용약관 어쩌구 저쩌구</p>
            <button @click.prevent="toggleRules">닫기</button>
        </div>
    </div>
</template>
<script>
import SubmitButton from './UI/SubmitButton.vue';

const regPass = /^(?=.*[a-zA-Z])(?=.*[\W_]).{8,16}$/;

export default {
    name: 'SignupForm',
    data() {
        return {
            emailInput: "",
            emailAuth: "",
            pwInput: "",
            pwCheck: "",
            pwWarning: "생성 규칙",
            pwCheckWarning: "",
            agree: false,
            showRules: false,
            showPwPattern: false,
        }
    },
    components: {
        SubmitButton
    },  

    watch: {
        pwInput() {
            if (this.pwInput.length > 0 && !regPass.test(this.pwInput)) {
                document.querySelector(".pwRule").classList.remove("pwOk");
                document.querySelector(".pwRule").classList.add("pwNotok");
                this.pwWarning = "사용 불가";
            } else if (this.pwInput.length === 0) {
                document.querySelector(".pwRule").classList.remove("pwOk");
                document.querySelector(".pwRule").classList.remove("pwNotok");
                this.pwWarning = "생성 규칙"
            } else {
                document.querySelector(".pwRule").classList.add("pwOk");
                document.querySelector(".pwRule").classList.remove("pwNotok");
                this.pwWarning = "사용 가능";
                this.showPwPattern = false;
            }
        },
        pwCheck() {
            if (this.pwWarning !== "사용 불가") {
                if (this.pwCheck.length > 0 && this.pwInput !== this.pwCheck) {
                    document.querySelector(".pwCheckWarning").classList.remove("ok");
                    this.pwCheckWarning = "불일치"
                } else if (this.pwCheck.length === 0) {
                    this.pwCheckWarning = "";
                } else {
                    document.querySelector(".pwCheckWarning").classList.add("ok");
                    this.pwCheckWarning = "일치";
                }
            }  
        }
    },

    methods: {
        signup() {
            if (!this.agree) {
                alert("이용약관에 동의해주세요")
            } else {
                alert("회원가입 성공")
            }
        },

        toggleRules() {
            this.showRules = !this.showRules;
        },
        
        togglePwPattern() {
            if (this.pwWarning !== "사용 가능") {
                this.showPwPattern = !this.showPwPattern;
            }
        }
    }
}
</script>
<style scoped>
    #signupForm {
        width: 60%;
        margin: auto;
    }

    #signupButton {
        width: 100%;
    }

    #rules {
        width: 50vw;
        background-color: red;
        position: absolute;
        top: 40vh;
        left: 25vw;
    }

    .innerButton{
        font-size: 1.4rem !important;
        width: auto !important;
        position: absolute;
        right: 0.5rem;
    }

    .emailInput{
        position: relative;
    }

    .authInput {
        position: relative;
    }

    .pwInput {
        position: relative;
    }

    .pwCheckInput {
        position: relative;
    }

    .pwRule {
        position: absolute;
        right: 0.5rem;
        top: 1.5rem;
        color: black;
        text-decoration: underline;
    }

    .pwRule:hover {
        cursor: pointer;
    }
    .pwNotok {
        position: absolute;
        right: 0.5rem;
        top: 1.5rem;
        color: red;
        text-decoration: none;
    }

    .pwOk {
        position: absolute;
        right: 0.5rem;
        top: 1.5rem;
        color: black;
        text-decoration: none;
    }

    .pwOk:hover {
        cursor: auto;
    }

    .pwCheckWarning{
        position: absolute;
        right: 0.5rem;
        top: 1.5rem;
        color: red;
    }

    .ok {
        color: black;
        text-decoration: none;
    }
    .pwCondition {
        margin: 0;
        font-size: 0.8rem;
        text-align: left;
    }

    .ruleCheck {
        display: none;
    }

    .ruleCheck + label {
        display: inline-block;
        width: 1.3rem;
        height: 1.3rem;
        border-radius: 0.5rem;
        border: 0.1rem solid #707070;
        position: relative;
        vertical-align: bottom;
        margin-top: 0.5rem;
    }

    .agree {
        margin: 0.5rem;
    }

    .ruleCheck:checked + label::after {
        content:'✔';
        font-size: 1.0rem;
        width: 1.3rem;
        height: 1.3rem;
        text-align: center;
        position: absolute;
        left: 0;
        top: 0;
        color: #707070;
    }

    .toggleRules {
        text-decoration: underline;
    }

    .toggleRules:hover {
        cursor: pointer;
    }
</style>