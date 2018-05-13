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

  deletePortfolio: ({commit}, payload) => {
    var requestData = {
      portfolioId: payload.portfolioId
    };
    return new Promise((resolve, reject) => {
      axios.post('http://localhost:8080/deletePortfolio', requestData)
        .then(response => {
          commit('deletePortfolio', payload.portfolioName);
          resolve();
        })
        .catch(error => {
          console.log(error)
        })
    }).catch(error => {
      reject(error)
    })
  },

  sellCoin: ({commit}, payload) => {
    return new Promise((resolve, reject) => {
      axios.post('http://localhost:8080/sellCoin', payload)
        .then(response => {
          resolve();
        })
        .catch(error => {
          console.log(error)
        })
    }).catch(error => {
      reject(error)
    })
  },

  buyCoin: ({commit}, payload) => {
    return new Promise((resolve, reject) => {
      axios.post('http://localhost:8080/buyCoin', payload)
        .then(response => {
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

  getGraphData: ({commit}, portfolioId) => {
    var chart = AmCharts.makeChart("chartdiv", {
      "type": "serial",
      "theme": "light",
      "marginTop": 0,
      "marginRight": 20,
      "dataProvider": [],
      "valueAxes": [{
        "axisAlpha": 0,
        "position": "left"
      }],
      "graphs": [{
        "id": "g1",
        "balloonText": "[[category]]<br><b><span style='font-size:14px;'>[[value]]</span></b>",
        "bullet": "round",
        "bulletSize": 4,
        "lineColor": "#304FFE",
        "lineThickness": 3,
        "negativeLineColor": "#637bb6",
        "type": "smoothedLine",
        "valueField": "value"
      }],
      "chartScrollbar": {
        "graph": "g1",
        "gridAlpha": 0,
        "color": "#888888",
        "scrollbarHeight": 55,
        "backgroundAlpha": 0,
        "selectedBackgroundAlpha": 0.1,
        "selectedBackgroundColor": "#888888",
        "graphFillAlpha": 0,
        "autoGridCount": true,
        "selectedGraphFillAlpha": 0,
        "graphLineAlpha": 0.2,
        "graphLineColor": "#304FFE",
        "selectedGraphLineColor": "#888888",
        "selectedGraphLineAlpha": 1

      },
      "chartCursor": {
        "categoryBalloonDateFormat": "DD",
        "color": "#FFFFFF",
        "cursorColor": "#E91E63",
        "cursorAlpha": 0,
        "valueLineEnabled": true,
        "valueLineBalloonEnabled": true,
        "valueLineAlpha": 0.5,
        "fullWidth": true
      },
      "dataDateFormat": "YYYY-MM-DD JJ-NN-SS",
      "categoryField": "year",
      "categoryAxis": {
        "gridPosition": "start",
        "minPeriod": "DD",
        "parseDates": true,
        "minorGridAlpha": 0.1,
        "minorGridEnabled": true
      },
      "export": {
        "enabled": true
      },
    });
    return new Promise((resolve, reject) => {
      var requestData = {
        portfolioId: portfolioId
      };
      axios.post('http://localhost:8080/getGraphData', requestData)
        .then(response => {
          for (var key in response.data) {
            if (response.data.hasOwnProperty(key)) {
              var obj = {
                year: key,
                value: response.data[key]
              }
            }
            chart.dataProvider.push(obj)
          }
          chart.validateData();

          chart.addListener("rendered", zoomChart);
          if (chart.zoomChart) {
            chart.zoomChart();
          }

          function zoomChart() {
            chart.addListener("rendered", zoomChart);
            chart.tapToActivate = false
            chart.zoomToIndexes(Math.round(chart.dataProvider.length * 0.4), Math.round(chart.dataProvider.length * 0.55));
          }

          commit('getGraphData', chart.dataProvider);
          resolve()
        })
        .catch(error => {
          console.log(error)
        })
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

  getTotalPrice: ({commit}, portfolioId) => {
    var requestData = {
      portfolioId: portfolioId
    };
    axios.post('http://localhost:8080/getTotal', requestData)
      .then(response => {
        commit('getTotalPrice', response.data)
      })
      .catch(error => {
        console.log(error)
      })
  },

  getProfit: ({commit}, portfolioId) => {
    var requestData = {
      portfolioId: portfolioId
    };
    axios.post('http://localhost:8080/getProfit', requestData)
      .then(response => {
        commit('getProfit', response.data)
      })
      .catch(error => {
        console.log(error)
      })
  },
  getMarketPrice: ({commit}, payload) => {
    return new Promise((resolve, reject) => {
      axios.post('http://localhost:8080/getMarketPrice', payload)
        .then(response => {
          commit('getMarketPrice', response.data)
          resolve()
        })
        .catch(error => {
          console.log(error)
        })
    }).catch(error => {
      reject(error);
    })
  }
}
