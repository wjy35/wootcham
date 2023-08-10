<template lang="">
    <div class='notice-list'>
        <h1>공 지 사 항</h1>
        <ol v-for='n in notice' :key=n.index>
            <li>
                <span>{{ n.index }}</span>
                <span>{{ n.title }}</span>
                <span>{{ n.date }}</span>
            </li>
        </ol>
        <div>
            <span>1</span>
            <span>2</span>
        </div>
        <button>공지 작성하기</button>
    </div>
</template>
<script>
export default {
    name: 'NoticeList',
    data() {
        return {
            notice: [],
            pageno: 1,
            totalPage: this.notice.length / 5,
            selectedNotice: [],
        }
    }
}
</script>
<style lang="">
    
</style>