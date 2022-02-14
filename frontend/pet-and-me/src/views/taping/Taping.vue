<template>
  <div>
  <p>awef</p>
  <p>asef</p>
  <p>asef</p>
  <p>asef</p>
  <p>asef</p>
  <button @click="catchImages">가져오기</button>
  <hr>
  <button>list형식 test</button>
  <!-- <div v-for="(file, idx) in images" :key="'photo' + idx">
    <img v-show="idx == 0" :src="`data:image/png;base64,${images[idx].photo}`" />
  </div> -->
  {{ this.images }}
  <div v-for="(file, idx) in images" :key="'photo' + idx">
    <img :src="`data:image/png;base64,${images[idx]}`" />
  </div>
  <div v-if="this.returnVideo">
    <video controls>
      <source type="video/mp4" :src="`data:video/mp4;base64,${returnVideo[0]}`">
    </video>
  </div>
  {{ this.returnVideo }}
  <!-- <div v-for="(file, idx) in returnVideo" :key="'video' + idx">
    <video :src="`data:video/mp4;base64,${returnVideo[idx]}`" />
  </div> -->
  <!-- <video :src="`data:video/mp4;base64,${returnVideo[0]}`" /> -->
  
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
import Taping from '../../components/taping/Taping'

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
      },
      // feedNumbers: {
      //   feedNumbers: "1"
      // },
      images: null,
      returnVideo: null,
    }
  },
  components: {
    Taping
  },
  methods: {
    catchImages() {
      console.log('구분4')
      // console.log(this.feedPhotoDtoList[1]*1)
      // console.log(this.feedPhotoDtoList.length)
      console.log('구분2')
      for (var step = 0; step < this.datas.feedPhotoDtoList.length; step++) {
        console.log(step)
        this.datas.feedPhotoDtoList[step] = {'feedNumber' : this.datas.feedPhotoDtoList[step]}
        console.log(this.datas.feedPhotoDtoList)
      }
      console.log('구분')
      console.log(this.datas.feedPhotoDtoList)
      axios({
        method: 'post',
        url: 'http://i6b106.p.ssafy.io:8080/main/imagelist',
        data: this.datas,
        })
          .then((res) => {
            // this.images = res.data
            // console.log(res)
            // console.log('pass')
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
        url: 'http://127.0.0.1:8000/api/v1/tape/',
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
    tape2() {
      console.log('tape2버튼')
    }
  },
}
            // axios.get('http://i6b106.p.ssafy.io:8080/main/imagelist', {
            //   params: {
            //     feedNumbers: 1,
            //   }
            // }).then(res => {
            //   console.log(res)
            // }).catch(err => {
            //   console.log(err)
            //   console.log('안돼')
            // })
</script>

<style>

</style>