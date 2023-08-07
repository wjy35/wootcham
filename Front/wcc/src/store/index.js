import Vue from "vue";
import Vuex from "vuex";
import createPersistedState from "vuex-persistedstate";
// module import
import memberStore from "@/store/modules/memberStore";
import gameStore from "@/store/modules/gameStore"

Vue.use(Vuex);

export default new Vuex.Store({
  modules: {
    memberStore, gameStore
  },
  plugins: [
    createPersistedState({
      // 브라우저 종료시 제거하기 위해 localStorage가 아닌 sessionStorage로 변경. (default: localStorage)
      storage: sessionStorage,
    }),
  ],
});
