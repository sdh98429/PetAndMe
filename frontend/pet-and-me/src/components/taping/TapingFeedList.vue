<template>
  <!-- feed select -->
  <div>
  <v-app class="taping-create-container">
    <div class="grid">
      <div v-for="(userFeed,index) in userFeeds" :key="userFeed.feedNumber">
        <input type="checkbox"
          :id="`feedItem${index}`"
          v-model="feedArr"
          :value="userFeed.feedNumber"
          :class="checked(userFeed.feedNumber)"
          style="display:none;"
        >
        <label :for="`feedItem${index}`">
          <img class="feed-thumbnail" :src="`data:image/png;base64,${userFeed.feedThumbnail}`" />
        </label>
      </div>
    </div>
  <div class="btn-container">
    <v-btn color="info" x-large @click="saveFeedNumber">선택 완료</v-btn>
  </div>
  </v-app>
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
      this.$emit("feedNumber-update", this.feedArr)
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

.grid {
  display: grid;
  justify-content: center;
  grid-template-columns: repeat(3, auto);
  gap: 10px;
  padding-bottom: 50px;
  padding-top: 50px;
  width: 100%;
}

.v-btn {
  display: block;
  padding-bottom: 100px;
  margin: auto;
}
.btn-container {
  padding-bottom: 200px;
}
</style>