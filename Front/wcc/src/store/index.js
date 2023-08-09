import { createStore } from "vuex";

export default createStore({
  namespaced: true,
  state: {
    userInfo: {
      id: "",
      email: "",
      password: "",
      nickname: "",
      point: "",
      money: "",
      admin: "", // 관리자: 0, 유저: 1
      suspension_day: "",
      Current_login: "",
      profile_img: "",
    },
    accessToken: null,
    refreshToken: null,
    isValidToken: false,
    cameraOn: false,
    gameReady: false,
  },

  mutations: {
    setAccessToken(state, payload) {
      state.accessToken = payload;
    },
  },

  getters: {
    getUserInfo(state) {
      return state.userInfo;
    },
    getAccessToken(state) {
      return state.accessToken;
    },
  },

  actions: {},
});
