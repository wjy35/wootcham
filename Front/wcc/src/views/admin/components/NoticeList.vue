<template lang="">
    <div class='notice-list'>
        <h1>공 지 사 항</h1>
        <div class='notices'>
            <ul v-for='n in selectedNotice' :key=n.index>
                <li class='notice'>
                    <div class='index'>{{ n.index }}</div>
                    <div class='title'>{{ n.title }}</div>
                    <div class='date'>{{ n.date }}</div>
                </li>
            </ul>
        </div>
        <div class='pagi-nation'>
            <div class='page' v-if='startPage > 1'><span @click='movePage(1)' class='page-num'>&#60;&#60;</span></div>
            <div class='page' v-for='index in 5' :key='index'>
                <span class='current' v-if='index + startPage - 1 >= 1 && index + startPage - 1 <= endPage && index + startPage - 1 === startPage'>{{ index + startPage - 1 }}</span>
                <span class='page-num' v-else-if='index + startPage - 1 >= 1 && index + startPage - 1 <= endPage' @click='movePage(index + startPage - 1)'>{{ index + startPage - 1 }}</span>
            </div>
            <div class='page' v-if='endPage < totalPage'><span @click='movePage(totalPage)' class='page-num'>&#62;&#62;</span></div>
        </div>
        <button>공지 작성하기</button>
    </div>
</template>
<script>
export default {
    name: 'NoticeList',
    data() {
        return {
            notice: [
                {index: 1, title: '공지사항1', date: '2023-08-10'},
                {index: 2, title: '공지사항2', date: '2023-08-10'},
                {index: 3, title: '공지사항3', date: '2023-08-10'},
                {index: 4, title: '공지사항4', date: '2023-08-10'},
                {index: 5, title: '공지사항5', date: '2023-08-10'},
                {index: 6, title: '공지사항6', date: '2023-08-10'},
                {index: 7, title: '공지사항7', date: '2023-08-10'},
                {index: 8, title: '공지사항8', date: '2023-08-10'},
                {index: 9, title: '공지사항9', date: '2023-08-10'},
                {index: 10, title: '공지사항10', date: '2023-08-10'},
                {index: 11, title: '공지사항10', date: '2023-08-10'},
                {index: 12, title: '공지사항10', date: '2023-08-10'},
                {index: 13, title: '공지사항10', date: '2023-08-10'},
                {index: 14, title: '공지사항10', date: '2023-08-10'},
            ],
            pageno: 1,
            pagination: 2,
            startPage: 1,
            endPage: 5,
            totalPage: 0,
            selectedNotice: [],
        }
    },
    mounted() {
        this.totalPage = this.notice.length / this.pagination;
        for (let i = 0; i < this.pagination; i++) {
            if (this.notice.length >= i + 1) {
                this.selectedNotice.push(this.notice[i]);
            }
        }
    },
    methods: {
        movePage(index) {
            this.selectedNotice = [];
            this.startPage = index;
            if (index + 4 <= this.totalPage) {
                this.endPage = index + 4;
            } else {
                this.endPage = this.totalPage;
            }
            for (let i = (index - 1) * this.pagination; i < index * this.pagination; i++) {
                if (this.notice.length >= i + 1) {
                    this.selectedNotice.push(this.notice[i]);
                }
            }
        }
    }
}
</script>
<style scoped>
    .notice-list {
        background-color: #FFF2EA;
        /* display: flex;
        justify-content: center; */
        text-align: center;
        
        height: calc(100vh - 150px);
        width: 1000px;
        margin: 70px 30px 0;
        border-radius: 25px;
        border: 3px solid #FFCDAD;
        padding: 0;
    }

    .notices {
        width: 80%;
        background-color: white;
        margin-left: auto;
        margin-right: auto;
        font-size: 1.5rem;
    }

    .notice {
        display: flex;
        justify-content: center;
    }
    
    .index {
        width: 10%;
    }
    
    .title {
        width: 60%;
    }

    .date {
        width: 30%;
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