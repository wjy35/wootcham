<template>
    <div>
        <form id="findPasswordForm">
            <div id="emailInput">
                <input type="email" placeholder="이메일" v-model="emailInput">
                <ul class="findPwInfo">
                    <li>가입한 이메일을 입력하시면 해당 이메일로 임시 비밀번호를 보내드립니다.</li>
                    <li>임시 비밀번호로 로그인 후 비밀번호를 꼭 변경해주세요.</li>
                </ul>
                <SubmitButton @click="findPw" value="임시 비밀번호 전송"></SubmitButton>
            </div>
            
            <div id="routes">
                <SubmitButton class='routeButton' value="로그인"></SubmitButton >
                <SubmitButton class='routeButton' value="회원가입"></SubmitButton >
            </div>
        </form>
    </div>
</template>
<script>
import SubmitButton from './UI/SubmitButton.vue';

export default {
    name: 'FindPasswordForm',
    data() {
        return {
            emailInput: "",
        }
    },
    components: {
        SubmitButton
    },
    methods: {
        findPw() {
            if (this.emailInput === 'ssafy@ssafy.com') {
                alert('가입하신 이메일로 새로운 비밀번호를 전송했습니다.');
                // 로그인 페이지로 리다이렉트하기
            } else if (!this.emailInput.includes('@')) {
                alert('이메일 형식을 지켜주세요');
                this.emailInput = "";
            } else {
                alert('존재하지 않는 이메일입니다');
                this.emailInput = "";
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

    #routes {
        display: flex;
        justify-content: space-evenly;
        margin: 0;
    }

    .routeButton {
        width: 45% !important;
        margin: 0 !important;
    }

    .findPwInfo {
        text-align: left;
        font-size: 0.8rem;
    }
</style>