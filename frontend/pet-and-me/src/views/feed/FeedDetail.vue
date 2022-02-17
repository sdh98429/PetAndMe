<template>
  <div class="feed-detail-container">
    <!-- Feed Image -->
    <div class="feed-img">
      <span v-show="photoIndex > 0" class="material-icons left-btn" @click="leftPhoto">arrow_forward_ios</span>
      <img :src="`data:image/png;base64,${feedPhotoDtoList[photoIndex].photo}`" class="show-img" />
      <span v-show="photoIndex < feedPhotoDtoList.length-1" class="material-icons right-btn" @click="rightPhoto">arrow_forward_ios</span>
    </div>

    <!-- Feed User Profile -->
    <div v-if="profile" class="feed-profile">
      <img 
        @click="toUserFeed(profile.userID)" 
        class="feed-profile-image" 
        :src="`http://i6b106.p.ssafy.io:8080/main/image?file=${profile.saveFolder}${profile.userPhotoName}`" 
        alt="프로필 사진"
      />
      <div class="feed-profile-nickname">{{profile.userNickName}}</div>
      <div class="feed-profile-nickname2">@{{profile.userID}}</div>
      <div class="feed-profile-mypet">
          {{profile.petName}}({{profile.animalName}}),
          <font-awesome-icon icon="mars" v-if="profile.petGender==='M'" style="font-size:18px"></font-awesome-icon>
          <font-awesome-icon icon="venus" style="font-size:18px" v-else></font-awesome-icon>
          ,<span v-if="petMonth < 12">{{petMonth}}개월</span><span v-else>{{petAge}}살</span>
      </div>
    </div>

    <!-- Feed Content -->
    <div class="feed-content">{{feedContent}}</div>
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
      <div class="comment">
        <div class="material-icons btn--comment" @click="goToComment">chat_bubble_outline</div>
        <div class="comment-cnt">{{cntComment}}</div>
      </div>
      <div v-if="myUserNumber === feedUserNumber" class="material-icons delete-btn" @click="deleteFeedDetail">delete_outline</div>
    </div>

    <div class="comment-create">
      <img class="my-profile-image" src="@/assets/unknown_image.jpg" alt="">
      <input class="comment-input" type="text" v-model.trim="commentContent" placeholder="댓글을 남겨주세요" @keyup.enter="createComment">
      <div class="material-icons comment-btn--mobile" @click="createComment">north</div>
      <div class="comment-btn--pc" @click="createComment">Enter</div>
      <!-- <span  class="comment-btn" @click="createComment">게시</span> -->
      <!-- <img src="@/assets/arrow.png" alt="" class="comment-btn"> -->
      <!-- <div  class="material-icons comment-btn" @click="createComment">done_outline</div> -->
      <!-- <font-awesome-icon icon="arrow-right" class="comment-btn" @click="createComment"></font-awesome-icon> -->
    </div>
  
    <div class="comment-list">
      <div v-if="commentList.length == 0">댓글이 없습니다.</div>
      <div v-for="(comment, idx) in commentList" :key="'comment' + idx" class="comment-item">
        <img :src="`data:image/png;base64,${comment.userProfilePhoto}`" alt="프로필 사진" class="comment-user-profile">
        <div class="comment-user-nickname">{{comment.userNickName}}</div>
        <div class="comment-content">{{comment.commentContent}}</div>
        <div class="material-icons comment-btn-delete" v-if="comment.userNumber === myUserNumber" @click="deleteComment(comment)">clear</div>
      </div>
    </div>

  </div>
</template>

<script>
import axios from 'axios'
import move from '@/js/move.js'
import {BASE_API_URL} from '@/config/config.js'
import '@/css/feeddetail.css'

