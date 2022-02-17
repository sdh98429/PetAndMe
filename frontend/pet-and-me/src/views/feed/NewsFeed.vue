<template>
  <div class="newsfeed-container">
    <!-- <button v-for="(tab, index) in tabs"
      :key="index"
      v-bind="{active: currentTab === index}"
      @click="currentTab = index">{{tab}}
    </button> -->
    <div>
      <div v-for="(feed, idx) in RecentFeedList" :key="idx">
        <NewsFeedListItem
        :feed="feed"
        :feedUploadDate="getUploadDate(feed)"
        />
      </div>
    </div>


    <!-- <div>인피니트 스크롤</div> -->
    <infinite-loading :identifier="currentTab" @infinite="infiniteHandler" spinner="waveDots">
      <div slot="no-more" style="color: rgb(102, 102, 102); font-size: 14px; padding: 10px 0px;"></div>
    </infinite-loading>

  </div>
</template>

<script>
import '@/css/newsfeed.css'
import NewsFeedListItem from '@/components/feed/NewsFeedListItem'
import axios from "axios"
import infiniteLoading from 'vue-infinite-loading'
import {BASE_API_URL} from '@/config/config.js'
import move from '@/js/move.js'

export default {
  name: 'NewsFeed',
  data: function () {
    return {
      today: new Date(),
      NewsFeedList: [], // 현재 화면에 보여줄 뉴스피드리스트
      RecentFeedList: [], // 뉴스피드리스트1 최신피드리스트
      // FavFeedList: [], // 뉴스피드리스트2 선호피드리스트
      // FollowFeedList: [], // 뉴스피드리스트3 친구피드리스트

      cursor: '', // 스크롤에서 가장 마지막 게시글 생성 날짜

      currentTab: 0, // 햔재 탭 (1 - 최신 피드, 2 - 선호 피드, 3 - 친구 피드)
      tabs: ['최신 피드', '선호 동물 피드', '친구 피드'],
      NewsFeedUrl : null, // 피드 요청 보낼 뉴스피드 url

      RecentFeedUrl : `${BASE_API_URL}/main/newsfeed?userNumber=${this.myUserNumber}&cursor=${this.cursor}`,
      // FavFeedUrl : `${BASE_API_URL}/main/favfeed?userNumber=${this.myUserNumber}&cursor=${this.cursor}`,
      // FollowFeedUrl : `${BASE_API_URL}/main/followfeed?userNumber=${this.myUserNumber}&cursor=${this.cursor}`,

    }
  },
  components: {
    NewsFeedListItem,
    infiniteLoading
  },
  props: {

  },
  methods: {
    getUploadDate(feed) {
      const tempdate = new Date(feed.feedDate)
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
    infiniteHandler: function($state){ // 인피니트 스크롤 함수 수정
      this.NewsFeedList = []

      this.NewsFeedList = this.RecentFeedList
      if (localStorage.getItem('NewsCursor')){
        this.cursor = localStorage.getItem('NewsCursor')
      } else {
        this.cursor = ''
      }
      this.NewsFeedUrl = `${BASE_API_URL}/main/newsfeed?userNumber=${this.myUserNumber}&cursor=${this.cursor}`

      if (this.NewsFeedList.length){
        axios({
          method: 'get',
          url: this.NewsFeedUrl, 
        })
          .then(res => {
            setTimeout(() => {
              this.NewsFeedList = this.NewsFeedList.concat(res.data)
              this.RecentFeedList = this.NewsFeedList
              localStorage.setItem('NewsCursor', this.NewsFeedList[this.NewsFeedList.length-1].feedDate);
 
              $state.loaded()
              if (!res.data.length){
                $state.complete();
              }
            }, 500)
          })
          .catch(err => {
            console.log(err)
          })

      } else {
        axios({
          method: 'get',
          url: this.NewsFeedUrl, 
        })
          .then(res => {
            this.NewsFeedList = res.data
            this.RecentFeedList = this.NewsFeedList
            

            if (!res.data.length){
              // $state.complete();
              localStorage.setItem('NewsCursor', '')
              $state.loaded();
            } else {
              setTimeout(() => {
                localStorage.setItem('NewsCursor', this.NewsFeedList[this.NewsFeedList.length-1].feedDate);
                $state.loaded();
              }, 500)
            }
          })
          .catch(err => {
            console.log(err)
          })
      }
    }
  },
  created: function () {
    // 로그인 확인 함수 넣을 곳
    // this.getNewsFeeds()
    const footerEl = document.querySelector('#footer-container')
    footerEl.classList.remove('inactive')
    const searchEl = document.querySelector('.my-search-bar')
    searchEl.classList.remove('inactive')
  },
  mounted() {
    move('1', '10%', '#fff')
  },
  destroyed() {
    const footerEl = document.querySelector('#footer-container')
    const searchEl = document.querySelector('.my-search-bar')
    searchEl.classList.remove('inactive')
    footerEl.classList.remove('inactive')
  },
  computed: {
    myUserNumber () {
      return this.$store.getters.getUserNumber
    }
  },

}
</script>

<style scoped>
/* COMMON */
img {
  width: 200px;
}
</style>