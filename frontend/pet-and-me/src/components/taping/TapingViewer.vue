<template>
  <v-app id="app">
    <v-carousel hide-delimiters height="auto">
    <v-carousel-item v-for="(vid, i) in returnVideo" :key="vid">
      <v-sheet :color="white" height="100%" width="100%" tile>
        <v-row class="fill-height" align="center" justify="center">
        <!-- video -->
          <div class="return-video">
            <video controls>
              <source type="video/mp4" :src="`data:video/mp4;base64,${returnVideo[i]}`">
            </video>
            <!-- <h3>총{{returnVideo.length}}개의 추억중 {{ i + 1}}번째 Tape📽</h3> -->
          </div>
          
        </v-row>
      </v-sheet>
    </v-carousel-item>
  </v-carousel>
  </v-app>


</template>

<script>
import axios from 'axios'
import {VIDEO_API_URL} from '@/config/config.js'

export default {
    data() {
    return {
      model: 0,
      returnVideo: null,
      userId: {
        returnUserId: null,
      }
    }
  },
  props: ['returnUserId'],
  methods: {
    catchTape() {
      this.userId.returnUserId = this.returnUserId
      axios({
        method: 'post',
        url: `${VIDEO_API_URL}/api/v1/returntape/`,
        // url: 'http://127.0.0.1:8000/api/v1/returntape/',
        data: this.userId,
      })
        .then(res => {
          this.returnVideo = res.data
          console.log(this.returnVideo)
        })
        .catch(err => {
          console.log(err)
        })
    },
  },
  created() {
    this.catchTape()
  },
  computed: {
    // returnUserId() {
    //   return this.$store.getters.getUserInfo.userID
    // }
  }
}
</script>

<style scoped>
video { 
  width:90%;
  max-width:520px;
  height:auto;
  align-items: center;
}

.return-video {
  text-align: center;
}
</style>>