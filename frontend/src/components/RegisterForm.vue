<template>
  <v-container fluid>
    <v-layout row wrap>
      <v-flex xs12 class="text-xs-center" mt-5>
        <h1>Sign up</h1>
      </v-flex>
      <v-flex xs12 sm6 offset-sm3 mt-3>
        <form @submit.prevent="register">
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
            <v-flex>
              <v-text-field color="indigo darken-4"
                            name="confirmPassword"
                            label="Confirm Password"
                            id="confirmPassword"
                            type="password"
                            required
              ></v-text-field>
            </v-flex>
            <v-flex class="text-xs-center" mt-5>
              <v-btn color="indigo accent-3" class="white--text" type="submit">Sign up</v-btn>
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
      register : function () {
        axios.post('http://localhost:8080/register', this.user)
          .then(response => {
            if (response.status === 201){
              this.$router.push('Login')
            }
          })
          .catch(e => {
            alert('something went wrong, try again!');
            this.$router.push('Register')
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
