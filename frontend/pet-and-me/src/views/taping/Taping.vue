<template>
  <div>

  <h1>네브바</h1>
  <h1>네브바</h1>
  <div v-if="level == 0">
    <h1>Taping하고싶은 피드를 추가해주세요!</h1>
    <div>
      <TapingFeedList
      :your-user-number="yourUserNumber"
      @feedNumber-update="checkedSave"
      />
    </div>
    <hr>
  </div>
  {{ this.returnVideo }}
  
  <hr>
    <h2>Taping test용 페이지</h2>
    <Taping
      @tapedata-update="tape2"
      >
    </Taping>
    <button @click="tape">리얼테스트</button>
  </div>
</template>

<script>
import axios from 'axios'
import Taping from '@/components/taping/Taping'
import {VIDEO_API_URL, BASE_API_URL} from '@/config/config.js'

export default {
  data() {
    return {
      datas: {
        feedPhotoDtoList: [1, 3],
        },
      tapeMovie: {
        type: 1,
        bgm: 5,
        images: null,
        userId: null,
      },
      images: null,
      returnVideo: null,
    }
  },
  components: {
    Taping
  },
  methods: {
    catchImages() {
      for (var step = 0; step < this.datas.feedPhotoDtoList.length; step++) {
        console.log(step)
        this.datas.feedPhotoDtoList[step] = {'feedNumber' : this.datas.feedPhotoDtoList[step]}
        console.log(this.datas.feedPhotoDtoList)
      }
      console.log(this.datas.feedPhotoDtoList)
      axios({
        method: 'post',
        url: 'http://i6b106.p.ssafy.io:8080/main/imagelist',
        data: this.datas,
        })
          .then((res) => {
            this.images = res.data
            this.tapeMovie.images = res.data
            console.log(typeof(this.tapeMovie.images))
          })
          .catch(err => {
            console.log(this.feedPhotoDtoList)
            console.log('구분3')
            console.log(err)
            console.log('안돼?')
          })
    },
    tape() {
        axios({
        method: 'post',
        url: `${VIDEO_API_URL}/api/v1/tape/`,
        data: this.tapeMovie
      })
        .then((res) => {
          console.log('생성요청')
          this.returnVideo = res.data
          console.log(this.returnVideo)
          console.log(res.data)
        })
        .catch(err => {
          console.log(err.response)
          console.log('에러')
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
      this.datas.userId = this.yourUserId
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
      for (var item in res) {
        this.datas.feedPhotoDtoList.push(item*1)
      }
      this.level ++
      this.catchImages()
    },
  },
}
</script>

<style scoped>
video { 
  width:90%;
  max-width:720px;
  height:auto;
}
</style>