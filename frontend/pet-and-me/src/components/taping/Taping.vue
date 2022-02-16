<template>
  <div>
    <h3>style</h3>
      <input type="radio" id="nomal" v-model="types.type" value="1"> <label for="nomal"> 기본 </label>
      <input type="radio" id="cartoon" v-model="types.type" value="2"><label for="cartoon"> 만화풍 </label>
      <input type="radio" id="turn" v-model="types.type" value="3"><label for="turn"> 좌우반전 </label>
      <input type="radio" id="grayscale" v-model="types.type" value="4"><label for="grayscale"> 흑백 </label>
    <h3>bgm</h3>
      <input type="radio" id="bright" v-model="types.bgm" value="1"> <label for="bright"> 밝은 </label>
      <input type="radio" id="calm" v-model="types.bgm" value="2"> <label for="calm"> 차분한 </label>
      <input type="radio" id="cure" v-model="types.bgm" value="3"> <label for="cure"> 힐링 </label>
      <input type="radio" id="flashback" v-model="types.bgm" value="4"> <label for="flashback">회고</label>
      <input type="radio" id="funcky" v-model="types.bgm" value="5"> <label for="funcky"> 펑키 </label>
      <input type="radio" id="optimistc" v-model="types.bgm" value="6"> <label for="optimistc"> 천진난만한 </label>

    <button @click="createTape">스타일 및 bgm 선택 완료</button>
  </div>
</template>

<script>

export default {
  name: 'Taping',
  data() {
    return {
      types: {
        type: null,
        bgm: null,
      },
    }
  },
  methods: {
    createTape() {
      if (this.types.type === null) {
        alert('영상 스타일을 선택해주세요!')
      } else if (this.types.bgm === null) {
        alert('영상 배경음악을 선택해주세요!')
      } else {
        this.$emit("tapedata-update", this.types)
      }

    }
  },
}
</script>

<style>

</style>