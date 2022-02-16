<template>
  <div>
    <input multiple @change='onInputImage()' accept="image/*" ref="image" type="file">
    <h3>style</h3>
      <input type="radio" id="nomal" v-model="types.type" value="1"> <label for="nomal"> 기본 </label>
      <input type="radio" id="cartoon" v-model="types.type" value="2"><label for="cartoon"> 만화풍 </label>
      <input type="radio" id="turn" v-model="types.type" value="3"><label for="turn"> 좌우반전 </label>
      <!-- <input type="radio" id="grayscale" v-model="types.type" value="4"><label for="grayscale"> 흑백 </label> -->
    <h3>bgm</h3>
      <input type="radio" id="bright" v-model="types.bgm" value="1"> <label for="bright"> 밝은 </label>
      <input type="radio" id="calm" v-model="types.bgm" value="2"> <label for="calm"> 차분한 </label>
      <input type="radio" id="cure" v-model="types.bgm" value="3"> <label for="cure"> 발랄한 </label>
      <input type="radio" id="flashback" v-model="types.bgm" value="4"> <label for="flashback"> 추억 </label>
      <input type="radio" id="funcky" v-model="types.bgm" value="5"> <label for="funcky"> 크리스마스 </label>
      <input type="radio" id="optimistc" v-model="types.bgm" value="6"> <label for="optimistc"> 행복 </label>
      <input type="radio" id="optimistc" v-model="types.bgm" value="7"> <label for="optimistc"> 웅장 </label>
      <input type="radio" id="optimistc" v-model="types.bgm" value="8"> <label for="optimistc"> 슬픔 </label>
      <input type="radio" id="optimistc" v-model="types.bgm" value="9"> <label for="optimistc"> 코메딕 </label>
      <input type="radio" id="optimistc" v-model="types.bgm" value="10"> <label for="optimistc"> 소개 </label>
    <h3>Taping!</h3>
    <button @click="createTape">스타일 및 bgm 선택 완료</button>
  </div>
</template>

<script>

export default {
  name: 'Taping',
  data() {
    return {
      files: [],
      types: {
        type: 1,
        bgm: 2,
        images: 3,
      },
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
      this.$emit("tapedata-update", this.types)

    }
  },
}
</script>

<style scoped>
</style>