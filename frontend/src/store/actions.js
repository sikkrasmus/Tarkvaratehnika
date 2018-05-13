import axios from 'axios'

export default {
  clearSession: ({commit}) => {
    commit('clearSession')
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

  updatePortfolios: ({commit}, payload) => {
    return new Promise((resolve, reject) => {
      axios.post('http://localhost:8080/getPortfolio', payload)
        .then(response => {
          var portfolios = []
          commit('saveFullPortfolioData', response.data);
          for (var key in response.data) {
            if (response.data.hasOwnProperty(key)) {
              if (!portfolios.includes(response.data[key][0]))
                portfolios.push(response.data[key][0])
            }
          }
          commit('updatePortfolios', portfolios);
          resolve()
        }).catch(e => {
        console.log(e)
      })
    }).catch(err => {
      reject(err)
    })
  },

  deletePortfolio: ({commit}, portfolioId) => {
    var requestData = {
      portfolioId: portfolioId
    }
    return new Promise((resolve, reject) => {
      axios.post('http://localhost:8080/deletePortfolio', requestData)
        .then(response => {
          commit('deletePortfolio');
          resolve();
        })
        .catch(error => {
          console.log(error)
        })
    }).catch(error => {
      reject(error)
    })
  },


  addPortfolio: ({commit}, payload) => {
    return new Promise((resolve, reject) => {
      axios.post('http://localhost:8080/addPortfolio', payload)
        .then(response => {
          commit('addPortfolio');
          resolve();
        })
        .catch(error => {
          console.log(error)
        })
    }).catch(error => {
      reject(error)
    })

  },

  setPortfolioId: ({commit}, portfolioName) => {
    return new Promise((resolve, reject) => {
      commit('setPortfolioId', portfolioName)
      resolve()
    }).catch(error => {
      console.log(error)
    })
  },

  selectPortfolio: ({commit}, portfolio) => {
    return new Promise((resolve, reject) => {
      if (portfolio !== null) {
        commit('selectPortfolio', portfolio)
      }
      resolve()
    }).catch(error => {
      console.log(error)
    })
  },

  getPortfolioCoins: ({commit}, portfolioId) => {
    return new Promise((resolve, reject) => {
      var requestData = {
        portfolioId: portfolioId
      };
      axios.post('http://localhost:8080/getPortfolioCoins', requestData)
        .then(response => {
          commit('getPortfolioCoins', response.data);
          resolve();
        })
        .catch(error => {
          console.log(error)
        })

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
