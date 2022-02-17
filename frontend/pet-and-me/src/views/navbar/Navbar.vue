<template>
  <div class="nav">
    <nav class="my-nav affix">
      <div class="navcontainer">
        <!-- Logo (Home Btn) -->
        <span class="logo" >
          <div @click="goHome">PET & ME</div>
        </span>
      </div>
    </nav>

    <!-- Search Bar -->
    <div class="my-search-bar">
      <input v-model="searchWord" class="my-search-input" type="text"  placeholder="검색 (ex 닉네임, @아이디, #동물종류)" @focus="onFocus" @input="getRealTimeSearch" />
      <!-- <div v-if="isRecent">
        <div v-for="(recent, idx) in resultRecent" :key="idx">
          <div @click="goToSearchResult(recent)" style="border: 1px solid;">{{recent}}</div>
        </div>
      </div> -->
      
      <div class="my-search-placeholder">
        <font-awesome-icon icon="search" class="my-search-icon"></font-awesome-icon>
      </div>
      <div class="my-search-btn"></div>

      <div v-if="searchState" class="my-search-result">
        <div 
          v-for="(real, idx) in realTimeSearch" 
          :key="idx" 
          class="my-search-result-item"
          @click="toUserFeed(real.userID)" 
        >
          <img 
            class="my-search-result-image"
            :src="`http://i6b106.p.ssafy.io:8080/main/image?file=${real.saveFolder}${real.userPhotoName}`"
            alt="프로필 사진" 
          />
          <div class="my-search-result-nickname">{{real.userNickName}}</div>
          <div class="my-search-result-id">@{{real.userID}}</div>
        </div>
      </div>
      <div v-if="searchState" class="result-left-btn material-icons" @click="resultGoLeft">arrow_back_ios</div>
      <div v-if="searchState" class="result-right-btn material-icons" @click="resultGoRight">arrow_forward_ios</div>
      <span class="material-icons close-btn" v-if="searchState" @click="closeTab">close</span>
    </div>

    <!-- Footer Bar -->
    <div id="footer-container">
      <div id="my-footer">
        <div id="bubbleWrapper">
          <div id="bubble1" class="bubble"><span class="icon"><font-awesome-icon icon="home"></font-awesome-icon></span></div>
          <div id="bubble2" class="bubble"><span class="icon"><font-awesome-icon icon="paw"></font-awesome-icon></span></div>
          <div id="bubble3" class="bubble"><span class="icon"><font-awesome-icon icon="plus"></font-awesome-icon></span></div>
          <div id="bubble4" class="bubble"><span class="icon"><font-awesome-icon icon="video"></font-awesome-icon></span></div>
          <div id="bubble5" class="bubble"><span class="icon"><font-awesome-icon icon="user"></font-awesome-icon></span></div>
        </div>
        <div id="menuWrapper">
          <div id="menu1" class="menuElement" @click="goToNewsFeed()"><font-awesome-icon icon="home"></font-awesome-icon></div>
          <div id="menu2" class="menuElement" @click="goToSimilarAnimal()"><font-awesome-icon icon="paw"></font-awesome-icon></div>
          <div id="menu3" class="menuElement" @click="goToFeedCreate()"><font-awesome-icon icon="plus"></font-awesome-icon></div>
          <div id="menu4" class="menuElement" @click="goToTaping()"><font-awesome-icon icon="video"></font-awesome-icon></div>
          <div id="menu5" class="menuElement" @click="goToMyPage()"><font-awesome-icon icon="user"></font-awesome-icon></div>
        </div>
      </div>
      <div id="bgWrapper">
        <div id="bg"></div>
        <div id="bgBubble"></div>
      </div>
    </div>
    <svg width="0" height="0">

      <defs>
        <filter id="goo">
          <feGaussianBlur in="SourceGraphic" stdDeviation="20" result="blur" id="blurFilter" />
          <!-- -12 수정하면 벡터 깊이 수정가능 -->
          <feColorMatrix in="blur" mode="matrix" values="1 0 0 0 0  0 1 0 0 0  0 0 1 0 0  0 0 0 30 -11"
            result="goo" />
          <feComposite in="SourceGraphic" in2="goo" operator="atop" />
        </filter>
      </defs>
    </svg>
  </div>
</template>

