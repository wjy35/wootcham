<template lang="">
    <div>
        <h1>공 지 사 항</h1>
        <form>
            <input type='text' v-model='title'>
            <input type='text' v-model='content'>
            <button>작성</button>
            <button>목록</button>
        </form>
    </div>
</template>
<script>
export default {
    name: 'NoticeForm',
    data() {
        return {
            title: '',
            content: '',
        }
    }
}
</script>
<style lang="">
    
</style>