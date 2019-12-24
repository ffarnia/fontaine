import Vue from 'vue'
import App from './App.vue'

import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
import BootstrapVue from 'bootstrap-vue'
import { VBModal } from 'bootstrap-vue'

Vue.config.productionTip = false

Vue.directive('b-modal', VBModal)
Vue.use(BootstrapVue);
new Vue({
  render: h => h(App)
}).$mount('#app')
