<template>
  <div>
    <v-toolbar color="primary" dark tabs>
      <v-toolbar-side-icon :to="'/home'" class="hidden-md-and-down"><img src='../assets/cwlogo.png' style="width: 36px; height: 36px;" class="hidden-md-and-down">
      </v-toolbar-side-icon>
      <v-toolbar-title class="hidden-md-and-down">coin<strong>Watch</strong></v-toolbar-title>
      <v-spacer></v-spacer>
      <v-toolbar-items class="text-sm-center">
        <v-btn class="white--text" :to="'/profile'" flat>{{username}}</v-btn>
        <v-btn class="white--text" v-on:click="logOut" flat>Log out</v-btn>
      </v-toolbar-items>
      <v-tabs centered color="primary" slot="extension" slider-color="white" v-model="model">
        <v-tab :href="'#tab-1'">
          Overview
        </v-tab>
        <v-tab :href="'#tab-2'">
          Watchlist
        </v-tab>
      </v-tabs>
    </v-toolbar>
    <v-tabs-items v-model="model">
      <v-tab-item :id="'tab-1'">
        <v-container grid-list-sm>
          <portfolioswitch></portfolioswitch>
        </v-container>
      </v-tab-item>
    </v-tabs-items>
    <v-tabs-items v-model="model">
      <v-tab-item :id="'tab-2'">
        <v-container>
          <v-card flat>
            <v-card-text ><h2 class="text-xs-center">{{watchlist}}</h2></v-card-text>
          </v-card>
        </v-container>
      </v-tab-item>
    </v-tabs-items>
  </div>
</template>

<script>
import Portfoliocoins from './PortfolioCoins.vue'
import Portfolioswitch from "./PortfolioSwitch.vue";

export default {
  components: {
    Portfolioswitch,
    Portfoliocoins},
  name: 'navlogged',
  data () {
    return {
      model: 'tab-1',
      overview: 'this is overview tab',
      watchlist: 'Coming soon!'
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
