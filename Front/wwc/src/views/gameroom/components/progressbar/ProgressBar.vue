<template>
    <div>
        <button v-on:click="start">start</button>
        <button v-on:click="stop">stop</button>
        <div class="progressBar">
            <div id="bar" class="innerbar"></div>
        </div>
    </div>
</template>

<script>
export default {
    data() {
        return {
            progressbarHandler: null,
            timeBegan: null,
            MAX_TIME: 60000,
        };
    },
    methods: {
        start() {
            this.timeBegan = new Date();
            let el = document.getElementById("bar");
            el.style.width = "0%";

            if (this.progressbarHandler == null) {
                this.progressbarHandler = setInterval(this.updateProgressbar, 30);
            }
        },
        stop() {
            if (this.progressbarHandler != null) {
                clearInterval(this.progressbarHandler);
                this.progressbarHandler = null;
            }
        },
        updateProgressbar() {
            var currentTime = new Date();
                // timeElapsed = new Date(
                //     this.countDownTime - (currentTime - this.timeBegan)
                // );
            let time = currentTime - this.timeBegan;
            let el = document.getElementById("bar");
            let width = (time / this.MAX_TIME) * 100 + "%";
            width = parseFloat(width).toFixed(2);
            if (width > 100) width = 100;

            //console.log("width" + width);
            let widthStr = width + "%";
            el.style.width = widthStr;
        },
    }
};
</script>
<style>
.progressBar {
    max-width: 330px;
    width: 90%;
    margin: 10px auto;
    margin-top: 100px;
    height: 8px;

    border-radius: 3px;
    background: linear-gradient(#6fa6d66c, #7db1df54);
}

.innerbar {
    max-width: 330px;
    height: 100%;
    text-align: right;
    height: 8px;
    /* same as #progressBar height if we want text middle aligned */
    width: 30%;
    border-radius: 3px;
    background: linear-gradient(#f27059, #f27059);
}
</style>