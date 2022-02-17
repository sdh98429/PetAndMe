<template>
  <div class="second-step">
    <h1>이메일인증</h1>
    <div class="group">
        <input class="my-input" type="text" id="userEmail" v-model="credentials.userEmail" @focus="checkFlag=false" placeholder="이메일을 입력해주세요">
        <label for="userEmail">Email</label>
        <span class="highlight"></span>
        <span class="bar"></span>
        <button class="cert-btn" @click="certi">인증메일보내기</button>
    </div>

    <div v-show="certiFlag">
      <div class="group">      
        <input class="my-input"  type="text" id="authkey" v-model="credentials.authKey" placeholder="인증번호를 입력해주세요">
        <label for="authkey">인증번호 입력</label>
        <span class="highlight"></span>
        <span class="bar"></span>
        <button class="cert-btn" @click="certificateEmail">인증하기</button>
      </div>
    </div>
    <button class="next-btn bttn-gradient bttn-md bttn-warning" @click="saveData">Next</button>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  data() {
    return {
      checkFlag: false,
      certiFlag: false,
      certificate: false,
      credentials: {
        userEmail: '',
        authKey: '',
      },
    };
  },
  methods: {
    saveData() {
      if (this.certificate){
        this.$emit("email-update", this.credentials)
      } else {
        alert('이메일 인증 절차를 완료해주세요')
      }
    },
    certi() {
      var checkEmail = /^[A-Za-z0-9_]+[A-Za-z0-9]*[@]{1}[A-Za-z0-9]+[A-Za-z0-9]*[.]{1}[A-Za-z]{1,3}$/
      if (!this.credentials.userEmail){
        alert('이메일을 입력해주세요')
      } else if(!checkEmail.test(this.credentials.userEmail)) {
        alert('올바른 이메일 형식이 아닙니다')
      } else{
        alert('인증번호 전송에 성공했습니다. 전송 후 약 30초 정도 시간이 소요됩니다. 잠시 기다려주세요')
          axios({
            method: 'post',
            url: 'http://i6b106.p.ssafy.io:8080/user/sendEmail/'+ this.credentials.userEmail ,
            data: this.credentials
            })
              .then(() => {
                this.certiFlag = true;
              })
              .catch(err => {
                console.log(err.response)
              })
      }
      
    },
    certificateEmail() {
      console.log(this.credentials)
        axios({
          method: 'post',
          url: 'http://i6b106.p.ssafy.io:8080/user/emailConfirm',
          data: this.credentials
        })
          .then((res) => {
            console.log(res)
            if (res.data == 'success'){
              alert('인증을 성공했습니다.')
              this.certificate = true
            } else if (res.data == 'fail'){
              alert('인증번호를 다시 확인해주세요')
              this.credentials.authKey = ''
            } else if (res.data == 'duplicate'){
              alert('이미 가입된 이메일입니다.')
            } else if (res.data == 'email error'){
              alert(('올바른 이메일 형식인지 다시한번 확인해주세요.'))
            }
            })
            .catch(err => {
              console.log(err.response) 
            })
    }
  },
};
</script>