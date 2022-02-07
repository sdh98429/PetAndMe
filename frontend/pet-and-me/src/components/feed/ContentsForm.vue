<template>
  <div style="border: 1px solid;">
    <h3>댓글 작성</h3>
    <textarea 
      id="textarea"
      v-model.trim = "content"
      placeholder="내용을 적어주세요."
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