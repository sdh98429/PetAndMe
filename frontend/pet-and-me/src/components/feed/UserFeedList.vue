<template>
  <div style="border: 1px solid;" class="file-preview-container">유저 피드리스트

    <div v-for="userFeed in userFeeds" :key="userFeed.feedNumber">
      <img class="file-preview-wrapper" @click="goToFeedDetail(userFeed)" :src="`data:image/png;base64,${userFeed.feedThumbnail}`" />
    </div>
  </div>
</template>

<script>
import axios from 'axios'


export default {
  name: 'UserFeedList',
  data: function () {
    return {
      yourUserNumber: null,
      userFeeds: [
      ],
    }
  },
  components: {

  },
  props: {

  },
  methods: {
    getUserFeeds: function () { // 유저 피드 리스트 가져오기
      axios({
        method: 'get',
        url: `http://i6b106.p.ssafy.io:8080/main/feed/list/` + this.$route.params.yourUserNumber, // 임시 URL
      })
        .then(res => {
          console.log(res.data)
          this.userFeeds = res.data
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
    position: relative;
  }
</style>