<template>
  <div>
    <h2>뉴스피드</h2>
    <div>
      <div v-for="(feed, idx) in NewsFeedList" :key="idx">
        <NewsFeedListItem
        :feed="feed"
        />
      </div>
    </div>
  </div>
</template>

<script>
import NewsFeedListItem from '@/components/feed/NewsFeedListItem'
import axios from "axios"

export default {
  name: 'NewsFeed',
  data: function () {
    return {
      NewsFeedList: [],
      myUserNumber: 2,
      cursor: '',
    }
  },
  components: {
    NewsFeedListItem,
  },
  props: {

  },
  methods: {
    getNewsFeeds: function () { // 뉴스피드 가져오기
      axios({
        method: 'get',
        url: `http://i6b106.p.ssafy.io:8080/main/newsfeed?userNumber=` + this.myUserNumber + `&cursor=` + this.cursor, 
      })
        .then(res => {
          console.log(res.data)
          this.NewsFeedList = res.data
        })
        .catch(err => {
          console.log(err)
        })
    },

    
  },
  created: function () {
    // 로그인 확인 함수 넣을 곳
    this.getNewsFeeds()
  },
}
</script>

<style>

</style>