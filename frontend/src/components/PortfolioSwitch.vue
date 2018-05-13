<template>
  <v-container fluid grid-list-sm>
    <v-layout row wrap>
      <v-flex xs12 sm12 md12>
        <v-card>
          <v-toolbar extended>
            <v-layout row wrap>
              <v-flex xs12 sm12 md12>
                <div>
                  <div class="headline">
                    <v-container id="dropdown-example">
                      <v-layout row wrap>
                        <v-flex xs12 sm12 md12>
                          <v-select
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
              color="pink"
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
                  <v-card-title style="background-color: #3F51B5; color: white;" class="headline">Add coins
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
            <!--<h2>Total: 60505 USD -5%</h2>-->
            <h2>Total: {{this.$store.state.totalPrice}}</h2>
            <h2>Profit: {{this.$store.state.profit}}</h2>

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
        dialog: false,
        total: '',
        profit: ''
      }
    },

    mounted: function () {
      var dto = {
        portfolioId: this.$store.state.portfolioId
      };
      this.getTotalPrice(dto).then(() => {
        this.total = this.$store.state.totalPrice;

      });

      this.getProfit(dto).then(() => {
        this.profit = this.$store.state.profit;

      })
    },

    methods: {
      ...mapActions([
        'selectPortfolio',
        'getPortfolioCoins',
        'getPortfolioId',
        'setPortfolioId',
        'updatePortfolios',
        'getTotalPrice',
        'getProfit'
      ]),

      cancel() {
        this.dialog = false;
        this.getPortfolioCoins(this.$store.state.portfolioId)
      },

      switchPortfolio(value) {
        this.selectPortfolio(value).then(() => {
          this.updatePortfolios({email: this.$store.state.username}).then(() => {
            this.setPortfolioId(value).then(() => {
              this.getTotalPrice(this.$store.state.getSelectedPortfolio());
              this.getPortfolioCoins(this.$store.state.portfolioId)
            })
          })
        })
      }
    }

  }
</script>

<style>

</style>
