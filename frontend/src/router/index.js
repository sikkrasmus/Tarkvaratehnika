import Vue from 'vue'
import Router from 'vue-router'
import Landing from '@/components/Landing.vue'
import Login from '@/components/Login.vue'
import Register from '@/components/Register.vue'
import Home from '@/components/Home.vue'
import UserProfile from '@/components/UserProfile.vue'
Vue.use(Router)

export default new Router({
  routes: [
    {path: '/', name: 'Landing', component: Landing},
    {path: '/login', name: 'Login', component: Login},
    {path: '/register', name: 'Register', component: Register},
    {path: '/home', name: 'Home', component: Home},
    {path: '/profile', name: 'Profile', component: UserProfile}
  ]
})
