<template lang="">
    <div class='modal1'>
        <slot/>
    </div>
</template>
<script>
export default {
    name: 'CommonModal',
}
</script>
<style scoped>
    .modal1 {
        width: 40vw;
        background-color: #F27059;
        border: 3px solid #FFF2EA;
        border-radius: 20px;
        position: fixed;
        top: 20vh;
        left: 30vw;
        max-height: 60vh;
    }
</style>