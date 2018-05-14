<template>
  <v-container fluid grid-list-sm>
    <v-layout row wrap>
      <v-flex xs12 sm12 md12>
        <v-card>
          <v-toolbar extended>
            <v-layout row wrap>
              <v-flex xs12 sm12 md12>
                <div>
                  <div>
                    <v-container id="dropdown-example">
                      <v-layout row wrap>
                        <v-flex xs12 sm12 md12>
                          <v-select
                            :hide-selected="this.hideSelected"
                            :label="this.$store.state.portfolioNames[0]"
                            @input="switchPortfolio"
                            :items="this.$store.state.portfolioNames"
                            :value="this.$store.state.selectedPortfolio"
                            overflow
                            target="#dropdown-example">
                          </v-select>
                        </v-flex>
                      </v-layout>
                    </v-container>
                  </div>
                </div>
              </v-flex>
            </v-layout>
            <v-btn
              color="indigo accent-4"
              dark
              medium
              absolute
              bottom
              right
              fab
              dark @click.native.stop="dialog = true">
              <v-icon>add</v-icon>
            </v-btn>
            <v-layout row justify-center>
              <v-dialog v-model="dialog" max-width="600px" class="hidden-lg-and-up">
                <v-card>
                  <v-card-title style="background-color: #3b58f9; color: white;" class="headline">Add coins
                    <v-spacer></v-spacer>
                    <v-btn color="white" flat="flat" @click.native="cancel()">Cancel</v-btn>
                  </v-card-title>
                  <v-card-actions>
                  </v-card-actions>
                  <coinsearch></coinsearch>
                </v-card>
              </v-dialog>
            </v-layout>
          </v-toolbar>
          <br><br>
          <v-container>
            <div class="text-xs-center">
              <h1 v-bind:style="{ color: getPercentColor(calculatePercent().toString()) }">{{calculatePercent()}}%</h1>
              <h2>Total: {{this.roundTotal()}} USD </h2>
              <h2>Profit: {{this.roundProfit()}} USD</h2>
            </div>
          </v-container>
          <usertotalchart></usertotalchart>
          <portfoliocoins></portfoliocoins>
        </v-card>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
  import Portfoliocoins from "./PortfolioCoins.vue";
  import Coinsearch from "./CoinSearch.vue";
  import Usertotalchart from "./UserTotalChart.vue";
  import {mapActions} from 'vuex'

  export default {
    components: {
      Usertotalchart,
      Coinsearch,
      Portfoliocoins
    },

    data() {
      return {
        hideSelected: true,
        dialog: false,
        total: '',
        profit: ''
      }
    },

    mounted: function () {

    },

    methods: {
      ...mapActions([
        'selectPortfolio',
        'getPortfolioCoins',
        'getPortfolioId',
        'setPortfolioId',
        'updatePortfolios',
        'getTotalPrice',
        'getProfit',
        'getGraphData'
      ]),
      roundTotal: function () {
        return Math.round(this.$store.state.totalPrice * 100) / 100
      },
      roundProfit: function () {
        return Math.round(this.$store.state.profit * 100) / 100
      },
      calculatePercent: function () {
        let percent = (((this.$store.state.totalPrice) - (this.$store.state.totalPrice - this.$store.state.profit)) / (this.$store.state.totalPrice - this.$store.state.profit)) * 100;
        if (isNaN(percent)) {
          return 0
        } else {
          return Math.round(percent * 100) / 100
        }
      },
      cancel() {
        this.dialog = false;
        this.getPortfolioCoins(this.$store.state.portfolioId)
      },
      getPercentColor(number) {
        if (number.charAt(0) === "-") {
          return "#D50000"
        } else {
          return "#00C853"
        }
      },
      switchPortfolio(value) {
        this.selectPortfolio(value).then(() => {
          this.updatePortfolios({email: this.$store.state.username}).then(() => {
            this.setPortfolioId(value).then(() => {
              this.getPortfolioCoins(this.$store.state.portfolioId).then(() => {
                this.getGraphData(this.$store.state.portfolioId);
                this.getTotalPrice(this.$store.state.portfolioId);
                this.getProfit(this.$store.state.portfolioId)
              })
            })
          })
        })
      }
    }

  }
</script>

<style>
  .primary--text {
    color: #304FFE !important;
  }
</style>
