<template>
  <div>
    <div v-if="(yourUserNumber === myUserNumber)">
      <div>
        <h1>회원 정보 수정</h1>
          <div>비밀번호</div>
            <input type="password" name="password" id="password" v-model="credentials.userPW" placeholder="영문 숫자를 포함한 8~16자 이내">
          <div>비밀번호 확인</div>
            <input type="password" name="passwordConfirmation" id="passwordConfirmation" v-model="credentials.passwordConfirmation" placeholder="비밀번호를 확인합니다">

        <button @click="savePW"> 확인 </button>

        <div>
          <h3>반려동물 있는지 여부 체크</h3>
          <button @click="isYes()">Yes</button>
          <button @click="isNo()">No</button>
        </div>
        <div v-if="petCheck">
          <div>
            <div>
              <h3>이름</h3>
                <input v-model="petName" type="text" placeholder="이름을 입력해주세요" maxlength="20" @focus="checkFlag = false"/>
              <span v-if="checkFlag && !petName">이름을 입력하세요</span>
            </div>

            <!-- datepicker -->
            <DatePicker v-model="petBirth" @date-update="dateSave"></DatePicker>

            <div>
              <h3>성별</h3>
                <select
                  v-model="petGender"
                  @focus="checkFlag = false"
                >
                  <option value="">성별</option>
                  <option
                    v-for="(item, index) in genderList"
                    :key="index"
                    :value="item.value"
                  >
                    {{ item.text }}
                  </option>
                </select>
              <span v-if="checkFlag && !petGender">성별을 선택하세요</span>
            </div>
          </div>

          <div>
            <h3>종류</h3>
              <label> <input type="radio" v-model="petType" value="1"> 강아지 </label>
              <label> <input type="radio" v-model="petType" value="2"> 고양이 </label>
              <label> <input type="radio" v-model="petType" value="3"> 조류 </label>
              <label><input type="radio" v-model="petType" value="4"> 설치류 </label>
              <label> <input type="radio" v-model="petType" value="5"> 기타 </label>
          </div>

        </div>

        <h3>선호동물 체크</h3>

        <div>
          <input type="checkbox" value="1" v-model="selected"> 강아지
          <input type="checkbox" value="2" v-model="selected"> 고양이
          <input type="checkbox" value="3" v-model="selected"> 조류
          <input type="checkbox" value="4" v-model="selected"> 설치류
          <input type="checkbox" value="5" v-model="selected"> 기타
        </div>

        <button @click="sendData"> 가입완료 </button>

      </div>
    </div>
    <div v-else>
      내 유저 피드 페이지만 업데이트할 수 있습니다.
    </div>
  </div>
</template>

<script>
import axios from 'axios'
import {BASE_API_URL} from '@/config/config.js'

import DatePicker from '../../components/Signup/DatePicker'

export default {
  name: 'UserFeedUpdate',
  data: function () {
    return {

      yourUserId: this.$route.params.yourUserId,
      yourUserNumber: 1,

      pwFlag: false,
      petFlag: false,
      preferFlag: false,


      petCheck: false,
      petName: '',
      petBirth: '',
      petGender: '',
      petType: '',

      checkFlag: false,
      genderList: [
        {
          value: "M",
          text: "남아",
        },
        {
          value: "F",
          text: "여아",
        },
      ],
      selected: [],

      credentials: {
        userNumber: null, 
        // userID: null,
        userPW: null,
        passwordConfirmation: null,
        // userEmail: null,
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
    DatePicker,
  },
  props: {

  },
  methods: {
    getUserProfile: function(){ // 프로필 정보 가져오기
      console.log(this.$route.params.yourUserId)
      axios({
        method: 'get',
        url: 'http://i6b106.p.ssafy.io:8080/user/number/' + this.$route.params.yourUserId,
      })
        .then(response => {
          this.yourUserNumber = response.data
          this.credentials.userNumber = response.data
        })
        .catch(err => {
          console.log(err)
        })
    },

    savePW() {
      if (!this.credentials.userPW || !this.credentials.passwordConfirmation) {
        alert('패스워드 미입력')
        this.pwFlag = false
      } else if (this.credentials.userPW.length < 8 || this.credentials.userPW.length > 16) {
        alert('패스워드 양식 미충족')
        this.pwFlag = false
      } else if(!/^(?=.*[a-z])(?=.*[0-9]).{8,16}$/.test(this.credentials.userPW)) {
        alert('패스워드는 영문과 숫자를 포함해야 합니다.')  
        this.pwFlag = false
      } else if (this.credentials.userPW != this.credentials.passwordConfirmation) {
        alert('패스워드와 패스워드 확인이 다릅니다. 확인하시기 바랍니다.')
        this.pwFlag = false
      } else {
        alert('패스워드 통과하였습니다')
        this.pwFlag = true
      } 
    },

    isYes () {
      this.petCheck = true;
    },
    isNo () {
      this.petCheck = false;
    },

    dateSave(datePickerString) {
      this.petBirth = datePickerString
    },

    savePet() {
      if (this.petCheck){
        if (!this.petName) {
          alert('반려동물 이름 미입력')
          this.petFlag = false
        } else if (!this.petBirth) {
          alert('반려동물 생일 미입력')
          this.petFlag = false
        } else if (!this.petGender) {
          alert('반려동물 성별 미입력')
          this.petFlag = false
        } else if (!this.petType) {
          alert('반려동물 종류 미입력')
          this.petFlag = false
        } else {
          this.petFlag = true
          this.credentials.petCheck = this.petCheck
          this.credentials.userPet[0].petName = this.petName
          this.credentials.userPet[0].petBirth = this.petBirth
          this.credentials.userPet[0].petGender = this.petGender
          this.credentials.userPet[0].animalNumber = this.petType*1
        } 
      } else{
        this.petFlag = true
        this.credentials.petCheck = this.petCheck
        this.credentials.userPet[0].petName = this.petName
        this.credentials.userPet[0].petBirth = this.petBirth
        this.credentials.userPet[0].petGender = this.petGender
        this.credentials.userPet[0].animalNumber = this.petType*1
      }
    },

    savePrefer() {
      if (this.selected.length < 1) {
          alert('선호동물 미선택')
          this.preferFlag = false
        } else {
          this.credentials.userPreference = this.selected
          for (var step=0; step < this.selected.length; step++) {
            this.credentials.userPreference[step] = {'animalNumber' : this.selected[step]*=1}
          }
          console.log('선호 동물')
          console.log(this.credentials.userPreference)
          this.preferFlag = true
        } 
    },

    sendData(){
      this.savePW()
      if (this.pwFlag){
        this.savePet()
        if (this.petFlag){
          this.savePrefer()
          if (this.preferFlag){
            axios({
              method: 'put',
              url: `${BASE_API_URL}/user`,
              data: this.credentials
            })
              .then(() => {
                console.log(this.credentials.userPet)
                console.log(this.selected)
                this.$router.push({ name: 'Login' })
              })
              .catch(err => {
                console.log('에러')
                console.log(this.credentials)
                console.log(err.response)
              })
          }
        }
      }
    }
  },
  created : function(){
    this.getUserProfile()
  },
  computed: {
    myUserNumber () {
      return this.$store.getters.getUserNumber
    }
  },
}
</script>

<style>

</style>