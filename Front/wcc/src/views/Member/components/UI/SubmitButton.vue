<template>
    <button class="submitButton">{{ value }}</button>
</template>
<script>
export default {
    name: "SubmitButton",
    props: {
        value: String,
    }
}
</script>
<style scoped>
    .submitButton {
        font-family: 'Black Han Sans', sans-serif;
        width: 100%;
        background-color: #FF7926;
        color: white;
        font-size: 2rem; 
        border: none;
        border-radius: 1rem;
        box-shadow: 2px 2px 2px gray;
        margin-top: 1rem;
        margin-bottom: 1rem;
        padding: 0.5rem;
    }

    .submitButton:hover {
        box-shadow: 5px 5px 5px black;
        cursor: pointer;
    }
</style>