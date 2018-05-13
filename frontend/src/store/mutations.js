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

  loginValidation(state, payload) {
    state.username = payload.username;
    state.sessionId = payload.sessionid
  },

  updatePortfolios(state, portfolios) {
    for (var key in portfolios) {
        if (!state.portfolioNames.includes(portfolios[key])) {
          state.portfolioNames.push(portfolios[key])
        }
    }
    if (state.selectedPortfolio === null && state.portfolioNames.length > 0) {
      state.selectedPortfolio = portfolios[0]
    }

    for (var k in state.portfolios) {
      if (state.portfolios.hasOwnProperty(k)) {
        if (state.portfolios[k][0] === state.selectedPortfolio) {
          state.portfolioId = k;
        }
      }
    }
    var requestData = {
      portfolioId: state.portfolioId
    }

    axios.post('http://localhost:8080/getPortfolioCoins', requestData)
      .then(response => {
        state.coinData = response.data
      })
      .catch(error => {
        console.log(error)
      })

  },

  addPortfolio(state, portfolio){

  },

  deletePortfolio(state, portfolioName){

  },

  setPortfolioId(state, portfolioName) {
    state.portfolioId = Object.keys(state.portfolios).filter(function(key) {return state.portfolios[key][0] === portfolioName})[0];
    console.log(state.portfolios)
  },

  getPortfolioId(state, name) {
    for (var key in state.portfolios) {
      if (state.portfolios.hasOwnProperty(key)) {
        if (state.portfolios[key] === name) {
          return state.portfolioId
        }
      }
    }
  },

  selectPortfolio(state, portfolio) {
    state.selectedPortfolio = portfolio;
  },

  getPortfolioCoins(state, payload) {
    state.coinData = payload;
  },

  saveFullPortfolioData(state, payload) {
    state.portfolios = payload;
  },

  addCoinToPortfolio(state, payload) {
    state.coinData.push(payload)
  }
}
