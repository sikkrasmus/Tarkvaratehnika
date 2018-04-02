<template>
  <v-toolbar class="teal lighten-3">
    <v-toolbar-side-icon :to="'/'"><img src='../assets/cwlogo.png' style="width: 36px; height: 36px;">
    </v-toolbar-side-icon>
    <v-toolbar-title>coin<strong>Watch</strong></v-toolbar-title>
    <v-spacer></v-spacer>
    <v-toolbar-items>
      <v-btn class="white--text" :to="'/login'" flat>Sign in</v-btn>
      <v-btn class="white--text" :to="'/register'" flat>Sign up</v-btn>
    </v-toolbar-items>
  </v-toolbar>
</template>

<script>
  export default {
    name: 'navunlogged',
  }
</script>

<style scoped>
  text {
    color: white;
  }
</style>
