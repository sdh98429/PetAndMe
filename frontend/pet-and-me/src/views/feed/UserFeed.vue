<template>
  <div>
    <h2>유저 피드</h2>
    <div>프로필</div>
    <div>{{profile}}</div>
    <br>
    <div>팔로우하기 버튼</div>
    <br>
    <div>follower, following 표시</div>
    <div>유저 피드 주인 : {{yourUserNumber}}</div>
    <div>로그인한 유저 :{{myUserNumber}}</div>
    <div v-if="yourUserNumber != myUserNumber">
      <button>팔로우하기</button>
    </div>
    <div>영상 썸네일, 영상 제목, 영상 내용</div>
    <div>영상 만드는 버튼</div>
    <br>
    <div><UserFeedList
      />
    </div>
  </div>
</template>

<script>
import axios from 'axios'
import UserFeedList from '@/components/feed/UserFeedList'


export default {
  name: 'UserFeed',
  data: function () {
    return {
      profile : null,

      userNumber: "null",
      yourUserNumber: this.$route.params.yourUserNumber,
      myUserNumber: 2,
    }
  },
  components: {

    UserFeedList,

  },
  props: {

  },
  methods: {
    getUserNumber : function (){ // 로그인한 유저 넘버 가져오기
      this.myUserNumber = 2 // 현재 페이지의 유저로 userNumber 가져오는 로직
    },

    getUserProfile: function(){ // 프로필 정보 가져오기
      axios({
        method: 'get',
        url: 'http://i6b106.p.ssafy.io:8080/users/info/' + this.$route.params.yourUserNumber,
      })
        .then(response => {
          this.profile = response.data
        })
        .catch(err => {
          console.log(err)
        })
    },
  },
  created: function(){
    this.getUserNumber()
    this.getUserProfile()
  }
}
</script>

<style>

</style>