<template>
  <div style="border: 1px solid;">
    <div>피드 디테일 페이지</div>
    <div>뉴스피드 리스트 아이템과 유사</div>
    <div>프로필</div>
    <div>{{profile}}</div>
    <div>유저 번호 : {{userNumber}}</div>
    <div>피드 내용 : {{feedContent}}</div>
    <div>피드 날짜 : {{feedDate}}</div>
    <div>좋아요 수 : {{cntLike}}</div>
    <div>댓글 개수 : {{cntComment}}</div>
    <div v-if="fileInfoDtoList">이미지 개수{{fileInfoDtoList.length}}</div>
    <div v-for="(file, idx) in fileInfoDtoList" :key="idx">
      <div>{{fileInfoDtoList[idx].photoName}}</div>
      <img v-if="fileInfoDtoList" :src="`data:image/png;base64,${fileInfoDtoList[idx].photo}`" />
    </div>
    <div></div>
    <textarea id="textarea" v-model.trim = "commentContent" placeholder="댓글을 달아주세요." cols="30" rows="10"></textarea>
    <button @click="createComment">댓글 생성</button>
    <div v-if="commentList.length == 0">댓글이 없습니다.</div>
    <div v-if="commentList.length != 0">댓글이 {{commentList.length}}개 있습니다.</div>
    <div v-for="(comment, idx) in commentList" :key="idx">
      <div>{{comment.userNickName}} : {{comment.commentContent}}</div>
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

      commentContent : null,

      userNumber: 1,
      feedContent: null,
      feedDate: null,
      commentList: null,
      cntLike: null,
      cntComment: null,
      commentDtoList: null,
      fileInfoDtoList: null,

    }
  },
  components: {
  },
  props: {
  },
  methods: {
    getFeed: function(){
      axios({
        method: 'get',
        url: 'http://i6b106.p.ssafy.io:8080/main/feed/' + this.$route.params.feedNumber,
      })
        .then(response => {
          console.log(response)
          this.feed = response.data

          // this.userNumber = response.data.userNumber
          this.feedContent = response.data.feedContent
          this.feedDate = response.data.feedDate
          this.cntLike = response.data.cntLike
          this.cntComment = response.data.cntComment
          this.commentDtoList = response.data.commentDtoList
          this.fileInfoDtoList = response.data.fileInfoDtoList

        })
        .catch(err =>{
          console.log(err)
        })
    },
    getComments: function(){
      axios({
        method: 'get',
        url: 'http://i6b106.p.ssafy.io:8080/comment/' + this.$route.params.feedNumber ,
      })
        .then(response => {
          console.log('댓글')
          console.log(response)
          this.commentList = response.data
        })
        .catch(err => {
          console.log(err)
        })
    },
    // getUserProfile: function(){
    //   axios({
    //     method: 'get',
    //     url: 'http://i6b106.p.ssafy.io:8080/user/' + this.$route.params.userNumber,
    //   })
    //     .then(response => {
    //       console.log(response)
    //       this.profile = response.data
    //     })
    //     .catch(err => {
    //       console.log(err)
    //     })
    // },
    createComment: function(){
      if (this.commentContent) {
        console.log(this.commentContent)
        const commentItem = {
          commentContent : this.commentContent
        }


        axios({
          method: 'post',
          url: 'http://i6b106.p.ssafy.io:8080/comment/' + this.$route.params.feedNumber +'/' + this.userNumber,
          data: commentItem,
        })
        .then(response => {
          console.log('댓글 생성 완료')
          console.log(response)
          this.getComments()
          this.getFeed()
        })
        .catch(err => {
          console.log(err)
        })
      }
    }
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