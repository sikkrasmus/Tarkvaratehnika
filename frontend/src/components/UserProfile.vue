<template>
  <v-app>
    <navloggedwithouttabs></navloggedwithouttabs>
    <v-container grid-list-md>
      <v-flex 12>
        <br>
        <v-btn color="primary" dark @click.native.stop="dialog = true" style="width: 100%; margin: 0px">Add portfolio
        </v-btn>
        <br><br><br><br>
        <v-dialog v-model="dialog" max-width="400">
          <v-card>
            <v-card-title class="headline">Add new portfolio</v-card-title>
            <v-container>
              <v-form @submit.prevent="addPortf">
                <v-text-field
                  label="Portfolio Name"
                  v-model="info.portfolioName"
                  required
                ></v-text-field>
                <v-text-field
                  label="Portfolio Description"
                  v-model="info.description"
                  required
                ></v-text-field>
                <v-card-actions>
                  <v-spacer></v-spacer>
                  <v-btn color="green darken-1" flat="flat" @click.native="dialog = false">Cancel</v-btn>
                  <v-btn color="green darken-1" flat="flat" type="=submit">Add</v-btn>
                </v-card-actions>
              </v-form>
            </v-container>
          </v-card>
        </v-dialog>
      </v-flex>
      <v-layout row wrap>
        <v-flex 12>
          <v-card style="margin-top: 20px" color="blue-grey darken-2" class="white--text"
                  v-for="(portfolio, id) in this.portfolios" :key="value">
            <v-card-title primary-title>
              <div class="headline">{{ portfolio[0] }}</div>
            </v-card-title>
            <v-card-text>
              {{ portfolio[1] }}
            </v-card-text>
            <v-card-actions>
              <v-btn flat dark v-on:click="delPortfolio(id, portfolio[0])">Delete</v-btn>
            </v-card-actions>
          </v-card>
        </v-flex>
      </v-layout>
    </v-container>
  </v-app>
</template>


<script>
  import Navlogged from "./NavLogged.vue";
  import Navloggedwithouttabs from "./NavLoggedWithOutTabs.vue";
  import axios from 'axios';
  import {mapActions} from 'vuex'

  export default {
    components: {
      Navloggedwithouttabs,
    },

    name: 'userprofile',

    data() {
      return {
        valid: false,
        info: {
          portfolioName: '',
          description: '',
          email: ''
        },
        portfolios: {},
        errors: [],
        dialog: false
      }
    },

    mounted() {
      this.updatePortfolios({email: this.$store.state.username}).then(() => {
        this.portfolios = this.$store.state.portfolios;
      })
    },

    methods: {
      ...mapActions([
        'selectPortfolio',
        'getPortfolioCoins',
        'updatePortfolios',
        'addPortfolio',
        'setPortfolioId',
        'deletePortfolio'

      ]),

      delPortfolio(portfolioId, portfolioName) {
        var reqData = {
          portfolioId: portfolioId,
          portfolioName: portfolioName
        }
        this.deletePortfolio(reqData).then(() => {
          location.reload()
        })
      },

      addPortf() {
        this.info.email = this.$store.state.username;
        this.addPortfolio(this.info).then(() => {
          this.updatePortfolios({email: this.$store.state.username}).then(() => {
            this.selectPortfolio(this.info.portfolioName).then(() => {
              this.setPortfolioId(this.$store.state.selectedPortfolio).then(() => {
                this.getPortfolioCoins(this.$store.state.portfolioId).then(() => {
                  this.$router.push("/home")
                })
              });
            })
          })
        })

      }
    }
  }
</script>

<style>
</style>
