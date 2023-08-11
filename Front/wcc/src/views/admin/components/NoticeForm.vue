<template lang="">
    <div class='notice-create'>
        <h1>공 지 사 항</h1>
        <form class='notice-form'>
            <input type='text' v-model='title' placeholder='제목'>
            <textarea rows='7' v-model='content' placeholder='내용'></textarea>
            <div class='button-area'>
                <SubmitButton @click.prevent='toggleSubmit' class='create-button' :value='value'></SubmitButton>
                <SubmitButton @click='noticeList' class='list-button' value='목록'></SubmitButton>
            </div>
        </form>
        <CommonModal class='modal2' v-if='submit'>
            <p>공지사항을 {{ value }}하시겠습니까?</p>
            <SubmitButton class='no' @click.prevent="toggleSubmit" value="아니오"></SubmitButton>
            <SubmitButton class='yes' @click.prevent="sendNotice" value="네"></SubmitButton>
        </CommonModal>
    </div>
</template>
<script>
import SubmitButton from '@/views/Member/components/UI/SubmitButton.vue';
import CommonModal from '@/views/Member/components/UI/CommonModal.vue';
import api from '@/api/index'
  
export default {
    name: 'NoticeForm',
    components: {
        SubmitButton, CommonModal
    },
    props: {
        type: String,
        notice: Object,
    },
    data() {
        return {
            title: '',
            content: '',
            value: '작성',
            submit: false,
        }
    },

    mounted() {
        if (this.type === 'update') {
            this.title = this.notice.title;
            this.content = this.notice.content;
            this.value = '수정';
        }
    },
    methods: {
        sendNotice() {
            if (!this.title || !this.content) {
                alert('내용을 입력해주세요.');
                this.submit = false;
                return;
            }
            // if (this.type === 'update') {
            //     api.defaults.headers["access_token"] = localStorage.getItem("access_token");
            //     api.put('/notice', {
            //         id: this.notice.id,
            //         subject: this.title,
            //         content: this.content,                    
            //     }).then(() => {
            //         this.$router.push({name: 'admin'});
            //     }).catch((err) => {
            //         alert('공지사항 수정에 실패했습니다. ' + err);
            //     })
            // } else {
            //     api.defaults.headers["access_token"] = localStorage.getItem("access_token");
            //     api.post('/notice', {
            //         subject: this.title,
            //         content: this.content,                    
            //     }).then(() => {
            //         this.$router.push({name: 'admin'});
            //     }).catch((err) => {
            //         alert('공지사항 작성에 실패했습니다. ' + err);
            //     })
            // }
        },
        noticeList() {
            this.$router.push({name: 'admin'})
        },
        toggleSubmit() {
            this.submit = !this.submit;
        }
    }
}
</script>
<style scoped>
    .notice-create {
        background-color: #FFF2EA;
        text-align: center;
        height: calc(100vh - 150px);
        width: 1000px;
        margin: 70px 30px 0;
        border-radius: 25px;
        border: 3px solid #FFCDAD;
        padding: 0;
    }

    h1 {
        margin-top: 2rem;
    }

    .notice-form {
        width: 80%;
        margin-top: 2rem;
        margin-left: auto;
        margin-right: auto;
        padding: 1rem;
        font-size: 1rem;
        font-family: 'Noto Sans KR', sans-serif;
        background-color: #FFCDAD;
        border-left: 5px solid #F8837A;
        clip-path: polygon(0 0, 100% 0, 100% calc(100% - 20px), calc(100% - 20px) 100%, 0 100%);
    }

    .notice-form input {
        font-size: 1rem;
        padding: 0.5rem;
        width: 95%;
        border-radius: 0;
        color: black;
        background-color: #FFF2EA;
        border: none;
        outline: none;
        margin-bottom: 20px;
        font-weight: bold;
        transition: all 0.2s ease-in-out;
        border-left: 1px solid transparent;
    }

    .notice-form input:focus {
        border-left: 5px solid #F8837A;
    }

    .notice-form textarea {
        font-size: 1rem;
        padding: 0.5rem;
        color: black;
        background-color: #FFF2EA;
        width: 95%;
        resize: none;
        box-shadow: 2px 2px 2px gray;
        border: none;
        outline: none;
        font-weight: bold;
        transition: all 0.2s ease-in-out;
        border-left: 1px solid transparent;
    }

    .notice-form textarea:focus {
        outline: none;
        border-left: 5px solid #F8837A;
    }

    .button-area {
        display: flex;
        justify-content: end;
    }
    .create-button {
        width: auto;
        margin-top: 1.5rem;
        height: 3.5rem;
        padding-left: 2rem;
        padding-right: 2rem;
        margin-right: 1rem;
        background-color: #FFF2EA;
        color: #F8837A;
        align-items: center;
    }

    .list-button {
        width: auto;
        margin-top: 1.5rem;
        height: 3.5rem;
        padding-left: 2rem;
        padding-right: 2rem;
        margin-right: 1rem;
        color: #FFF2EA;
        background-color: #F8837A;
        align-items: center;
    }

    .yes {
        background-color: #FFF2EA;
        width: 6rem;
        color: #F27059;
        font-size: 1.5rem;
        margin: 1rem;
    }

    .no {
        background-color: #F27059;
        width: 6rem;
        color: #FFF2EA;
        font-size: 1.5rem;
        margin: 1rem;
        box-shadow: none;
    }

    .no:hover {
        box-shadow: none;
    }

    .modal2 {
        top: 40vh;
        left: 41vw;
    }

    .modal2 p {
        font-family: 'Noto Sans KR', sans-serif;
        color: #FFF2EA;
        font-size: 1.5rem;
        text-align: center;
    }
</style>