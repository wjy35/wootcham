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
<<<<<<< HEAD

=======
    keywords: {},
>>>>>>> 99a3308e3436434ca03c148dbcd1a863d2e2ad27
    notices: [],
    userList: [],

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
<<<<<<< HEAD
    setUserPoint(state, payload){
      state.userInfo.point = payload;
    },
=======
>>>>>>> 99a3308e3436434ca03c148dbcd1a863d2e2ad27
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
