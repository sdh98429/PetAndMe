<template>
  <!-- feed select -->
  <v-app class="taping-create-container">
    <div calss="return-circular" style="text-align: center;" v-if="!returnFeeds">
      <v-progress-circular
        :size="150"
        color="amber"
        indeterminate
      ></v-progress-circular>
    </div>
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
    <!-- <v-btn color="info" x-large @click="saveFeedNumber">다음단계</v-btn> -->
    <button class="bttn-pill bttn-md bttn-warning next-step-btn" @click="saveFeedNumber">다음단계</button>
  </v-app>
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
      updateNumber: [],
      returnFeeds: false,
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
          this.returnFeeds = true
          console.log(res)
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
@media screen and (max-width:768px){
  .taping-create-container {
    width: 100%;
    max-width: 600px;
    border: 1px solid #ccc;
    position: relative;
    padding-bottom: 150px;
    font-family: 'MinSans-Regular', 'Quicksand';
  }
  
  input.checked + label > img {
    border: 10px solid orange;
    box-sizing: border-box;
  }
  
  /* .grid {
    display: grid;
    justify-content: center;
    grid-template-columns: repeat(3, auto);
    gap: 10px;
    padding-bottom: 50px;
    padding-top: 50px;
    width: 100%;
  } */
  .grid {
    position: relative;
    display: flex;
    flex-wrap: wrap;
    width: 100%;
    max-width: 600px;
  }
  .feed-thumbnail{
    display:block;
    width:32.8vw;
    max-width: 198px;
    height:32.8vw;
    max-height: 198px;
  }
}
@media screen and (min-width:768px){
  .taping-create-container {
    width: 800px;
    border: 1px solid #ccc;
    position: relative;
    left: -1px;
    padding-bottom: 150px;
    font-family: 'MinSans-Regular', 'Quicksand';
  }
  input.checked + label > img {
    border: 10px solid orange;
    box-sizing: border-box;
  }
  .grid {
    position: relative;
    display: flex;
    flex-wrap: wrap;
    width: 800px;
    gap: 1px;
  }
  .feed-thumbnail{
    display:block;
    width:33vw;
    max-width: 265px;
    height:33vw;
    max-height: 265px;
  }
}

.v-btn {
  display: block;
  padding-bottom: 100px;
  margin: auto;
}
.next-step-btn {
  position: relative;
  width: 200px;
  left: 50%;
  transform: translateX(-50%);
}
</style>