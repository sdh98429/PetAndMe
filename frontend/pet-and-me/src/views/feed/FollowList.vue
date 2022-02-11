<template>
  <div>
    <h2 v-if="profile">{{profile.userNickName}}</h2>
    <div>팔로워 : {{followerCnt}}</div>
    <div>팔로잉 : {{followingCnt}}</div>
    <br>
    <button v-for="(tab, index) in tabs"
      :key="index"
      v-bind="{active: currentTab === index}"
      @click="currentTab = index">{{tab}}</button>
    <div v-show="currentTab==0">
      <div>팔로워 리스트</div>
      <div v-for="(follower, ind) in followerList" :key="'follower' + ind" style="border: 1px solid;">
        <img @click="toUserFeed(follower.userID)" :src="'http://i6b106.p.ssafy.io:8080/main/image?file=' + follower.saveFolder + follower.userPhotoName" alt="프로필 사진" style="width: 300px; height: 150px; object-fit: contain;">
        <div>{{follower.userNickName}}</div>
        <div>@{{follower.userID}}</div>
        <div>팔로워 : {{follower.followerCnt}}</div>
        <div>팔로잉 : {{follower.followingCnt}}</div>
        <button @click="unfollowUser(follower.userNumber)" v-if="myFollowingList.includes(follower.userNumber)">언팔로우</button>
        <button @click="followUser(follower.userNumber)" v-else>팔로우</button>
      </div>
    </div>
    <div v-show="currentTab==1">
      <div>팔로잉 리스트</div>
      <div v-for="(following, ind) in followingList" :key="'following' + ind" style="border: 1px solid;">
        <img @click="toUserFeed(following.userID)" :src="'http://i6b106.p.ssafy.io:8080/main/image?file=' + following.saveFolder + following.userPhotoName" alt="프로필 사진" style="width: 300px; height: 150px; object-fit: contain;">
        <div>{{following.userNickName}}</div>
        <div>@{{following.userID}}</div>
        <div>팔로워 : {{following.followerCnt}}</div>
        <div>팔로잉 : {{following.followingCnt}}</div>
        <button @click="unfollowUser(follower.userNumber)" v-if="myFollowingList.includes(following.userNumber)">언팔로우</button>
        <button @click="followUser(follower.userNumber)" v-else>팔로우</button>
      </div>
    </div>
    <div></div>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  name: 'FollowList',
  data: function () {
    return {
      profile : null,
      followingList : null, // 팔로잉 리스트
      followerList : null, // 팔로워 리스트
      followerCnt: 0, // 팔로워 수
      followingCnt: 0, // 팔로잉 수

      myFollowingList: [], // 내 팔로잉 리스트

      yourUserNumber : 0,
      myUserNumber : 2,

      currentTab: 0, // 현재 탭
      tabs: ['팔로워', '팔로잉'],
    }
  },
  components: {

  },
  props: {

  },
  methods: {
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

    getFollowingList: function(){ // 팔로잉 리스트 가져오기
      axios({
        method: 'get',
        url: 'http://i6b106.p.ssafy.io:8080/user/following/' + this.yourUserNumber,
      })
      .then(response => {
        this.followingCnt = response.data.length
        this.followingList = response.data
        var ind;
        for (ind = 0; ind < response.data.length; ind++) {
          if (this.yourUserNumber == response.data[ind].userNumber){
            this.isFollow = true
          }
        }
      })
      .catch(err => {
        console.log(err)
      })
    },

    getFollowerList: function(){ // 팔로워 리스트 가져오기
      axios({
        method: 'get',
        url: 'http://i6b106.p.ssafy.io:8080/user/follower/' + this.yourUserNumber,
      })
      .then(response => {
        this.followerCnt = response.data.length
        this.followerList = response.data
      })
      .catch(err => {
        console.log(err)
      })
    },

    getMyFollowingList: function(){ // 내 팔로잉 리스트 가져오기
      this.myFollowingList = []
      axios({
        method: 'get',
        url: 'http://i6b106.p.ssafy.io:8080/user/following/' + this.myUserNumber,
      })
      .then(response => {
        var ind;
        for (ind=0;ind < response.data.length; ind++){
          this.myFollowingList.push(response.data[ind].userNumber);
        }

      })
      .catch(err => {
        console.log(err)
      })
    },

    followUser: function(toUserNumber){ // 유저 팔로우하기
      axios({
        method: 'post',
        url: 'http://i6b106.p.ssafy.io:8080/user/follow/' + this.myUserNumber + '/' + toUserNumber,
      })
      .then(() => {
        this.isFollow = true
        this.asyncCall()
      })
      .catch(err => {
        console.log(err)
      })
    },

    unfollowUser: function(toUserNumber){ // 유저 언팔로우하기
      axios({
        method: 'delete',
        url: 'http://i6b106.p.ssafy.io:8080/user/follow/' + this.myUserNumber + '/' + toUserNumber,
      })
      .then(() => {
        this.isFollow = false
        this.asyncCall()
      })
      .catch(err => {
        console.log(err)
      })
    },

    toUserFeed : function(userId){ // 유저 피드 이동
      this.$router.push({name: `UserFeed`, params : {yourUserId: userId}})
    },

    asyncCall : async function(){ // 비동기적 통신
      await this.getUserProfile();
      await this.getMyFollowingList();
      await this.getFollowerList();
      await this.getFollowingList();
    }
  },
  created: function(){
    this.asyncCall(); // 비동기적 통신
  }
}
</script>

<style>

</style>