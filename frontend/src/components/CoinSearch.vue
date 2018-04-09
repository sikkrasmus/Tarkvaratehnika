<template>
  <v-container>
    <input type="text" v-model="value" class="search-box" placeholder="Search here">
    <v-expansion-panel>
      <v-expansion-panel-content v-for="data in computedDatas" :key="data">
        <div slot="header" v-on:click="getCoinName(data)">
          <img v-bind:src="'/static/coins/' + getShortNameFromLongName(data) + '.png'" style="width: 25px; height: 25px; margin: 0 20px 0 0"/>
          {{data}}
        </div>
        <v-divider></v-divider>
        <v-card>
          <v-container grid-list id="dropdown-example">
            <v-layout row wrap>
              <v-flex xs12 sm12>
                <v-select
                  @input="selectExchange"
                  :items="exchanges"
                  label="Exchange"
                  target="#dropdown-example"
                ></v-select>
              </v-flex>
            </v-layout>
          </v-container>
          <v-card-text>
            <v-form @submit.prevent="addCoin">
              <v-text-field
                label="Amount"
                v-model="requestData.amount"
              ></v-text-field>
              <v-text-field
                label="Price"
                v-model="requestData.priceBought"
              ></v-text-field>
              <v-btn outline color="indigo" type="submit">Add</v-btn>
            </v-form>
          </v-card-text>
        </v-card>
        <div v-show="!haveResults" class="no-result">
          No result found !
        </div>
      </v-expansion-panel-content>
    </v-expansion-panel>
  </v-container>
</template>

<script>

  import axios from 'axios'

  export default {
    name: 'coinsearch',
    data() {
      return {
        value: '',
        haveResults: true,
        selectedExchange: '',
        coinData: {},
        shortName: '',
        requestData: {
          longName: '',
          amount: null,
          exchange: '',
          portfolioId: null,
          priceBought: null,
        },

        exchanges: ['Bittrex', 'Binance'],
        coinList: []
      }
    },
    computed: {
      computedDatas: function () {
        var result = this.coinList.filter((value) => {
          return value.toLowerCase().includes(this.value.toLowerCase());
        });
        if (this.value !== "") {
          result = result.map((value) => {
            return value.replace(this.value, this.value)
          });
        }
        this.haveResults = result.length !== 0;
        return result;
      }
    },
    mounted () {
      axios.post('http://localhost:8080/getAllCoins')
        .then(response => {
          this.coinData = response.data;
          for (var i = 0; i < response.data.length; i++){
            this.coinList.push(Object.values(response.data[i])[2])
          }
        }).error(error => {
        this.errors.push(error)

      })
    },

    methods : {
      addCoin : function () {
        this.$store.dispatch('getPortfolioId', this.$store.state.selectedPortfolio)

        this.requestData.exchange = this.selectedExchange
        this.requestData.portfolioId = this.$store.state.portfolioId

        axios.post('http://localhost:8080/addCoin', this.requestData)
          .then(response => {
            console.log(response)
          })
          .catch(error => {
            this.errors.push(error)
          })

      },

      getShortNameFromLongName: function(longName){

        for (var i = 0; i < this.coinData.length; i++) {
          if (Object.values(this.coinData[i])[2] === longName){
            return Object.values(this.coinData[i])[1]
          }
        }

      },

      selectExchange : function (value) {
        this.selectedExchange = value;
      },

      getCoinName: function (name) {
        this.requestData.longName = name;
      },

      redirectToHome() {
//       this.$router.go()
      }
    }
  }
</script>

<style scoped>
  @import url('https://fonts.googleapis.com/css?family=Montserrat');

  * {
    box-sizing: border-box;
    font-family: 'Montserrat', sans-serif;
  }

  .search-box {
    display: block;
    width: 100%;
    height: 45px;
    margin: 10px auto;
    border: none;
    outline: 0;
    box-shadow: 0px 2px 3px rgba(0, 0, 0, 0.2);
    border-radius: 4px;
    background-color: #ececec;
    padding: 0 20px;
    font-size: 13px;
  }

  .data {
    width: 100%;
    background-color: #4facfe;
    background-image: linear-gradient(45deg, #4facfe, #667eea);
    color: rgba(255, 255, 255, 0.6);
    height: 45px;
    line-height: 45px;
    margin: 5px 0 0 0;
    padding: 0 15px;
    border-radius: 4px;
    font-size: 13px;
    box-shadow: 0 2px 3px rgba(0, 0, 0, 0.3);
  }

  .result {
    font-weight: 900;
    color: rgba(255, 255, 255, 1);
  }

  .no-result {
    width: 100%;
    background-color: #ff0844;
    background-image: linear-gradient(45deg, #ff0844, #ffb199);
    color: #fff;
    height: 45px;
    line-height: 45px;
    margin: 5px 0 0 0;
    padding: 0 15px;
    border-radius: 4px;
    font-size: 13px;
    box-shadow: 0 2px 3px rgba(0, 0, 0, 0.3);
  }

  a {
    text-decoration: none;
    color: #4facfe;
    font-weight: 900;
  }
</style>
