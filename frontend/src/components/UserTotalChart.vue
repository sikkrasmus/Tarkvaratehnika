<template>
  <div id="chartdiv" style="width: 100%; height: 400px;"></div>
</template>

<script>
  import axios from 'axios'
  import {mapActions} from 'vuex'

  export default {
    name: 'usertotalchart',
    data() {
      return {
        requestData: {
          portfolioId: null
        },
        response: {
          date: '',
          value: ''
        },
      }
    },

    methods: {
      ...mapActions([
        'getPortfolioId',
        'getGraphData'
      ]),
    },

    mounted() {
      this.getGraphData(this.$store.state.portfolioId)
    }
  }
</script>

<style>
  #chartdiv {
    width: 100%;
    height: 500px;
  }
</style>
