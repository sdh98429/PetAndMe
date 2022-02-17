<template>
  <div class="signup-container">
    <p> {{ this.level }} / 4 단계</p>
    <SignupForm v-if="level == 0"></SignupForm>
    <SignupInfo 
      v-if="level == 1"
      @info-update="infoSave"
      >
    </SignupInfo>
    <SignupEmail
      v-if="level == 2"
      @email-update="emailSave"
      >
    </SignupEmail>
    <SignupPet 
      v-if="level == 3"
      @pet-update="petSave"
      >
    </SignupPet>
    <SignupPreference 
      v-if="level == 4"
      @preference-update="preferenceSave"
      >
      </SignupPreference>
    <SignupNext v-if="level == 0" @levelEvent="updateLevel"></SignupNext>

  </div>
</template>

<script>
import axios from 'axios'

import SignupForm from '../../components/Signup/SignupForm'
import SignupInfo from '../../components/Signup/SignupInfo'
import SignupEmail from '../../components/Signup/SignupEmail'
import SignupPet from '../../components/Signup/SignupPet'
import SignupPreference from '../../components/Signup/SignupPreference'
import SignupNext from '../../components/Signup/SignupNext'
// import '@/css/signup.css'

export default {
  name: 'Signup',
  data: function () {
    return {
      level: 0,
      credentials: {
        userID: null,
        userPW: null,
        passwordConfirmation: null,
        userEmail: null,
        petCheck: false,
        userPet: [
          {
          petName: null,
          petBirth: null,
          petGender: null,
          animalNumber: null,
          }
        ],
        userPreference: [],
      }
    }
  },
  components: {
    SignupForm,
    SignupEmail,
    SignupInfo,
    SignupPet,
    SignupPreference,
    SignupNext,
  },
  methods: {
    updateLevel() {
      this.level++
    },
    infoSave: function(res) {
      this.credentials.userID = res.userId
      this.credentials.userPW = res.password
      this.credentials.passwordConfirmation = res.passwordConfirmation
      this.level++
    },
    emailSave: function(res) {
      this.credentials.userEmail = res.userEmail
      this.level++
    },
    petSave: function(res) {
      this.credentials.petCheck = res.petCheck
      this.credentials.userPet[0].petName = res.petName
      this.credentials.userPet[0].petBirth = res.petBirth
      this.credentials.userPet[0].petGender = res.petGender
      this.credentials.userPet[0].animalNumber = res.petType*1
      this.level++
    },
    preferenceSave: function(res) {
      this.credentials.userPreference = res.selected
      for (var step=0; step < res.selected.length; step++) {
        this.credentials.userPreference[step] = {'animalNumber' : res.selected[step]*=1}
      }
      // console.log(this.credentials.userPreference)
        axios({
          method: 'post',
          url: 'http://i6b106.p.ssafy.io:8080/user',
          data: this.credentials
        })
          .then(() => {
            this.$router.push({ name: 'Login' })
          })
          .catch(err => {
            console.log(err.response)
          })
    }
  },
  mounted(){
    
    const footerEl = document.querySelector('#footer-container')
    footerEl.classList.add('inactive')
    const inputEl = document.querySelector('.my-search-bar')
    inputEl.classList.add('inactive')
  }
}
</script>

