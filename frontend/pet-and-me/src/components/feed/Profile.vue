<template>
  <div>
    <div>프로필</div>
    <div>유저 번호 {{userNumber}}</div>

  </div>
</template>

<script>
// import axios from 'axios'

export default {
  name: 'Profile',
  data: function () {
    return {

    }
  },
  components: {

  },
  props: {
    userNumber: {
      type: String,
    }
  },
  methods: {

  },
  // created: function(){
  //   this.getUserNumber()
  // }
}
</script>

<style>

</style>