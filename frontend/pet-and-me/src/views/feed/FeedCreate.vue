<template>
  <div class="container">
    <h2> 피드 작성 페이지 </h2>
    <img :src="`data:image/png;base64,${fileInfoDtoList[0].photo}`" />
    <ul> 
      <li>
      <input multiple @change='onInputImage()' ref="Images" type="file">
      </li> 
    </ul>

    <hr>
    <div align="right">
      <button @click="toNewsFeed">X</button>
    </div>
    <br>
    <div><ContentsForm
      :is-create="isCreate"
      @form-to-create="createFeedContent"
      /></div>
    <br>
    <div align="right"><CreateBtn
      @create-click="createFeed"
      /></div>
    <br>
  </div>
</template>

<script>
import ContentsForm from '@/components/feed/ContentsForm'
import CreateBtn from '@/components/feed/CreateBtn'
import axios from 'axios'

export default {
  name: 'FeedCreate',
  data: function () {
    return {
      feedContent: null, 

      isCreate: false, // createBtn 눌렀는지
      isArrive: 1, // Picture, ContentsForm 신호 도착 개수
      files: [],
      fileInfoDtoList : [{"photo" : null,}],

    }
  },
  components: {

    ContentsForm,
    CreateBtn,
  },
  props: {

  },
  methods: {
    onInputImage: function(){
      this.files = this.$refs.Images.files
    },

    createFeed: function (){ // createBtn 버튼 누름
      // console.log('신호 도착!')
      this.isCreate = true
    },
    createFeedContent: function (inputData){ // ContentsForm 에서 내용 도착
      // console.log("내용 도착")
      this.feedContent = inputData
      this.isArrive = this.isArrive + 1
    },
    toNewsFeed: function(){ // NewsFeed 페이지로 이동
      this.$router.push({name: "NewsFeed"})
    }
  },
  
  watch: {
    isArrive: function(){ // 사진, 내용 도착
      console.log(this.files)
      var fileInput = this.files;
      console.log("fileInput")
      console.log(fileInput)

      if (this.isArrive == 2){ // 사진, 내용 둘 다 도착 시 back으로 보내주기
        if (this.feedContent) {
          console.log(fileInput)
          if (fileInput != []) {
            var formData = new FormData();
            var step;
            for (step = 0; step < this.$refs.Images.files.length; step++){
              formData.append('feedPhoto', fileInput[step]);

            }
            // // formData.append("document", documentJson); instead of this, use the line below.
            formData.append("feedContent", JSON.stringify(this.feedContent));
            formData.append("userNumber", JSON.stringify(2));

            console.log(formData)
            axios({
                method: 'post',
                url: 'http://i6b106.p.ssafy.io:8080/main/feed',
                data: formData,
                header: {
                          // 'Accept': 'application/json',
                          'Content-Type': 'multipart/form-data',
                        },
            })
            .then(function (response) {
                console.log(response);
                axios({
                  method: 'get',
                  url: 'http://i6b106.p.ssafy.io:8080/main/feed/16'
                })
                .then(function (response){
                  this.fileInfoDtoList = response.fileInfoDtoList
                })
                .catch(function (err) {
                  console.log(err);
                })
            })
            .catch(function (err) {
                console.log(err);
            });
          }
          else {
            alert("피드 그림을 넣어주세요")
          }
        } else {
          alert("피드 내용을 넣어주세요")
        }
        this.isCreate = false // 초기화
        this.isArrive = 1
      }
    }
  }
}
</script>

<style>

</style>