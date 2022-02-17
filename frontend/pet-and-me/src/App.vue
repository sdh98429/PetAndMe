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
/* Loading Spinner */
.loader {
  top: 25vh;
  font-size: 10px;
  margin: 50px auto;
  text-indent: -9999em;
  width: 11em;
  height: 11em;
  border-radius: 50%;
  background: #000000;
  background: -moz-linear-gradient(left, #000000 10%, rgba(0,0,0, 0) 42%);
  background: -webkit-linear-gradient(left, #000000 10%, rgba(0,0,0, 0) 42%);
  background: -o-linear-gradient(left, #000000 10%, rgba(0,0,0, 0) 42%);
  background: -ms-linear-gradient(left, #000000 10%, rgba(0,0,0, 0) 42%);
  background: linear-gradient(to right, #000000 10%, rgba(0,0,0, 0) 42%);
  position: relative;
  -webkit-animation: load3 1.4s infinite linear;
  animation: load3 1.4s infinite linear;
  -webkit-transform: translateZ(0);
  -ms-transform: translateZ(0);
  transform: translateY(0);
}
.loader:before {
  width: 50%;
  height: 50%;
  background: #000000;
  border-radius: 100% 0 0 0;
  position: absolute;
  top: 0;
  left: 0;
  content: '';
}
.loader:after {
  background: #fff;
  width: 75%;
  height: 75%;
  border-radius: 50%;
  content: '';
  margin: auto;
  position: absolute;
  top: 0;
  left: 0;
  bottom: 0;
  right: 0;
}
@-webkit-keyframes load3 {
  0% {
    -webkit-transform: rotate(0deg);
    transform: rotate(0deg);
  }
  100% {
    -webkit-transform: rotate(360deg);
    transform: rotate(360deg);
  }
}
@keyframes load3 {
  0% {
    -webkit-transform: rotate(0deg);
    transform: rotate(0deg);
  }
  100% {
    -webkit-transform: rotate(360deg);
    transform: rotate(360deg);
  }
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
  body {
    /* height: 100%; */
    width: 100%;
    height: 100%;
    background-image: url("./assets/bg_test_2.png");
    background-repeat: repeat-y;
    background-size: cover;
    z-index: -10;
  }
}
</style>