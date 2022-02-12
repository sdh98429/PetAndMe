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
        <label for="input-image"><span class="input-image-label">사진을 선택해주세요</span></label>
        <input class="input-image" id="input-image" type="file" ref="inputImage" accept="image/*" @change="fileRead">
        <img class="accept-image" :src="inputfile" alt="input img" id="face-img" />
      </div>
      <button @click="test()">NEXT</button>
    </div>
    
    <similar-animal-result 
      v-show="step===2"
      :animal="myAnimal"
      :nicknameList="recommendNicknameList"
      @re-try="reTry"
    />
  </div>
</template>

<script>
import '@tensorflow/tfjs'
import * as tmImage from '@teachablemachine/image';
import move from '@/js/move.js'
import SimilarAnimalResult from '@/components/similaranimal/SimilarAnimalResult.vue'
import axios from 'axios';
import {BASE_API_URL} from '@/config/config.js'
// 1차버전 (21개동물, return 영어이름)
// const URL = "https://teachablemachine.withgoogle.com/models/wYmHduvKG/";
// 2차버전 (11개동물, return 숫자)
const URL = "https://teachablemachine.withgoogle.com/models/xe7b7XaZQ/";
let model,  maxPredictions // ,labelContainer

export default {
  components: {
    SimilarAnimalResult
  },
  data: function() {
    return {
      inputfile: require('@/assets/plus.png'),
      step: 0,
      myAnimal: '',
      recommendNicknameList: [],
      animalNumber: null,
    }
  },
  methods: {
    test() {
      this.step = 2
    },
    reTry() {
      this.step = 1
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
      this.myAnimal = animal.className
      this.getNicknameList()
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
        url: `${BASE_API_URL}/deco/${this.myAnimal}`
      })
        .then(res =>{
          console.log(res)
          this.recommendNicknameList = res.data
        })
        .catch(err =>{
          console.log(err)
        })
    },
    // image read
    fileRead: function() {
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
  }
}
</script>

<style scoped lang="scss" src="../../css/similaranimal.scss">

</style>