<template>
  <div class="sa-search-container">
    <h2>{{propData.myAnimal}} 키우는 친구들</h2>
    <div 
      v-for="(user,idx) in propData.searchAnimalList"
      :key="idx"
      class="sa-search-item"
      @click="goToUserFeed(user.userID)"
    >
      <img class="sa-user-image" :src="`http://i6b106.p.ssafy.io:8080/main/image?file=${user.saveFolder}${user.userPhotoName}`" alt="프로필 사진" />
      <div class="sa-user-nickname">{{user.userNickName}}</div>
      <div class="sa-user-id">@{{user.userID}}</div>
      <div class="sa-user-follow">팔로워 {{user.followerCnt}}명</div>
    </div>
    <button class="bttn-gradient bttn-md bttn-warning back-btn" @click="goBack">돌아가기</button>
  </div>
</template>

<script>
import '@/css/sasearch.css'
export default {
  name: 'SimilarAnimalSearchResult',
  props: {
    propData: Object,
  },
  methods: {
    goBack(){
      this.$emit('go-back')
    },
    goToUserFeed(userID){
      this.$router.push({name:'UserFeed', params:{yourUserId:userID}})
    }
  },
  watch:{
    propData(){
      console.log(this.propData)
    }
  },
  created(){
    console.log(this.propData)
  }
}
</script>

<style>

</style>