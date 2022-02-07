<template>
  <div>
    <h3>로그인</h3>
    <form id="loginform">
      <div>
        <input type="text" id="userid" v-model="credentials.userId" placeholder="아이디 입력" >
      </div>
      <div>
        <input type="password" id="password" v-model="credentials.password" placeholder="비밀번호 입력">
      </div>
      <div>
        <button @click="login" class="pointer">로그인</button>
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

export default {
    data: function () {
    return {
      credentials: {
        userId: '',
        password: '',
      }
    }
  },

  methods: {
    isValid: function () {
      if (this.credentials.username === '') {
        alert('아이디 미입력')
      } else if (this.credentials.password === '') {
        alert('패스워드 미입력')
      } else {
        this.login()
      }
    },
    login: function () {
      axios({
      method: 'post',
      url: 'http://127.0.0.1:8000/user/login',
      data: this.credentials
      })
      .then(res => {
        localStorage.setItem('jwt', res.data.token)
        this.$emit('login')
        console.log('userid', this.userid)
        this.$router.push({ name: 'home' })
      })
      .catch(err => {
        console.log(err)
      })
    },
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