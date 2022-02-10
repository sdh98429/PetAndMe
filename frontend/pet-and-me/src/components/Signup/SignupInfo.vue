<template>
  <div>
    <h1>signup info</h1>
      <div>아이디</div>
        <input type="text" name="userId" id="userId" v-model="credentials.userId" placeholder="6~16자 이내" required />
      <div>비밀번호</div>
        <input type="password" name="password" id="password" v-model="credentials.password" placeholder="영문 숫자를 포함한 8~16자 이내">
      <div>비밀번호 확인</div>
        <input type="password" name="passwordConfirmation" id="passwordConfirmation" v-model="credentials.passwordConfirmation" placeholder="비밀번호를 확인합니다">
    <!-- emit -->
    <button @click="saveData"> 다음 </button>
  </div>
</template>

<script>
export default {
  data: function () {
    return {
      credentials: {
        userId: '',
        password: '',
        passwordConfirmation: '',
      }
    }
  },
  methods: {
    saveData() {
      if (!this.credentials.userId) {
        alert('아이디 미입력')
      } else if(this.credentials.userId.length < 6 || this.credentials.userId.length > 16) {
        alert('아이디 형식 미충족')
      } else if (!this.credentials.password || !this.credentials.passwordConfirmation) {
        alert('패스워드 미입력')
      } else if (this.credentials.password.length < 8 || this.credentials.password.length > 16) {
        alert('패스워드 양식 미충족')
      } else if(!/^(?=.*[a-z])(?=.*[0-9]).{8,16}$/.test(this.credentials.password)) {
        alert('패스워드는 영문과 숫자를 포함해야 합니다.')  
      } else if (this.credentials.password != this.credentials.passwordConfirmation) {
        alert('패스워드와 패스워드 확인이 다릅니다. 확인하시기 바랍니다.')
      } else {
        this.$emit("info-update", this.credentials)
      } 
    },
  },
  computed: {
    checkPassword() {
      if(this.password == this.passwordConfirmation) return true
      else return ('비밀번호가 일치하지 않습니다.')
    }
  },
}
</script>

<style>

</style>