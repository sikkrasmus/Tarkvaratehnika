<template>
  <v-app>
  <navloggedwithouttabs class="white--text"></navloggedwithouttabs>
  <v-container>
    <v-layout row justify-center>
      <v-btn color="primary" dark @click.native.stop="dialog = true">Add portfolio</v-btn>
      <v-dialog v-model="dialog" max-width="290">
        <v-card>
          <v-card-title class="headline">Add new portfolio</v-card-title>
            <v-container>
              <v-form v-model="valid">
                <v-text-field
                  label="Portfolio Name"
                  v-model="portfolioName"
                  required
                ></v-text-field>
                <v-text-field
                  label="Portfolio Description"
                  v-model="portfolioDescription"
                  required
                ></v-text-field>
              </v-form>
            </v-container>
          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn color="green darken-1" flat="flat" @click.native="dialog = false">Cancel</v-btn>
            <v-btn color="green darken-1" flat="flat" @click.native="dialog = false">Add</v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
    </v-layout>
  </v-container>
  </v-app>
</template>

<script>
  import Navlogged from "./NavLogged.vue";
  import Navloggedwithouttabs from "./NavLoggedWithOutTabs.vue";

  export default {
    components: {
      Navloggedwithouttabs,
      Navlogged},
    name: 'userprofile',
    data () {
      return {
        valid: false,
        portfolioName: '',
        portfolioDescription: '',
        dialog: false
      }
    }
  }
</script>

<style>
</style>
