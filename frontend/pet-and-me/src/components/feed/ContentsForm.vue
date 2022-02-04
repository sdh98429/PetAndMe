<template>
  <div class="container" style="border: 1px solid;">
    <div>ContentsForm</div>
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
      // console.log('Form에서 명령 받음')
      this.isCreateContent = this.isCreate
      if (this.isCreateContent){
        // console.log("내용 전송 준비")
        this.$emit('form-to-create', this.content)
        // console.log(this.content)
        // this.isCreateContent = false
      }
    }
  }
}
</script>

<style>

</style>