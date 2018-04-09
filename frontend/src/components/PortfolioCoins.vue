<template>
  <v-expansion-panel>
    <v-expansion-panel-content v-for="(value, key, index) in coinData">
      <div slot="header">
        <v-container grid-list-md text-xs-center>
          <v-layout row wrap>
            <v-flex xs1 class="hidden-md-and-down">
             <img v-bind:src="'frontend/src/assets/coins/' + value[0] + '.png'" style="width: 25px;"/>
            </v-flex>
            <v-flex xs3 class="hidden-lg-and-up">
            <img :src="'../assets/coins/' + value[0] + '.png'" style="width: 25px;"/> style="width: 50px; align-content: center;"/>
            </v-flex>
            <v-flex xs2 text-xs-left class="hidden-md-and-down">
            {{ key }}
            </v-flex>
            <v-flex xs3 text-xs-left class="hidden-lg-and-up">
              {{ key }}
            </v-flex>
            <v-flex xs3 text-xs-left class="hidden-md-and-down">
              {{ value[1] }} {{value[0]}} ({{ value[2] * value[1] }} {{currency}})
            </v-flex>
            <v-flex xs6 text-xs-left class="hidden-lg-and-up">
              {{ value[1] }} {{value[0]}} ({{ (value[2] * value[1]).toFixed(2) }} {{currency}})
            </v-flex>
            <v-flex xs2 text-xs-right class="hidden-md-and-down">
              {{value[2]}}
            </v-flex>
            <v-flex xs7 offset-xs3 text-xs-left class="hidden-lg-and-up">
              {{value[2]}}
            </v-flex>
            <v-flex xs1 text-xs-left class="hidden-md-and-down">
            {{currency}}
          </v-flex>
            <v-flex xs2 text-xs-right red--text class="hidden-md-and-down">
              {{value[3]}}
            </v-flex>
            <v-flex xs2 text-xs-right red--text class="hidden-lg-and-up">
              {{value[3]}}
            </v-flex>
          </v-layout>
        </v-container>
      </div>
      <v-card>
        <v-card-text class="grey lighten-3">
          Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.
        </v-card-text>
      </v-card>
    </v-expansion-panel-content>
  </v-expansion-panel>
</template>

<script>
  import axios from 'axios'
export default {
  name: 'portfoliocoins',
  data () {
    return {
      coinName: '',
      shortName: '',
      price: '',
      currency: 'BTC',
      change: '',
      coinData: null,

      requestCoins: {
        portfolioId: null
      }
    }
  },

  mounted () {
    console.log('portfolio name: ' + this.$store.state.selectedPortfolio)
    this.$store.dispatch('getPortfolioId', this.$store.state.selectedPortfolio)

    this.requestCoins.portfolioId = this.$store.state.portfolioId

    axios.post('http://localhost:8080/getPortfolioCoins', this.requestCoins)
      .then(response => {
        this.coinData = response.data;
        console.log()
      })
      .catch(error => {
        console.log("error")
        console.log(error)
      })
  },

  methods: {
    findTotalAmmount: function (amount, price) {
      return amount * price
    }
  }
}
</script>

<style>
</style>
