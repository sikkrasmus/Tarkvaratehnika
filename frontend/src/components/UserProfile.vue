<template>
  <v-app>
    <navloggedwithouttabs></navloggedwithouttabs>
    <v-container grid-list-md>
      <v-flex 12>
        <v-card style="margin-top: 20px; background: linear-gradient(to bottom, #396afc, #2948ff);"
                color="blue-grey darken-2" class="white--text">
          <v-form ref="form" v-model="valid" lazy-validation>
            <v-card-title primary-title>
              <div class="headline">
                Add new portfolio
                <v-text-field
                  color="white"
                  :rules="nameRules"
                  label="Portfolio Name"
                  v-model="info.portfolioName"
                  required
                  dark
                ></v-text-field>
              </div>
            </v-card-title>
            <v-card-text>
              <v-text-field
                label="Portfolio Description"
                color="white"
                v-model="info.description"
                dark
              ></v-text-field>
            </v-card-text>
            <v-card-actions>
              <v-btn flat dark large :disabled="!valid" v-on:click="addPortf">Add</v-btn>
            </v-card-actions>
          </v-form>
        </v-card>
      </v-flex>
      <v-layout row wrap>
        <v-flex lg6 xs12 v-for="(portfolio, id) in this.portfolios" :key="value">
          <v-card style="margin-top: 20px; background: linear-gradient(to bottom, #606c88, #3f4c6b);"
                  color="blue-grey darken-2" class="white--text">
            <v-card-title primary-title>
              <div class="headline">{{ portfolio[0] }}</div>
            </v-card-title>
            <v-card-text>
              {{ portfolio[1] }}
            </v-card-text>
            <v-card-actions>
              <v-btn flat dark v-on:click="delPortfolio(id)">Delete</v-btn>
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

      delPortfolio(portfolioId) {
        this.deletePortfolio(portfolioId).then(() => {
          this.updatePortfolios({email: this.$store.state.username})
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
                this.$router.push("/home")
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
