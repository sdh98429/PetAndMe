<template>
  <div>
    <h2>유저 피드</h2>

    <button v-if="(myUserNumber == yourUserNumber)" @click="toUserFeedUpdate">유저 피드 업데이트</button>
    <div>프로필</div>
    <div v-if="profile">
      <input v-if="(myUserNumber == yourUserNumber)" @change='onInputImage()' accept="image/*" ref="image" type="file" style="display : none">
      <img @click="profileChange()" :src="'http://i6b106.p.ssafy.io:8080/main/image?file=' + profile.saveFolder + profile.userPhotoName" alt="프로필 사진" style="width: 300px; height: 150px; object-fit: contain;">
      <div>유저 닉네임 : {{profile.userNickName}}</div>
      <div>유저 아이디 : @{{profile.userID}}</div>
      <br>
      <div>반려동물 이름 : {{profile.petName}}</div>
      <div>반려동물 성별 : {{profile.petGender}}</div>
      <div>반려동물 생일 : {{profile.petBirth}}</div>
      <div>반려동물 종류 : {{profile.animalName}}</div>
    </div>
    <br>
    <div @click="toFollowList()">팔로워 수 : {{followerCnt}}</div>
    <div @click="toFollowList()">팔로잉 수 : {{followingCnt}}</div>
    <br>
    <div v-if="yourUserNumber != myUserNumber">
      <button v-if="!isFollow" @click="followUser">팔로우하기</button>
      <button v-if="isFollow" @click="unfollowUser">언팔로우하기</button>
    </div>
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

      yourUserId: this.$route.params.yourUserId,
      yourUserNumber: 0,
      myUserNumber: 0,
      isFollow : false,

      followerCnt : 0,
      followingCnt : 0,

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

    getUserProfile: async function(){ // 프로필 정보 가져오기
        await axios({
        method: 'get',
        url: 'http://i6b106.p.ssafy.io:8080/user/number/' + this.$route.params.yourUserId,
      })
        .then(response => {
          this.yourUserNumber = response.data
        })
        .catch(err => {
          console.log(err)
        })
        
        await axios({
          method: 'get',
          url: 'http://i6b106.p.ssafy.io:8080/user/info/' + this.yourUserNumber,
        })
          .then(response => {
            this.profile = response.data
          })
          .catch(err => {
            console.log(err)
          })
    },

    getFollowing: function(){
      axios({
        method: 'get',
        url: 'http://i6b106.p.ssafy.io:8080/user/following/' + this.yourUserNumber,
      })
      .then(response => {
        this.followingCnt = response.data.length
        var ind;
        for (ind = 0; ind < response.data.length; ind++) {
          if (this.yourUserNumber == response.data[ind].userNumber){
            // console.log(response.data[ind].userNumber)
            this.isFollow = true
            // break
          }
        }
      })
      .catch(err => {
        console.log(err)
      })
    },

    getFollower: function(){
      axios({
        method: 'get',
        url: 'http://i6b106.p.ssafy.io:8080/user/follower/' + this.yourUserNumber,
      })
      .then(response => {
        this.followerCnt = response.data.length
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
        this.getFollowing()
        this.getFollower()
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
        this.getFollowing()
        this.getFollower()  
      })
      .catch(err => {
        console.log(err)
      })
    },

    toUserFeedUpdate: function(){
      this.$router.push({name: `UserFeedUpdate`, params : {yourUserId: this.yourUserId}})
    },

    toFollowList: function(){
      this.$router.push({name: `FollowList`, params : {yourUserId: this.yourUserId}})
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
        if (this.files.length) {
          var formData = new FormData();

          formData.append('userPhoto', this.files[0]);

          formData.append("userNumber", JSON.stringify(this.yourUserNumber));

          axios({
              method: 'put',
              url: 'http://i6b106.p.ssafy.io:8080/user/userPhoto',
              data: formData,
              header: {
                        // 'Accept': 'application/json',
                        'Content-Type': 'multipart/form-data',
                      },
          })
          .then(() => {
              console.log("프로필 사진 수정 완료");
              this.getUserProfile()
          })
          .catch( (err) => {
              console.log(err);
          });
        }
        else {
          alert("수정할 프로필 사진을 넣어주세요")
        }
      }
    },

    asyncCall : async function(){
      await this.getUserNumber();
      await this.getUserProfile();
      await this.getFollowing();
      await this.getFollower();
    }

  },
  created: function(){

    this.asyncCall()
  }
}
</script>

<style>

</style>