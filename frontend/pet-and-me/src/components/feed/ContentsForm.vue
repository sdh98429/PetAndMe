<template>
  <div>
    <h3>피드에 적을 내용을 적어주세요.</h3>
    <textarea 
      id="textarea"
      v-model.trim = "content"
      rows="4"
      max-rows="4"></textarea>
    </div>
</template>

<script>
export default {
  name: 'ContentsForm',
  data: function () {
    return {
      content: null,
      isCreateContent: false,
    }
  },
  components: {

  },
  props: {
    isCreate : Boolean,
  },
  methods: {

  },
  watch: {
    isCreate: function(){ // createBtn 눌렸으면 feed create로 데이터 전송

      this.isCreateContent = this.isCreate
      if (this.isCreateContent){

        this.$emit('form-to-create', this.content)

      }
    }
  }
}
</script>

<style>

</style>