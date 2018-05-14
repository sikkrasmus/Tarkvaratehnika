<template>
  <v-app>
    <navloggedwithouttabs></navloggedwithouttabs>
    <v-container grid-list-md>
      <v-flex 12>
        <br>
        <v-card style="background-color: white;"
                color="white" class="">
          <v-form ref="form" v-model="valid" lazy-validation>
            <v-card-title primary-title>
              <div class="headline" style="color: #304FFE">
                <strong>Add a new portfolio</strong>
                <v-text-field
                  color="indigo accent-4"
                  :rules="nameRules"
                  label="Portfolio Name"
                  v-model="info.portfolioName"
                  required
                ></v-text-field>
              </div>
            </v-card-title>
            <v-card-text>
              <v-text-field
                label="Portfolio Description"
                color="indigo accent-4"
                v-model="info.description"
              ></v-text-field>
            </v-card-text>
            <v-card-actions>
              <v-btn outline large :disabled="!valid" v-on:click="addPortf" style="color: #304FFE">Add</v-btn>
            </v-card-actions>
          </v-form>
        </v-card>
      </v-flex>
      <v-layout row wrap>
        <v-flex lg6 xs12 v-for="(portfolio, id) in this.portfolios" :key="value">
          <v-card style="margin-top: 20px; background: linear-gradient(to top, #304FFE, #3b58f9);"
                  color="blue-grey darken-2" class="white--text">
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
        valid: true,
        info: {
          portfolioName: '',
          description: '',
          email: ''
        },
        portfolios: {},
        errors: [],
        dialog: false,
        nameRules: [
          v => !!v || 'Name is required',
          v => (v && v.length <= 10) || 'Name must be less than 10 characters'
        ],
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
        if (this.$refs.form.validate()) {
          this.info.email = this.$store.state.username;
          this.addPortfolio(this.info).then(() => {
            this.updatePortfolios({email: this.$store.state.username}).then(() => {
              this.selectPortfolio(this.info.portfolioName).then(() => {
                this.setPortfolioId(this.$store.state.selectedPortfolio);
                this.getPortfolioCoins(this.$store.state.portfolioId).then(() => {
                  this.$router.push("/home")
                })
              })
            })
          })
        }
      }
    }
  }
</script>

<style>
</style>
