<template>
  <div style="border: 1px solid;">뉴스피드리스트아이템

    <div>프로필</div>
    <div v-if="profile">{{profile}}
      <img :src="'http://i6b106.p.ssafy.io:8080/main/image?file=' + profile.saveFolder + profile.userPhotoName" alt="프로필 사진" style="width: 300px; height: 150px; object-fit: contain;">
      <div>유저 닉네임 : {{profile.userNickName}}</div>
      <div>유저 아이디 : @{{profile.userID}}</div>
      <br>
      <div>반려동물 이름 : {{profile.petName}}</div>
      <div>반려동물 성별 : {{profile.petGender}}</div>
      <div>반려동물 생일 : {{profile.petBirth}}</div>
      <div>반려동물 종류 : {{profile.animalName}}</div>
    </div>
    
    <div>{{feed}}</div>
    <div>유저 번호 : {{feedUserNumber}}</div>
    <div @click="goToFeedDetail(feed)">피드 내용 : {{feedContent}}</div>
    <div>피드 날짜 : {{feedDate}}</div>
    <div>좋아요 수 : {{cntLike}}</div>
    <div>댓글 개수 : {{cntComment}}</div>
    <div v-if="feedPhotoDtoList">이미지 개수 : {{feedPhotoDtoList.length}}</div>
    <div v-for="(file, idx) in feedPhotoDtoList" :key="'photo' + idx">
      <div>{{feedPhotoDtoList[idx].photoName}}</div>
      <button v-show="idx == photoIndex && idx > 0" @click="leftPhoto">왼쪽</button>
      <img v-show="idx == photoIndex" :src="'http://i6b106.p.ssafy.io:8080/main/image?file=' + feedPhotoDtoList[idx].saveFolder + feedPhotoDtoList[idx].photoName" alt="피드 사진">
      <!-- <img v-show="idx == photoIndex" :src="`data:image/png;base64,${feedPhotoDtoList[idx].photo}`" /> -->
      <button v-show="idx == photoIndex && idx < feedPhotoDtoList.length - 1" @click="rightPhoto">오른쪽</button>
    </div>

    <button v-if="!checkLike" @click="likeFeed">좋아요</button>
    <button v-if="checkLike" @click="likeFeed">좋아요 취소</button>
    <br>


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

      myUserNumber: 2,
      feedUserNumber: this.feed.userNumber,

      feedNumber: this.feed.feedNumber,

      feedContent: this.feed.feedContent,
      feedDate: this.feed.feedDate,

      cntLike: this.feed.cntLike,
      cntComment: this.feed.cntComment,

      feedPhotoDtoList: this.feed.feedPhotoDtoList,

      checkLike : this.feed.checkLike, // 좋아요 체크 추가
    }
  },
  components: {

  },
  props: {
    feed : Object,
  },
  methods: {

    likeFeed: function(){
      
      if (!this.checkLike){
        axios({
          method: 'post',
          url: 'http://i6b106.p.ssafy.io:8080/like/' + this.myUserNumber + '/' + this.feed.feedNumber,
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
          url: 'http://i6b106.p.ssafy.io:8080/like/' + this.myUserNumber + '/' + this.feed.feedNumber,
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

    getUserProfile: function(){ // 유저 프로필 가져오기
      axios({
        method: 'get',
        url: 'http://i6b106.p.ssafy.io:8080/user/info/' + this.feedUserNumber,
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

    goToFeedDetail(feed){ // 상세 피드로 이동
          this.$router.push({path: `/feed/${feed.feedNumber}`})
        },
  },
  created: function () {
      this.getUserProfile()
  },
  watch: {
    // feed: function() {
    //   this.getUserProfile()
    // }
  }
}
</script>

<style>

</style>