<template>
  <div class="user-list">
    <h1>유저 조회</h1>
    <div class="users">
        <div class='user head'>
            <div class='index center'>번호</div>
            <div class='type center'>유형</div>
            <div class='nickname center'>닉네임</div>
            <div class='email center'>이메일</div>
            <div class="point center">포인트</div>
            <div class="money center">머니</div>
            <div class="report center">리폿</div>
            <div class="susp center">정지 종료일</div>
        </div>
        <div v-for='(u, index) in selectedUser' :key='u.id' class="user">
            <div class="index">{{ index + 1 }}</div>
            <div class="type">{{ u.admin === 1 ? '' : '관리자' }}</div>
            <div class="nickname">{{ u.nickname }}</div>
            <div class="email">{{ u.email }}</div>
            <div class="point">{{ u.point }}</div>
            <div class="money">{{ u.money }}</div>
            <div class="report">{{ u.report_count }}</div>
            <div class="susp">{{ !u.suspension_date ? '' : u.suspension_date }}</div>
        </div>
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
            users: [],
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
    beforeMount() {
        let token = localStorage.getItem("accessToken");
        api.get('/report/member', { headers : { 'Authorization': token }}).then(({data}) => {
            this.$store.commit('setUserList', data.data);
        }).then(() => {
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
        }).catch((err) => console.log(err))
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
        width: 9%;
        text-align: center;
    }

    .type {
        width: 10%;
        text-align: center;
    }
    
    .nickname {
        width: 15%;
    }

    .email {
        width: 30%;
    }

    .point {
        width: 8%;
        text-align: center;
    }

    .money { 
        width: 8%;
        text-align: center;
    }

    .report {
        width: 5%;
        text-align: center;
    }
    
    .susp {
        width: 15%;
        text-align: center;
    }

    .user {
        display: flex;
        justify-content: center;
        margin-top: 0.5rem;
        margin-bottom: 0.5rem;
        height: 1.5rem;
        text-align: left;
    }


    .head {
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