<style>
@media screen and (max-width:768px){
  .signup-container {
    position: relative;
    font-family: 'MinSans-Regular', 'Quicksand';
    top: 60px;
    width: 100%;
    max-width: 600px;
    height: 80vh;
    padding-top: 10%;
    margin: 0 auto;
    background-color: #fff;
  }
  .signup-container h2{
    font-size: 30px;
  }
  .first-step {
    width: 100%;
    position: relative;

  }
  .signup-container h1 {
    width: 400px;
    top: 0;
    position: relative;
    text-align: center;
    left: 50%;
    transform: translateX(-50%);
    font-size: 20px;
  }
  .group { 
    position: relative; 
    top: 50px;
    padding-bottom:45px; 
    width: 300px;
    left: 50%;
    transform: translateX(-50%);
  }
  .group .my-input {
    font-size:18px;
    padding:10px 10px 10px 5px;
    display:block;
    width:300px;
    border:none;
    border-bottom:1px solid #757575;
  }
  .group .my-input:focus { outline:none; }

  .group label {
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
  .next-btn {
    position: relative;
    left: 50%;
    transform: translateX(-50%);
    bottom: -50px;
    width: 300px;
  }
  .frst-step {
    position: relative;
    top: 50px;
  }
  .first-step .check-btn {
    position: relative;
    text-align: right;
    width: 300px;
    font-size: 18px;
  }

  /* active state */
  .first-step .group .my-input:focus ~ label, .my-input:valid ~ label 		{
    top:-20px;
    font-size:14px;
    color:#5264AE;
  }
  .second-step .cert-btn{
    /* position: relative; */
    width: 100%;
    font-size: 18px;
    text-align: right;
  }
  .third-step .btn-container{
    font-size: 20px;
    display: flex;
    justify-content: space-around;
  }
  .third-step {
    position: relative;
    width: 100%;
  }
  .third-step h3 {
    width: 100%;
    text-align: center;
  }
  .third-step .gender-box {
    position: relative;
    display: flex;
  }
  .third-step .select-box {
    position: relative;
    display: flex;
    justify-content: space-around;
    gap: 50px;
    margin-top: 50px;
  }
  .third-step .select-input{
    width: 300px;
    font-size: 20px;
    text-align: center;
  }
  .third-step .save-animal-box{
    position: relative;
    width: 90%;
    top:0;
    display: flex;
    flex-wrap: wrap;
    justify-content: center;
    gap: 20px;
  }
  .third-step .save-animal-box .save-animal-checkbox{
    width: 70px;
    height: 30px;
    font-size: 14px;
    border: 1px solid #ccc;
    border-radius: 20px;
    position: relative;
  }
  .third-step .save-animal-box .save-animal-checkbox input{
    position: relative;
    top: 5px;
    left: 5px;
  }
  .third-step .save-animal-box .save-animal-checkbox label{
    position: relative;
    top: 5px;
    left: 5px;
    font-size: 16px;
  }
  .final-step .save-animal-box{
    position: relative;
    width: 90%;
    top:0;
    display: flex;
    flex-wrap: wrap;
    justify-content: center;
    gap: 20px;
  }
  .final-step .save-animal-box .save-animal-checkbox{
    width: 70px;
    height: 30px;
    font-size: 14px;
    border: 1px solid #ccc;
    border-radius: 20px;
    position: relative;
  }
  .final-step .save-animal-box .save-animal-checkbox input{
    position: relative;
    top: 5px;
    left: 5px;
  }
  .final-step .save-animal-box .save-animal-checkbox label{
    position: relative;
    top: 5px;
    left: 5px;
    font-size: 16px;
  }
}
@media screen and (min-width:768px){
  .signup-container {
    position: relative;
    top: 60px;
    width: 800px;
    height: calc(100vh - 60px);
    margin: 0 auto;
    border: 1px solid #ccc;
    background-color: #fff;
    font-size:30px;
    font-family: 'MinSans-Regular', 'Quicksand';
  }
  .signup-container h2{
    font-size: 30px;
  }
  .first-step {
    width: 100%;
    position: relative;

  }
  .signup-container h1 {
    width: 500px;
    top: 0;
    position: relative;
    text-align: center;
    left: 50%;
    transform: translateX(-50%);
  }
  .group { 
    position:relative; 
    margin-bottom:45px; 
    width: 300px;
    left: 50%;
    transform: translateX(-50%);
  }
  .group .my-input {
    font-size:18px;
    padding:10px 10px 10px 5px;
    display:block;
    width:300px;
    border:none;
    border-bottom:1px solid #757575;
  }
  .group .my-input:focus 		{ outline:none; }

  .group label 				 {
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
  .next-btn {
    position: relative;
    left: 50%;
    transform: translateX(-50%);
    bottom: -50px;
    width: 300px;
  }
  .first-step .check-btn {
    position: relative;
    right: -150px;
    width: 200px;
    font-size: 18px;
  }

  /* active state */
  .first-step .group .my-input:focus ~ label, .my-input:valid ~ label 		{
    top:-20px;
    font-size:14px;
    color:#5264AE;
  }
  .second-step .cert-btn{
    /* position: relative; */
    width: 100%;
    font-size: 18px;
    text-align: right;
  }
  .third-step .btn-container{
    font-size: 20px;
    display: flex;
    justify-content: space-around;
  }
  .third-step {
    position: relative;
    width: 100%;
  }
  .third-step h3 {
    width: 100%;
    text-align: center;
  }
  .third-step .group {
    margin-top: 50px;
  }
  .third-step .gender-box {
    position: relative;
    display: flex;
  }
  .third-step .select-box {
    position: relative;
    display: flex;
    justify-content: space-around;
    gap: 50px;
    margin-top: 50px;
  }
  .third-step .select-input{
    width: 300px;
    font-size: 20px;
    text-align: center;
  }
  .third-step .save-animal-box{
    position: relative;
    width: 90%;
    top:0;
    display: flex;
    flex-wrap: wrap;
    justify-content: center;
    gap: 20px;
  }
  .third-step .save-animal-box .save-animal-checkbox{
    width: 70px;
    height: 30px;
    font-size: 14px;
    border: 1px solid #ccc;
    border-radius: 20px;
    position: relative;
  }
  .third-step .save-animal-box .save-animal-checkbox input{
    position: relative;
    top: 5px;
    left: 5px;
  }
  .third-step .save-animal-box .save-animal-checkbox label{
    position: relative;
    top: 5px;
    left: 5px;
    font-size: 16px;
  }
  .final-step .save-animal-box{
    position: relative;
    width: 90%;
    top:0;
    display: flex;
    flex-wrap: wrap;
    justify-content: center;
    gap: 20px;
  }
  .final-step .save-animal-box .save-animal-checkbox{
    width: 70px;
    height: 30px;
    font-size: 14px;
    border: 1px solid #ccc;
    border-radius: 20px;
    position: relative;
  }
  .final-step .save-animal-box .save-animal-checkbox input{
    position: relative;
    top: 5px;
    left: 5px;
  }
  .final-step .save-animal-box .save-animal-checkbox label{
    position: relative;
    top: 5px;
    left: 5px;
    font-size: 16px;
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
