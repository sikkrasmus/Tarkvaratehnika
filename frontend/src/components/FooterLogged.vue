<template>
  <v-footer height="auto" class="primary" :app="app">
    <v-layout row wrap justify-center>
      <v-flex xs12 py-3 text-xs-center white--text>
        <p><img src='../assets/cwlogo.png' style="width: 30px; height: 30px; margin-left: 10px"></p>
        &copy; {{ new Date().getFullYear() }} — <strong>coinWatch</strong>
      </v-flex>
    </v-layout>
  </v-footer>
</template>

<script>
export default {
  name: 'materialfooterlogged',
  data () {
    return {
      app: true
    }
  }
}
</script>

<style>
</style>
