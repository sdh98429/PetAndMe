<template>
  <div id="app" class="my-container">
    <Navbar/>
    <router-view/>
    <footer >
      <div class="my-footer"></div>
    </footer>
  </div>
</template>
<script>
import Navbar from '@/views/navbar/Navbar'

export default {
  components: {
    Navbar
  },
  methods: {
    createFeed() {
      this.$router.push({ name: 'FeedCreate' })
    }
  }
}
</script>

<style>
@font-face {
    font-family: 'MinSans-Regular';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2201-2@1.0/MinSans-Regular.woff') format('woff');
    font-weight: normal;
    font-style: normal;
}
@import url(//fonts.googleapis.com/earlyaccess/notosanskr.css);

.notosanskr * { 
 font-family: 'Noto Sans KR', sans-serif;
}
#app{
  box-sizing: border-box;
}
html,
body {
  margin: 0px;
  padding: 0px;
  font-family: "Quicksand", sans-serif;
  font-size: 10px;
}

/* Mobile Web*/
@media screen and (max-width:768px){
  .my-container {
    height: 0;
    /* width: 400px; */
    width: 100%;
  }
  /* Feed Create Button */
  footer {
    position: fixed;
    bottom: 0;
  }
  footer .my-footer {
    position: relative;
    height: 100px;
  }
}
/* PC Web */
@media screen and (min-width:768px){

}
</style>