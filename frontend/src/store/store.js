import Vue from 'vue'
import Vuex from 'vuex'
Vue.use(Vuex)

export default new Vuex.Store({
  strict: true,
  state: {
    logged: false,
    username: 'test'
  },
  mutations: {
    getUsername (state, uname) {
      this.state.username = uname
    }
  },
  actions: {
  },
  getters: {
  }
})
