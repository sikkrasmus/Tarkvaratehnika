<template>
  <v-app>
    <div>
      <navunlogged class="white--text"></navunlogged>
      <v-container fluid grid-list-xs><landingtext></landingtext></v-container>
      <br><br>
      <materialfooter class="hidden-md-and-down"></materialfooter>
    </div>
  </v-app>
</template>

<script>
import Navunlogged from './NavUnLogged.vue'
import Materialfooter from './Footer.vue'
import Landingtext from './Landingtext.vue'
import Materialfootermin from './FooterMin.vue'

export default {
  components: {
    Materialfootermin,
    Landingtext,
    Materialfooter,
    Navunlogged},
  name: 'landing',
  data () {
    return {
    }
  }
}
</script>

<style>
  text {
    color: white;
  }
</style>
