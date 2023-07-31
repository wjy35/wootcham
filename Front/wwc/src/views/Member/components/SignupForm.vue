<template>
    <div>
        <form id="signupForm">
            <div>
                <input type="email" placeholder="이메일" v-model="emailInput">
                <button @click="emailCheck">중복 확인</button>
            </div>
            <div>
                <input type="text" placeholder="이메일" v-model="emailAuth">
                <button @click="authorize">인증</button>
            </div>
            <div>
                <input type="password" placeholder="비밀번호" v-model="pwInput">
                <button @click="pwPattern">?</button>
                <p>{{ pwWarning }}</p>
            </div>
            <div>
                <input type="password" placeholder="비밀번호 확인" v-model="pwCheck">
                <p>{{ pwCheckWarning }}</p>
            </div>
            <button @click.prevent="toggleRules">이용약관</button>
            <input type="checkbox" v-model="agree">
            <label>이용약관에 동의합니다.</label>
            <button id="signupButton" @click="signup">회원가입</button>
        </form>
        <div v-if="showRules" id="rules">
            <p>이용약관 어쩌구 저쩌구</p>
            <button @click.prevent="toggleRules">닫기</button>
        </div>
    </div>
</template>
<script>
export default {
    name: 'SignupForm',
    data() {
        return {
            emailInput: "",
            emailAuth: "",
            pwInput: "",
            pwCheck: "",
            pwWarning: "",
            pwCheckWarning: "",
            agree: false,
            showRules: false,
        }
    },

    watch: {
        pwInput() {
            if (this.pwInput.length > 0 && this.pwInput.length < 8) {
                this.pwWarning = "비밀번호는 8자리 이상입니다.";
            } else {
                if (this.pwCheck.length > 0 && this.pwInput !== this.pwCheck) {
                    this.pwCheckWarning = "비밀번호가 일치하지 않습니다."
                } else {
                    this.pwWarning = "";
                }
            }
        },
        pwCheck() {
            if (this.pwCheck.length > 0 && this.pwInput !== this.pwCheck) {
                this.pwCheckWarning = "비밀번호가 일치하지 않습니다."
            } else if (this.pwCheck.length === 0) {
                this.pwCheckWarning = "";
            } else {
                this.pwCheckWarning = "비밀번호가 일치합니다.";
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
        }
    }
}
</script>
<style scoped>
    #signupForm {
        width: 60%;
        margin: auto;
    }

    input {
        width: 100%;
    }

    #signupButton {
        width: 100%;
    }

    #rules {
        width: 50vw;
        height: 20vh;
        background-color: red;
        position: absolute;
        top: 40vh;
        left: 25vw;
    }
</style>