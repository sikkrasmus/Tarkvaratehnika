<template>
  <v-app>
    <navunlogged class="white--text"></navunlogged>
    <v-container>
      <loginform></loginform>
    </v-container>
    <materialfooter class="hidden-md-and-down"></materialfooter>
  </v-app>
</template>

<script>
import Navunlogged from './NavUnLogged.vue'
import Materialfooter from './Footer.vue'
import Materialfootermin from './FooterMin.vue'
import Loginform from './LoginForm.vue'

export default {
  components: {
    Loginform,
    Materialfootermin,
    Materialfooter,
    Navunlogged},
  name: 'login'
}
</script>

<style scoped>
  text {
    color: white;
  }
</style>
