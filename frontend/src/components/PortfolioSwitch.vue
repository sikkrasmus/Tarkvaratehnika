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
                            :value="selectedPortfolio"
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
      }
    },

    methods: {
      ...mapActions([
        'selectPortfolio',
        'getPortfolioCoins',
        'getPortfolioId',
        'setPortfolioId'

      ]),

      cancel() {
        this.dialog = false;
        this.getPortfolioCoins(this.$store.state.portfolioId)
      },

      switchPortfolio(value) {
        console.log(value + "id: " + this.$store.state.portfolioId)
        this.selectPortfolio(value)
        this.setPortfolioId(value);
        this.getPortfolioCoins(this.$store.state.portfolioId)
      }
    }

  }
</script>

<style>

</style>
