<template>
    <div>
        <form id="findPasswordForm">
            <div class="heading">Forgot Password?</div>
            <div id="emailInput">
                <input type="email" placeholder="이메일" v-model="emailInput">
                <div class="findPwInfo">
                    <p 임시>가입한 이메일을 입력하시면 해당 이메일로 임시 비밀번호를 보내드립니다.
                    임시 비밀번호로 로그인 후 비밀번호를 꼭 변경해주세요.</p>
                </div>
                <SubmitButton class="emailSendButton" @click="findPw" value="임시 비밀번호 전송"></SubmitButton>
            </div>
            
            <div id="routes">
                <SubmitButton class='routeButton' value="로그인" @click="goLogin"></SubmitButton >
            </div>
            <div>
                <div @click="goForsignUp" class="goForsignUp">아직 회원이 아니신가요?</div>
            </div>
        </form>
    </div>
</template>
<script>
import SubmitButton from './UI/SubmitButton.vue';
import api from '@/api'

export default {
    name: 'FindPasswordForm',
    data() {
        return {
            emailInput: "",
            modalVisible: false
        }
    },
    components: {
        SubmitButton,
    },
    methods: {
        findPw() {
            if(!this.emailInput.includes('@')){
                alert('올바른 이메일을 입력해주세요.')
            }else{
                // api.
            }
            // if (this.emailInput === 'ssafy@ssafy.com') {
            //     alert('가입하신 이메일로 임시 비밀번호를 전송했습니다.');
            //     this.modalVisible = true; // show modal
            // } else if (!this.emailInput.includes('@')) {
            //     alert('올바른 이메일이 아닙니다.');
            //     this.emailInput = "";
            // } else {
            //     alert('존재하지 않는 이메일입니다');
            //     this.emailInput = "";
            // }
        },
        goLogin(){
            this.$router.push('/');
        },
        goForsignUp(){
            this.$router.push('/signup');
        },
    }
}
</script>


<style scoped>
#findPasswordForm {
    width: 60%;
    margin: auto;
}

.heading {
    font-family: Luckiest Guy;
    color: #F27059;
    font-size: 1.5em;
}

#findPasswordForm input {
    background-color: #FFF2EA;
}

#routes {
    display: flex;
    justify-content: space-evenly;
    margin: 0;
}

.emailSendButton {
    background-color: #F27059;
    color: #FFF2EA;
}

.routeButton {
    width: 100% !important;
    margin: 2px !important;
    font-size: 1.5em;
}

.findPwInfo {
    text-align: center;
    font-size: 0.8rem;
}

.goForsignUp {
    cursor: pointer;
    margin: 20px 0;
}
.goForsignUp:hover {
    color: #F27059;
}
</style>