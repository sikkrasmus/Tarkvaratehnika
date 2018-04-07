<template>
  <v-app>
    <navunlogged class="white--text"></navunlogged>
    <v-container>
      <registerform></registerform>
    </v-container>
    <materialfooter class="hidden-md-and-down"></materialfooter>
  </v-app>
</template>

<script>
import Navunlogged from './NavUnLogged.vue'
import Materialfootermin from './FooterMin.vue'
import Materialfooter from './Footer.vue'
import Registerform from './RegisterForm.vue'

export default {
  components: {
    Registerform,
    Materialfootermin,
    Materialfooter,
    Navunlogged},
  name: 'register'
}
</script>

<style scoped>
  text {
    color: white;
  }
</style>
