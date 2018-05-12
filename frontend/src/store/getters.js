export default {

  getSelectedPortfolio(state) {
    return state.selectedPortfolio
  },

  getAllPortfolios(state){
    return state.portfolioNames;
  },

  getCoinData(state){
    return state.coinData;
  }
}
