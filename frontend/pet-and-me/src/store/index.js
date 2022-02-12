import Vue from 'vue'
import Vuex from 'vuex'
import createPersistedState from "vuex-persistedstate"

Vue.use(Vuex)

import axios from 'axios'
import SERVER from '@/api/server.js'

export default new Vuex.Store({
  plugins: [createPersistedState()],
  state: {
    jwtToken: localStorage.getItem("jwt"),
  },
  getters: {
    config: function (state) {
      return {
        Authorization: `JWT ${state.jwtToken}`,
      }
    },
  },

  mutations: {
    INIT_DATA: function (state) {
      state.jwtToken = localStorage.getItem("jwt")
    },
    LOGIN_GET_TOKEN: function (state) {
      state.jwtToken = localStorage.getItem("jwt")
    },
  },
  actions: {
    initData: function ({commit}) {
      commit("INIT_DATA")
    },
    loginGetToken: function ({commit}) {
      commit("LOGIN_GET_TOKEN")
    },
  },
  modules: {
  }
})
