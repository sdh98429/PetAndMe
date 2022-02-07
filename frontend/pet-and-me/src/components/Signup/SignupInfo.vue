<template>
  <div>
    <h1>signup info</h1>
      <div>아이디</div>
        <input type="text" name="userId" id="userId" v-model="credentials.userId" placeholder="5~15자 이내" required />
      <div>이름</div>
        <input type="text" name="userName" id="userName" v-model="credentials.userName" placeholder="한글 10자 이내">
      <div>비밀번호</div>
        <input type="password" name="password" id="password" v-model="credentials.password" placeholder="비밀번호는 영문 대소문자 8~15자 이내로 생성 가능합니다.">
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
        userId: null,
        userName: null,
        password: null,
        passwordConfirmation: null,
      }
    }
  },
  methods: {
    saveData() {
      // console.log(this.credentials)
      this.$emit("info-update", this.credentials)
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