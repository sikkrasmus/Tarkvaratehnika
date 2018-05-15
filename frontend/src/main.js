// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store/store'
import VueCookies from 'vue-cookies'
import Vuetify from 'vuetify'
import 'vuetify/dist/vuetify.min.css'
import colors from 'vuetify/es5/util/colors'
import cookie from 'vue-cookie'
import AmCharts from 'amcharts3'
import AmSerial from 'amcharts3/amcharts/serial'
Vue.config.productionTip = false

Vue.use(Vuetify, {
  theme: {
    primary: "#fffFff", // #E53935
    secondary: colors.red.lighten4, // #FFCDD2
    accent: colors.teal.accent4 // #3F51B5
  }
})

/* eslint-disable no-new */
new Vue({
  el: '#app',
  cookie,
  AmCharts,
  AmSerial,
  store,
  router,
  VueCookies,
  components: {App},
  template: '<App/>'
}).$mount('#app')
