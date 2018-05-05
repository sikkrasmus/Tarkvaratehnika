<template>
  <v-expansion-panel>
    <v-expansion-panel-content v-for="(value, key, index) in this.$store.state.coinData " :key="value">
      <div slot="header">
        <v-layout row wrap>
          <v-flex xs1 class="hidden-md-and-down">
            <img v-bind:src="'/static/coins/' + value[0] + '.png'" style="width: 25px;"/>
          </v-flex>
          <v-flex xs3 class="hidden-lg-and-up">
            <img v-bind:src="'/static/coins/' + value[0] + '.png'" style="width: 50px;"/>
          </v-flex>
          <v-flex xs2 text-xs-left class="hidden-md-and-down">
            {{ key }}
          </v-flex>
          <v-flex xs3 text-xs-left class="hidden-lg-and-up">
            {{ key }}
          </v-flex>
          <v-flex xs3 text-xs-left class="hidden-md-and-down">
            {{ value[1] }} {{value[0]}} ({{ value[2] * value[1] }} {{getCurrency(value[0])}})
          </v-flex>
          <v-flex xs6 text-xs-left class="hidden-lg-and-up">
            {{ value[1] }} {{value[0]}} ({{ (value[2] * value[1]).toFixed(2) }} {{getCurrency(value[0])}})
          </v-flex>
          <v-flex xs2 text-xs-right class="hidden-md-and-down">
            {{value[2]}}
          </v-flex>
          <v-flex xs7 offset-xs3 text-xs-left class="hidden-lg-and-up">
            {{value[2]}}
          </v-flex>
          <v-flex xs1 text-xs-left class="hidden-md-and-down">
            {{getCurrency(value[0])}}
          </v-flex>
          <v-flex xs2 text-xs-right class="hidden-md-and-down" v-bind:style="{ color: getPercentColor(value[3]) }">
            {{value[3]}}
          </v-flex>
          <v-flex xs2 text-xs-right class="hidden-lg-and-up" v-bind:style="{ color: getPercentColor(value[3]) }">
            {{value[3]}}
          </v-flex>
        </v-layout>
      </div>
      <v-card>
        <v-card-text>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.</v-card-text>
      </v-card>
    </v-expansion-panel-content>
  </v-expansion-panel>
</template>

<script>
  import axios from 'axios'
  import {mapActions} from 'vuex'
  import {mapGetters} from 'vuex'
export default {
  name: 'portfoliocoins',
  data () {
    return {
      coinName: '',
      shortName: '',
      price: '',
      currency: '',
      change: '',
      coins: {},
    }
  },

  mounted: function() {
    this.getPortfolioCoins(this.$store.state.portfolioId)
    this.coins = this.$store.state.coinData;

  },

  methods: {
    ...mapActions([
      'loginValidation',
      'updatePortfolios',
      'getPortfolioCoins',
      'getPortfolioId'
    ]),

    findTotalAmmount: function (amount, price) {
      return amount * price
    },

    getCurrency: function (shortName) {
      // console.log(shortName)
      if (shortName === "BTC") {
        return "USD"
      } else {
        return "BTC"
      }
    },

    getPercentColor(number) {
      if (number.charAt(0) === "-") {
        return "red"
      } else {
        return "green"
      }
    }
  }
}
</script>

<style>
</style>
