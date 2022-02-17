<template>
  <div class="follow-container">
    <h2>{{profile.userNickName}}</h2>
    <button class="back-btn bttn-gradient bttn-md bttn-default" @click="goBack">돌아가기</button>
    <!-- <button v-for="(tab, index) in tabs"
      :key="index"
      v-bind="{active: currentTab === index}"
      class="follow-toggle-btn"
      @click="currentTab = index">{{tab}}</button> -->
    <div class="follow-toggle-btn">
      <button class="follow-list-btn isactive" @click="followView">팔로워</button>
      <button class="following-list-btn" @click="followingView">팔로잉</button>
    </div>
    <div v-show="currentTab==0" v-if="followerCnt" class="follow-list">
      <div v-for="(follower, ind) in followerList" :key="`follower${ind}`" class="follow-item">
        <img @click="toUserFeed(follower.userID)" class="follow-image" :src="`http://i6b106.p.ssafy.io:8080/main/image?file=${follower.saveFolder}${follower.userPhotoName}`" alt="프로필 사진">
        <div class="follow-user" @click="toUserFeed(follower.userID)">{{follower.userNickName}}<br>@{{follower.userID}}</div>
        <div v-if="follower.userNumber != myUserNumber">
          <button class="follow-btn bttn-gradient bttn-md bttn-warning" @click="unfollowUser(follower.userNumber)" v-if="myFollowingList.includes(follower.userNumber)">언팔로우</button>
          <button class="follow-btn bttn-gradient bttn-md bttn-warning" @click="followUser(follower.userNumber)" v-else>팔로우</button>
        </div>
        <div class="follow-user-pet">
          {{follower.petName}}({{follower.animalName}}),
          <font-awesome-icon icon="mars" v-if="follower.petGender==='M'" style="font-size:18px"></font-awesome-icon>
          <font-awesome-icon icon="venus" style="font-size:18px" v-else></font-awesome-icon>
          ,<span v-if="follower.petMonth < 12">{{follower.petMonth}}개월</span><span v-else>{{follower.petAge}}살</span>
        </div>
      </div>
    </div>
    <div v-show="currentTab==1" v-if="followingCnt" class="follow-list">
      <div v-for="(following, ind) in followingList" :key="`following${ind}`" class="follow-item">
        <img @click="toUserFeed(following.userID)" class="follow-image" :src="`http://i6b106.p.ssafy.io:8080/main/image?file=${following.saveFolder}${following.userPhotoName}`" alt="프로필 사진">
        <div class="follow-user" @click="toUserFeed(following.userID)">{{following.userNickName}}<br>@{{following.userID}}</div>
        <div v-if="following.userNumber != myUserNumber">
          <button class="follow-btn bttn-gradient bttn-md bttn-warning" @click="unfollowUser(following.userNumber)" v-if="myFollowingList.includes(following.userNumber)">언팔로우</button>
          <button class="follow-btn bttn-gradient bttn-md bttn-warning" @click="followUser(following.userNumber)" v-else>팔로우</button>
        </div>
        <div class="follow-user-pet">
          {{following.petName}}({{following.animalName}}),
          <font-awesome-icon icon="mars" v-if="following.petGender==='M'" style="font-size:18px"></font-awesome-icon>
          <font-awesome-icon icon="venus" style="font-size:18px" v-else></font-awesome-icon>
          ,<span v-if="following.petMonth < 12">{{following.petMonth}}개월</span><span v-else>{{following.petAge}}살</span>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios'
import '@/css/followlist.css'
import { BASE_API_URL } from '@/config/config.js'

