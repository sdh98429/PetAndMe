<template>
  <div class="user-update-container">
    <div v-if="(yourUserNumber === myUserNumber)">
      <div>
        <h1>회원 정보 수정</h1>
          <h3>아이디</h3>
            <div>@{{yourUserId}}</div>
          <h3>이메일</h3>
            <div>{{userEmail}}</div>

          <h3>닉네임</h3>
            <input type="text" name="nickname" id="nickname" v-model="credentials.userNickName" placeholder="닉네임을 입력해주세요" style="border-style: solid;">

          <h3>프로필 소개</h3>
            <input type="text" name="userProfileContent" id="userProfileContent" v-model="credentials.userProfileContent" placeholder="자신을 소개해주세요" style="width:300px;height:200px;border-style: solid;">

        <h3>선호동물 체크</h3>

        <div>
          <span v-for="(animal, index) in animalList" class="saveAnimalIndex" v-bind:key="animal">
            <input type="checkbox" :value="animal.animalNumber" v-model="selected">{{animal.animalName}}
            <br v-if="(index+1) % 3 == 0">
          </span>
        </div>

        <button @click="sendData"> 수정 완료 </button>
        <br>
        <button @click="goToMyPage()">내 페이지로 돌아가기</button>
        <br>
        <button @click="logout" class="bttn-pill bttn-md bttn-success">LogOut</button>
      </div>
    </div>
    <!-- 다른 사람의 회원 정보를 수정하려 할 때 -->
    <div v-else>
      내 유저 피드 페이지만 업데이트할 수 있습니다.
    </div>
  </div>
</template>

<script>
import axios from 'axios'
import {BASE_API_URL} from '@/config/config.js'
import '@/css/userupdate.css'
import { mapActions } from "vuex"

