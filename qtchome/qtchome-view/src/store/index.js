import Vuex from 'vuex'
import Vue from 'vue'
import { user, getters } from './modules/permissions'

Vue.use(Vuex)
export default new Vuex.Store({
  modules: {
    user
  },
  getters
})
