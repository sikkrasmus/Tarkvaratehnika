<template>
  <v-footer height="auto" class="" style="background-color: #304FFE" :app="app">
    <v-layout row wrap justify-center>
      <v-btn dark flat  :to="'/'">Home</v-btn>
      <v-btn dark flat  :to="'/about'">About us</v-btn>
      <v-btn dark flat  :to="'/team'">Team</v-btn>
      <v-btn dark flat  :to="'/contact'">Contact us</v-btn>
      <v-flex xs12 py-3 text-xs-center white--text>
        <p><img src='../assets/cwlogo.png' style="width: 30px; height: 30px; margin-left: 10px"></p>
        &copy; {{ new Date().getFullYear() }} — <strong>coinWatch</strong>
      </v-flex>
    </v-layout>
  </v-footer>
</template>

<script>
export default {
  name: 'materialfooter',
  data () {
    return {
      app: true
    }
  }
}
</script>

<style>
</style>
