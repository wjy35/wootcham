import LoginViewVue from "@/views/Member/LoginView.vue";
import FindPasswordViewVue from "@/views/Member/FindPasswordView.vue";
import NicknameViewVue from "@/views/Member/NicknameView.vue";
import NicknameChangeViewVue from "@/views/Member/NicknameChangeView.vue";
import PwChangeViewVue from "@/views/Member/PwChangeView.vue";
import SignupViewVue from "@/views/Member/SignupView.vue";
import WelcomeViewVue from "@/views/Member/WelcomeView.vue";
import GoodbyeViewVue from "@/views/Member/GoodbyeView.vue";
import AdminHome from '@/views/admin/AdminHome.vue'
import NoticeCreateViewVue from '@/views/admin/NoticeCreateView.vue'
import NoticeDetailViewVue from '@/views/admin/NoticeDetailView.vue'
import UserManageViewVue from '@/views/admin/UserManageView.vue'
import HomeView from "@/views/home/HomeView.vue"
import GameRoomVue from "@/views/gameroom/GameRoom.vue";
import GamePrepareVue from "@/views/gameroom/prepare/GamePrepare.vue";
// import MainGameVue from "@/views/gameroom/maingame/MainGame.vue";
import GameResultVue from "@/views/gameroom/gameresult/GameResult.vue"

import { createWebHistory, createRouter } from "vue-router";

const routes = [
  {
    path: "/",
    name: "login",
    component: LoginViewVue,
  },
  {
    path: "/welcome",
    name: "welcome", 
    component: WelcomeViewVue,
  },
  {
    path: "/goodbye",
    name: "goodbye", 
    component: GoodbyeViewVue,
  },
  {
    path: "/findpw",
    name: "findpw",
    component: FindPasswordViewVue,
  },
  {
    path: "/nickname",
    name: "nickname",
    component: NicknameViewVue,
  },
  {
    path: "/nicknamechange",
    name: "nicknamechange",
    component: NicknameChangeViewVue,
  },
  {
    path: "/pwchange",
    name: "pwchange",
    component: PwChangeViewVue,
  },
  {
    path: "/signup",
    name: "signup",
    component: SignupViewVue,
  },

  {
    path: "/home",
    name: "homeview",
    component: HomeView,
  },

  {
    path: "/gameroom",
    name: "gameroom",
    component: GameRoomVue,
  },
  {
    path: "/gameprepare",
    name: "gameprepare",
    component: GamePrepareVue,
  },
  // {
  //   path: "/maingame",
  //   name: "maingame",
  //   component: MainGameVue,
  // },
  {
    path: "/gameresult",
    name: "gameresult",
    component: GameResultVue,
  },
  {
    path: "/admin",
    name: "admin",
    component: AdminHome,
  },
  {
    path: "/admin/notice",
    name: "noticecreate",
    component: NoticeCreateViewVue,
  },
  {
    path: "/admin/notice/:id",
    name: "noticedetail",
    component: NoticeDetailViewVue,
  },
  {
    path: "/admin/user",
    name: "user",
    component: UserManageViewVue,
  },

];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;
