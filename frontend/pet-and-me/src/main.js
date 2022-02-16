import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from "./store";
import { library } from '@fortawesome/fontawesome-svg-core'
import { faUserCircle, faUser, faSearch, faBell, faMars, faVenus, faArrowRight, faVideo, faHome, faPaw, faPlus } from '@fortawesome/free-solid-svg-icons'
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome'
// vuetify
import Vuetify from 'vuetify'
import 'vuetify/dist/vuetify.min.css'
Vue.use(Vuetify);

library.add(faUserCircle, faUser, faSearch, faBell, faMars, faVenus, faArrowRight, faVideo, faHome, faPaw, faPlus)
Vue.component('font-awesome-icon', FontAwesomeIcon)

// import VeeValidate, { Validator } from 'vee-validate'
// Vue.use(VeeValidate, {})

Vue.config.productionTip = false

new Vue({
  router,
  store,
  created () {
    const user = store.getters['getAccessToken']
    if (user) {
      this.$store.commit('SET_LOGIN', user)
    }
  },
  vuetify: new Vuetify(),
  render: h => h(App)
}).$mount('#app')