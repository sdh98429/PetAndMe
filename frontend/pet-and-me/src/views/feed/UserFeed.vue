<template>
  <div class="user-feed-container">
    <div v-show="!ready" class="loader"></div>
    <div class="user-profile" v-show="ready">
      <input v-if="(yourUserNumber == myUserNumber)" @change='onInputImage()' accept="image/*" ref="image" type="file" style="display : none">
      <img 
        @click="profileChange()" 
        :src="`http://i6b106.p.ssafy.io:8080/main/image?file=${profile.saveFolder}${profile.userPhotoName}`" 
        alt="프로필 사진"
        class="user-profile-image"
      />

      <div class="nickname">
        {{profile.userNickName}}
        <div 
          v-if="(myUserNumber == yourUserNumber)" 
          @click="toUserFeedUpdate" 
          class="material-icons profile-change-btn"
        >settings
        </div>
      </div>
      <div class="id">@{{profile.userID}}</div>
      <div class="profile-content">{{profile.userProfileContent}}</div>
      
      <div class="user-pet">
        {{profile.petName}}({{profile.animalName}}),
        <font-awesome-icon icon="mars" v-if="profile.petGender==='M'" style="font-size:18px"></font-awesome-icon>
        <font-awesome-icon icon="venus" style="font-size:18px" v-else></font-awesome-icon>
        ,<span v-if="petMonth < 12">{{petMonth}}개월</span><span v-else>{{petAge}}살</span>
      </div>
      <!-- <TapingViewer
        :returnUserId="this.yourUserId"
        class="taping-list"
      >
      </TapingViewer> -->
      <div class="follow-and-feed-mobile">
        <div class="feed-length">게시글<br>{{feedLength}}</div>
        <div class="follower" @click="toFollowList()">팔로워<br>{{followerCnt}}</div>
        <div class="follwing" @click="toFollowList()">팔로잉<br>{{followingCnt}}</div>
      </div>
      <div class="follow-and-feed">
        <div class="feed-length">게시글&nbsp;{{feedLength}}</div>
        <div class="follower" @click="toFollowList()">팔로워&nbsp;{{followerCnt}}</div>
        <div class="follwing" @click="toFollowList()">팔로잉&nbsp;{{followingCnt}}</div>
      </div>
      <div class="taping-thumbnail" v-if="returnVideo[0]">
        <video controls>
          <source :src="`data:video/mp4;base64,${returnVideo[0]}`" type="video/mp4">
        </video>
      </div>
      <div v-if="yourUserNumber != myUserNumber">
        <button class="follow-btn bttn-gradient bttn-sm bttn-warning bttn-block" v-if="!isFollow" @click="followUser">팔로우</button>
        <button class="follow-btn bttn-gradient bttn-sm bttn-warning bttn-block" v-else @click="unfollowUser">언팔로우</button>
      </div>
    </div>
    <div class="user-feed-list" v-show="ready">
      <UserFeedList
        :your-user-number="yourUserNumber"
        @feed-length="getFeedLength"
      />
    </div>
  </div>
</template>

<script>
import axios from 'axios'
import UserFeedList from '@/components/feed/UserFeedList'
import {BASE_API_URL, VIDEO_API_URL} from '@/config/config.js'
import '@/css/userfeed.css'
import move from '@/js/move.js'
// import TapingViewer from '@/components/taping/TapingViewer'


