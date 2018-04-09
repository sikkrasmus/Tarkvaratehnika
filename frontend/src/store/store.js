import Vue from 'vue'
import Vuex from 'vuex'
import createPersistedState from 'vuex-persistedstate'
import * as localstorage from "local-storage";


Vue.use(Vuex)

export default new Vuex.Store({
  plugins: [createPersistedState()],
  strict: true,
  state: {
    dialog: true,
    logged: false,
    username: '',
    portfolioNames: [],
    portfolios: {},
    selectedPortfolio: null,
    portfolioId: null
  },
  mutations: {
    getPortfolioId(state, name){
      for(var key in state.portfolios) {
        if(state.portfolios[key] === name) {
          state.portfolioId = key
          return
        }
      }
    },

    selectPortfolio(state, portfolio){
      state.selectedPortfolio = portfolio.name
    },

    savePortfolios(state, data) {
      for (var key in data){
        if (data.hasOwnProperty(key)){
          if (!state.portfolioNames.includes(data[key])){
            state.portfolioNames.push(data[key])
          }
        }
      }
      state.portfolios = data
    },

    getUsername(state, uname) {
      state.username = uname
    },

    getDialog(state, value){
      state.dialog = value
    },

    clearSession(state) {
      state.selectedPortfolio = null
      state.portfolios = {}
      state.portfolioNames = []
      localstorage.clear()
      document.cookie.split(";")
        .forEach(function (c) {
          document.cookie = c.replace(/^ +/, "")
            .replace(/=.*/, "=;expires=" + new Date()
              .toUTCString() + ";path=/");
        });
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

    selectPortfolio: ({commit}, payload) => {
      commit('selectPortfolio', payload)
    },

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
    savePortfolios: ({commit}, payload) => {
      commit('savePortfolios', payload)
    },

    getPortfolioId: ({commit}, payload) => {
      commit('getPortfolioId', payload)
    },

    getDialog: ({commit}, payload) => {
      commit('getDialog', payload)
    }
  },
  getters: {},
  methods: {}
})
