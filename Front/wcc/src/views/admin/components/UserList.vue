<template>
  <div class="user-list">
    <h1>유저 조회</h1>
    <div class="users">
        <div class='user head'>
            <div class='index center'>번호</div>
            <div class='nickname center'>닉네임</div>
            <div class='email center'>이메일</div>
        </div>
        <ul v-for='u in selectedUser' :key=u.index>
            <li class="user">
                <div class="index">{{ u.index }}</div>
                <div class="nickname">{{ u.nickname }}</div>
                <div class="email">{{ u.email }}</div>
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
  </div>
</template>

<script>
import { mapState } from 'vuex';
import api from '@/api/index'

export default {
    name: 'UserList',
    data() {
        return {
            users: [
                {index: 1, nickname: 'yaong1', email: 'yaong@naver.com'},
                {index: 2, nickname: 'yaong2', email: 'yaong@naver.com'},
                {index: 3, nickname: 'yaong3', email: 'yaong@naver.com'},
                {index: 4, nickname: 'yaong4', email: 'yaong@naver.com'},
                {index: 5, nickname: 'yaong5', email: 'yaong@naver.com'},
                {index: 6, nickname: 'yaong6', email: 'yaong@naver.com'},
                {index: 7, nickname: 'yaong7', email: 'yaong@naver.com'},
                {index: 8, nickname: 'yaong8', email: 'yaong@naver.com'},
                {index: 9, nickname: 'yaong9', email: 'yaong@naver.com'},
                {index: 10, nickname: 'yaong9', email: 'yaong@naver.com'},
                {index: 11, nickname: 'yaong9', email: 'yaong@naver.com'},
                {index: 12, nickname: 'yaong9', email: 'yaong@naver.com'},
                {index: 13, nickname: 'yaong9', email: 'yaong@naver.com'},
                {index: 14, nickname: 'yaong9', email: 'yaong@naver.com'},
                {index: 15, nickname: 'yaong9', email: 'yaong@naver.com'},
                {index: 16, nickname: 'yaong9', email: 'yaong@naver.com'},
            ],
            pagination: 7,
            currentPage: 1,
            startPage: 1,
            endPage: 0,
            totalPage: 0,
            selectedUser: [],
        }
    },
    computed: {
        ...mapState(["userList"]),
    },
    mounted() {
        // api.defaults.headers["access_token"] = localStorage.getItem("access_token");
        // api.get('/report/member').then(({data}) => {
        //     this.$store.commit('setUserList', data);
        // })
        this.$store.commit('setUserList', this.users);
        this.totalPage = Math.floor(this.userList.length / this.pagination) + 1;
        if (this.totalPage <= this.startPage + 4) {
            this.endPage = this.totalPage;
        } else {
            this.endPage = this.startPage + 4;
        }
        for (let i = 0; i < this.pagination; i++) {
            if (this.userList.length >= i + 1) {
                this.selectedUser.push(this.userList[i]);
            }
        }
    },
    methods: {
        movePage(index) {
            this.selectedUser = [];
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
                if (this.userList.length >= i + 1) {
                    this.selectedUser.push(this.userList[i]);
                }
            }
        },
    }
}
</script>

<style scoped>
    .user-list {
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

    .users {
        width: 90%;
        background-color: white;
        margin-top: 2rem;
        margin-left: auto;
        margin-right: auto;
        padding: 0.5rem;
        font-size: 1rem;
        font-family: 'Noto Sans KR', sans-serif;
        border-left: 5px solid #F8837A;
        clip-path: polygon(0 0, 100% 0, 100% calc(100% - 20px), calc(100% - 20px) 100%, 0 100%);
    }

    .index {
        width: 10%;
        text-align: center;
    }
    
    .nickname {
        width: 40%;
        text-align: left;
    }

    .email {
        width: 50%;
        text-align: left;
    }

    .user {
        display: flex;
        justify-content: center;
        margin-top: 0.5rem;
        margin-bottom: 0.5rem;
        height: 1.5rem;
    }

    .center {
        text-align: center;
    }

    .head {
        padding-left: 32px;
        font-weight: bolder;
        margin-bottom: 1rem;
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
</style>