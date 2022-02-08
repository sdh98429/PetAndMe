<template>
  <div>
    <h2>유저 피드</h2>
    <div>프로필</div>
    <div>{{profile}}</div>
    <div v-if="profile">
      <img :src="'http://i6b106.p.ssafy.io:8080/main/image?file=' + profile.saveFolder + profile.userPhotoName" alt="프로필 사진">
      <div>유저 닉네임 : {{profile.userNickName}}</div>
      <div>유저 아이디 : @{{profile.userID}}</div>

      <div>반려동물 이름 : {{profile.petName}}</div>
      <div>반려동물 성별 : {{profile.petGender}}</div>
      <div>반려동물 생일 : {{profile.petBirth}}</div>
      <div>반려동물 종류 : {{profile.animalName}}</div>
    </div>
    <br>
    <div>팔로우하기 버튼</div>
    <br>
    <div>follower, following 표시</div>
    <div>유저 피드 주인 : {{yourUserNumber}}</div>
    <div>로그인한 유저 :{{myUserNumber}}</div>
    <div>{{yourUserNumber}}</div>
    <div>{{myUserNumber}}</div>
    <div v-if="yourUserNumber != myUserNumber">
      <button>팔로우하기</button>
    </div>
    <div>영상 썸네일, 영상 제목, 영상 내용</div>
    <div>영상 만드는 버튼</div>
    <br>
    <div><UserFeedList
      :your-user-number="yourUserNumber"
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
      // yourUserNumber: this.$route.params.yourUserNumber,
      yourUserId: this.$route.params.yourUserId,
      yourUserNumber: 1,
      myUserNumber: 1,
    }
  },
  components: {

    UserFeedList,

  },
  props: {

  },
  methods: {
    getUserNumber : function (){ // 로그인한 유저 넘버 가져오기
      this.myUserNumber = 1 // 현재 페이지의 유저로 userNumber 가져오는 로직
    },

    getUserProfile: function(){ // 프로필 정보 가져오기
      axios({
        method: 'get',
        url: 'http://i6b106.p.ssafy.io:8080/user/number/' + this.$route.params.yourUserId,
      })
        .then(response => {
          this.yourUserNumber = response.data
          axios({
            method: 'get',
            url: 'http://i6b106.p.ssafy.io:8080/user/info/' + this.yourUserNumber,
          })
            .then(response => {
              this.profile = response.data
            })
            .catch(err => {
              console.log(err)
            })
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