<template>
  <div class="test-container">
    <input type="text" v-model.trim="searchWord" @keyup.enter="goToSearchResult(searchWord)" @input="getRealTimeSearch(searchWord)">
    <button @click="goToSearchResult(searchWord)">검색하기</button>

    <div v-if="isRecent">
      <div v-for="(recent, idx) in resultRecent" :key="idx">
        <div @click="goToSearchResult(recent)" style="border: 1px solid;">{{recent}}</div>
      </div>
    </div>
    <div v-if="!isRecent">
      <div v-for="(real, idx) in realTimeSearch" :key="idx" style="border: 1px solid;">
        <img @click="toUserFeed(real.userID)" :src="'http://i6b106.p.ssafy.io:8080/main/image?file=' + real.saveFolder + real.userPhotoName" alt="프로필 사진" style="width: 300px; height: 150px; object-fit: contain;">
        <div>{{real.userNickName}}</div>
        <div>@{{real.userID}}</div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios'
import {BASE_API_URL} from '@/config/config.js'

export default {
  name: 'Search',
  data: function () {
    return {
      searchWord: null,
      resultRecent: null, // 최근 검색 결과
      resultId: null, // ID 검색 결과
      resultNickname: null, // 닉네임 검색 결과
      realTimeSearch: null, // 실시간 검색 결과
      isRecent: true, // 최근 검색 결과 보여주는지 여부


    }
  },
  components: {

  },
  props: {

  },
  methods: {
    goToSearchResult: function(searchWord){ // 검색 결과 페이지로 이동
      if (searchWord){
        this.$router.push({path: `/search/${searchWord}`})
      }      
    },

    getRealTimeSearch: function(searchWord){ // 실시간 검색 결과

      if (searchWord && searchWord[0] == '@' && searchWord.length > 1){
        this.isRecent = false
        axios({
          method: 'get',
          url: `${BASE_API_URL}/search/rt/userid/${searchWord.slice(1)}`
        })
          .then(response => {
            this.realTimeSearch = response.data
          })
          .catch(err => {
            console.log(err)
          })
      } else if (searchWord && searchWord[0] != '@'){
        this.isRecent = false
        axios({
          method: 'get',
          url: `${BASE_API_URL}/search/rt/userName/${searchWord}`,
        })
          .then(response => {
            this.realTimeSearch = response.data
          })
          .catch(err => {
            console.log(err)
          })
      } else {
        this.isRecent = true
      }
    },

    getSearchHistory: function(){ // 최근 검색 결과
      axios({
        method: 'get',
        url: `${BASE_API_URL}/search/${this.myUserNumber}`,
      })
        .then(response => {
          this.resultRecent = response.data
        })
        .catch(err => {
          console.log(err)
        })
    },
    toUserFeed : function(userId){ // 유저 피드로 이동
      this.$router.push({name: `UserFeed`, params : {yourUserId: userId}})
    }
  },

  created : function(){
    this.getSearchHistory()
  },
  computed: {
    myUserNumber () {
      return this.$store.getters.getUserNumber
    }
  },
}
</script>

<style scoped>
.test-container {
  position: relative;
  top: 100px;

}
</style>