export default {
  name: 'UserFeedUpdate',
  data: function () {
    return {

      yourUserId: this.$route.params.yourUserId, // 업데이트하려는 페이지의 유저 아이디
      yourUserNumber: 1, // 업데이트하려는 페이지의 유저 번호
      userEmail : null, // 유저 이메일 

      preferFlag: false, // 선호 동물 선택했는지 확인하는 flag

      selected: [], // 선호 동물 종류를 담아두는 selected

      animalList: [
        {
          animalNumber: null,
          animalName: null
        }
      ],
      credentials: {
        userNumber: null, 
        userPW: null,
        userNickName: null,
        petCheck: false,
        userProfileContent: null,

        userPet: [
          {
          animalNumber: null,
          petBirth: null,
          petGender: null,
          petName: null,
          }
        ],
        userPreference: [],
      }
    }
  },
  components: {

  },
  props: {

  },
  methods: {
    ...mapActions([
      'logoutRemoveToekn'
      ]),

    getUserProfile: async function(){ // 프로필 정보 가져오기
        await axios({
        method: 'get',
        url: `${BASE_API_URL}/user/number/${this.$route.params.yourUserId}`,
      })
        .then(response => {
          this.yourUserNumber = response.data
          this.credentials.userNumber = response.data
        })
        .catch(err => {
          console.log(err)
        })
        
        await axios({
          method: 'get',
          url: `${BASE_API_URL}/user/info/${this.yourUserNumber}`,
        })
          .then(response => {
            this.profile = response.data
          })
          .catch(err => {
            console.log(err)
          })
    },

    getUserChangeInfo: async function(){ // 유저 번호를 바탕으로 유저 정보 가져오기 (회원정보 수정 요청 보낼 시 필요한 모든 정보 가져오기)
      await axios({ // 닉네임, 프로필 소개, 비밀번호, 펫 여부 가져오기
        method: 'get',
        url: `${BASE_API_URL}/user/${this.yourUserNumber}`
      })
        .then(response => {
          this.credentials.userPW = response.data.userPW
          this.credentials.userNickName = response.data.userNickName
          this.credentials.userProfileContent = response.data.userProfileContent
          this.credentials.petCheck = response.data.petCheck
          this.userEmail = response.data.userEmail
        })
        .catch(err => {
          console.log(err)
        })

      await axios({ // 펫 생일, 동물종류, 이름, 성별 가져오기 (업데이트 페이지에서 실제로 변경하진 않지만, 요청 보내기 위해 필요)
        method: 'get',
        url: `${BASE_API_URL}/user/pet/${this.yourUserNumber}`
      })
        .then(response => {
          this.credentials.userPet[0].petBirth = response.data[0].petBirth
          this.credentials.userPet[0].animalNumber = response.data[0].animalNumber
          this.credentials.userPet[0].petName = response.data[0].petName
          this.credentials.userPet[0].petGender = response.data[0].petGender
        })
        .catch(err => {
          console.log(err)
        })

      await axios({ // 선호 동물 가져오기
        method: 'get',
        url: `${BASE_API_URL}/animal/${this.yourUserNumber}`
      })
        .then(response => { // 선호 동물 차례로 selected에 추가
          for (var step=0; step < response.data.length; step++) {
            this.selected.push(response.data[step].animalNumber)
          }
        })
        .catch(err => {
          console.log(err)
        })
    },

    getAnimal() {
      axios({
          method: 'get',
          url: 'http://i6b106.p.ssafy.io:8080/animal',
        })
        .then((res) => {
          console.log(res)
          this.animalList = res.data
        })
        .catch(err => {
          console.log(err.response) 
        })
    },

    savePrefer() { // 선호 동물 선택했는지 검증하기
      if (this.selected.length < 1) { // 선택 0마리 했다면
          alert('선호동물 미선택')
          this.preferFlag = false
        } else {
          this.credentials.userPreference = [] // 선호 동물 목록 초기화
          for (var step=0; step < this.selected.length; step++) { // selected에 있는 선호동물 하나씩 담아주기
            this.credentials.userPreference[step] = {'animalNumber' : this.selected[step]*=1}
          }
          console.log('선호 동물')
          console.log(this.credentials.userPreference)
          this.preferFlag = true
        } 
    },

    sendData(){ // 회원 정보 수정 요청 보내기 
    
      this.credentials.userNickName = this.credentials.userNickName.trim() // 닉네임 공백 제거
      this.credentials.userProfileContent = this.credentials.userProfileContent.trim() // 프로필 소개 공백 제거
      if (this.credentials.userNickName){ // 닉네임 입력값이 있다면
        if (this.credentials.userProfileContent){ // 프로필 소개 입력값이 있다면
          this.savePrefer() // 선호 동물 선택했는지 검증
          if (this.preferFlag){ // 선호 동물 선택했다면
            axios({ // 회원 정보 수정 요청 보내기
              method: 'put',
              url: `${BASE_API_URL}/user`,
              data: this.credentials
            })
              .then(() => {
                this.goToMyPage()
              })
              .catch(err => {
                console.log(this.credentials) // 회원 정보 수정 데이터값
                console.log(err.response)
              })
          }
        } else {
          alert('프로필 소개를 입력해주세요.')
        }
      } else {
        alert('닉네임을 입력해주세요')
      }
    },

    goToMyPage() { // 내 유저 피드 페이지로 돌아가기
      this.$router.push({ name : 'UserFeed', params: { yourUserId : this.yourUserId }})
    },

    logout() {
      this.logoutRemoveToekn()
      this.$router.push({name:'Landing'})
    },

    async asyncCall(){ // url의 유저 아이디를 유저 번호로 바꾸고, 유저 번호를 토대로 정보 가져오기 (시간 순서가 지켜져야 해서 async 요청)
      await this.getUserProfile() // 아이디 -> 번호
      await this.getUserChangeInfo() // 유저 정보 가져오기
    }
  },
  created : function(){
    this.asyncCall(), // 유저 아이디 -> 번호로 변환 후 정보 가져오기
    this.getAnimal()
  },
  computed: {
    myUserNumber () {
      return this.$store.getters.getUserNumber // 로그인한 유저의 번호 가져오기
    }
  },
}
</script>

<style>

</style>