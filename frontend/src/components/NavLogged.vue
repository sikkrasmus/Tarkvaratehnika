<template>
  <div>
    <v-toolbar color="teal lighten-3" dark tabs>
      <v-toolbar-side-icon :to="'/home'"><img src='../assets/cwlogo.png' style="width: 36px; height: 36px;">
      </v-toolbar-side-icon>
      <v-toolbar-title class="hidden-md-and-down">coin<strong>Watch</strong></v-toolbar-title>
      <v-spacer></v-spacer>
      <v-toolbar-items>
        <v-btn class="white--text" flat>{{username}}</v-btn>
        <v-btn class="white--text" flat>Manage</v-btn>
        <v-btn class="white--text" flat>Log out</v-btn>
      </v-toolbar-items>
      <v-tabs centered color="teal lighten-3" slot="extension" slider-color="indigo darken-4" v-model="model">
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
        <v-container>
        <v-card flat>
          <v-card-text>{{overview}}</v-card-text>
        </v-card>
        </v-container>
      </v-tab-item>
    </v-tabs-items>
    <v-tabs-items v-model="model">
      <v-tab-item :id="'tab-2'">
        <v-container>
          <v-card flat>
            <v-card-text>{{watchlist}}</v-card-text>
          </v-card>
        </v-container>
      </v-tab-item>
    </v-tabs-items>
  </div>
</template>

<script>
export default {
  name: 'navlogged',
  data () {
    return {
      model: 'tab-1',
      overview: 'this is overview tab',
      watchlist: 'this is watchlist tab'
    }
  },
  computed: {
    username () {
      return this.$store.state.username
    }
  }
}
</script>

<style scoped>
  text {
    color: white;
  }
</style>
