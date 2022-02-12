<template>
  <div class="feed-detail-container">
    <!-- Feed Image -->
    <div class="feed-img">
      <span v-show="photoIndex > 0" class="material-icons left-btn" @click="leftPhoto">arrow_forward_ios</span>
      <!-- <slide v-if="myDevice==='mobile'" :data="slide" :time="0"/>
      <img v-else :src="`data:image/png;base64,${feedPhotoDtoList[photoIndex].photo}`" class="show-img" /> -->
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
          <font-awesome-icon icon="venus" v-else></font-awesome-icon>
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
        <div class="material-icons btn--comment" @click="test">chat_bubble_outline</div>
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

export default {
  name: 'FeedListItem',
  components: {
    // slide
  },
  data: function () {
    return {
      today: new Date(),
      feedUploadDate: null,
      // Mobile Web 전용 터치슬라이드
      slide: [],
      myDevice: 'mobile',
      feed : null,
      profile : null,
      photoIndex : 0,
      commentContent : null,
      myUserNumber: 1,
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
    test() {
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
    const searchEl = document.querySelector('.search-bar')
    searchEl.classList.add('inactive')
    const footerEl = document.querySelector('#footer-container')
    footerEl.classList.add('indetail')
    move('0', '0', '#faf4e4')
  },
  destroyed() {
    const searchEl = document.querySelector('.search-bar')
    searchEl.classList.remove('inactive')
    const footerEl = document.querySelector('#footer-container')
    footerEl.classList.remove('indetail')
  }
}
</script>

<style scoped>
@font-face {
  font-family: 'MinSans-Regular';
  src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2201-2@1.0/MinSans-Regular.woff') format('woff');
  font-weight: normal;
  font-style: normal;
}
@media screen and (max-width:768px){
  img {
    display: block;
  }
  .feed-detail-container{
    font-family: 'MinSans-Regular';
    width: 100%;
    max-width: 600px;
    position: relative;
    height: auto;
    top: 47px;
    margin: 0 auto;
    padding-bottom: 120px;
    z-index: -1;
    font-size: 14px;
  }
  .feed-detail-container .feed-img {
    /* width: 400px;*/
    width: 100%;
    max-width: 600px;
    position: relative;
    margin: 0 auto 15px;
  }
  .feed-detail-container .feed-img .show-img {
    /* width: 400px; */
    width: 100%;
    /* max-height: 300px;*/
    /* max-height: 30%;
    height: auto; */
    height:400px;
    /* border-radius: 30px;*/
    z-index: -10;
    object-fit: cover;
  }
  .feed-detail-container .feed-img .material-icons.left-btn {
    transform: rotate(180deg);
    position: absolute;
    top: 50%;
    left: 5px;
    width:24px; 
    margin: auto;
    background-color:#333; 
    border-radius:50%; 
    color: #fff;
  }
  .feed-detail-container .feed-img .material-icons.right-btn {
    position: absolute;
    top: 50%;
    right:5px;
    width:24px;
    margin: auto;
    background-color:#333; 
    border-radius:50%; 
    color: #fff;
  }
  .feed-detail-container .feed-profile {
    position:relative;
    display: flex;
    /* width: 350px;*/
    width: 90%;
    height: 65px;
    margin: 0 auto;
    overflow: auto;
  }
  .feed-detail-container .feed-profile .feed-profile-image {
    border-radius: 50%;
    width: 50px;
    height: 50px;
    object-fit: cover;
    position: absolute;
    left: 5px;
    top: 5px;
  }
  .feed-detail-container .feed-profile .feed-profile-nickname {
    width: 65%;
    max-width: 300px;
    font-weight: bold;
    position: absolute;
    left: 65px;
    top:0
  }
  .feed-detail-container .feed-profile .feed-profile-nickname2 {
    width: 65%;
    max-width: 300px;
    font-weight: bold;
    position: absolute;
    left: 65px;
    top: 16px;
  }
  .feed-detail-container .feed-profile .feed-profile-mypet {
    width: auto;
    position: absolute;
    left: 65px;
    top: 40px;
  }
  .feed-detail-container .feed-content {
    position: relative;
    margin: 10px auto;
    width: 90%;
    height: 130px;
    overflow: scroll;
    word-break: break-all;
  }
  .feed-detail-container .feed-upload-date {
    position: relative;
    margin: 0 auto;
    width: 90%;
  }
  .feed-detail-container .like-and-comment {
    position: relative;
    width: 90%;
    height:30px;
    top: 0px;
    margin: 10px auto;
  }
  .feed-detail-container .like-and-comment .like{
    display:flex;
    align-items: center;
    position: absolute;
    top: 0px;
    left: 0px;
    font-size: 18px;
  }
  .feed-detail-container .like-and-comment .like .material-icons.btn--like{
    color: #333;
    font-size: 26px;
  }
  .feed-detail-container .like-and-comment .like .material-icons.btn--dislike{
    /* color: #fa7b53;*/
    color: #fa7b53;
    font-size: 26px;
  }
  .feed-detail-container .like-and-comment .comment{
    display:flex;
    align-items: center;
    position: absolute;
    left: 60px;
    top: 1px;
    font-size: 18px;
  }
  .feed-detail-container .like-and-comment .comment .material-icons.btn--comment {
    font-size: 26px;
  }
  .feed-detail-container .like-and-comment .material-icons.delete-btn{
    font-size:30px;
    display:flex;
    align-items: center;
    position: absolute;
    right: 0px;
  }
  .feed-detail-container .comment-list {
    width: 90%;
    margin: 0 auto;
    min-height: 50px;
    max-height: 300px;
    overflow: scroll;
    position: relative;
    z-index: -1;
  }
  .feed-detail-container .comment-list .comment-item {
    position: relative;
    width: 100%;
    min-height: 30px;
    margin-bottom: 30px;
  }
  .feed-detail-container .comment-list .comment-item .comment-user-profile{
    position: absolute;
    left: 0px;
    width: 30px;
    height: 30px;
    object-fit: cover;
    border-radius: 50%;
  }
  .feed-detail-container .comment-list .comment-item .comment-user-nickname{
    position: absolute;
    left: 40px;
    font-weight: bold;
    cursor: pointer;
  }
  .feed-detail-container .comment-list .comment-item .comment-btn-delete{
    position: absolute;
    right: 0;
    top:0;
  }
  .feed-detail-container .comment-list .comment-item .comment-content{
    position: relative;
    left: 40px;
    top: 20px;
    width: 75%;
    word-break: break-all;
  }
  .feed-detail-container .comment-create {
    position: fixed;
    width: 90%;
    bottom: 80px;
    left:50%;
    transform: translateX(-50%);
    display: flex;
    gap: 10px;
    background: #fff;
    background-color: #fff;
    
    /* left: 50%;
    margin-left: -50%;
    display:block;
    align-items: center;
    transition: all .5s; */
    /* display: flex;
    justify-content: center; */

  }
  .feed-detail-container .comment-create .my-profile-image{
    border-radius: 50%;
    width: 30px;
    height: 30px;
  }
  .feed-detail-container .comment-create .comment-input {
    width: 70%;
    height: 30px;
    border-radius: 15px;
    outline: none;
    border: 1px solid #333;
  }
  .feed-detail-container .comment-create .comment-btn--mobile {
    font-size: 30px;
    background-color: #faf4e4;
    color: #333;
    border-radius: 50%;
  }
  .feed-detail-container .comment-create .comment-btn--pc {
    display:none;
  }
}
/* PC web */
@media screen and (min-width:768px){
  img {
    display: block;
  }
  .feed-detail-container{
    font-family: 'MinSans-Regular';
    width: 100%;
    max-width: 600px;
    position: relative;
    height: auto;
    top: 0px;
    margin: 47px auto 80px;
    z-index: 2;
    font-size: 14px;
    border: 1px solid #ccc;
    background-color: #fff;
  }
  .feed-detail-container .feed-img {
    /* width: 400px;*/
    width: 100%;
    max-width: 600px;
    position: relative;
    margin: 0 auto 15px;
  }
  .feed-detail-container .feed-img .show-img {
    width: 100%;
    height: 600px;
    z-index: -10;
    object-fit: cover;
  }
  .feed-detail-container .feed-img .material-icons.left-btn {
    transform: rotate(180deg);
    position: absolute;
    top: 50%;
    left: 5px;
    width:24px; 
    margin: auto;
    background-color:#333; 
    border-radius:50%; 
    color: #fff;
    cursor: pointer;
  }
  .feed-detail-container .feed-img .material-icons.right-btn {
    position: absolute;
    top: 50%;
    right:5px;
    width:24px;
    margin: auto;
    background-color:#333; 
    border-radius:50%; 
    color: #fff;
    cursor: pointer;
  }
  .feed-detail-container .feed-profile {
    position:relative;
    display: flex;
    /* width: 350px;*/
    width: 90%;
    height: 65px;
    margin: 0 auto;
    overflow: auto;
    cursor: pointer;
  }
  .feed-detail-container .feed-profile .feed-profile-image {
    border-radius: 50%;
    width: 50px;
    height: 50px;
    object-fit: cover;
    position: absolute;
    left: 5px;
    top: 5px;
  }
  .feed-detail-container .feed-profile .feed-profile-nickname {
    width: 65%;
    max-width: 300px;
    font-weight: bold;
    position: absolute;
    left: 65px;
    top:0
  }
  .feed-detail-container .feed-profile .feed-profile-nickname2 {
    width: 65%;
    max-width: 300px;
    font-weight: bold;
    position: absolute;
    left: 65px;
    top: 16px;
  }
  .feed-detail-container .feed-profile .feed-profile-mypet {
    width: auto;
    position: absolute;
    left: 65px;
    top: 40px;
  }
  .feed-detail-container .feed-content {
    position: relative;
    margin: 10px auto;
    width: 90%;
    overflow: auto;
    word-break: break-all;
  }
  .feed-detail-container .feed-upload-date {
    position: relative;
    margin: 0 auto;
    width: 90%;
  }
  .feed-detail-container .like-and-comment {
    position: relative;
    width: 90%;
    height:30px;
    top: 0px;
    margin: 10px auto;
  }
  .feed-detail-container .like-and-comment .like{
    display:flex;
    align-items: center;
    position: absolute;
    top: 0px;
    left: 0px;
    font-size: 18px;
    gap: 5px;
  }
  .feed-detail-container .like-and-comment .like .material-icons.btn--like{
    color: #333;
    font-size: 26px;
    cursor: pointer;
  }
  .feed-detail-container .like-and-comment .like .material-icons.btn--dislike{
    /* color: #fa7b53;*/
    color: #fa7b53;
    font-size: 26px;
    cursor: pointer;
  }
  .feed-detail-container .like-and-comment .comment{
    display:flex;
    align-items: center;
    position: absolute;
    left: 60px;
    top: 1px;
    font-size: 18px;
    gap: 5px;
  }
  .feed-detail-container .like-and-comment .comment .material-icons.btn--comment {
    font-size: 26px;
    cursor: pointer;
  }
  .feed-detail-container .like-and-comment .material-icons.delete-btn{
    font-size:30px;
    display:flex;
    align-items: center;
    position: absolute;
    right: 0px;
  }
  .feed-detail-container .comment-list {
    width: 90%;
    margin: 0 auto;
    min-height: 50px;
    overflow: auto;
    position: relative;
    z-index: -1;
  }
  .feed-detail-container .comment-list .comment-item {
    position: relative;
    width: 100%;
    min-height: 30px;
    margin-bottom: 30px;
  }
  .feed-detail-container .comment-list .comment-item .comment-user-profile{
    position: absolute;
    left: 0px;
    width: 30px;
    height: 30px;
    object-fit: cover;
    border-radius: 50%;
    cursor: pointer;
  }
  .feed-detail-container .comment-list .comment-item .comment-user-nickname{
    position: absolute;
    left: 40px;
    font-weight: bold;
    cursor: pointer;
  }
  .feed-detail-container .comment-list .comment-item .comment-btn-delete{
    position: absolute;
    right: 0;
    top:0;
  }
  .feed-detail-container .comment-list .comment-item .comment-content{
    position: relative;
    left: 40px;
    top: 20px;
    width: 75%;
    word-break: break-all;
  }
  /* .feed-detail-container .comment-create {
  /   position: relative;
  /   margin: 0 auto;
  /   width: 350px;
  /   top: 0px;
  /   left: 20px;
  /   display:flex;
  /   align-items: center;
  /   transition: all .5s;
  /   max-width: 95%;
  } */
  .feed-detail-container .comment-create {
    position: relative;
    width: 90%;
    max-width: 600px;
    height:30px;
    margin: 15px auto 20px;
    /* left: 50%;
    margin-left: -50%;
    display:block;
    align-items: center;
    transition: all .5s; */
    background: #fff;
    background-color: #fff;
    z-index: 10;
    display: flex;
    justify-content: center;
  }
  .feed-detail-container .comment-create .my-profile-image{
    position: absolute;
    left: 0;
    border-radius: 50%;
    width: 30px;
    height: 30px;
    margin-right: 10px;
  }
  .feed-detail-container .comment-create .comment-input {
    position: absolute;
    left: 40px;
    width: 444px;
    height: 30px;
    /* border-radius: 15px; */
    outline: none;
    border: 1px solid #aaa;
  }
  .feed-detail-container .comment-create .comment-btn--pc {
    position: absolute;
    display: flex;
    align-items: center;
    justify-content: center;
    top: 0px;
    right : 0px;
    font-size: 17px;
    width: 49px;
    height: 32px;
    border: 1px solid #aaa;
    /* background-color: #faf4e4; */
    color: #333;
    /* border-radius: 10px; */
    cursor: pointer;
  }
  .feed-detail-container .comment-create .comment-btn--mobile {
    display:none;
  }
}
</style>