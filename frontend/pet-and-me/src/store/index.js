import Vue from 'vue'
import Vuex from 'vuex'
import jwt_decode from "jwt-decode";
import createPersistedState from "vuex-persistedstate"

Vue.use(Vuex)

export default new Vuex.Store({
  plugins: [createPersistedState()],
  state: {
    accessToken: localStorage.getItem("accessToken"), // 토큰정보
    userInfo: null
  },
  getters: {
    config: function (state) {
      return {
        Authorization: `JWT ${state.accessToken}`,
      }
    },
    // 로그인 했는지 확인
    isLogin: function (state) { 
      return !!state.accessToken;
    },
    getAccessToken: function (state) {
      return state.accessToken;
    },
    getUserNumber: function (state) {
      return state.userInfo.userNumber;
    },
    getUserInfo: function (state) {
      return state.userInfo;
    }
  },
  mutations: {
    SET_LOGIN: function (state, accessToken) {
      let decode_token = jwt_decode(accessToken);
      console.log(decode_token)
      state.userInfo = decode_token.loginUser

      state.accessToken = accessToken
      state.isLogin = true

      localStorage.setItem('accessToken', accessToken)
    },
    SET_LOGOUT: function (state) {
      state.accessToken = null
      state.isLogin = false
      state.myuserNumber = null
      localStorage.removeItem('accessToken')
      location.reload();
    },
  },
  actions: {
    loginGetToken: function ({commit}, token) {
      commit("SET_LOGIN", token)
    },
    logoutRemoveToekn: function ({commit}) {
      commit("SET_LOGOUT")
    }
  },
  modules: {
  }
})