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
    createClick: function() {
      this.$emit('create-click')
    }
  }
}
</script>

<style>

</style>