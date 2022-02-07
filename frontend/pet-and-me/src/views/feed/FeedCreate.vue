<template>
  <div>
    <h2> 피드 작성 페이지 </h2>
    <ul> 
      <li>
      <input multiple @change='onInputImage()' accept="image/*" ref="image" type="file">
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

      myUserNumber : 2,

      isCreate: false, // createBtn 눌렀는지
      isArrive: 0, // Picture, ContentsForm 신호 도착 개수
      files: [],


    }
  },
  components: {

    ContentsForm,
    CreateBtn,
  },
  props: {

  },
  methods: {
    onInputImage: function(){ // 이미지 추가
      this.files = this.$refs.image.files
      
      if (this.$refs.image.files.length >=5){
        this.files = []
        this.$refs.image.value = ''
        alert('이미지는 최대 5장까지 가능합니다.')
      } else {
        var step;
        for (step = 0; step < this.files.length; step++) {
          if (this.files[step].size/(1024*1024) > 5){
            this.files = []
            this.$refs.image.value = ''
            alert('이미지 파일은 최대 5MB까지 가능합니다.')
          }
        }
      }
    },

    createFeed: function (){ // createBtn 버튼 누름
      this.isCreate = true
    },
    createFeedContent: function (inputData){ // ContentsForm 에서 내용 도착
      this.feedContent = inputData
      this.isArrive = this.isArrive + 1
    },
    toNewsFeed: function(){ // NewsFeed 페이지로 이동
      this.$router.push({name: "NewsFeed"})
    }
  },
  
  watch: {
    isArrive: function(){ // 사진, 내용 도착
      var fileInput = this.files;

      if (this.isArrive == 1){ // 사진, 내용 둘 다 도착 시 back으로 보내주기
        if (this.feedContent) {

          if (fileInput.length) {
            var formData = new FormData();
            var step;
            for (step = 0; step < this.$refs.image.files.length; step++){
              formData.append('feedPhoto', fileInput[step]);

            }

            formData.append("feedContent", JSON.stringify(this.feedContent));
            formData.append("userNumber", JSON.stringify(this.myUserNumber));


            axios({
                method: 'post',
                url: 'http://i6b106.p.ssafy.io:8080/main/feed',
                data: formData,
                header: {
                          // 'Accept': 'application/json',
                          'Content-Type': 'multipart/form-data',
                        },
            })
            .then(() => {
                console.log("생성 완료");
                this.$router.push('NewsFeed')
            })
            .catch( (err) => {
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
      }
      this.isArrive = 0
    }
  }
}
</script>

<style>

</style>