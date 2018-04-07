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

export default {
  components: {
    Materialfooterlogged,
    Materialfooter,
    Materialfootermin,
    Navlogged},
  name: 'home',
  data () {
    return {
    }
  }
}
</script>

<style scoped>
  text {
    color: white;
  }
</style>
