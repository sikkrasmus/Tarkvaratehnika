import Vue from 'vue'
import Vuex from 'vuex'
import createPersistedState from 'vuex-persistedstate'
import * as localstorage from "local-storage";


Vue.use(Vuex)

export default new Vuex.Store({
  plugins: [createPersistedState()],
  strict: true,
  state: {
    logged: false,
    username: '',
  },
  mutations: {
    getUsername(state, uname) {
      this.state.username = uname
    },

    clearSession(state) {
      localstorage.clear()
      document.cookie.split(";")
        .forEach(function(c) {
          document.cookie = c.replace(/^ +/, "")
            .replace(/=.*/, "=;expires=" + new Date()
              .toUTCString() + ";path=/"); });
    },

    addCookie(state, data) {
      var maxAge = "; max-age=" + data.expirationDateInSeconds;
      document.cookie = data.name + "=" + data.value + ";" + maxAge + ";path=/";

    },
    deleteCookie(state, data) {
      document.cookie = data.name + '=; Max-Age=-99999999;';
    },
    getCookie(state, data) {
      var nameEQ = data.name + "=";
      var ca = document.cookie.split(';');
      for (var i = 0; i < ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0) === ' ') c = c.substring(1, c.length);
        if (c.indexOf(nameEQ) === 0) {
          this.username = c.substring(nameEQ.length, c.length);
          return c.substring(nameEQ.length, c.length);
        }
      }
      return null;
    }
  },
  actions: {
    addCookie: ({commit}, payload) => {
      commit('addCookie', payload)
    },
    getCookie: ({commit}, payload) => {
      commit('getCookie', payload)
    },

    clearSession: ({commit}) => {
      commit('clearSession')
    },
    deleteCookie: ({commit}, payload) => {
      commit('addCookie', payload)
    },
  },
  getters: {},
  methods: {}
})
