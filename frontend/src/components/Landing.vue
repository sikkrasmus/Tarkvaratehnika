<template>
  <v-app>
    <div>
      <navunlogged class="white--text"></navunlogged>
      <br>
      <br>
      <v-container><landingtext></landingtext></v-container>
      <br><br>
      <materialfooter class="hidden-md-and-down"></materialfooter>
      <materialfootermin class="hidden-lg-and-up"></materialfootermin>
    </div>
  </v-app>
</template>

<script>
import Navunlogged from "./NavUnLogged.vue";
import Materialfooter from "./Footer.vue";
import Landingtext from "./Landingtext.vue";
import Materialfootermin from "./FooterMin.vue";

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
