<template>
  <div>
    <h3>로그인</h3>
    <form @submit.prevent="login">
      <div>
        <input type="text" id="userID" v-model="credentials.userID" placeholder="아이디 입력" >
      </div>
      <div>
        <input type="password" id="userPW" v-model="credentials.userPW" placeholder="비밀번호 입력">
      </div>
      <div>
        <button type="submit">로그인</button>
      </div>
    </form>
    <div>
      <span> 계정이 없다면? <router-link :to="{ name: 'Signup' }">가입하러가기</router-link></span>
    </div>
    <div>
      <span><router-link :to="{ name: 'Signup' }">비밀번호를 찾고 계신가요?</router-link></span>
    </div>
    <h2> - O R - </h2>
    <!-- google -->
    <div class="g-signin2" id="google-signin-btn" data-onsuccess="onSignIn" data-width="385" data-longtitle="true"></div>
  </div>
</template>

<script>
import axios from 'axios'
import {mapActions} from 'vuex'
<<<<<<< HEAD
=======
import {BASE_API_URL} from '@/config/config.js'
>>>>>>> develop

export default {
    name: 'Login',
    data: function () {
    return {
      credentials: {
        userID: '',
        userPW: '',
      }
    }
  },
  methods: {
    ...mapActions([
        'loginGetToken',
    ]),
    login() {
      if (this.credentials.userID === '') {
        alert('아이디 미입력')
        return false;
      } else if (this.credentials.userPW === '') {
        alert('패스워드 미입력')
        return false;
      }

      axios({
          method: 'post',
          url: `${BASE_API_URL}/user/login`,
          data: this.credentials
        })
        .then(res => {
          console.log(this.credentials)
          if (res.data.message === 'success') {
            alert('로그인 성공')
            this.$emit('login')
            this.loginGetToken(res.data.accessToken)
            this.$router.push({ name: 'NewsFeed' })
          }
          else {
            alert(res.data.message)
          }
        })
        .catch(err => {
          console.log(err)
          alert('로그인 실패')
        })
    },
    ...mapActions([
			'loginGetToken',
		]),
    // google
    onSignIn(googleUser){
      const profile = googleUser.getBasicProfile();
      console.log('ID Token: ', googleUser.getAuthResponse().id_token)
      console.log('Name: ' + profile.getName())
    },
    signout() {
      const authInst = window.gapi.auth2.getAuthInstance();
      // eslint-disable-next-line
      console.log(authInst);
    },
  },
}
</script>

<style>

</style>