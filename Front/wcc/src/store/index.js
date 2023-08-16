import { createStore } from "vuex";

import gameStore from "./modules/gameStore";

export default createStore({
  namespaced: true,
  modules: {
    gameStore,
  },
  state: {
    userNickname: "",
    profileImg: "",
    accessToken: "",
    refreshToken: "",
    isValidToken: false,
    mainStreamManager: "",
    client: "",
    keywords: {},
    notices: [],
    userList: [],
    ready: false,
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
      state.accessToken = "";
      state.refreshToken = "";
    },
    setUserNickname(state, payload) {
      state.userNickname = payload;
    },
    setNotices(state, notices) {
      state.notices = notices;
    },
    setUserList(state, userList) {
      state.userList = userList;
    },
    setProfileImg(state, payload){
      state.profileImg = payload;
    },
    setKeywords(state, payload){
      state.keywords = payload;
    },
    setReady(state) {
      state.ready = !state.ready;
    }
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
    getProfileImg(state){
      return state.profileImg;
    },
    getKeywords(state){
      return state.keywords;
    }
  },
});
