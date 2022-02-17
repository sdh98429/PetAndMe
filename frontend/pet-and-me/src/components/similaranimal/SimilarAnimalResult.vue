<template>
  <div class="result-container">
    <div class="second-step">
      <div  class="result-box">
        <h1 class="result-message">
          {{ propData.myAnimal }}
        </h1>
        <div class="image-box">
          <img class="result-image" :src="examImage" alt="sample img" style="width:300px;">
        </div>
      <button class="bttn-gradient bttn-md bttn-warning retry-btn" @click="reTry">다시해보기</button>
      <button class="bttn-gradient bttn-md bttn-warning nickname-btn" @click="goToNickname">{{propData.myAnimal}}닉네임 추천받기</button>
      <button class="bttn-gradient bttn-md bttn-warning search-btn" @click="goToSearchResult">{{propData.myAnimal}}친구 찾으러 가기</button>
      <button class="bttn-gradient bttn-md bttn-warning feed-btn" @click="goToFeed">{{propData.myAnimal}}피드 보러가기</button>
      </div>
    </div>
    </div>
</template>

<script>
import _ from 'lodash'

export default {
  name: 'SimilarAnimalResult',
  components: {
  },
  data() {
    return {
      resultStep: 1,
      
    }
  },
  props: {
    propData: Object,
  },
  methods: {
    reTry(){
      this.$emit('re-try')
    },
    goToNickname(){
      this.$emit('go-to-nickname')
    },
    goToSearchResult(){
      // this.$router.push({name:'SearchResult', params: {searchWord: this.propData.myAnimal}})
      this.$emit('go-to-search-result')
    },
    goToFeed(){
      this.$emit('go-to-feed')
    }
  },
  computed: {
    examImage(){
      return require(`@/assets/animalexam/${this.propData.animalNumber}/${_.random(1,10)}.jpg`)
    } 
  }
}
</script>

<style>

</style>