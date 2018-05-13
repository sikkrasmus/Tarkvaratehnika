<template>
  <v-expansion-panel>
    <v-expansion-panel-content v-for="(value, key, index) in this.$store.state.coinData" :key="value">
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
            {{ value[1] }} {{value[0]}} ({{ (value[2] * value[1]).toFixed(4) }} {{getCurrency(value[0])}})
          </v-flex>
          <v-flex xs6 text-xs-left class="hidden-lg-and-up">
            {{ value[1] }} {{value[0]}} ({{ (value[2] * value[1]).toFixed(4) }} {{getCurrency(value[0])}})
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
        <v-flex>
          <div>
            <v-btn flat small color="primary" v-on:click="sellPressed()">Sell</v-btn>
            <v-btn flat small color="primary" v-on:click="buyPressed()">Buy</v-btn>
          </div>
        </v-flex>
        <v-card-text>
          <div v-show="sellOption === true">
            <v-form v-model="valid">
              <v-text-field
                v-model="sellAmount"
                :rules="amountRules"
                label="Sell Amount"
                required
                v-once
              ></v-text-field>
              <v-text-field
                v-model="sellPrice"
                :rules="priceRules"
                label="Sell Price"
                required
                v-once
              ></v-text-field>
              <v-btn color="success" v-on:click="sell(value[0])">Sell</v-btn>
            </v-form>
          </div>
          <div v-show="buyOption === true">
            <v-form v-model="valid">
              <v-text-field
                v-model="buyAmount"
                :rules="amountRules"
                label="Buy Amount"
                required
              ></v-text-field>
              <v-text-field
                v-model="buyPrice"
                :rules="priceRules"
                label="Buy Price"
                required
              ></v-text-field>
              <v-btn color="success" v-on:click="buy(value[0])">Buy</v-btn>
            </v-form>
          </div>
        </v-card-text>
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
    data() {
      return {
        sellOption: false,
        buyOption: false,
        coinName: '',
        shortName: '',
        price: '',
        currency: '',
        change: '',
        coins: {},
        valid: false,
        sellAmount: '',
        sellPrice: '',
        buyAmount: '',
        buyPrice: '',
        amountRules: [
          v => !!v || 'Amount is required',
        ],
        priceRules: [
          v => !!v || 'Price is required',
        ]
      }
    },

    mounted: function () {
      this.getPortfolioCoins(this.$store.state.portfolioId)
    },

    methods: {
      ...mapActions([
        'loginValidation',
        'updatePortfolios',
        'getPortfolioCoins',
        'getPortfolioId',
        'sellCoin',
        'buyCoin'
      ]),

      sellPressed: function () {
        this.sellOption = true;
        this.buyOption = false
      },
      buyPressed: function () {
        this.sellOption = false;
        this.buyOption = true
      },
      sell: function (coinShortName) {
        var requestData = {
          portfolioId: this.$store.state.portfolioId,
          shortName: coinShortName,
          amount: this.sellAmount,
          priceBought: this.sellPrice,
        };

        this.sellCoin(requestData).then(() => {
          location.reload()
        })
      },
      buy: function (coinShortName) {
        var requestData = {
          portfolioId: this.$store.state.portfolioId,
          shortName: coinShortName,
          amount: this.buyAmount,
          priceBought: this.buyPrice,
        };

        this.buyCoin(requestData).then(() => {
          location.reload()
        })
      },

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

      logData: function (value1, value2, value3) {
        console.log(value1, value2, value3)
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
