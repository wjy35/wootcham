import { createStore } from "vuex";

import gameStore from "./modules/gameStore";

export default createStore({
  namespaced: true,
  modules: {
    gameStore,
  },
  state: {
    userNickname: "",
    accessToken: "",
    refreshToken: "",
    isValidToken: false,
    mainStreamManager: "",
    client: "",
  },
  mutations: {
    setClient(state, payload) {
      state.client = payload;
    },
    setAccessToken(state, payload) {
      console.log("setAccessToken Called................payload: ", payload);
      state.accessToken = payload;
    },
    initUserInfo(state) {
      state.userNickname = "";
    },
    setUserNickname(state, payload) {
      state.userNickname = payload;
    },
  },
  getters: {
    getUserNickname(state) {
      return state.userNickname;
    },
    getAccessToken(state) {
      console.log("setAccessToken Called................");
      return state.accessToken;
    },
    getClient(state) {
      return state.client;
    },
  },
});
