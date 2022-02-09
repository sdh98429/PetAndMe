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
      this.credentials.userPet.petName = res.petName
      this.credentials.userPet.petBirth = res.petBirth
      this.credentials.userPet.petGender = res.petGender
      this.credentials.userPet.animalNumber = res.petType*1
      this.level++
    },
    preferenceSave: function(res) {
      this.credentials.userPreference = res.selected
      for (var step=0; step < res.selected.length; step++) {
        this.credentials.userPreference[step] = {'AnimalNumber' : res.selected[step]*=1}
      }
      console.log(this.credentials.userPreference)
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
  }
}
</script>

<style>

</style>