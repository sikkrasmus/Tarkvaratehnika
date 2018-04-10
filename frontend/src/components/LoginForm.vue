<template>
  <v-container fluid>
    <v-layout row wrap>
      <v-flex xs12 class="text-xs-center" mt-5>
        <h1>Sign in</h1>
      </v-flex>
      <v-flex xs12 sm6 offset-sm3 mt-3>
        <form @submit.prevent="login">
          <v-layout column>
            <v-flex>
              <v-text-field color="accent"
                            v-model="user.email"
                            name="email"
                            label="Email"
                            id="email"
                            type="email"
                            required></v-text-field>
            </v-flex>
            <v-flex>
              <v-text-field color="accent"
                            v-model="user.password"
                            name="password"
                            label="Password"
                            id="password"
                            type="password"
                            required></v-text-field>
            </v-flex>
            <v-flex class="text-xs-center" mt-5>
              <v-btn color="indigo accent-3" class="white--text" type="submit">Sign in</v-btn>
            </v-flex>
          </v-layout>
        </form>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
  import axios from 'axios'
  import {mapActions} from 'vuex'

  export default {
    data() {
      return {
        user: {
          email: '',
          password: ''
        },
        errors: []
      }
    },

    methods: {
      ...mapActions([
        'loginValidation',
        'savePortfolios',
        'getPortfolioCoins',
        'getPortfolioId'
      ]),
      login() {
        this.loginValidation(this.user)
          .then(() => {
            this.$router.push('/home')
            this.savePortfolios({email: this.user.email})
            this.getPortfolioId(this.$store.state.selectedPortfolio)
            this.getPortfolioCoins(this.$store.state.portfolioId)
          }).catch(e => {
          console.log(e)
          //alert("Invalid Credentials")
        })
      }
    }
  }
</script>

<style scoped>
  text {
    color: white;
  }
</style>
