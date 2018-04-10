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
              <v-form @submit.prevent="addPortfolio">
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
      addPortfolio: function () {
        console.log(this.info)
        this.info.email = this.$store.state.username
        axios.post('http://localhost:8080/addPortfolio', this.info)
          .then(response => {
            this.$router.push('Home')
          })
          .catch(error => {
            console.log("err: " + error)
            this.errors.push(error)
          })
      }
    }
  }
</script>

<style>
</style>
