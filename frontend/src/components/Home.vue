<template>
  <v-app>
    <div>
    <navlogged class="white--text"></navlogged>
    <v-container>
      <br><br>
    </v-container>
    </div>
  </v-app>
</template>

<script>
import Navlogged from './NavLogged.vue'
import Materialfootermin from './FooterMin.vue'
import Materialfooter from './Footer.vue'
import Materialfooterlogged from './FooterLogged.vue'
import axios from 'axios'

export default {
  components: {
    Materialfooterlogged,
    Materialfooter,
    Materialfootermin,
    Navlogged},

  data () {
    return {
      info : {
        email: ''
      },
      errors: []
    }
  },
}
</script>

<style scoped>
  text {
    color: white;
  }
</style>
