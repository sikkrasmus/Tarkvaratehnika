<template>
  <v-app>
    <navloggedwithouttabs></navloggedwithouttabs>
    <v-container>
      <v-layout row justify-center>
        <v-btn color="primary" dark @click.native.stop="dialog = true">Add portfolio</v-btn>
        <v-dialog v-model="dialog" max-width="290">
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
        errors: [],
        dialog: false
      }
    },

    methods: {
      ...mapActions([
        'selectPortfolio',
        'getPortfolioCoins',
        'updatePortfolios',
        'addPortfolio'

      ]),

      addPortf () {
        this.info.email = this.$store.state.username;
        this.addPortfolio(this.info)
        this.updatePortfolios({email: this.$store.state.username})
        this.$router.push("/home")
      }
    }
  }
</script>

<style>
</style>
