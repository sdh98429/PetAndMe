<template>
  <div style="border: 1px solid;">
    <div>유저 피드 리스트 아이템</div>
    <img :src="userFeed.feedThumbnail" alt="이미지 없음" class="preview-image" @click="toFeedDetail(userFeed)">
</div>
</template>

<script>

export default {
  name: 'UserFeedListItem',
  data: function () {
    return {

    }
  },
  components: {

  },
  props: {
    userFeed: {
      type: Object,
    }
  },
  methods: {
    toFeedDetail: function(userFeed){
      this.$router.push({path: `/feed/${userFeed.feedNumber}`, userFeed:userFeed})

    }
  }
}
</script>

<style>

</style>