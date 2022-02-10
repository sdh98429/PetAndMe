<template>
  <div>

    <button v-for="(tab, index) in tabs"
      :key="index"
      v-bind="{active: currentTab === index}"
      @click="currentTab = index">{{tab}}
    </button>
    <div>
      <!-- <div>최신 피드</div> -->
      <div v-show="currentTab==0">
        <div v-for="(feed, idx) in RecentFeedList" :key="idx">
          <NewsFeedListItem
          :feed="feed"
          />
        </div>
      </div>

      <!-- <div>선호 피드</div> -->
      <div v-show="currentTab==1">
        <div v-for="(feed, idx) in FavFeedList" :key="idx">
          <NewsFeedListItem
          :feed="feed"
          />
        </div>
      </div>

      <!-- <div>친구 피드</div> -->
      <div v-show="currentTab==2">
        <div v-for="(feed, idx) in FollowFeedList" :key="idx">
          <NewsFeedListItem
          :feed="feed"
          />
        </div>
      </div>

      <!-- <div>인피니트 스크롤</div> -->
      <infinite-loading :identifier="currentTab" @infinite="infiniteHandler" spinner="waveDots">
        <div slot="no-more" style="color: rgb(102, 102, 102); font-size: 14px; padding: 10px 0px;">목록의 끝입니다 :)</div>
      </infinite-loading>
    </div>

  </div>
</template>

<script>
import NewsFeedListItem from '@/components/feed/NewsFeedListItem'
import axios from "axios"
import infiniteLoading from 'vue-infinite-loading'

export default {
  name: 'NewsFeed',
  data: function () {
    return {
      NewsFeedList: [], // 현재 화면에 보여줄 뉴스피드리스트
      RecentFeedList: [], // 뉴스피드리스트1 최신피드리스트
      FavFeedList: [], // 뉴스피드리스트2 선호피드리스트
      FollowFeedList: [], // 뉴스피드리스트3 친구피드리스트
      myUserNumber: 2,
      cursor: '', // 스크롤에서 가장 마지막 게시글 생성 날짜

      currentTab: 0, // 햔재 탭 (1 - 최신 피드, 2 - 선호 피드, 3 - 친구 피드)
      tabs: ['최신 피드', '선호 동물 피드', '친구 피드'],
      NewsFeedUrl : null, // 피드 요청 보낼 뉴스피드 url

      RecentFeedUrl : `http://i6b106.p.ssafy.io:8080/main/newsfeed?userNumber=` + this.myUserNumber + `&cursor=` + this.cursor,
      FavFeedUrl : `http://i6b106.p.ssafy.io:8080/main/favfeed?userNumber=` + this.myUserNumber + `&cursor=` + this.cursor,
      FollowFeedUrl : `http://i6b106.p.ssafy.io:8080/main/followfeed?userNumber=` + this.myUserNumber + `&cursor=` + this.cursor,

    }
  },
  components: {
    NewsFeedListItem,
    infiniteLoading
  },
  props: {

  },
  methods: {

    infiniteHandler: function($state){ // 인피니트 스크롤 함수
      this.NewsFeedList = []
      if (this.currentTab === 0){
        this.NewsFeedList = this.RecentFeedList
        if (this.NewsFeedList.length){
          this.cursor = this.NewsFeedList[this.NewsFeedList.length-1].feedDate
        } else {
          this.cursor = ''
        }
        this.NewsFeedUrl = `http://i6b106.p.ssafy.io:8080/main/newsfeed?userNumber=` + this.myUserNumber + `&cursor=` + this.cursor
      } else if (this.currentTab === 1){
        this.NewsFeedList = this.FavFeedList
        if (this.NewsFeedList.length){
          this.cursor = this.NewsFeedList[this.NewsFeedList.length-1].feedDate
        } else {
          this.cursor = ''
        }
        this.NewsFeedUrl = `http://i6b106.p.ssafy.io:8080/main/favfeed?userNumber=` + this.myUserNumber + `&cursor=` + this.cursor
      } else if (this.currentTab === 2){
        this.NewsFeedList = this.FollowFeedList
        if (this.NewsFeedList.length){
          this.cursor = this.NewsFeedList[this.NewsFeedList.length-1].feedDate
        } else {
          this.cursor = ''
        }
        this.NewsFeedUrl = `http://i6b106.p.ssafy.io:8080/main/followfeed?userNumber=` + this.myUserNumber + `&cursor=` + this.cursor
      }

      if (this.NewsFeedList.length){
        axios({
          method: 'get',
          url: this.NewsFeedUrl, 
        })
          .then(res => {
            setTimeout(() => {
              this.NewsFeedList = this.NewsFeedList.concat(res.data)
              if (this.currentTab === 0){
                this.RecentFeedList = this.NewsFeedList
              } else if (this.currentTab === 1){
                this.FavFeedList = this.NewsFeedList
              } else if (this.currentTab === 2){
                this.FollowFeedList = this.NewsFeedList
              }

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
            if (this.currentTab === 0){
              this.RecentFeedList = this.NewsFeedList
            } else if (this.currentTab === 1){
              this.FavFeedList = this.NewsFeedList
            } else if (this.currentTab === 2){
              this.FollowFeedList = this.NewsFeedList
              }
            $state.loaded();
            if (!res.data.length){
              $state.complete();
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
  },
}
</script>

<style>

</style>