<template>
  <div class="login-container">
    <h2>로그인</h2>
    <form @submit.prevent="login">
      <div class="group">      
        <input type="text" id="userID" v-model="credentials.userID">
        <label>ID</label>
        <span class="highlight"></span>
        <span class="bar"></span>
      </div>
        
      <div class="group">      
        <input type="password" id="userPW" v-model="credentials.userPW">
        <label>password</label>
        <span class="highlight"></span>
        <span class="bar"></span>
      </div>
      <button>로그인</button>
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
import {BASE_API_URL} from '@/config/config.js'
import "@/css/login.css"

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

<style scoped>
@media screen and (max-width:768px){
  .login-container {
    position: relative;
    top: 60px;
    width: 800px;
    height: 80vh;
    padding-bottom: 200px;
    margin: 0 auto;
    border: 1px solid #ccc;
    background-color: #fff;
  }
  .login-container h2{
    font-size: 30px;
  }
  h2 { 
    text-align:center; 
    margin-bottom:50px; 
  }
  h2 small { 
    font-weight:normal; 
    color:#888; 
    display:block; 
  }
  .footer 	{ text-align:center; }
  .footer a  { color:#53B2C8; }

  /* form starting stylings ------------------------------- */
  .group 			  { 
    position:relative; 
    margin-bottom:45px; 
  }
  input 				{
    font-size:18px;
    padding:10px 10px 10px 5px;
    display:block;
    width:300px;
    border:none;
    border-bottom:1px solid #757575;
  }
  input:focus 		{ outline:none; }

  /* LABEL ======================================= */
  label 				 {
    color:#999; 
    font-size:18px;
    font-weight:normal;
    position:absolute;
    pointer-events:none;
    left:5px;
    top:10px;
    transition:0.2s ease all; 
    -moz-transition:0.2s ease all; 
    -webkit-transition:0.2s ease all;
  }

  /* active state */
  input:focus ~ label, input:valid ~ label 		{
    top:-20px;
    font-size:14px;
    color:#5264AE;
  }
}
@media screen and (min-width:768px){
  .login-container {
    position: relative;
    top: 60px;
    width: 800px;
    height: 80vh;
    padding-bottom: 200px;
    margin: 0 auto;
    border: 1px solid #ccc;
    background-color: #fff;
  }
  .login-container h2{
    font-size: 30px;
  }
  h2 { 
    text-align:center; 
    margin-bottom:50px; 
  }
  h2 small { 
    font-weight:normal; 
    color:#888; 
    display:block; 
  }
  .footer 	{ text-align:center; }
  .footer a  { color:#53B2C8; }

  /* form starting stylings ------------------------------- */
  .group 			  { 
    position:relative; 
    margin-bottom:45px; 
  }
  input 				{
    font-size:18px;
    padding:10px 10px 10px 5px;
    display:block;
    width:300px;
    border:none;
    border-bottom:1px solid #757575;
  }
  input:focus 		{ outline:none; }

  /* LABEL ======================================= */
  label 				 {
    color:#999; 
    font-size:18px;
    font-weight:normal;
    position:absolute;
    pointer-events:none;
    left:5px;
    top:10px;
    transition:0.2s ease all; 
    -moz-transition:0.2s ease all; 
    -webkit-transition:0.2s ease all;
  }

  /* active state */
  input:focus ~ label, input:valid ~ label 		{
    top:-20px;
    font-size:14px;
    color:#5264AE;
  }
}
  /* BOTTOM BARS ================================= */
  .bar 	{ position:relative; display:block; width:300px; }
  .bar:before, .bar:after 	{
    content:'';
    height:2px; 
    width:0;
    bottom:1px; 
    position:absolute;
    background:#5264AE; 
    transition:0.2s ease all; 
    -moz-transition:0.2s ease all; 
    -webkit-transition:0.2s ease all;
  }
  .bar:before {
    left:50%;
  }
  .bar:after {
    right:50%; 
  }

  /* active state */
  input:focus ~ .bar:before, input:focus ~ .bar:after {
    width:50%;
  }

  /* HIGHLIGHTER ================================== */
  .highlight {
    position:absolute;
    height:60%; 
    width:100px; 
    top:25%; 
    left:0;
    pointer-events:none;
    opacity:0.5;
  }

  /* active state */
  input:focus ~ .highlight {
    -webkit-animation:inputHighlighter 0.3s ease;
    -moz-animation:inputHighlighter 0.3s ease;
    animation:inputHighlighter 0.3s ease;
  }

  /* ANIMATIONS ================ */
  @-webkit-keyframes inputHighlighter {
    from { background:#5264AE; }
    to 	{ width:0; background:transparent; }
  }
  @-moz-keyframes inputHighlighter {
    from { background:#5264AE; }
    to 	{ width:0; background:transparent; }
  }
  @keyframes inputHighlighter {
    from { background:#5264AE; }
    to 	{ width:0; background:transparent; }
  }
</style>