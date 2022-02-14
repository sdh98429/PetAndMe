<template>
  <div class="second-step">
    <div class="result-box">
      <div class="result-message">
        결과메시지
      </div>
      <div class="image-box">
        <img class="result-image" src="@/assets/unknown_image.jpg" alt="sample img">
      </div>
      <div class="retry-btn" @click="reTry">다시해보기</div>
    </div>
    <div class="nickname-box">
      <div 
        v-for="(nickname, idx) in nicknameList"
        :key="idx"
        class="nickname-result"
      >
        {{nickname}}
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'SimilarAnimalResult',
  props: {
    animal: String,
    nicknameList: Array,
  },
  methods: {
    reTry(){
      this.$emit('re-try')
    }
  }
}
</script>

<style>

</style>