<template>
  <v-app>
  <div v-if="level == 0">
    <h1>Taping하고싶은 피드를 추가해주세요!</h1>
    <div>
      <TapingFeedList
      :your-user-number="yourUserNumber"
      @feedNumber-update="checkedSave"
      />
    </div>
  </div>
  
    <Taping
      v-if="level == 1"
      @tapedata-update="tape2"
      >
    </Taping>

  <div v-if="level == 2" class="return-video">
    <div v-if="this.returnVideo">
      <video controls>
        <source type="video/mp4" :src="`data:video/mp4;base64,${returnVideo[0]}`">
      </video>
    </div>
    <div class="btn-container">
      <v-btn color="orange lighten-5" x-large @click="tape">Taping</v-btn>
      <v-btn color="orange lighten-5" x-large @click="tapingNew">Taping 페이지로 돌아가기</v-btn>
      <v-btn color="orange lighten-5" x-large @click="toHome">홈으로</v-btn>
    </div>
  </div>


      <div v-if="level == 2" class="return-video">
        <h1>Taping 버튼을 누르고 잠시만 기다리시면 동영상이 보입니다.</h1>
          <div calss="return-circular" style="text-align: center;" v-if="!returnVideo && tapingChecked">
              <v-progress-circular
                :size="50"
                color="amber"
                indeterminate
              ></v-progress-circular>
          </div>
        <div v-if="this.returnVideo">
          <video controls autoplay>
            <source type="video/mp4" :src="`data:video/mp4;base64,${returnVideo[0]}`">
          </video>
        </div>
        <div class="btn-container">
          <!-- <v-btn color="orange lighten-5" x-large @click="tape">Taping</v-btn>
          <v-btn color="orange lighten-5" x-large @click="tapingNew">Taping 페이지로 돌아가기</v-btn>
          <v-btn color="orange lighten-5" x-large @click="toHome">홈으로</v-btn> -->
          <button class="bttn-pill bttn-md bttn-warning taping-btn" @click="tape">Taping</button>
          <button class="bttn-pill bttn-md bttn-warning retry-btn" @click="tapingNew">피드 다시 고르기</button>
          <button class="bttn-pill bttn-md bttn-warning gohome-btn" @click="toHome">뉴스피드로 가기</button>
        </div>
      </div>
  </v-app>
</template>

<script>
import axios from 'axios'
import Taping from '@/components/taping/Taping'
import TapingFeedList from '@/components/taping/TapingFeedList'
import {VIDEO_API_URL, BASE_API_URL} from '@/config/config.js'
// import {BASE_API_URL} from '@/config/config.js'

export default {
  data() {
    return {
      level: 0,
      datas: {
        feedPhotoDtoList: [],
        },
      tapeMovie: {
        type: null,
        bgm: null,
        images: null,
        userId: null,
      },
      images: null,
      returnVideo: null,
      yourUserNumber: null,
      tapingChecked: false,
    }
  },
  components: {
    Taping,
    TapingFeedList,
  },
  methods: {
    // base64 이미지 받아오기
    catchImages() {
      for (var step = 0; step < this.datas.feedPhotoDtoList.length; step++) {
        this.datas.feedPhotoDtoList[step] = {'feedNumber' : this.datas.feedPhotoDtoList[step]}
      }
      axios({
        method: 'post',
        url: `${BASE_API_URL}/main/imagelist`,
        data: this.datas,
        })
          .then((res) => {
            this.images = res.data
            this.tapeMovie.images = res.data
          })
          .catch(err => {
            console.log(err)
          })
    },
    // taping
    tape() {
      this.tapingChecked = true
        axios({
        method: 'post',
        url: `${VIDEO_API_URL}/api/v1/tape/`,
        // url: 'http://127.0.0.1:8000/api/v1/tape/',
        data: this.tapeMovie
      })
        .then((res) => {
          this.returnVideo = res.data
        })
        .catch(err => {
          console.log(err)
        })
    },
    // type, bgm 선정
    tape2(res) {
      this.tapeMovie.type = res.type
      this.tapeMovie.bgm = res.bgm
      this.level++
    },
    getUserNumber() {
      this.myUserNumber = 2
    },
    asyncCall : async function(){
      await this.getUserNumber();
      await this.getFeed();
    },
    // 다시 만들기
    tapingNew() {
      this.$router.go()
    },
    // 홈으로
    toHome() {
      this.$router.push({ name: 'Landing' })
    },
    // 유저 feed list 받아오기
    getFeed: async function() {
      this.tapeMovie.userId = this.$route.params.yourUserId
      await axios({
        method: 'get',
        url: `${BASE_API_URL}/user/number/` + this.$route.params.yourUserId,
      })
        .then(response => {
        this.yourUserNumber = response.data
        })
    },
    // 체크한 이미지 저장
    checkedSave(res) {
      console.log(res)
      for (var item in res) {
        this.datas.feedPhotoDtoList.push(res[item])
      }
      this.level ++
      this.catchImages()
    },
  },
  created: function() {
    this.asyncCall()
  }
}
</script>

<style scoped>
.video { 
  width:90%;
  max-width:520px;
  height:auto;
}

.h1 {
  padding-top: 100px;
  font-family: sans-serif;
  font-size: 2rem;
  text-align: center;
}

.return-video {
  margin-top: 100px;
}

.btn-container {
  text-align: center;
}
</style>