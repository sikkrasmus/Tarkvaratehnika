<template>
  <div>
    <v-toolbar color="primary" tabs>
      <v-toolbar-title class="hidden-md-and-down"><router-link :to="'home'"><div style="color: black">coin<strong>Watch</strong></div></router-link></v-toolbar-title>
      <v-spacer></v-spacer>
      <v-toolbar-items>
        <v-btn class="clipped-left" :to="'/home'" flat>Home</v-btn>
        <v-btn :to="'/profile'" flat>{{username}}</v-btn>
        <v-btn v-on:click="logOut" flat>Log out</v-btn>
      </v-toolbar-items>
    </v-toolbar>
  </div>
</template>

<script>
  export default {
    components: {
      name: 'navloggedwithouttabs',
    },
    data () {
      return {
        model: 'tab-1',
        overview: 'this is overview tab',
        watchlist: 'this is watchlist tab',
        username: this.$store.state.username
      }
    },

    methods: {
      logOut : function () {

        this.$store.dispatch('clearSession')
        this.$router.push('/')
      }
    }
  }
</script>

<style scoped>
  text {
    color: white;
  }
</style>
