<template>
  <div>
      <button class="next-btn bttn-gradient bttn-md bttn-warning" @click="updateLevel">Next</button>
  </div>
</template>

<script>
export default {
  name: "SignupNext",
  data: function() {
    return {
      // step: 0,
    }
  },
  methods: {
    updateLevel() {
      // this.step++
      this.$emit('levelEvent')
    }
  }
}
</script>

<style>

</style>