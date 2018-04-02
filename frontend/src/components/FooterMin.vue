<template>
  <v-footer height="auto" class="indigo darken-4" :app="true">
    <v-layout row wrap justify-center>
      <v-flex xs12 py-3 text-xs-center white--text>
        &copy; {{ new Date().getFullYear() }} — <strong>coinWatch</strong>
      </v-flex>
    </v-layout>
  </v-footer>
</template>

<script>
  export default {
    name: 'materialfootermin',
    data () {
      return {
        app: true,
      }
    }
  }
</script>

<style>
</style>
