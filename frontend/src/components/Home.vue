<template>
  <v-app>
    <div>
    <navlogged class="white--text"></navlogged>
    <v-container>
      <br><br>
      <template>

      </template>
    </v-container>
    <materialfootermin class="hidden-lg-and-up"></materialfootermin>
    <materialfooterlogged class="hidden-md-and-down"></materialfooterlogged>
    </div>
  </v-app>
</template>

<script>
  import Navlogged from "./NavLogged.vue";
  import Materialfootermin from "./FooterMin.vue";
  import Materialfooter from "./Footer.vue";
  import Materialfooterlogged from "./FooterLogged.vue";

  export default {
    components: {
      Materialfooterlogged,
      Materialfooter,
      Materialfootermin,
      Navlogged},
    name: 'home',
    data () {
      return {
        model: 'tab-2',
        text: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.'
      }
    }
  }
</script>

<style scoped>
  text {
    color: white;
  }
</style>