export default {
  name: 'FeedDetail',
  components: {
  },
  data: function () {
    return {
      today: new Date(),
      feedUploadDate: null,
      feed : null,
      profile : null,
      photoIndex : 0,
      commentContent : null,

      feedUserNumber: null,
      feedContent: null,
      feedDate: null,
      commentList: [],
      cntLike: null,
      cntComment: null,
      commentDtoList: null,
      feedPhotoDtoList: null,
      checkLike: null,
      petAge: null,
      petMonth: null,
      commentUploadDate: null,
    }
  },
  props: {
  },
  methods: {
    goToComment() {
      const commentInputEl = document.querySelector('.comment-input')
      const commentListEl = document.querySelector('.comment-list').offsetTop
      window.scrollTo({top:commentListEl+200, behavior:'smooth'})
      commentInputEl.focus()
    },
    getFeed: function(){ // 피드 가져오기
      axios({
        method: 'get',
        url: `${BASE_API_URL}/main/feed/${this.myUserNumber}/${this.$route.params.feedNumber}`,
      })
        .then(response => {
          this.feedUserNumber = response.data.userNumber
          this.feed = response.data
          this.feedContent = response.data.feedContent
          this.feedDate = response.data.feedDate
          this.feedUploadDate = this.getUploadDate()  
          this.cntLike = response.data.cntLike
          this.cntComment = response.data.cntComment
          this.commentDtoList = response.data.commentDtoList
          this.checkLike = response.data.checkLike
          this.feedPhotoDtoList = response.data.feedPhotoDtoList
          this.checkLike = response.data.checkLike

          response.data.feedPhotoDtoList.forEach(el => {
            this.slide.push(`data:image/png;base64,${el.photo}`)
          });
          this.getUserProfile()
        })
        .catch(err =>{
          console.log(err)
        })
    },
    deleteFeedDetail: function(){ // 상세 피드 삭제
      axios({
        method: 'delete',
        url: `${BASE_API_URL}/main/feed/${this.$route.params.feedNumber}`,
      })
        .then(()=> {
          this.$router.push('NewsFeed')
        })
        .catch((err) => {
          console.log(err)
        })
    },

    likeFeed: function(){ // 좋아요 로직 변경
      if (!this.checkLike){
        axios({
          method: 'post',
          url: `${BASE_API_URL}/like/${this.myUserNumber}/${this.$route.params.feedNumber}`,
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
          url: `${BASE_API_URL}/like/${this.myUserNumber}/${this.$route.params.feedNumber}`,
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

    getComments: function(){ // 댓글 가져오기
      axios({
        method: 'get',
        url: `${BASE_API_URL}/comment/${this.$route.params.feedNumber}` ,
      })
        .then(response => {
          this.commentList = response.data
        })
        .catch(err => {
          console.log(err)
        })
    },

    deleteComment: function(comment){ // 댓글 삭제하기
      axios({
        method: 'delete',
        url: `${BASE_API_URL}/comment/${comment.commentNumber}`,
      })
        .then(()=> {
          this.getComments()
          this.getFeed()
        })
        .catch((err) => {
          console.log(err)
        })
    },

    getUserProfile: function(){ // 유저 프로필 가져오기
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

    createComment: function(){ // 댓글 생성
      if (this.commentContent) {
        const commentItem = {
          commentContent : this.commentContent
        }

        axios({
          method: 'post',
          url: `${BASE_API_URL}/comment/${this.$route.params.feedNumber}/${this.myUserNumber}`,
          data: commentItem,
        })
        .then(() => {
          this.getComments()
          this.getFeed()
          this.commentContent = null // 댓글 초기화
        })
        .catch(err => {
          console.log(err)
        })
        const commentListEl = document.querySelector('.comment-list')
        commentListEl.scrollTo({top : 0, behavior:'smooth'})
        window.scrollTo({top : 1000, behavior: 'smooth'})
      }
    },

    leftPhoto: function(){ // 앞 사진으로 이동
      if(this.photoIndex > 0){
        this.photoIndex -= 1
      }
    },
    rightPhoto: function(){ // 뒷 사진으로 이동
      if(this.photoIndex < this.feedPhotoDtoList.length-1){
        this.photoIndex += 1
      }
    },

    toUserFeed : function(userId){
      this.$router.push({name: `UserFeed`, params : {UserId: userId}})
      move('0', '0', '#faf4e4')
    },
    getUploadDate() {
      const tempdate = new Date(this.feedDate)
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
    getPetAge() {
      // const tempdate = new Date(`${this.profile.petBirth.slice(0,4)}-${this.profile.petBirth.slice(4,6)}-${this.profile.petBirth.slice(6,8)}`)
      const tempdate = new Date(this.profile.petBirth);
      this.petMonth = parseInt(Math.floor((this.today.getTime()-tempdate.getTime())/(24 * 60 * 60 * 1000 * 30)))
      this.petAge = parseInt(this.petMonth/12)
    }
  },
  // watch: {
  //   commentContent() {
  //     const commentBtn = document.querySelector('.material-icons.comment-btn')
  //     if (!this.commentContent){
  //       commentBtn.classList.remove('btn--active')
  //     } else{
  //       commentBtn.classList.add('btn--active')
  //     }
  //   }
  // },
  created() {
    this.getFeed()
    this.getComments()
  },
  mounted(){
    // const searchEl = document.querySelector('.search-bar')
    // searchEl.classList.add('inactive')
    const footerEl = document.querySelector('#footer-container')
    footerEl.classList.add('indetail')
    const searchEl = document.querySelector('.my-search-bar')
    searchEl.classList.add('inactive')
    move('0', '0', '#faf4e4')
  },
  destroyed() {
    // const searchEl = document.querySelector('.search-bar')
    // searchEl.classList.remove('inactive')
    const searchEl = document.querySelector('.my-search-bar')
    searchEl.classList.remove('inactive')
    const footerEl = document.querySelector('#footer-container')
    footerEl.classList.remove('indetail')
  },
  computed: {
    myUserNumber () {
      return this.$store.getters.getUserNumber
    }
  },
}
</script>

<style scoped>
</style>