<script>
import move from '../../js/move.js'
import $ from 'jquery'
import axios from 'axios'
import {BASE_API_URL} from '@/config/config.js'
import '@/css/navbar.scss'
export default {
  data() {
    return {
      searchWord: null,
      resultRecent: null, // 최근 검색 결과
      resultId: null, // ID 검색 결과
      resultNickname: null, // 닉네임 검색 결과
      realTimeSearch: null, // 실시간 검색 결과
      isRecent: false, // 최근 검색 결과 보여주는지 여부
      // myUserNumber: 1,
      searchState: false,
    }
  },
  methods: {
    resultGoLeft(){
      const el = document.querySelector('.my-search-result')
      // console.log(el.scrollLeft)
      el.scrollTo({left:el.scrollLeft - 200, behavior:'smooth'})
    },
    resultGoRight(){
      const el = document.querySelector('.my-search-result')
      // console.log(el.scrollLeft)
      el.scrollTo({left:el.scrollLeft + 200, behavior:'smooth'})
    },
    onFocus() {
      this.searchState = true
      // const searchResultEl = document.querySelector('.search-result')
      // searchResultEl.classList.remove('inactive')
      },
    closeTab() {
      this.searchState = false
      // const searchResultEl = document.querySelector('.search-result')
      // searchResultEl.classList.add('inactive')
    },
    goHome() {
      this.$router.push({ name : 'NewsFeed'})
    },
    goToNewsFeed() {
      move('1', '10%', '#fff')
      this.$router.push({ name : 'NewsFeed'})
    },
    goToSimilarAnimal() {
      move('2', '30%', '#fff')
      this.$router.push({ name : 'SimilarAnimal' })
    },
    goToFeedCreate() {
      move('3', '50%', '#fff')
      this.$router.push({ name : 'FeedCreate'})
    },
    goToTaping(){
      move('4', '70%', '#fff')
      this.$router.push({ name: 'Taping', params: { yourUserId : this.userInfo.userID } })
    },
    goToMyPage() {
      move('5', '90%', '#fff')
      // console.log(this.userInfo)
      this.$router.push({ name : 'UserFeed', params: { yourUserId : this.userInfo.userID }})
    },
    
    goToSearchResult: function(){ // 검색 결과 페이지로 이동
      if (this.searchWord){
        // this.$router.push({path: `/search/${this.searchWord}`})
        if (this.searchWord[0] == '@' || this.searchWord[0] == '#'){
          this.searchWord = this.searchWord.slice(1)
        }
        encodeURIComponent(this.searchWord)
        this.$router.push({name: 'SearchResult', params: {searchWord:this.searchWord} })
      }
    },

    getRealTimeSearch(e){ // 실시간 검색 결과
      this.searchWord = e.target.value
      if (this.searchWord && this.searchWord[0] == '@' && this.searchWord.length > 1){
        this.isRecent = false
        axios({
          method: 'get',
          url: `${BASE_API_URL}/search/rt/userid/${this.searchWord.slice(1)}`
        })
          .then(response => {
            this.realTimeSearch = response.data
            // console.log(this.realTimeSearch)
          })
          .catch(err => {
            console.log(err)
          })
      } else if (this.searchWord && this.searchWord[0] != '@' && this.searchWord[0] != '#'){
        this.isRecent = false
        axios({
          method: 'get',
          url: `${BASE_API_URL}/search/rt/userName/${this.searchWord}`,
        })
          .then(response => {
            this.realTimeSearch = response.data
          })
          .catch(err => {
            console.log(err)
          })
      } else if (this.searchWord && this.searchWord[0] === '#'){
        this.isRecent = false
        axios({
          method: 'get',
          url: `${BASE_API_URL}/search/animal/${this.searchWord.slice(1)}`,
        })
          .then(response => {
            console.log(this.searchWord.slice(1))
            console.log(response)
            this.realTimeSearch = response.data
          })
          .catch(err => {
            console.log(err)
          })
      } 
      else {
        this.isRecent = true
        this.realTimeSearch = null
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
      window.location.reload()
    }
  },
  computed: {
    myUserNumber () {
      return this.$store.getters.getUserNumber
    },
    userInfo() {
      return this.$store.getters.getUserInfo
    }
  },
  created() {
    this.getSearchHistory()
  },
  mounted() {
    $('.navTrigger').click(function () {
      $(this).toggleClass('active');
      $(".sidebar_list").toggleClass("show_list");
      $(".sidebar_list").fadeIn();
    });
  }
}
  

</script>

<style lang="scss">

</style>