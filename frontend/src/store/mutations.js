import axios from 'axios'
export default {
  clearSession(state) {
    state.selectedPortfolio = null
    state.portfolios = {}
    state.portfolioNames = []
    state.username = null
    state.coinData = null
    state.portfolioId = null

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
  },

  loginValidation(state, payload) {
      state.username = payload.username
      state.sessionId = payload.sessionid
  },

  updatePortfolios(state, portfolios) {
    for (var key in portfolios){
      state.portfolioNames.push(portfolios[key])
    }
    if (state.selectedPortfolio === null && state.portfolioNames.length > 0){
      state.selectedPortfolio = portfolios[0]
    }

    for (var k in state.portfolios) {
      if (state.portfolios[k] === state.selectedPortfolio) {
        state.portfolioId = k;
      }
    }

    var requestData = {
      portfolioId: state.portfolioId
    }

    axios.post('http://localhost:8080/getPortfolioCoins', requestData)
      .then(response => {
        console.log("response data" + response.data)
        state.coinData = response.data
      })
      .catch(error => {
        console.log(error)
      })

  },

  addPortfolio(){
    this.$router.push('Home')
  },

  setPortfolioId(state, portfolioId) {
    state.portfolioId = Object.keys(state.portfolios).find(key => state.portfolios[key] === portfolioId);
  },

  getPortfolioId(state, name) {
    for (var key in state.portfolios) {
      if (state.portfolios[key] === name) {
        return state.portfolioId
      }
    }
  },

  selectPortfolio(state, portfolio) {
    state.selectedPortfolio = portfolio;
  },

  getPortfolioCoins(state, payload){
    state.coinData = payload;
  },

  saveFullPortfolioData(state, payload){
    state.portfolios = payload;
  },

  addCoinToPortfolio(state, payload){
    state.coinData.push(payload)
  }
}
