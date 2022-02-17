<template>
  <div class="sa-feed-container">
    <button class="bttn-gradient bttn-md bttn-warning back-btn" @click="goBack" style="top:0px; position:relative;">돌아가기</button>
    <h2>#{{animal}} 피드 검색결과</h2>
    <div v-if="feedList">
      <div
        v-for="feed in feedList"
        :key="feed.index"
      >
        <SimilarAnimalFeedItem
        :feed="feed"
        :feedUploadDate="getUploadDate(feed)"
        @go-back="goBack"
        />
      </div>
    </div>
    <div v-else>
      피드가 없습니다.
      <button @click="test"></button>
    </div>
  </div>
</template>

<script>
// import '@/css/safeed.css'
import SimilarAnimalFeedItem from './SimilarAnimalFeedItem.vue'
export default {
  props: {
    feedList: Array,
    animal: String,
  },
  data() {
    return {
      today: new Date(),
    }
  },
  components: {
    SimilarAnimalFeedItem,
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
    goBack(){
      this.$emit('go-back')
    },
    
  },
}
</script>

<style scoped src="@/css/safeed.css">

</style>