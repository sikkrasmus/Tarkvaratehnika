<template>
  <v-expansion-panel>
    <v-expansion-panel-content v-for="(item,i) in 5" :key="i">
      <div slot="header">
        <v-container grid-list-md text-xs-center>
          <v-layout row wrap>
            <v-flex xs1 class="hidden-md-and-down">
             <img src="http://www.freelogovectors.net/wp-content/uploads/2018/02/VeChain-Logo.png" style="width: 25px;"/>
            </v-flex>
            <v-flex xs3 class="hidden-lg-and-up">
              <img src="http://www.freelogovectors.net/wp-content/uploads/2018/02/VeChain-Logo.png" style="width: 50px; align-content: center;"/>
            </v-flex>
            <v-flex xs2 text-xs-left class="hidden-md-and-down">
            {{coinName}}
            </v-flex>
            <v-flex xs3 text-xs-left class="hidden-lg-and-up">
              {{coinName}}
            </v-flex>
            <v-flex xs3 text-xs-left class="hidden-md-and-down">
              {{walletAmmount}} {{shortName}} ({{findTotalAmmount()}} {{currency}})
            </v-flex>
            <v-flex xs6 text-xs-left class="hidden-lg-and-up">
              {{walletAmmount}} {{shortName}} ({{findTotalAmmount()}} {{currency}})
            </v-flex>
            <v-flex xs2 text-xs-right class="hidden-md-and-down">
              {{price}}
            </v-flex>
            <v-flex xs7 offset-xs3 text-xs-left class="hidden-lg-and-up">
              {{price}} {{currency}}
            </v-flex>
            <v-flex xs1 text-xs-left class="hidden-md-and-down">
            {{currency}}
          </v-flex>
            <v-flex xs2 text-xs-right red--text class="hidden-md-and-down">
              {{change}}
            </v-flex>
            <v-flex xs2 text-xs-right red--text class="hidden-lg-and-up">
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
