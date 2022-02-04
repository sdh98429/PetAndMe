<template>
  <div style="border: 1px solid;">뉴스피드리스트아이템
  </div>
</template>

<script>
import axios from 'axios'



export default {
  name: 'FeedListItem',
  data: function () {
    return {
      userNumber: null,
      newsFeeds: [
        {},
        {},
        {},
        {},
        {},
      ],
    }
  },
  components: {

  },
  props: {

  },
  methods: {
    getNewsFeeds: function () {
      axios({
        method: 'get',
        // url: `http://i6b106.p.ssafy.io/newsfeed`, // 요청 보내야 할 URL
        url: `http://i6b106.p.ssafy.io/newsfeed`, // 임시 URL
      })
        .then(res => {
          console.log(res.data)
          this.newsFeeds = res.data
        })
        .catch(err => {
          console.log(err)
        })
    },
  },
  created: function () {
    // 로그인 확인
    this.getNewsFeeds()
  }
}
</script>

<style>

</style>