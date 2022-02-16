<template>
  <div>
    <div v-if="(yourUserNumber === myUserNumber)">
      유저 피드 업데이트
    </div>
    <div v-else>
      내 유저 피드 페이지만 업데이트할 수 있습니다.
    </div>
  </div>
</template>

<script>
import axios from 'axios'
// import {BASE_API_URL} from '@/config/config.js'
import '@/css/userupdate.css'
// import DatePicker from '../../components/Signup/DatePicker'

export default {
  name: 'UserFeedUpdate',
  data: function () {
    return {
      profile : null,
      yourUserId: this.$route.params.yourUserId,
      yourUserNumber: 1,
      myUserNumber: 1,
    }
  },
  components: {

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
        })
        .catch(err => {
          console.log(err)
        })
    },
  },
  created : function(){
    this.getUserProfile()
  }
}
</script>

<style>

</style>