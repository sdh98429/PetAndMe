<template>
  <div>
    <button @click="createClick">생성</button>
  </div>
</template>

<script>
export default {
  name: 'CreateBtn',
  data: function () {
    return {

    }
  },
  components: {

  },
  props: {

  },
  methods: {
    createClick: function() { // 버튼 눌리면 FeedCreate로 신호 보냄
      this.$emit('create-click')
    }
  }
}
</script>

<style>

</style>