<template>
  <v-app id="app">
    <v-carousel hide-delimiters height="auto">
    <v-carousel-item v-for="(vid, i) in returnVideo" :key="vid">
      <v-sheet :color="white" height="100%" width="100%" tile>
        <v-row class="fill-height" align="center" justify="center">
        <!-- video -->
          <div>
            <video controls>
              <source type="video/mp4" :src="`data:video/mp4;base64,${returnVideo[i]}`">
            </video>
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
    }
  },
  // props: ['returnUserId'],
  methods: {
    catchTape() {
      axios({
        method: 'post',
        url: `${VIDEO_API_URL}/api/v1/returntape/`,
        data: this.returnUserId,
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
    returnUserId() {
      return this.$store.getters.getUserInfo.userID
    }
  }
}
</script>

<style scoped>
video { 
  width:90%;
  max-width:520px;
  height:auto;
}

</style>>