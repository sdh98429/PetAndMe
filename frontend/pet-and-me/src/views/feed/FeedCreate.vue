<template>
  <div class="feed-create-container">
    <h2>피드 만들기</h2>
    <h3>피드를 생성할 사진을 선택해주세요</h3>
    <ul> 
      <li>
        <input multiple @change='onInputImage()' accept="image/*" ref="image" type="file">
      </li> 
    </ul>

    <div>
      <ContentsForm
        :is-create="isCreate"
        @form-to-create="createFeedContent"
      />
    </div>
    <div class="form-footer">
      <button class="bttn-gradient bttn-sm bttn-warning back-btn" @click="toBack">돌아가기</button>
      <button class="bttn-gradient bttn-sm bttn-warning create-btn" @click="createFeed">생성</button>
    </div>
  </div>
</template>

<script>
import ContentsForm from '@/components/feed/ContentsForm'
import axios from 'axios'
import move from '@/js/move.js'
import '@/css/feedcreate.css'

export default {
  name: 'FeedCreate',
  data: function () {
    return {
      feedContent: null, 

      isCreate: false, // createBtn 눌렀는지
      isArrive: 0, // Picture, ContentsForm 신호 도착 개수
      files: [],
    }
  },
  components: {
    ContentsForm,
  },
  props: {

  },
  methods: {
    onInputImage: function(){ // 이미지 추가
      this.files = this.$refs.image.files
      if (this.$refs.image.files.length >5){
        this.files = []
        this.$refs.image.value = ''
        alert('이미지는 최대 5장까지 가능합니다.')
      } else {
        var step;
        for (step = 0; step < this.files.length; step++) {
          if (this.files[step].size/(1024*1024) > 3){
            this.files = []
            this.$refs.image.value = ''
            alert('이미지 파일은 최대 3MB까지 가능합니다.')
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
    toBack: function(){ // NewsFeed 페이지로 이동
      history.back()
      // this.$router.push({name: "NewsFeed"})
    }
  },
  mounted() {
    move('3', '50%', '#fff')
  },
  computed: {
    myUserNumber () {
      return this.$store.getters.getUserNumber
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

            formData.append("feedContent", this.feedContent);
            formData.append("userNumber", JSON.stringify(this.myUserNumber));

            // console.log(formData);
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
                this.$router.go()
                // 상세피드로 이동
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
  },
}
</script>

<style>
</style>