<template>
  <div>
    <v-container>
      <v-form @submit.prevent="addCoin">
        <v-layout row wrap>
          <v-flex xs12 sm12>
            <v-select
              :items="coinList"
              :filter="customFilter"
              v-model="requestData.longName"
              item-text="requestData.longName"
              label="Coin name"
              autocomplete
            ></v-select>
          </v-flex>
          <v-flex xs12 sm12>
            <v-select
              @input="selectExchange()"
              :items="exchanges"
              label="Exchange"
              target="#dropdown-example"
            ></v-select>
          </v-flex>
        </v-layout>
        <v-text-field
          label="Amount"
          v-model="requestData.amount"
        ></v-text-field>
        <v-text-field
          label="Price"
          v-model="requestData.priceBought"
        ></v-text-field>
        <v-btn color="green accent-3" type="submit" style="width: 100%; color: indigo; margin: 0 ">Add</v-btn>
      </v-form>
    </v-container>
  </div>
</template>

<script>

  import axios from 'axios'
  import {mapActions} from 'vuex'

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
        a1: null,
        customFilter(item, queryText, itemText) {
          const hasValue = val => val != null ? val : ''
          const text = hasValue(item)
          const query = hasValue(queryText)
          return text.toString()
            .toLowerCase()
            .indexOf(query.toString().toLowerCase()) > -1
        },
        exchanges: ['Bittrex', 'Binance'],
        coinList: []
      }
    },
    mounted() {
      axios.post('http://localhost:8080/getAllCoins')
        .then(response => {
          this.coinData = response.data;
          for (var i = 0; i < response.data.length; i++) {
            this.coinList.push(Object.values(response.data[i])[2])
          }
        }).error(error => {
        this.errors.push(error)

      })
    },

    methods: {
      ...mapActions([
        'addCoinToPortfolio',
        'getPortfolioCoins',
      ]),

      addCoin() {
        this.requestData.portfolioId = this.$store.state.portfolioId;
        this.addCoinToPortfolio(this.requestData).then(() => {
          alert('coin added');
          location.reload();
        })
      },

    },
    selectExchange: function (value) {
      this.requestData.exchange = value;
    }
  }
</script>

<style scoped>
  @import url('https://fonts.googleapis.com/css?family=Montserrat');

  * {
    box-sizing: border-box;
    font-family: 'Montserrat', sans-serif;
  }
</style>