export default {
  name: 'FollowList',
  data: function () {
    return {
      today: new Date(),
      profile : null,
      petMonth: null,
      petAge: null,
      followingList : null, // 팔로잉 리스트
      followerList : null, // 팔로워 리스트
      followerCnt: 0, // 팔로워 수
      followingCnt: 0, // 팔로잉 수

      myFollowingList: [], // 내 팔로잉 리스트

      yourUserNumber : 0,

      currentTab: 0, // 현재 탭
      tabs: ['팔로워', '팔로잉'],
    }
  },
  components: {

  },
  props: {

  },
  methods: {
    goBack() {
      history.back()
    },
    followView() {
      this.currentTab = 0
      const btn1El = document.querySelector('.follow-list-btn')
      const btn2El = document.querySelector('.following-list-btn')
      btn1El.classList.add('isactive')
      btn2El.classList.remove('isactive')
    },
    followingView() {
      this.currentTab = 1
      const btn1El = document.querySelector('.follow-list-btn')
      const btn2El = document.querySelector('.following-list-btn')
      btn1El.classList.remove('isactive')
      btn2El.classList.add('isactive')
    },
    getPetAge(user) {
      // const tempdate = new Date(`${this.profile.petBirth.slice(0,4)}-${this.profile.petBirth.slice(4,6)}-${this.profile.petBirth.slice(6,8)}`)
      const tempdate = new Date(user.petBirth);
      user.petMonth = parseInt(Math.floor((this.today.getTime()-tempdate.getTime())/(24 * 60 * 60 * 1000 * 30)))
      user.petAge = parseInt(user.petMonth/12)
    },
    getUserProfile: async function(){ // 프로필 정보 가져오기
      await axios({
        method: 'get',
        url: `${BASE_API_URL}/user/number/${this.$route.params.yourUserId}`,
      })
        .then(response => {
          this.yourUserNumber = response.data
        })
        .catch(err => {
          console.log(err)
        })

      await axios({
        method: 'get',
        url: `${BASE_API_URL}/user/info/${this.yourUserNumber}`,
      })
        .then(response => {
          this.profile = response.data
          this.getPetAge()
        })
        .catch(err => {
          console.log(err)
        })
    },

    getFollowingList: async function(){ // 팔로잉 리스트 가져오기
      await axios({
        method: 'get',
        url: `${BASE_API_URL}/user/following/${this.yourUserNumber}`,
      })
      .then(response => {
        this.followingCnt = response.data.length
        this.followingList = response.data
      })
      .catch(err => {
        console.log(err)
      })
      await this.followingList.forEach(follow => {
          axios({
            method: 'get',
            url: `${BASE_API_URL}/user/info/${follow.userNumber}`
          })
            .then(res =>{
              follow.petBirth = res.data.petBirth
              follow.petGender = res.data.petGender
              follow.petName = res.data.petName
              follow.animalName = res.data.animalName
              this.getPetAge(follow)
            })
            .catch(err =>{
              console.log(err)
            })
        })
    },

    getFollowerList: async function(){ // 팔로워 리스트 가져오기
      await axios({
        method: 'get',
        url: `${BASE_API_URL}/user/follower/${this.yourUserNumber}`,
      })
      .then(response => {
        this.followerCnt = response.data.length
        this.followerList = response.data
        
      })
      .catch(err => {
        console.log(err)
      })
      await this.followerList.forEach(follow => {
          axios({
            method: 'get',
            url: `${BASE_API_URL}/user/info/${follow.userNumber}`
          })
            .then(res =>{
              follow.petBirth = res.data.petBirth
              follow.petGender = res.data.petGender
              follow.petName = res.data.petName
              follow.animalName = res.data.animalName
              this.getPetAge(follow)
            })
            .catch(err =>{
              console.log(err)
            })
        })
    },

    getMyFollowingList: function(){ // 내 팔로잉 리스트 가져오기
      this.myFollowingList = []
      axios({
        method: 'get',
        url: `${BASE_API_URL}/user/following/${this.myUserNumber}`,
      })
      .then(response => {
        response.data.forEach(user => {
          this.myFollowingList.push(user.userNumber)
        })
      })
      .catch(err => {
        console.log(err)
      })
    },

    followUser: function(toUserNumber){ // 유저 팔로우하기
      axios({
        method: 'post',
        url: `${BASE_API_URL}/user/follow/${this.myUserNumber}/${toUserNumber}`,
      })
      .then(() => {
        this.myFollowingList.push(toUserNumber)
      })
      .catch(err => {
        console.log(err)
      })
    },

    unfollowUser: function(toUserNumber){ // 유저 언팔로우하기
      axios({
        method: 'delete',
        url: `${BASE_API_URL}/user/follow/${this.myUserNumber}/${toUserNumber}`,
      })
      .then(() => {
        this.myFollowingList = this.myFollowingList.filter(user => {
          return user != toUserNumber
        })
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
  },
  computed: {
    myUserNumber () {
      return this.$store.getters.getUserNumber
    },
    userInfo() {
      return this.$store.getters.getUserInfo
    }
  },
}
</script>

<style>

</style>