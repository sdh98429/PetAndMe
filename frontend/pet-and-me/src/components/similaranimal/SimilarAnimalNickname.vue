<template>
  <div class="second-step">
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
  }
}
</script>

<style>

</style>