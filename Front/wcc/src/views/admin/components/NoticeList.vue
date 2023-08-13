<template lang="">
    <div class='notice-list'>
        <h1>공 지 사 항</h1>
        <div class='notices'>
            <div class='notice head'>
                <div class='index center'>번호</div>
                <div class='title center'>제목</div>
                <div class='date center'>작성일</div>
            </div>
            <ul v-for='n in selectedNotice' :key=n.id>
                <li class='notice'>
                    <div class='index'>{{ n.id }}</div>
                    <div class='title' @click='noticeDetail(n.id)'>{{ n.title }}</div>
                    <div class='date'>{{ n.date }}</div>
                </li>
            </ul>
        </div>
        <div class='pagi-nation'>
            <div class='page' v-if='startPage > 1'><span @click='movePage(startPage - 1)' class='page-num'>&#60;&#60;</span></div>
            <div class='page' v-for='index in 5' :key='index'>
                <span class='current' v-if='index + startPage - 1 === currentPage'>{{ currentPage }}</span>
                <span class='page-num' v-else-if='index + startPage - 1 <= endPage' @click='movePage(index + startPage - 1)'>{{ index + startPage - 1 }}</span>
            </div>
            <div class='page' v-if='endPage < totalPage'><span @click='movePage(endPage + 1)' class='page-num'>&#62;&#62;</span></div>
        </div>
        <div class='button-area'>
            <SubmitButton @click='noticeCreate' class='notice-create' value='공지 작성하기'></SubmitButton>
        </div>
    </div>
</template>
<script>
import SubmitButton from '@/views/Member/components/UI/SubmitButton.vue';
import { mapState } from 'vuex';
import api from '@/api/index'

export default {
    name: 'NoticeList',
    components: {
        SubmitButton,
    },
    data() {
        return {
            noticeList: [],
            pagination: 5,
            currentPage: 1,
            startPage: 1,
            endPage: 0,
            totalPage: 0,
            selectedNotice: [],
        }
    },
    computed: {
        ...mapState(["notices"]),
    },
    mounted() {
        // notices 받아오기
        api.defaults.headers["access_token"] = localStorage.getItem("accessToken");
        api.get('/notice/list').then(({data}) => {
            this.$store.commit('setNotices', data.data);
        }).catch((err) => console.log(err))
        this.totalPage = Math.floor(this.notices.length / this.pagination) + 1;
        if (this.totalPage <= this.startPage + 4) {
            this.endPage = this.totalPage;
        } else {
            this.endPage = this.startPage + 4;
        }
        for (let i = 0; i < this.pagination; i++) {
            if (this.notices.length >= i + 1) {
                this.selectedNotice.push(this.notices[i]);
            }
        }
    },
    methods: {
        movePage(index) {
            this.selectedNotice = [];
            if (index < 1) {
                this.currentPage = 1;
            } else {
                this.currentPage = index;
            }
            this.startPage = Math.floor((this.currentPage - 1) / 5) * 5 + 1;
            if (this.startPage + 4 <= this.totalPage) {
                this.endPage = this.startPage + 4;
            } else {
                this.endPage = this.totalPage;
            }
            for (let i = (this.currentPage - 1) * this.pagination; i < this.currentPage * this.pagination; i++) {
                if (this.notices.length >= i + 1) {
                    this.selectedNotice.push(this.notices[i]);
                }
            }
        },
        noticeCreate() {
            this.$router.push({ name: 'noticecreate' })
        },
        noticeDetail(id) {
            this.$router.push({ name: 'noticedetail', params: { id: id }});
        }
    }
}
</script>
<style scoped>
    .notice-list {
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
    .notices {
        width: 90%;
        margin-top: 2rem;
        margin-left: auto;
        margin-right: auto;
        padding: 0.5rem;
        font-size: 1rem;
        font-family: 'Noto Sans KR', sans-serif;
        background-color: white;
        border-left: 5px solid #F8837A;
        clip-path: polygon(0 0, 100% 0, 100% calc(100% - 20px), calc(100% - 20px) 100%, 0 100%);
    }

    .notice {
        display: flex;
        justify-content: center;
        margin-top: 0.5rem;
        margin-bottom: 0.5rem;
        height: 1.5rem;
    }

    
    .index {
        width: 10%;
        text-align: center;
    }
    
    .title {
        width: 70%;
        text-align: left;
        overflow: hidden;
    }
    li .title:hover {
        cursor: pointer;
        text-decoration: underline;
    }

    .date {
        width: 20%;
    }
    .page {
        display: inline-block;
    }

    .page-num:hover {
        color: white;
        background-color: #F27059;
        cursor: pointer;
    }

    .pagi-nation {
        margin-top: 2rem;
        margin-left: auto;
        margin-right: auto;
    }

    .current {
        padding: 1rem;
        padding-top: 0.8rem;
        padding-bottom: 0.8rem;
        font-size: 1.5rem;
        color: white;
        background-color: #F27059;
    } 

    .page-num {
        padding: 1rem;
        padding-top: 0.8rem;
        padding-bottom: 0.8rem;
        font-size: 1.5rem;
        background-color: white;
    }
    .notice-create {
        width: auto;
        background-color: #FFCDAD;
        color: #F8837A;
        font-size: 1.5rem;
    }

    .button-area {
        width: 90%;
        display: flex;
        justify-content: end;
    }

    .center {
        text-align: center;
    }

    .head {
        padding-left: 32px;
        font-weight: bolder;
        margin-bottom: 1rem;
    }

</style>