<template>
  <v-container fluid>
    <v-layout row wrap>
      <v-flex xs12 class="text-xs-center" mt-5>
        <h1>Sign up</h1>
      </v-flex>
      <v-flex xs12 sm6 offset-sm3 mt-3>
        <v-form ref="form" v-model="valid" lazy-validation @submit.prevent="register">
          <v-layout column>
            <v-flex>
              <v-text-field :rules="emailRules"
                            color="accent"
                            v-model="user.email"
                            name="email"
                            label="Email"
                            id="email"
                            type="email"
                            required></v-text-field>
            </v-flex>
            <v-flex>
              <v-text-field :rules="passwordRules"
                            color="accent"
                            v-model="user.password"
                            name="password"
                            label="Password"
                            id="password"
                            type="password"
                            required></v-text-field>
            </v-flex>
            <v-flex>
              <v-text-field :rules="confirmRules"
                            color="accent"
                            name="confirmPassword"
                            label="Confirm Password"
                            id="confirmPassword"
                            type="password"
                            required
              ></v-text-field>
            </v-flex>
            <v-flex class="text-xs-center" mt-5>
              <v-btn :disabled="!valid" color="indigo accent-3" class="white--text" type="submit">Sign up</v-btn>
            </v-flex>
          </v-layout>
        </v-form>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
import axios from 'axios'

export default {
  data () {
    return {
      user: {
        email: '',
        password: ''
      },
      errors: [],

      valid: true,
      passwordRules: [
        v => !!v || 'Password is required',
        v => /^(?=.*\d)(?=.*[A-Z])(?!.*[^a-zA-Z0-9'!"#$%&\()*+-./:<=>?@[\\\]^_`{|}~])(.{8,99})$/.test(v) ||
          'Password must contain at least 1 capital letter, 1 number, 1 special character and be at least 8 characters long'
      ],
      emailRules: [
        v => !!v || 'E-mail is required',
        v => /^\w+([.-]?\w+)*@\w+([.-]?\w+)*(\.\w{2,3})+$/.test(v) || 'E-mail must be valid'
      ],
      confirmRules: [
        v => !!v || 'Confirm password is required',
        v => !!this.checkConfirm(v, this.user.password) || 'Confirm password must match password',
      ],

    }
  },

  methods: {
    checkConfirm: function (pass, confirm) {
      console.log(pass, confirm)
      return pass === confirm;
    },

    register: function () {
      if (this.$refs.form.validate()) {
        axios.post('http://localhost:8080/register', this.user)
          .then(response => {
            if (response.status === 201) {
              this.$router.push('Login')
            }
          })
          .catch(e => {
            alert('something went wrong, try again!')
            this.$router.push('Register')
            this.errors.push(e)
          })
      }
    }
  }
}
</script>

<style scoped>
  text {
    color: white;
  }
</style>
