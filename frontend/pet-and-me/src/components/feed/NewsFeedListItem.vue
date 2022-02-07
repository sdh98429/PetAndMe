<template>
  <div style="border: 1px solid;">뉴스피드리스트아이템

    <div>프로필</div>
    <div v-if="profile">{{profile}}</div>
    
    <div>{{feed}}</div>
    <div>유저 번호 : {{feedUserNumber}}</div>
    <div>피드 내용 : {{feedContent}}</div>
    <div>피드 날짜 : {{feedDate}}</div>
    <div>좋아요 수 : {{cntLike}}</div>
    <div>댓글 개수 : {{cntComment}}</div>
    <div v-if="feedPhotoDtoList">이미지 개수 : {{feedPhotoDtoList.length}}</div>
    <div v-for="(file, idx) in feedPhotoDtoList" :key="'photo' + idx">
      <div>{{feedPhotoDtoList[idx].photoName}}</div>
      <button v-show="idx == photoIndex && idx > 0" @click="leftPhoto">왼쪽</button>
      <img v-show="idx == photoIndex" :src="`data:image/png;base64,${feedPhotoDtoList[idx].photo}`" />
      <button v-show="idx == photoIndex && idx < feedPhotoDtoList.length - 1" @click="rightPhoto">오른쪽</button>
    </div>
  </div>
</template>

<script>
import axios from 'axios'



export default {
  name: 'NewsFeedListItem',
  data: function () {
    return {

      profile : null,
      photoIndex : 0,

      feedUserNumber: this.feed.userNumber,

      feedContent: this.feed.feedContent,
      feedDate: this.feed.feedDate,

      cntLike: this.feed.cntLike,
      cntComment: this.feed.cntComment,

      feedPhotoDtoList: this.feed.feedPhotoDtoList,
    }
  },
  components: {

  },
  props: {
    feed : Object,
  },
  methods: {
    getUserProfile: function(){ // 프로필 정보 가져오기
      axios({
        method: 'get',
        url: 'http://i6b106.p.ssafy.io:8080/users/info/' + this.feed.userNumber,
      })
        .then(response => {
          this.profile = response.data
        })
        .catch(err => {
          console.log(err)
        })
    },

    leftPhoto: function(){ // 앞 사진으로 이동
      this.photoIndex -= 1
    }, 
    rightPhoto: function(){ // 뒤 사진으로 이동
      this.photoIndex += 1
    },
  },
  created: function () {

  },
  watch: {
    feed: function() {
      this.getUserProfile()
    }
  }
}
</script>

<style>

</style>