<template>
  <div>
    <h1> 단계 표시 </h1>
    <p> {{ this.level }} / 4 단계 진행중 ,,,</p>
    <hr>
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
    <hr>
    <SignupNext v-if="level == 0" @levelEvent="updateLevel"></SignupNext>

    <h3> 지금까지 쌓인 데이터 목록 </h3>
    <p>아이디 : {{ credentials.userId }}</p>
    <p>이름 : {{ credentials.userName }}</p>
    <password>패스워드 : {{ credentials.password }}</password>
    <p>패스워드 확인 : {{ credentials.passwordConfirmation }}</p>
    <p>이메일 : {{ credentials.userEmail }}</p>
    <p>유저 애완동물 : (boolean + list) {{ credentials.petCheck }} {{ credentials.userPet }}</p>
    <p>유저 선호동물 : {{ credentials.userPreference }}</p>

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

export default {
  name: 'Signup',
  data: function () {
    return {
      level: 0,
      credentials: {
        userId: null,
        userName: null,
        password: null,
        passwordConfirmation: null,
        userEmail: null,
        petCheck: false,
        userPet: {
          petName: null,
          petBirth: null,
          petGender: null,
          petType: null,
        },
        userPreference: null,
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
      this.credentials.userId = res.userId
      this.credentials.userName = res.userName
      this.credentials.password = res.password
      this.credentials.passwordConfirmation = res.passwordConfirmation
      this.level++
    },
    emailSave: function(res) {
      this.credentials.userEmail = res.userEmail
      this.level++
    },
    petSave: function(res) {
      this.credentials.petCheck = res.petCheck
      this.credentials.userPet.petName = res.petName
      this.credentials.userPet.petBirth = res.petBirth
      this.credentials.userPet.petGender = res.petGender
      this.credentials.userPet.petType = res.petType
      this.level++
    },
    preferenceSave: function(res) {
      this.credentials.userPreference = res.selected
      axios({
        method: 'post',
        url: 'http://i6b106.p.ssafy.io:8080/user/',
        data: this.credentials
      })
        .then(() => {
          this.$router.push({ name: 'Login'})
        })
        .catch(err => {
          console.log(err.response)
        })
    }
  }
}
</script>

<style>

</style>