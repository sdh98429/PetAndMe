<template>
  <div>
    <h1>회원가입 폼 시작 멘트</h1>
    <span> 가입해 볼까요? 등등</span>
  </div>
</template>

<script>
export default {
  name: 'SginupForm',
  data: function () {
    return {
      level: 0,
      credentials: {
        userid: null,
        username: null,
        password: null,
        passwordConfirmation: null,
        email: null,
        userPet: null,
        preferenceAnimal: null,
      }
    }
  }
}
</script>

<style>

</style>