import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '@/Login.vue' 
import AdminHome from '@/views/admin/components/AdminHome.vue' 


Vue.use(VueRouter)

const routes = [
  { path: '/', component: Login }, // 기본 경로
  { path: '/admin', component: AdminHome } // 관리자 페이지로 이동
]

const router = new VueRouter({
  routes
})

export default router