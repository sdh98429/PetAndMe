import Vue from 'vue'
import App from './App.vue'
import router from './router'
import { library } from '@fortawesome/fontawesome-svg-core'
import { faUserCircle, faUser, faSearch, faBell } from '@fortawesome/free-solid-svg-icons'
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome'

library.add(faUserCircle, faUser, faSearch, faBell)
Vue.component('font-awesome-icon', FontAwesomeIcon)

// import VeeValidate, { Validator } from 'vee-validate'
// Vue.use(VeeValidate, {})

Vue.config.productionTip = false

new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
