<template>
  <div style="border: 1px solid;">
    <h2>피드 디테일 페이지</h2>
    <button v-if="myUserNumber == feedUserNumber" @click="deleteFeedDetail">피드 삭제하기</button>
    <div>프로필</div>
    <div v-if="profile">{{profile}}</div>

    <!-- <div>{{feed}}</div> -->
    <div>유저 번호 : {{feedUserNumber}}</div>
    <div>피드 내용 : {{feedContent}}</div>
    <div>피드 날짜 : {{feedDate}}</div>
    <div>좋아요 수 : {{cntLike}}</div>
    <div>댓글 개수 : {{cntComment}}</div>

    <!-- <div v-if="feedPhotoDtoList">이미지 개수 : {{feedPhotoDtoList.length}}</div> -->
    <div v-for="(file, idx) in feedPhotoDtoList" :key="'photo' + idx">
      <button v-show="idx == photoIndex && idx > 0" @click="leftPhoto">왼쪽</button>
      <img v-show="idx == photoIndex" :src="`data:image/png;base64,${feedPhotoDtoList[idx].photo}`" />
      <button v-show="idx == photoIndex && idx < feedPhotoDtoList.length - 1" @click="rightPhoto">오른쪽</button>
    </div>
  
    <button>좋아요</button>
    <br>

    <textarea id="textarea" v-model.trim = "commentContent" placeholder="댓글을 달아주세요." cols="30" rows="10"></textarea>
    <button @click="createComment">댓글 생성</button>
    <div v-if="commentList.length == 0">댓글이 없습니다.</div>
    <!-- <div>{{commentList}}</div> -->
    <div v-if="commentList.length != 0">댓글이 {{commentList.length}}개 있습니다.</div>
    <div v-for="(comment, idx) in commentList" :key="'comment' + idx">
      <div>{{comment.userNickName}} : {{comment.commentContent}}</div>
      <button v-if="comment.userNumber == myUserNumber" @click="deleteComment(comment)">X</button>
    </div>

  </div>
</template>

<script>
import axios from 'axios'

export default {
  name: 'FeedListItem',
  data: function () {
    return {
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

    }
  },
  components: {
  },
  props: {
  },
  methods: {
    getFeed: function(){ // 피드 가져오기
      axios({
        method: 'get',
        url: 'http://i6b106.p.ssafy.io:8080/main/feed/' + this.$route.params.feedNumber,
      })
        .then(response => {
          this.feed = response.data

          this.feedUserNumber = response.data.userNumber

          this.feedContent = response.data.feedContent
          this.feedDate = response.data.feedDate
          this.cntLike = response.data.cntLike
          this.cntComment = response.data.cntComment
          this.commentDtoList = response.data.commentDtoList
          this.feedPhotoDtoList = response.data.feedPhotoDtoList
          this.getUserProfile()
        })
        .catch(err =>{
          console.log(err)
        })
    },

    deleteFeedDetail: function(){ // 상세 피드 삭제
      axios({
        method: 'delete',
        url: 'http://i6b106.p.ssafy.io:8080/feed/' + this.$route.params.feedNumber,
      })
        .then(()=> {
          this.$router.push('NewsFeed')
        })
        .catch((err) => {
          console.log(err)
        })
    },

    getComments: function(){ // 댓글 가져오기
      axios({
        method: 'get',
        url: 'http://i6b106.p.ssafy.io:8080/comment/' + this.$route.params.feedNumber ,
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
        url: 'http://i6b106.p.ssafy.io:8080/comment/' + comment.commentNumber,
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
        url: 'http://i6b106.p.ssafy.io:8080/users/info/' + this.feedUserNumber,
      })
        .then(response => {
          this.profile = response.data
        })
        .catch(err => {
          console.log(err)
        })
    },

    createComment: function(){ // 댓글 생성
      if (this.commentContent) {
        console.log(this.commentContent)
        const commentItem = {
          commentContent : this.commentContent
        }

        axios({
          method: 'post',
          url: 'http://i6b106.p.ssafy.io:8080/comment/' + this.$route.params.feedNumber +'/' + this.myUserNumber,
          data: commentItem,
        })
        .then(() => {
          this.getComments()
          this.getFeed()
        })
        .catch(err => {
          console.log(err)
        })
      }
    },

    leftPhoto: function(){ // 앞 사진으로 이동
      this.photoIndex -= 1
    },
    rightPhoto: function(){ // 뒷 사진으로 이동
      this.photoIndex += 1
    },
  },

  created : function() {
    this.getFeed()
    this.getComments()
    // this.getUserProfile()
  },
}
</script>

<style>

</style>