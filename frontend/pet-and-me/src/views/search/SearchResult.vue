<template>
  <div>
    <div>검색 단어 : {{searchWord}}</div>
    <button v-for="(tab, index) in tabs"
      :key="index"
      v-bind="{active: currentTab === index}"
      @click="currentTab = index">{{tab}}
    </button>
    <div>
      <div v-show="currentTab==0">
        <div v-for="(Id, idx) in resultId" :key="idx" style="border: 1px solid;">
          <img @click="toUserFeed(Id.userID)" :src="'http://i6b106.p.ssafy.io:8080/main/image?file=' + Id.saveFolder + Id.userPhotoName" alt="프로필 사진" style="width: 300px; height: 150px; object-fit: contain;">
          <div>{{Id.userNickName}}</div>
          <div>{{Id.userID}}</div>
        </div>
      </div>
      <div v-show="currentTab==1">
        <div v-for="(Nickname, idx) in resultNickname" :key="idx" style="border: 1px solid;">
          <img @click="toUserFeed(Nickname.userID)" :src="'http://i6b106.p.ssafy.io:8080/main/image?file=' + Nickname.saveFolder + Nickname.userPhotoName" alt="프로필 사진" style="width: 300px; height: 150px; object-fit: contain;">
          <div>{{Nickname.userNickName}}</div>
          <div>{{Nickname.userID}}</div>
        </div>
      </div>
      <div v-show="currentTab==2">{{resultRecent}}</div>
    </div>

    <!-- <div>검색결과</div> -->
    <!-- <div>아이디 검색 : {{resultId}}</div>
    <div>닉네임 검색 : {{resultNickname}}</div>
    <div>최근 검색: {{resultRecent}}</div> -->
    
  </div>
</template>

<script>
import axios from 'axios'

export default {
  name: 'SearchResult',
  data: function () {
    return {
      resultId: null,
      resultNickname: null,
      resultRecent: null,
      searchWord: this.$route.params.searchWord,
      myUserNumber : 1,

      currentTab: 0,
      tabs: ['아이디 검색 결과', '닉네임 검색 결과', '최근 검색 단어']
    }
  },
  components: {

  },
  props: {

  },
  methods: {
    getSearchResult: function (){ // 검색 결과 가져오기
    // userid 검색 결과
      axios({
        method: 'get',
        url: 'http://i6b106.p.ssafy.io:8080/search/rt/userid/' + this.$route.params.searchWord,
      })
        .then(response => {
          this.resultId = response.data
        })
        .catch(err => {
          console.log(err)
        })

    // nickname 검색 결과
      axios({
        method: 'get',
        url: 'http://i6b106.p.ssafy.io:8080/search/rt/userName/' + this.$route.params.searchWord,
      })
        .then(response => {
          this.resultNickname = response.data
        })
        .catch(err => {
          console.log(err)
        })

      const searchSave = {
        searchWord : this.searchWord,
        userNumber : this.myUserNumber,
      }

    // 최근 검색 결과 저장 및 조회
      axios({
        method: 'post',
        url: 'http://i6b106.p.ssafy.io:8080/search?searchWord=' + this.searchWord + '&userNumber=' + this.myUserNumber,
        // data: searchSave,
      })
        .then(() => {
          axios({
            method: 'get',
            url: 'http://i6b106.p.ssafy.io:8080/search/' + this.myUserNumber,
          })
            .then(response => {
              this.resultRecent = response.data
            })
            .catch(err => {
              console.log(err)
            })
        })
        .catch(err => {
          console.log(err)
        })


    },
    toUserFeed : function(userId){
      console.log(userId)
      this.$router.push({name: `UserFeed`, params : {yourUserId: userId}})
    }
  },
  created: function(){
    this.getSearchResult()
  }
}
</script>

<style>

</style>