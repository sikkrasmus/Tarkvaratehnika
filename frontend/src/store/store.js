import Vue from 'vue'
import Vuex from 'vuex'
import createPersistedState from 'vuex-persistedstate'

import axios from 'axios'

import actions from './actions';
import mutations from './mutations';
import getters from './getters';

Vue.use(Vuex, axios)

export default new Vuex.Store({
  plugins: [createPersistedState()],
  strict: false,
  state: {
    sessionid:'',
    username: '',
    portfolioNames: [],
    portfolios: {},
    selectedPortfolio: null,
    portfolioId: null,
    coinData: {},
    totalPrice: '',
    profit: ''
  },

  mutations,
  actions,
  getters,
  methods: {}
})
