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
    currentMarketPrice: null,
    sessionid:'',
    username: '',
    portfolioNames: [],
    portfolios: {},
    selectedPortfolio: null,
    portfolioId: null,
    coinData: {}
  },

  mutations,
  actions,
  getters,
  methods: {}
})
