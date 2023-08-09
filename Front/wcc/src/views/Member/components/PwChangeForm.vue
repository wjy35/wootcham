<template>
    <div>
        <div class="pwChange">
            <h1>비밀변호 변경하기</h1>
            <form id="pwForm">
                <div class="pwInput">
                    <input type="password" placeholder="기존 비밀번호" v-model="originalPw">
                    <input type="password" placeholder="새로운 비밀번호" v-model="newPw">
                    <p class='warningtext' v-if="warning1">기존 비밀번호랑 동일한 비밀번호를 사용할 수 없습니다.</p>
                    <p class="warningtext" v-if="warning3">비밀번호 규칙을 지켜주세요.</p>
                    <input type="password" placeholder="새로운 비밀번호 확인" v-model="newPwCheck">
                    <p class="warningtext" v-if="warning2">비밀번호가 일치하지 않습니다.</p>
                </div>
                <SubmitButton class='button back' @click="back" value="돌아가기"></SubmitButton>
                <SubmitButton class='button' @click="pwChange" value="변경하기"></SubmitButton>
            </form>
        </div>
    </div>
</template>
<script>
import SubmitButton from './UI/SubmitButton.vue';

const regPass = /^(?=.*[a-zA-Z])(?=.*[\W_]).{8,16}$/;

export default {
    name: 'PwChangeForm',
    data() {
        return {
            originalPw: '',
            newPw: '',
            newPwCheck: '',
            warning1: false,
            warning2: false,
            warning3: false,
        }
    },
    components: {
    SubmitButton,
    },
    watch: {
        originalPw() {
            if (this.originalPw.length > 0 && this.originalPw === this.newPw) {
                this.warning1 = true;
            } else if (this.originalPw.length > 0 && this.originalPw !== this.newPw) {
                this.warning1 = false;
            }

            if (this.originalPw.length === 0) {
                this.warning1 = false;
            }
        },
        newPw() {
            // 기존 비밀번호와 비교
            if (this.originalPw.length > 0 && this.originalPw === this.newPw) {
                this.warning1 = true;
            } else if (this.originalPw.length > 0 && this.originalPw !== this.newPw) {
                this.warning1 = false;
            }
            // 새 비밀번호가 비밀번호 규칙에 맞는지
            if (this.newPw.length > 0 && !regPass.test(this.newPw)) {
                this.warning3 = true;
            } else if (this.newPw.length > 0 && regPass.test(this.newPw)) {
                this.warning3 = false;
            }
            // 확인 입력이 일치하는지
            if (this.newPw.length > 0 && this.newPwCheck.length > 0 && this.newPw !== this.newPwCheck) {
                this.warning2 = true;
            } else if (this.newPw.length > 0 && this.newPwCheck.length > 0 && this.newPw === this.newPwCheck) {
                this.warning2 = false;
            }

            if (this.newPw.length === 0) {
                this.warning3 = false;
                this.warning1 = false;
                this.warning2 = false;
            }
        },
        newPwCheck() {
            if (this.newPw.length > 0 && this.newPwCheck.length > 0 && this.newPw !== this.newPwCheck) {
                this.warning2 = true;
            } else if (this.newPw.length > 0 && this.newPwCheck.length > 0 && this.newPw === this.newPwCheck) {
                this.warning2 = false;
            }

            if (this.newPwCheck.length === 0) {
                this.warning2 = false;
            }
        }
    }, 

    methods: {
        back() {

        },
        pwChange() {
            // 셋 중 하나라도 공백이거나 warning이 활성화되어 있을 시 되돌려놓기

            // 비밀번호가 내 비밀번호가 아닐 때

            // 승인
        }
    }
}
</script>
<style scoped>
    h1 {
        font-family: 'Black Han Sans', sans-serif;
        color: #F27059;
    }
    #pwForm {
        margin: auto;
    }

    .pwChange {
        background-color: #FFCDAD;
        border-radius: 2rem;
        padding: 2rem;
        width: 80%;
    }

    .pwInput {
        position: relative;
    }

    .warningtext {
        color: red;
        text-align: left;
        margin: 0;
    }

    .button {
        width: 50%;
    }
    .back {
        background-color: #FFCDAD;
        box-shadow: none;
    }

    .back:hover {
        box-shadow: none;
        cursor: pointer;
    }
</style>
