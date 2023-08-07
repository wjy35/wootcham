import router from "@/router";
import {} from "@/api/game"

const gameStore = {
  namespaced: true,
  state: {
    session: undefined,
    sessionId: null,
    myStreamManager: undefined,
    players: [],
    messageList: [],
  },
  getters: {},
  mutations: {},
  actions: {},
};

export default gameStore;
