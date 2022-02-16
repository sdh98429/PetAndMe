<template>
  <div class="sa-container">
    <div v-show="step===0">
      <div class="spinner-box">
        <div class="pulse-container">  
          <div class="pulse-bubble pulse-bubble-1"></div>
          <div class="pulse-bubble pulse-bubble-2"></div>
          <div class="pulse-bubble pulse-bubble-3"></div>
        </div>
      </div>
    </div>

    <div v-show="step===1" class="first-step">
      <h1>나는 무슨 동물을 닮았을까?</h1>
      <h2>밝은 배경에 얼굴이 잘 나오는 사진을 넣어주세요</h2>
      <div class="img-upload-box">
        <label v-if="!inputAccept" for="input-image"><span class="input-image-label">버튼을 눌러 사진을 선택해주세요</span></label>
        <label v-else for="input-image"><span class="input-image-label">사진을 변경하려면 여기를 눌러주세요</span></label>
        <input class="input-image" id="input-image" type="file" ref="inputImage" accept="image/*" @change="fileRead">
        <img class="accept-image" :src="inputfile" alt="input img" id="face-img" />
      </div>
      <button class="bttn-pill bttn-md bttn-warning next-step-btn" @click="nextStep">다음 단계</button>
    </div>
    
    <similar-animal-result 
      v-show="step===2"
      :propData="propData"
      @re-try="reTry" 
      @go-to-nickname="test1"
      @go-to-search-result="test2"
      @go-to-feed="test3"
    />
    <similar-animal-nickname
      v-show="step==='nickname'"
      :propData="propData"
    />
    <similar-animal-feed-list
      v-show="step==='feedList'"
      :propData="propData"

    />
    <similar-animal-search-result
      v-show="step==='searchResult'"
      :propData="propData"
    />
  </div>
</template>

<script>
import '@/css/similaranimal.scss'
import '@tensorflow/tfjs'
import * as tmImage from '@teachablemachine/image';
import move from '@/js/move.js'
import SimilarAnimalResult from '@/components/similaranimal/SimilarAnimalResult.vue'
import axios from 'axios';
import {BASE_API_URL} from '@/config/config.js'
import SimilarAnimalNickname from '@/components/similaranimal/SimilarAnimalNickname'
import SimilarAnimalFeedList from '@/components/similaranimal/SimilarAnimalFeedList'
import SimilarAnimalSearchResult from '@/components/similaranimal/SimilarAnimalSearchResult'
// import _ from 'lodash'

// 1차버전 (21개동물, return 영어이름)
// const URL = "https://teachablemachine.withgoogle.com/models/wYmHduvKG/";
// 2차버전 (11개동물, return 숫자)
const URL = "https://teachablemachine.withgoogle.com/models/xe7b7XaZQ/";

let model,  maxPredictions // ,labelContainer

export default {
  components: {
    SimilarAnimalResult,
    SimilarAnimalNickname,
    SimilarAnimalFeedList,
    SimilarAnimalSearchResult,
  },
  data: function() {
    return {
      inputfile: require('@/assets/plus.png'),
      step: 0,
      inputAccept: false,
      propData: {
        animalNumber: 1,
        recommendNicknameList: null,
        feedList: null,
        myAnimal: null,
        searchAnimalList: null,
        examImage: null,
      }
    }
  },
  methods: {
    reTry() {
      this.step = 1
    },
    test1(){

    },
    test2(){

    },
    test3(){

    },
    nextStep() {
      if(this.inputAccept){
        this.step = 2
        this.getNicknameList()
        this.getSAFeed()
        this.getAnimalName()
        console.log(this.propData)
        this.getSearchResult()
      } else {
        alert('사진을 선택해주세요')
      }
    },

    // case predict
    predict: async function () {
      var image = document.getElementById("face-img")
      const prediction = await model.predict(image, false);
      
      // 가장 닮은 동물
      let animal = prediction[0]
      // 유사도
      let maxProbability = prediction[0].probability
      for (let i = 0; i < maxPredictions; i++) {
        // 최대유사도 확인
        if (prediction[i].probability > maxProbability){
          maxProbability = prediction[i].probability
          animal = prediction[i]
        }
      }
      this.propData.animalNumber = animal.className
    },

    // model load
    init: async function() {
      const modelURL = URL + "model.json";
			const metadataURL = URL + "metadata.json";
			model = await tmImage.load(modelURL, metadataURL);
			maxPredictions = model.getTotalClasses();
		},
    getNicknameList() {
      axios({
        method: 'get',
        url: `${BASE_API_URL}/deco/${this.propData.animalNumber}`
      })
        .then(res =>{
          this.propData.recommendNicknameList = res.data
        })
        .catch(err =>{
          console.log(err)
        })
    },
    getSAFeed() {
      axios({
        method: 'get',
        url: `${BASE_API_URL}/main/favfeed?animalNumber=${this.propData.animalNumber}&userNumber=${this.myUserNumber}&cursor=`
      })
        .then(res => {
          this.propData.feedList = res.data
        })
        .catch(err => {
          console.log(err)
        })
    },
    getSearchResult() {
      axios({
        method: 'get',
        url: `${BASE_API_URL}/search/animal/${this.propData.myAnimal}`,
      })
        .then(res => {
          this.propData.searchAnimalList = res.data
        })
        .catch(err => {
          console.log(err)
        })
    },
    getAnimalName() {
      // this.propData.examImage = `@/assets/animalexam/${this.propData.animalNumber}/${_.random(0,11)}.jpg`
      switch(this.propData.animalNumber){
        case '1':
          this.propData.myAnimal = '강아지';
          break;
        case '2':
          this.propData.myAnimal = '고양이';
          break;
        case '3':
          this.propData.myAnimal = '오리';
          break;
        case '4':
          this.propData.myAnimal = '물고기';
          break;
        case '5':
          this.propData.myAnimal = '여우';
          break;
        case '6':
          this.propData.myAnimal = '개구리';
          break;
        case '7':
          this.propData.myAnimal = '쥐';
          break;
        case '8':
          this.propData.myAnimal = '돼지';
          break;
        case '9':
          this.propData.myAnimal = '토끼';
          break;
        case '10':
          this.propData.myAnimal = '뱀';
          break;
        case '11':
          this.propData.myAnimal = '거북이';
          break;
      }
    },

    // image read
    fileRead: function() {
      this.inputAccept = true
      // this.inputfile = this.$refs.inputImage.files[0]
      let reader = new FileReader();
      reader.onload = function (e) {
        this.inputfile = e.target.result
        const imgTag = document.getElementById('face-img')
        imgTag.setAttribute('src', this.inputfile)
      }
      reader.readAsDataURL(this.$refs.inputImage.files[0])
      // 사진 업로드하면 자동으로 결과제공
      setTimeout(() => { this.predict() }, 300)
    },
  },
  created() {
    this.init()
    setTimeout(()=> {
      this.step=1
    }, 1500)
  },
  mounted() {
    move('2', '30%', '#fff')
  },
  computed: {
    myUserNumber () {
      return this.$store.getters.getUserNumber
    }
  },
}
</script>

<style scoped lang="scss">
img{
  display: block;
}

</style>