export default {
  name: 'UserFeed',
  data: function () {
    return {
      ready: true,
      today: new Date(),
      profile: null,
      petMonth: null,
      petAge: null,
      userNumber: "null",
      yourUserId: this.$route.params.yourUserId,
      yourUserNumber: 0,
      isFollow : false,
      followerCnt : 0,
      followingCnt : 0,
      feedLength: null,
      files: [],
      returnVideo: null,
    }
  },
  components: {
    UserFeedList,
    // TapingViewer

  },
  props: {

  },
  methods: {
    catchTape() {
      axios({
        method: 'post',
        url: `${VIDEO_API_URL}/api/v1/returntape/`,
        data: {returnUserId:this.yourUserId},
      })
        .then(res => {
          this.returnVideo = res.data
          console.log(this.returnVideo)
        })
        .catch(err => {
          console.log(err)
        })
    },
    getPetAge() {
      // const tempdate = new Date(`${this.profile.petBirth.slice(0,4)}-${this.profile.petBirth.slice(4,6)}-${this.profile.petBirth.slice(6,8)}`)
      const tempdate = new Date(this.profile.petBirth);
      this.petMonth = parseInt(Math.floor((this.today.getTime()-tempdate.getTime())/(24 * 60 * 60 * 1000 * 30)))
      this.petAge = parseInt(this.petMonth/12)
    },
    getFeedLength(length) {
      this.feedLength = length
    },

    getUserProfile: async function(){ // 프로필 정보 가져오기
        await axios({
        method: 'get',
        url: `${BASE_API_URL}/user/number/${this.yourUserId}`,
      })
        .then(response => {
          this.yourUserNumber = response.data
          this.catchTape()
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
            // console.log(response.data)
            this.getPetAge()
          })
          .catch(err => {
            console.log(err)
          })
    },

    getMyFollow() {
      axios({
        method: 'get',
        url: `${BASE_API_URL}/user/following/${this.myUserNumber}`
      })
        .then(res => {
          res.data.forEach(user => {
            if (user.userNumber == this.yourUserNumber){
              this.isFollow=true
            }
          })
        })
    },

    getFollowing: function(){
      axios({
        method: 'get',
        url: `${BASE_API_URL}/user/following/${this.yourUserNumber}`,
      })
      .then(response => {
        this.followingCnt = response.data.length
        // var ind;
        // for (ind = 0; ind < response.data.length; ind++) {
        //   if (this.yourUserNumber == response.data[ind].userNumber){
        //     this.isFollow = true
        //   }
        // }
        // console.log(response.data)
      })
      .catch(err => {
        console.log(err)
      })
    },

    getFollower: function(){
      axios({
        method: 'get',
        url: `${BASE_API_URL}/user/follower/${this.yourUserNumber}`,
      })
      .then(response => {
        this.followerCnt = response.data.length
        // var ind;
        // for (ind = 0; ind < response.data.length; ind++) {
        //   if (this.yourUserNumber == response.data[ind].userNumber){
        //     this.isFollow = true
        //   }
        // }
      })
      .catch(err => {
        console.log(err)
      })
    },

    followUser: function(){
      console.log(this.isFollow)
      console.log()
      axios({
        method: 'post',
        url: `${BASE_API_URL}/user/follow/${this.myUserNumber}/${this.yourUserNumber}`,
      })
      .then(() => {
        this.isFollow = true
        this.getFollowing()
        this.getFollower()
        this.getMyFollow()
      })
      .catch(err => {
        console.log(err)
      })
    },

    unfollowUser: function(){
      axios({
        method: 'delete',
        url: `${BASE_API_URL}/user/follow/${this.myUserNumber}/${this.yourUserNumber}`,
      })
      .then(() => {
        this.isFollow = false
        this.getFollowing()
        this.getFollower()
        this.getMyFollow()
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
        if (this.files.length) {
          var formData = new FormData();

          formData.append('userPhoto', this.files[0]);

          formData.append("userNumber", JSON.stringify(this.yourUserNumber));

          axios({
              method: 'put',
              url: `${BASE_API_URL}/user/userPhoto`,
              data: formData,
              header: {
                        // 'Accept': 'application/json',
                        'Content-Type': 'multipart/form-data',
                      },
          })
          .then(() => {
              this.getUserProfile()
              alert('프로필 사진이 변경되었습니다.')
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
      await this.getUserProfile();
      await this.getFollowing();
      await this.getFollower();
      await this.getMyFollow();
      await this.catchTape();
    }

  },
  created: function(){
    this.asyncCall()
    this.catchTape()
  },
  mounted() {
    move('5','90%','#fff')
  },
  computed: {
    myUserNumber () {
      return this.$store.getters.getUserNumber
    },
    // getUserInfo() {
    //   return this.$store.getters.getUserInfo
    // }
  },
}
</script>

<style>

</style>