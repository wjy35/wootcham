<template>
    <div>
        <form id="nicknameForm">
            <div class="nicknameInput">
                <input type="text" placeholder="닉네임" v-model="nicknameInput">
                <SubmitButton class='innerButton' value="중복 확인" v-if="!nicknameWarning" @click.prevent="nicknameCheck">
                </SubmitButton>
                <span v-if="nicknameWarning" class="nicknameNotok">사용 불가</span>
            </div>
            <ul class="nicknameRules">
                <li>게임 중 다른 사용자들에게 보여지는 이름으로, 민감한 개인정보를 입력하지 말아주세요.</li>
                <li>닉네임은 10자 이내이며 한글 자모나 특수기호는 사용할 수 없습니다.</li>
                <li>닉네임은 추후에 변경할 수 있습니다.</li>
            </ul>
            <SubmitButton @click.prevent="nickname" value="회원가입"></SubmitButton>
        </form>
    </div>
</template>
<script>
import api from "@/api"
import SubmitButton from './UI/SubmitButton.vue';
const regNickname = /^[a-zA-Z0-9가-힣]{1,10}$/
export default {
    name: 'NicknameForm',
    data() {
        return {
            nicknameInput: "",
            nicknameWarning: false,
            nicknameExists: undefined,
        }
    },
    components: {
        SubmitButton,
    },
    watch: {
        nicknameInput() {
            this.nicknameExists = undefined;
            if (this.nicknameInput.length === 0) {
                this.nicknameWarning = false;
            } else if (!regNickname.test(this.nicknameInput)) {
                this.nicknameWarning = true;
            } else {
                this.nicknameWarning = false;
            }
        }
    },

    methods: {
        // 닉네임 중복 검사
        nicknameCheck() {
            if (!this.nicknameInput) {
                alert("닉네임을 입력하세요")
            } else {
                api.post("/member/nickname", {
                    nickname: this.nicknameInput
                }).then(() => {
                    alert("사용 가능한 닉네임입니다.")
                    this.nicknameExists = false;
                }).catch(() => {
                    alert("사용할 수 없는 닉네임입니다.")
                })
            }
        },
        // 회원 가입
        nickname() {
            if (this.nicknameInput.length === 0) {
                alert('닉네임을 입력하세요')
            } else if (this.nicknameWarning) {
                alert('닉네임 조건을 확인해주세요')
            } else if (!this.nicknameWarning && this.nicknameExists === undefined) {
                alert('중복 확인을 해주세요');
            } else if (!this.nicknameWarning && this.nicknameExists) {
                alert('중복된 닉네임입니다')
            } else if (!this.nicknameWarning && !this.nicknameExists) {
                console.log(history.state);
                // 회원 가입
                api.post("/member/join", {
                    "email": history.state.email,
                    "password": history.state.password,
                    "nickname": this.nicknameInput
                }).then(() => {
                    console.log(history.state.email)
                    console.log(history.state.password)
                    alert('회원 가입 성공')
                    this.$router.push({ name: "login" })
                }).catch((error) => {
                    console.log(error.message)
                    alert('회원 가입 실패')
                })
            }
        }
    }
}
</script>
<style scoped>
#nicknameForm {
    width: 60%;
    margin: auto;
}

.nicknameInput {
    position: relative;
}

.innerButton {
    font-size: 1.4rem !important;
    width: auto !important;
    position: absolute;
    right: 0.5rem;
}

.nicknameRules {
    font-size: 0.8rem;
    text-align: left;
}

.nicknameNotok {
    position: absolute;
    right: 0.5rem;
    top: 1.5rem;
    color: red;
}

.nicknameOk {
    position: absolute;
    right: 0.5rem;
    top: 1.5rem;
    color: black;
}
</style>