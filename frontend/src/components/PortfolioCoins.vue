<template>
  <v-expansion-panel>
    <v-expansion-panel-content v-for="(item,i) in 5" :key="i" class="hidden-sm-and-down">
      <div slot="header">
        <v-container grid-list-md text-xs-center>
          <v-layout row wrap>
            <v-flex xs1>
             <img src="http://www.freelogovectors.net/wp-content/uploads/2018/02/VeChain-Logo.png" style="width: 25px;"/>
            </v-flex>
            <v-flex xs2 text-xs-left>
            {{coinName}}
            </v-flex>
            <v-flex xs3 text-xs-left>
              {{walletAmmount}} {{shortName}} ({{findTotalAmmount()}} {{currency}})
            </v-flex>
            <v-flex xs2 text-xs-right>
              {{price}}
            </v-flex>
            <v-flex xs1 text-xs-left>
              {{currency}}
            </v-flex>
            <v-flex xs2 text-xs-right red--text>
              {{change}}
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
    <v-expansion-panel-content v-for="(item,i) in 5" :key="i" class="hidden-md-and-up">
      <div slot="header">
        <v-container grid-list-md text-xs-center>
          <v-layout row wrap>
            <v-flex>
              <img src="http://www.freelogovectors.net/wp-content/uploads/2018/02/VeChain-Logo.png" style="width: 25px;"/>
            </v-flex>
            <v-flex xs3 text-xs-left>
              {{coinName}}
            </v-flex>
            <v-flex xs7 text-xs-left>
            </v-flex>
            <v-flex xs2 text-xs-left>
            </v-flex>
            <v-flex xs4 text-xs-right>
              {{walletAmmount}} {{shortName}} ({{findTotalAmmount()}} {{currency}})
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
export default {
  name: 'portfoliocoins',
  data () {
    return {
      coinName: 'VeChain',
      shortName: 'VEN',
      walletAmmount: 500,
      price: 0.00035231,
      currency: 'BTC',
      change: '-0.78%'
    }
  },
  methods: {
    findTotalAmmount: function () {
      return this.walletAmmount * this.price
    }
  }
}
</script>

<style>
</style>
