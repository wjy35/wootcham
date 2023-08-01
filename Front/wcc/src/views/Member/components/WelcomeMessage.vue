<template>
    <div>
        <p>{{ nickname }}님,</p>
        <p v-if="isSignup">웃참클럽에 오신 것을</p>
        <p>환영합니다.</p>
        <p v-if="isLoading">로딩중...</p>
    </div>
</template>
<script>
export default {
    name: "WelcomeMessage",
    props: {
        nickname: String,
        isSignup: Boolean,
    }, 
    data() {
        return {
            isLoading: true,
        }
    },
    mounted() {
        setTimeout(() => {
            this.isLoading = false;
        }, 5000);
    }
}
</script>
<style scoped>
    
</style>