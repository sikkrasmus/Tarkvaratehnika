<template>
  <v-toolbar class="primary">
    <v-toolbar-title class="hidden-md-and-down"><router-link :to="'/'"><div style="color: black">coin<strong>Watch</strong></div></router-link></v-toolbar-title>
    <v-toolbar-title>coin<strong>Watch</strong></v-toolbar-title>
    <v-spacer></v-spacer>
    <v-toolbar-items>
      <v-btn :to="'/login'" flat>Sign in</v-btn>
      <v-btn :to="'/register'" flat>Sign up</v-btn>
    </v-toolbar-items>
  </v-toolbar>
</template>

<script>
export default {
  name: 'navunlogged'
}
</script>

<style scoped>
  text {
    color: white;
  }
</style>
