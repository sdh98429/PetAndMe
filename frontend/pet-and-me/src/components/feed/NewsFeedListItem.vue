<template>
  <!-- NewsFeed Item -->
  <div class="feed-container">
    <!-- Feed User Profile -->
    <div class="feed-profile" @click="toUserFeed(profile.userID)">
      <img 
        class="feed-profile-image" 
        :src="`http://i6b106.p.ssafy.io:8080/main/image?file=${profile.saveFolder}${profile.userPhotoName}`" 
        alt="프로필 사진"
      />
      <div class="feed-profile-nickname">{{profile.userNickName}}</div>
      <div class="feed-profile-nickname2">@{{profile.userID}}</div>
      <div class="feed-profile-mypet">
          {{profile.petName}}({{profile.animalName}}),
          <font-awesome-icon icon="mars" v-if="profile.petGender==='M'" style="font-size:18px"></font-awesome-icon>
          <font-awesome-icon icon="venus" v-else style="font-size:18px"></font-awesome-icon>
          ,<span v-if="petMonth < 12">{{petMonth}}개월</span><span v-else>{{petAge}}살</span>
      </div>
    </div>
    
    <!-- Feed Img -->
    <div class="feed-img">
      <span v-show="photoIndex > 0" class="material-icons left-btn" @click="leftPhoto">arrow_forward_ios</span>
      <img 
        :src="`http://i6b106.p.ssafy.io:8080/main/image?file=${feedPhotoDtoList[photoIndex].saveFolder}${feedPhotoDtoList[photoIndex].photoName}`" 
        class="show-img" 
      />
      <span v-show="photoIndex < feedPhotoDtoList.length-1" class="material-icons right-btn" @click="rightPhoto">arrow_forward_ios</span>
    </div>

    <!-- Feed Content -->
    <div class="feed-content" @click="goToFeedDetail">
      {{feedContent}}
    </div>
    <div v-if="isOver" class="feed-content-more-btn" @click="goToFeedDetail">더 보기</div>
    <div class="feed-upload-date">{{feedUploadDate}}</div>

    <!-- Like and Comment -->
    <div class="like-and-comment">
      <!-- Like Count -->
      <div class="like">
        <div class="material-icons btn--like" v-if="!checkLike" @click="likeFeed">favorite_border</div>
        <div class="material-icons btn--dislike" v-else @click="likeFeed">favorite</div>
        <div class="like-cnt">{{cntLike}}</div>
      </div>
      <!-- Comment Count -->
      <div class="comment" @click="goToComment">
        <div class="material-icons btn--comment">chat_bubble_outline</div>
        <div class="comment-cnt">{{cntComment}}</div>
      </div>
    </div>

    <!-- Highlight Comment -->
    <div class="comment-list" @click="goToComment">
      <div v-if="!highlightComment">댓글이 없습니다.</div>
      <div v-else class="comment-item" @click="goToComment">
        <img :src="`data:image/png;base64,${highlightComment.userProfilePhoto}`" alt="프로필 사진" class="comment-user-profile">
        <div class="comment-user-nickname">{{highlightComment.userNickName}}</div>
        <div class="comment-content">{{highlightComment.commentContent}}</div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios'
import move from '@/js/move.js'
import {BASE_API_URL} from '@/config/config.js'


export default {
  name: 'NewsFeedListItem',
  data: function () {
    return {
      today: new Date(),
      profile : null,
      photoIndex : 0,

      feedUserNumber: this.feed.userNumber,
      feedNumber: this.feed.feedNumber,
      feedContent: this.feed.feedContent,
      feedDate: this.feed.feedDate,
      cntLike: this.feed.cntLike,
      cntComment: this.feed.cntComment,
      feedPhotoDtoList: this.feed.feedPhotoDtoList,
      checkLike : this.feed.checkLike, // 좋아요 체크 추가
      isOver : null,
      petMonth: null,
      petAge: null,
      highlightComment: null,
    }
  },
  components: {

  },
  props: {
    feed : Object,
    feedUploadDate : String,
  },
  methods: {
    goToComment() {
      this.$router.push({name: 'FeedDetail', params: { feedNumber: this.feedNumber }})
      window.scrollTo({top:300, behavior:'smooth'})
    },
    getUploadDate() {
      const tempdate = new Date(this.feed.feedDate)
      const betweenTime = Math.floor((this.today.getTime()-tempdate.getTime())/60000)
      if (betweenTime < 2) return '방금전';
      if (betweenTime < 60) {
          return `${parseInt(betweenTime)}분전`;
      }

      const betweenTimeHour = Math.floor(betweenTime / 60);
      if (betweenTimeHour < 24) {
          return `${parseInt(betweenTimeHour)}시간전`;
      }

      const betweenTimeDay = Math.floor(betweenTime / 60 / 24);
      if (betweenTimeDay < 365) {
          return `${parseInt(betweenTimeDay)}일전`;
      }
    },
    getComments: function(){
      axios({
        method: 'get',
        url: `${BASE_API_URL}/comment/${this.feedNumber}` ,
      })
        .then(response => {
          this.highlightComment = response.data[0]
        })
        .catch(err => {
          console.log(err)
        })
    },
    getPetAge() {
      // const tempdate = new Date(`${this.profile.petBirth.slice(0,4)}-${this.profile.petBirth.slice(4,6)}-${this.profile.petBirth.slice(6,8)}`)
      const tempdate = new Date(this.profile.petBirth)
      this.petMonth = parseInt(Math.floor((this.today.getTime()-tempdate.getTime())/(24 * 60 * 60 * 1000 * 30)))
      this.petAge = parseInt(this.petMonth/12)
    },
    likeFeed: function(){
      console.log('ok')
      if (!this.checkLike){
        axios({
          method: 'post',
          url: `${BASE_API_URL}/like/${this.myUserNumber}/${this.feed.feedNumber}`,
        })
        .then(() =>{
          this.cntLike += 1
          this.checkLike = !this.checkLike
        })
        .catch((err)=> {
          console.log(err)
        })
      } else {
        axios({
          method: 'delete',
          url: `${BASE_API_URL}/like/${this.myUserNumber}/${this.feed.feedNumber}`,
        })
        .then(() =>{
          this.cntLike -= 1
          this.checkLike = !this.checkLike
        })
        .catch((err)=> {
          console.log(err)
        })
      }
    },

    getUserProfile: function(){
      axios({
        method: 'get',
        url: `${BASE_API_URL}/user/info/${this.feedUserNumber}`,
      })
        .then(response => {
          this.profile = response.data
          this.getPetAge()
        })
        .catch(err => {
          console.log(err)
        })
    },

    leftPhoto: function(){
      if(this.photoIndex > 0){
        this.photoIndex -= 1
      }
    }, 
    rightPhoto: function(){
      if(this.photoIndex < this.feedPhotoDtoList.length-1){
        this.photoIndex += 1
      }
    },

    goToFeedDetail(){
      this.$router.push({path: `/feed/${this.feed.feedNumber}`})
      move('0','0','#faf4e4')
      
    },

    toUserFeed : function(userId){
      this.$router.push({name: `UserFeed`, params : {yourUserId: userId}})
    }
  },
  created() {
      this.getUserProfile()
      if(this.feedContent.length > 130){
        this.isOver = true
      } else {
        this.isOver = false
      }
      this.getComments()
  },
  mounted() {
  },
  computed: {
    myUserNumber () {
      return this.$store.getters.getUserNumber
    }
  },
}
</script>

<style scoped src="../../css/newsfeed.css">

</style>