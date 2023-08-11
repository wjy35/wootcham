<template>
  <div>
    <h1>공 지 사 항</h1>
    <p>{{ notice.title }}</p>
    <p>{{ notice.content }}</p>
    <p>{{ notice.date }}</p>
    <button>수정</button>
    <button>목록</button>
  </div>
</template>

<script>
export default {
    name: 'NoticeDetail',
    data() {
        return {
            notice: {},
        }
    }
}
</script>