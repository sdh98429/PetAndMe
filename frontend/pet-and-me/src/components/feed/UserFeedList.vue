<template>
  <div style="border: 1px solid;" class="file-preview-container">유저 피드리스트

    <UserFeedListItem 
    v-for="userFeed in userFeeds" 
    :key="userFeed.feedNumber"
    :userFeed="userFeed" 
    class="file-preview-wrapper"
    />
  </div>
</template>

<script>
import axios from 'axios'

import UserFeedListItem from '@/components/feed/UserFeedListItem'


export default {
  name: 'FeedList',
  data: function () {
    return {
      userNumber: null,
      userFeeds: [
        {},
        {},
        {},
        {}, 
        {},
        {},
        {},
        {}, 
      ],
    }
  },
  components: {
    UserFeedListItem,
  },
  props: {

  },
  methods: {
    getUserFeeds: function () {
      axios({
        method: 'get',
        // url: `http://i6b106.p.ssafy.io/feed/list/${userNumber}`, // 요청 보내야 할 URL
        url: `http://i6b106.p.ssafy.io/feed/list/{userNumber}`, // 임시 URL
      })
        .then(res => {
          console.log(res.data)
          this.userFeeds = res.data
        })
        .catch(err => {
          console.log(err)
        })
    },
  },
  created: function () {
    // 로그인 확인
    this.getUserFeeds()
  }
}
</script>

<style scoped>
  .file-preview-container {
      height: 100%;
      display: flex;
      flex-wrap: wrap;
  }

  .file-preview-wrapper {
    padding: 10px;
    /* width: (100/3)%; */
    position: relative;
  }
</style>