<template>
  <div>
    <h1>지금부터 Pet & Me에 함께 빠져볼까요?🐕</h1>
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