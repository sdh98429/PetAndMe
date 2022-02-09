<template>
  <div>
    <input multiple @change='onInputImage()' accept="image/*" ref="image" type="file">
    <h3>style</h3>
      <label> <input type="radio" v-model="style" value="1"> 기본 </label>
      <label> <input type="radio" v-model="style" value="2"> 만화풍 </label>
    <h3>bgm</h3>
      <label> <input type="radio" v-model="bgm" value="1"> 밝은 </label>
      <label> <input type="radio" v-model="bgm" value="2"> 차분한 </label>
      <label> <input type="radio" v-model="bgm" value="3"> 회고 </label>
      <label> <input type="radio" v-model="bgm" value="4"> 펑키 </label>
    <div>
      <p>스타일 {{this.style}}</p>
      <p>bgm {{ this.bgm }}</p>
    </div>

    <button @click="createTape">taping</button>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  name: 'Taping',
  data() {
    return {
      files: [],
      style: null,
      bgm: null,
    }
  },
  methods: {
      onInputImage: function(){
      this.files = this.$refs.image.files
      
      if (this.$refs.image.files.length > 16){
        this.files = []
        this.$refs.image.value = ''
        alert('이미지는 최대 16장까지 가능합니다.')
      } else {
        var step;
        for (step = 0; step < this.files.length; step++) {
          if (this.files[step].size/(1024*1024) > 30){
            this.files = []
            this.$refs.image.value = ''
            alert('이미지 파일은 최대 30MB까지 가능합니다.')
          }
        }
      }
    },
    createTape() {
      axios({
        method: 'post',
        url: 'http://127.0.0.1:8000/api/v1/tape/',
        data: this.credentials
      })
        .then(() => {
          console.log('생성요청')
        })
        .catch(err => {
          console.log(err.response)
        })
    }
  },
}
</script>

<style>

</style>