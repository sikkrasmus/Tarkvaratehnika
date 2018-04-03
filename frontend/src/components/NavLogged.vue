<template>
  <div>
    <v-toolbar color="teal lighten-3" dark tabs>
      <v-toolbar-side-icon :to="'/home'"><img src='../assets/cwlogo.png' style="width: 36px; height: 36px;">
      </v-toolbar-side-icon>
      <v-toolbar-title>coin<strong>Watch</strong></v-toolbar-title>
      <v-spacer></v-spacer>
      <v-toolbar-items>
        <v-btn class="white--text" flat>{{username}}</v-btn>
        <v-btn class="white--text" flat>Manage</v-btn>
        <v-btn class="white--text" flat>Log out</v-btn>
      </v-toolbar-items>
      <v-tabs centered color="teal lighten-3" slot="extension" slider-color="indigo darken-4" v-model="model">
        <v-tab
          v-for="i in 3"
          :key="i"
          :href="`#tab-${i}`"
        >
          Item {{ i }}
        </v-tab>
      </v-tabs>
    </v-toolbar>
    <v-tabs-items v-model="model">
      <v-tab-item v-for="i in 3" :key="i" :id="`tab-${i}`">
        <v-container>
        <v-card flat>
          <v-card-text v-text="text"></v-card-text>
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
      model: 'tab-2',
      text: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.'
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
