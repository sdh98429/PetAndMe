<template>
  <!-- <div style="border: 1px solid;" class="file-preview-container"> -->
  <div class="file-preview-container">
    <div v-for="userFeed in userFeeds.slice().reverse()" :key="userFeed.feedNumber">
      <img 
        class="feed-thumbnail" @click="goToFeedDetail(userFeed)" 
        :src="`data:image/png;base64,${userFeed.feedThumbnail}`" 
      />
    </div>
  </div>
</template>

<script>
import axios from 'axios'
import {BASE_API_URL} from '@/config/config.js'


export default {
  name: 'UserFeedList',
  data: function () {
    return {
      // yourUserNumber: null,
      userFeeds: [
      ],
    }
  },
  components: {

  },
  props: {
    yourUserNumber : Number,
  },
  methods: {
    getUserFeeds: function () { // 유저 피드 리스트 가져오기
      axios({
        method: 'get',
        url: `${BASE_API_URL}/main/feed/list/${this.yourUserNumber}` // 임시 URL
      })
        .then(res => {
          this.userFeeds = res.data
          // console.log(this.userFeeds)
          this.$emit('feed-length', this.userFeeds.length)
        })
        .catch(err => {
          console.log(err)
        })
    },

    goToFeedDetail(userFeed){ // 상세 피드로 이동
      this.$router.push({path: `/feed/${userFeed.feedNumber}`})
    },
  },
  created: function () {
    // 로그인 확인
    this.getUserFeeds()
  },
  watch: {
    yourUserNumber: function(){
      this.getUserFeeds()
    }
  }
}
</script>

<style scoped>
  /* .file-preview-container {
      height: 100%;
      display: flex;
      flex-wrap: wrap;
  }

  .file-preview-wrapper {
    padding: 10px;
    position: relative;
  } */

.feed-thumbnail{
  display:block;
  width:150px;
  height:150px;
}
</style>