<template>
  <v-container fluid>
    <v-layout row wrap>
      <v-flex xs12 class="text-xs-center" mt-5>
        <h1>Sign in</h1>
      </v-flex>
      <v-flex xs12 sm6 offset-sm3 mt-3>
        <form @submit.prevent="loginValidation">
          <v-layout column>
            <v-flex>
              <v-text-field color="indigo darken-4"
                            v-model="user.email"
                            name="email"
                            label="Email"
                            id="email"
                            type="email"
                            required></v-text-field>
            </v-flex>
            <v-flex>
              <v-text-field color="indigo darken-4"
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
      loginValidation : function () {
        axios.post('http://localhost:8080/login', this.user)
          .then(response => {
            if (response.data.email !== undefined){
              this.$router.push('Home')
            } else {
              alert('Wrong user or pw!');
              this.$router.push('Login')
            }
          })
          .catch(e => {
            this.errors.push(e)
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
