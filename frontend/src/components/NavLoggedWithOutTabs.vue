<template>
  <div>
    <v-toolbar color="primary" dark tabs>
      <v-toolbar-side-icon :to="'/home'" class="hidden-md-and-down"><img src='../assets/cwlogo.png' style="width: 36px; height: 36px;" class="hidden-md-and-down">
      </v-toolbar-side-icon>
      <v-toolbar-title class="hidden-md-and-down">coin<strong>Watch</strong></v-toolbar-title>
      <v-spacer></v-spacer>
      <v-toolbar-items>
        <v-btn class="white--text clipped-left" :to="'/home'" flat>Home</v-btn>
        <v-btn class="white--text" :to="'/profile'" flat>{{username}}</v-btn>
        <v-btn class="white--text" v-on:click="logOut" flat>Log out</v-btn>
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
        watchlist: 'this is watchlist tab'
      }
    },
    computed: {
      username () {
        this.$store.dispatch('getCookie', {
          name: 'username',
        })
        return this.$store.state.username
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
