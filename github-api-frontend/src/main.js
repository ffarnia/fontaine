import Vue from 'vue'
import App from './App.vue'

import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
import BootstrapVue from 'bootstrap-vue'
import { VBModal } from 'bootstrap-vue'
import ECharts from 'vue-echarts'
import 'echarts/lib/chart/bar'
import 'echarts/lib/component/tooltip'
import LightTimeline from 'vue-light-timeline';

Vue.config.productionTip = false


Vue.directive('b-modal', VBModal)
Vue.use(LightTimeline);
Vue.use(BootstrapVue);
Vue.component('v-chart', ECharts)

new Vue({
  render: h => h(App)
}).$mount('#app')
