<template>
    <div>
        <form id="nicknameForm">
            <div>
                <input type="text" placeholder="닉네임" v-model="nicknameInput">
                <button v-if="!nicknameWarning" id="nicknameCheck" @click.prevent="nicknameCheck">중복 확인</button>
            </div>
            <p v-if="nicknameWarning">닉네임은 10자 이상 사용할 수 없습니다.</p>
            <button @click="nickname">회원가입</button>
        </form>
    </div>
</template>
<script>
export default {
    name: 'NicknameForm',
    data() {
        return {
            nicknameInput: "",
            nicknameWarning: false,
            nicknameExists: false,
        }
    },
    
    watch: {
        nicknameInput() {
            if (this.nicknameInput.length >= 10) {
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
            } else if (this.nicknameInput === "ssafy") {
                alert("중복된 닉네임입니다.")
                this.nicknameExists = true;
            } else {
                alert("사용 가능한 닉네임입니다.")
                this.nicknameExists = false;
            }
        },
        nickname() {
            if (!this.nicknameWarning && !this.nicknameExists && this.nicknameInput) {
                alert("가입 성공")
            } else if (this.nicknameWarning) {
                alert("닉네임 길이 초과")
            } else if (this.nicknameExists) {
                alert("중복")
            } else if (this.nicknameInput.length === 0) {
                alert('닉네임을 입력하세요')
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

    input {
        width: 100%;
    }

    #nicknameButton {
        width: 100%;
    }
</style>