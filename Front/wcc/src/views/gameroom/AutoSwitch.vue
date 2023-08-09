<template>
  <div>
    <component :is="currentComponent" />
  </div>
</template>

<script>
import GamePrepare from './prepare/GamePrepare.vue'
import MainGame from './maingame/MainGame.vue'

export default {
  name: 'AutoSwitch',
  components: {
    GamePrepare,
    MainGame
  },
  data() {
    return {
      currentComponent: 'GamePrepare' // 처음에는 GamePrepare 컴포넌트를 보여줍니다.
    }
  },
  mounted() {
    this.switchComponentAfterDelay()
  },
  methods: {
    switchComponentAfterDelay() {
      setTimeout(() => {
        this.currentComponent = 'MainGame'
      }, 60000) // 1분 후에 컴포넌트 전환
    }
  }
}
</script>