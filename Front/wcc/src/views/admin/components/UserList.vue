<template>
  <div>
    <h1>유저 조회</h1>
        <ol>
            <li v-for='u in users' :key=u.id>
                <span>{{ u.index }}</span>
                <span>{{ u.nickname }}</span>
                <span>{{ u.email }}</span>
            </li>
        </ol>
  </div>
</template>

<script>
export default {
    name: 'UserList',
    data() {
        return {
            users: [],
        }
    }
}
</script>