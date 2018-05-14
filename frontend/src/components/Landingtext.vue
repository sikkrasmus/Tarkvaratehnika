<template>
  <v-container fluid grid-list-xs>
    <v-layout row wrap>
      <v-flex d-flex xs12 sm12 md12 class="text-xs-center">
        <v-card color="white" style="height: 100%">
          <v-card-title style="color: #3b58f9" class="title"></v-card-title>
          <v-card-text>
            <h1 style="color: #3b58f9"><strong>Take control of your Bitcoin, ICO & cryptocurrency portfolio</strong></h1>
            <br>
            <v-container class="hidden-md-and-down">
              <p style="margin-left: 200px; margin-right: 200px"><strong>coinWatch</strong>
                is the ultimate Bitcoin & cryptocurrency portfolio tracker app. Keep track of all cryptocoins, including Bitcoin, Ethereum, Litecoin and over 250 altcoins. Use the free web-app to get the latest coin prices to make sure you never miss your next crypto investment oppurtunity.
              </p>
            </v-container>
            <v-container class="hidden-lg-and-up">
              <p><strong>coinWatch</strong>
                is the ultimate Bitcoin & cryptocurrency portfolio tracker app. Keep track of all cryptocoins, including Bitcoin, Ethereum, Litecoin and over 250 altcoins. Use the free web-app to get the latest coin prices to make sure you never miss your next crypto investment oppurtunity.
              </p>
            </v-container>
            <v-btn large dark round style="background-color: #3B58F9;" :to="'/register'">Start tracking today</v-btn>
            <br><br><br>
            <img src="../../static/ViewPics/view1.png"/>
            <br><br>
            <h1 style="color: #3b58f9"><strong>Portfolio at glance</strong></h1>
            <v-container class="hidden-md-and-down">
              <p style="margin-left: 200px; margin-right: 200px">
                A clear overview of your total portfolio balance, total profit/loss since you started investing. See the trend of your cryptocurrency investments with your personal portfolio graph.</p>
            </v-container>
            <v-container class="hidden-lg-and-up">
              <p>
                A clear overview of your total portfolio balance, total profit/loss since you started investing. See the trend of your cryptocurrency investments with your personal portfolio graph.</p>
            </v-container>
            <img src="../../static/ViewPics/view2.png"/>
            <h1 style="color: #3b58f9"><strong>Manage multiple portfolios at ease</strong></h1>
            <v-container class="hidden-md-and-down">
              <p style="margin-left: 200px; margin-right: 200px">
                When you are so deep into crypto and one is just not enough for you. Make and manage multiple portfolios, It has never been easier before!</p>
            </v-container>
            <v-container class="hidden-lg-and-up">
              <p>
                When you are so deep into crypto and one is just not enough for you. Make and manage multiple portfolios, It has never been easier before!</p>
            </v-container>
            <img src="../../static/ViewPics/view3.png"/>
            <p>
            </p>
            <v-btn large dark round style="background-color: #3B58F9;" :to="'/register'">Join the revolution</v-btn>
            <div class="hidden-md-and-down"><br><br><br><br><br><br><br></div>
          </v-card-text>
        </v-card>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
  import VCardMedia from "vuetify/src/components/VCard/VCardMedia";

  export default {
    components: {VCardMedia},
    name: 'landingtext',
    data: () => ({
      lorem: `Lorem ipsum dolor sit amet, mel at clita quando. Te sit oratio vituperatoribus, nam ad ipsum posidonium mediocritatem, explicari dissentiunt cu mea. Repudiare disputationi vim in, mollis iriure nec cu, alienum argumentum ius ad. Pri eu justo aeque torquatos.`
    })
  }
</script>
<style>
  img {
    max-width: 100%;
    height: auto;
  }
</style>
