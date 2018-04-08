<template>
  <v-container fluid grid-list-sm>
    <v-layout row wrap>
      <v-flex xs12>
        <v-card>
          <v-toolbar extended>
            <v-layout row>
              <v-flex>
                <div>
                  <div class="headline">
                    <v-container id="dropdown-example">
                      <v-layout row wrap>
                        <v-flex xs12 sm12>
                          <v-select
                            :items="portfolios"
                            :value="portfolios[0]"
                            overflow
                            target="#dropdown-example"
                          ></v-select>
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
                <v-dialog v-model="dialog" max-width="100%" class="hidden-lg-and-up">
                <v-card>
                  <v-card-title class="headline">Add new coin</v-card-title>
                  <coinsearch></coinsearch>
                  <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn color="green darken-1" flat="flat" @click.native="dialog = false">Cancel</v-btn>
                  </v-card-actions>
                </v-card>
              </v-dialog>
            </v-layout>
          </v-toolbar>
          <br><br>
            <portfoliocoins></portfoliocoins>
        </v-card>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
  import Portfoliocoins from "./PortfolioCoins.vue";
  import Coinsearch from "./CoinSearch.vue";
  export default {
    components: {
      Coinsearch,
      Portfoliocoins},
    data: () => ({
      portfolios: ['Main', 'Alts', 'Shitcoins'],
      dialog: false
    })
  }
</script>

<style>
</style>
