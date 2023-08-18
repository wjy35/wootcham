<template>
    <div>
        <div class="nicknameChange">
            <h1>닉네임 수정하기</h1>
            <form id="nicknameForm">
                <div class="nicknameInput">
                    <input type="text" placeholder="닉네임" v-model="nicknameInput">
                    <SubmitButton class='innerButton' value="중복 확인" v-if="!nicknameWarning" @click.prevent="nicknameCheck"></SubmitButton>
                    <span v-if="nicknameWarning" class="nicknameNotok">사용 불가</span>
                </div>
                <ul class="nicknameRules">
                    <li>게임 중 다른 사용자들에게 보여지는 이름으로, 민감한 개인정보를 입력하지 말아주세요.</li>
                    <li>닉네임은 10자 이내이며 한글 자모나 특수기호는 사용할 수 없습니다.</li>
                    <li>닉네임은 추후에 변경할 수 있습니다.</li>
                </ul>
                <SubmitButton class='button back' @click="back" value="돌아가기"></SubmitButton>
                <SubmitButton class='button' @click.prevent="nickname" value="변경하기"></SubmitButton>
            </form>
        </div>
    </div>
</template>
<script>
import api from '@/api';
import SubmitButton from './UI/SubmitButton.vue';
const regNickname = /^[a-zA-Z0-9가-힣]{1,10}$/
export default {
    name: 'NicknameChangeForm',
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
        nicknameCheck() {
            if (!this.nicknameInput) {
                alert("닉네임을 입력하세요")
            } else{
                api.post("/member/nickname", {
                    "nickname": this.nicknameInput
                }).then(({data}) => {
                    if(data.unique == true){
                        alert("사용 가능한 닉네임입니다.")
                        this.nicknameExists = false;
                    }else{
                        alert("사용 불가능한 닉네임입니다.")
                        this.nicknameExists = true;
                    }
                }).catch(()=>{
                    alert("사용 불가능한 닉네임입니다.")
                })
            }
        },
        nickname() {
            console.log("nicknameWarning: ", this.nicknameWarning)
            console.log("nicknameExist: ", this.nicknameExists)
            if (this.nicknameInput.length === 0) {
                alert('닉네임을 입력하세요')
            } else if (this.nicknameWarning) {
                alert('닉네임 조건을 확인해주세요')
            } else if (!this.nicknameWarning && this.nicknameExists === undefined) {
                alert('중복 확인을 해주세요');
            } else if (!this.nicknameWarning && this.nicknameExists) {
                alert('중복된 닉네임입니다')
            } else if (!this.nicknameWarning && !this.nicknameExists) {
                console.log("중복 검사 요청..")
                api.defaults.headers["Authorization"] = localStorage.getItem("accessToken");
                console.log(localStorage.getItem("accessToken"));
                api.put("/member", {
                    "nickname" : this.nicknameInput, 
                }).then(()=>{
                    alert("수정 성공");
                    this.$router.go(-1);
                }).catch(()=>{
                    alert("일시적 오류입니다. 잠시 후 다시 시도해주세요.")
                })
            }
        },
        back() {
            // 이전 페이지로 돌아가기
            this.$router.go(-1);
        }
    }
}
</script>
<style scoped>
    h1 {
        font-family: 'Black Han Sans', sans-serif;
        color: #F27059;
    }
    #nicknameForm {
        margin: auto;
    }

    .nicknameChange {
        background-color: #FFCDAD;
        border-radius: 2rem;
        padding: 2rem;
        width: 80%;
    }

    .nicknameInput {
        position: relative;
    }

    .innerButton{
        font-size: 1.4rem !important;
        width: auto !important;
        position: absolute;
        right: 0.5rem;
    }

    .nicknameRules {
        font-size: 0.8rem;
        text-align: left;
        list-style-type: disc;
        margin: 0;
        margin-top: 1rem;
        margin-bottom: 1rem;
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
