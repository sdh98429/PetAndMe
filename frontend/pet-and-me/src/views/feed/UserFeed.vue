<template>
  <div>
    <h2>유저 피드</h2>
    <!-- <input @change='onInputImage()' accept="image/*" ref="image" type="file" style="display : none">
    <button @click="$refs.image.click()">open file dialog</button> -->

    <button v-if="(myUserNumber == yourUserNumber)" @click="toUserFeedUpdate">유저 피드 업데이트</button>
    <div>프로필</div>
    <div>{{profile}}</div>
    <div v-if="profile">
      <input v-if="(myUserNumber == yourUserNumber)" @change='onInputImage()' accept="image/*" ref="image" type="file" style="display : none">
      <img @click="profileChange()" :src="'http://i6b106.p.ssafy.io:8080/main/image?file=' + profile.saveFolder + profile.userPhotoName" alt="프로필 사진" style="width: 300px; height: 150px; object-fit: contain;">
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
    <div>{{isFollow}}</div>
    <div v-if="yourUserNumber != myUserNumber">
      <button v-if="!isFollow" @click="followUser">팔로우하기</button>
      <button v-if="isFollow" @click="unfollowUser">언팔로우하기</button>
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
      isFollow : false,

      files: [],
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

    getFollowing: function(){
      axios({
        method: 'get',
        url: 'http://i6b106.p.ssafy.io:8080//user/following/' + this.myUserNumber,
      })
      .then(response => {
        var ind;
        for (ind = 0; ind < response.data.length; ind++) {
          if (this.yourUserNumber == response.data[ind].userNumber){
            console.log(response.data[ind].userNumber)
            this.isFollow = true
            // break
          }
        }
      })
      .catch(err => {
        console.log(err)
      })
    },

    followUser: function(){
      axios({
        method: 'post',
        url: 'http://i6b106.p.ssafy.io:8080/user/follow/' + this.myUserNumber + '/' + this.yourUserNumber,
      })
      .then(() => {
        this.isFollow = true
      })
      .catch(err => {
        console.log(err)
      })
    },

    unfollowUser: function(){
      axios({
        method: 'delete',
        url: 'http://i6b106.p.ssafy.io:8080/user/follow/' + this.myUserNumber + '/' + this.yourUserNumber,
      })
      .then(() => {
        this.isFollow = false
      })
      .catch(err => {
        console.log(err)
      })
    },

    toUserFeedUpdate: function(){
      this.$router.push({name: `UserFeedUpdate`, params : {yourUserId: this.yourUserId}})
    },

    profileChange: function(){
      if (this.myUserNumber == this.yourUserNumber){
        this.$refs.image.click()
      } else {
        console.log('본인 프로필 사진만 업데이트 할 수 있습니다.')
      }
    },

    onInputImage: function(){ // 이미지 추가
      this.files = this.$refs.image.files
      if (this.files[0].size/(1024*1024) > 3){
        this.files = []
        this.$refs.image.value = ''
        alert('이미지 파일은 최대 3MB까지 가능합니다.')
      } else {
        console.log('프사 업뎃 준비 완료')
      }
    }

  },
  created: function(){
    this.getUserNumber()
    this.getUserProfile()
    this.getFollowing()
  }
}
</script>

<style>

</style>