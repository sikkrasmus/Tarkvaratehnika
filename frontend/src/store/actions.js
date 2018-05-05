import axios from 'axios'
import state from "./store";

export default {
  clearSession: ({commit}) => {
    commit('clearSession')
  },
  deleteCookie: ({commit}, payload) => {
    commit('addCookie', payload)
  },

  getPortfolioId: ({commit}, payload) => {
    commit('getPortfolioId', payload)
  },

  getDialog: ({commit}, payload) => {
    commit('getDialog', payload)
  },

  loginValidation: ({commit}, payload) => {
    return new Promise((resolve, reject) => {
      axios.post('http://localhost:8080/login', payload)
        .then(response => {
          if (response.data.username !== undefined) {
            commit('loginValidation', response.data)
            resolve()
          } else {
            alert("Invalid Credentials")
          }
        }).catch(e => {
        console.log(e)
      })
    }).catch(err => {
      reject()
    })
  },

  savePortfolios: ({commit}, payload) => {
    axios.post('http://localhost:8080/getPortfolio', payload)
      .then(response => {
        var portfolios = []
        commit('saveFullPortfolioData', response.data)
        for (var key in response.data) {
          if (response.data.hasOwnProperty(key)) {
            if (!portfolios.includes(response.data[key]))
              portfolios.push(response.data[key])
          }
        }
        commit('savePortfolios', portfolios)
      }).catch(e => {
      console.log(e)
    })
  },

  selectPortfolio: ({commit}, payload) => {
    if (payload !== null) {
      commit('selectPortfolio', payload)
    }
  },

  getPortfolioCoins: ({commit}, portfolioId) => {
    var requestData = {
      portfolioId: portfolioId
    }
    axios.post('http://localhost:8080/getPortfolioCoins', requestData)
      .then(response => {
        commit('getPortfolioCoins', response.data)
      })
      .catch(error => {
        console.log(error)
      })

  },

  addCoinToPortfolio: ({commit}, payload) => {
    axios.post('http://localhost:8080/addCoin', payload)
      .then(response => {
        commit('addCoinToPortfolio', response.data)
      })
      .catch(error => {
        console.log(error)
      })
  },

}
