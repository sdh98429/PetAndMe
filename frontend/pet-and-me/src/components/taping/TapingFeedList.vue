<template>
  <div style="border: 1px solid;" class="file-preview-container">
    <div v-for="userFeed in userFeeds" :key="userFeed.feedNumber">
      <input type="checkbox"
        :id="userFeed.feedDate"
        v-model="feedArr"
        :value="userFeed.feedNumber"
        :class="checked(userFeed.feedNumber)"
      >
      <label :for="userFeed.feedDate">
        <img class="feed-thumbnail" :src="`data:image/png;base64,${userFeed.feedThumbnail}`" />
      </label>
    </div>
    <button @click="saveFeedNumber">피드 선택 완료</button>
  </div>
</template>

<script>
import axios from 'axios'
import {BASE_API_URL} from '@/config/config.js'

export default {
  name: 'UserFeedList',
  data: function () {
    return {
      userFeeds: [],
      feedArr: [],
      updateNumber: []
    }
  },
  components: {

  },
  props: {
    yourUserNumber : Number,
  },
  methods: {
    getUserFeeds: function () {
      axios({
        method: 'get',
        url: `${BASE_API_URL}/main/feed/list/` + this.yourUserNumber,
      })
        .then(res => {
          this.userFeeds = res.data
        })
        .catch(err => {
          console.log(err)
        })
    },
    checked(target) {
    const index = this.feedArr.indexOf(target)
    return index >= 0 ? { checked: true } : { checked: false }
    },
    saveFeedNumber() {
      for (var feedNum in this.feedArr){
        this.updateNumber.push(feedNum*=1)
      }
      this.$emit("feedNumber-update", this.updateNumber)
      console.log(this.feedArr)
    }
  },
  watch: {
    yourUserNumber: function(){
      this.getUserFeeds()
    }
  }
}
</script>

<style scoped>
.feed-thumbnail{
  display:block;
  width:150px;
  height:150px;
}

input.checked + label > img {
  border: 10px solid rgb(255, 242, 65);
  box-sizing: border-box;
}
</style>