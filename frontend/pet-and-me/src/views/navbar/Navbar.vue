<template>
  <div class="nav">
    <nav class="my-nav affix">
      <div class="navcontainer">
        <!-- Logo (Home Btn) -->
        <span class="logo" >
          <div @click="goHome">PET & ME</div>
        </span>
      </div>
    </nav>

    <!-- Search Bar -->
    <div class="search-bar">
      <input v-model="searchKeyword" type="text" class="search-input" @focus="onFocus" @blur="onBlur"/>
      <div class="search-placeholder">
        <i class="fas fa-search"></i>
        <div>검색</div>
      </div>
    </div>

    <!-- Footer Bar -->
    <div id="footer-container">
      <div id="my-footer">
        <div id="bubbleWrapper">
          <div id="bubble1" class="bubble"><span class="icon"><i class="fas fa-home"></i></span></div>
          <div id="bubble2" class="bubble"><span class="icon"><i class="fas fa-paw"></i></span></div>
          <div id="bubble3" class="bubble"><span class="icon"><i class="fas fa-plus"></i></span></div>
          <div id="bubble4" class="bubble"><span class="icon"><i class="fas fa-search"></i></span></div>
          <div id="bubble5" class="bubble"><span class="icon"><i class="fas fa-user"></i></span></div>
        </div>
        <div id="menuWrapper">
          <div id="menu1" class="menuElement" @click="goToNewsFeed()"><i class="fas fa-home"></i></div>
          <div id="menu2" class="menuElement" @click="goToSimilarAnimal()"><i class="fas fa-paw"></i></div>
          <div id="menu3" class="menuElement" @click="goToFeedCreate()"><i class="fas fa-plus"></i></div>
          <div id="menu4" class="menuElement" @click="goToSearch()"><i class="fas fa-search"></i></div>
          <div id="menu5" class="menuElement" @click="goToMyPage()"><i class="fas fa-user"></i></div>
        </div>
      </div>
      <div id="bgWrapper">
        <div id="bg"></div>
        <div id="bgBubble"></div>
      </div>
    </div>

    <svg width="0" height="0">
      <defs>
        <filter id="goo">
          <feGaussianBlur in="SourceGraphic" stdDeviation="20" result="blur" id="blurFilter" />
          <!-- -12 수정하면 벡터 깊이 수정가능 -->
          <feColorMatrix in="blur" mode="matrix" values="1 0 0 0 0  0 1 0 0 0  0 0 1 0 0  0 0 0 30 -11"
            result="goo" />
          <feComposite in="SourceGraphic" in2="goo" operator="atop" />
        </filter>
      </defs>
    </svg>
  </div>
</template>

<script>
import move from '../../js/move.js'
import $ from 'jquery'
export default {
  data() {
    return {
      searchKeyword: null,
    }
  },
  methods: {
    onFocus() {
      const placeholderEl = document.querySelector('.search-placeholder')
      placeholderEl.classList.add('focused')
    },
    onBlur() {
      const placeholderEl = document.querySelector('.search-placeholder')
      if(!this.searchKeyword.trim()){
      placeholderEl.classList.remove('focused')
      this.searchKeyword = null
      }
    },
    goHome() {
      this.$router.push({ name : 'Landing'})
    },
    goToNewsFeed() {
      move('1', '10%', '#fff')
      this.$router.push({ name : 'NewsFeed'})
    },
    goToSimilarAnimal() {
      move('2', '30%', '#fff')
      this.$router.push({ name : 'SimilarAnimal' })
    },
    goToFeedCreate() {
      move('3', '50%', '#fff')
      this.$router.push({ name : 'FeedCreate'})
    },
    goToSearch(){
      move('4', '70%', '#fff')
      this.$router.push({ name: 'Search' })
    },
    goToMyPage() {
      move('5', '90%', '#fff')
      this.$router.push({ name : 'UserFeed', params: { yourUserId : 'person1' }})
    },
  },
  mounted() {
    $('.navTrigger').click(function () {
      $(this).toggleClass('active');
      $(".sidebar_list").toggleClass("show_list");
      $(".sidebar_list").fadeIn();
    });
  }
}
  

</script>

<style lang="scss">
@import url('https://fonts.googleapis.com/css?family=Quicksand:400,500,700');

/* Navbar */
.my-nav {
  width: 100%;
  height: 60px;
  position: fixed;
  line-height: 60px;
  text-align: center;
  top:0;
  padding: 20px 0px;
  -webkit-transition: all 0.4s ease;
  transition: all 0.4s ease;
}

.my-nav span.logo {
  margin: 0 auto;
  font-weight: bold;
}

.my-nav span.logo div {
  text-decoration: none;
  color: #333;
  font-size: 2.5rem;
  text-align: center;
}

.navTrigger {
  
  display: none;
  float: left;
}

/* Media query section */

@media screen and (max-width: 768px){
  .navcontainer {
    width: 100%;
    position: relative;
    margin:0 auto;
  }
  .my-nav span.logo div {
    text-decoration: none;
    color: #333;
    font-size: 3.4rem;
  }
  .navTrigger {
    display: block;
  }

  .my-nav div.logo {
    margin-left: 15px;
  }

  .my-nav div.media_button {
    display: block;
  }

  .search-bar {
    width: 100%;
    position: fixed;
    bottom: 75px;
    background-color: #fff;
    height:50px;
    display:flex;
    align-items: center;
    justify-content: center;
  }
  .search-bar.inactive {
    display: none;
  }
  .search-bar .search-input {
    width: 62%;
    background-color: #eee;
    outline: none;
    border-radius: 10px;
    border:none;
    height:30px;
    padding: 0 15%;
    margin:0 auto;
  }
  .search-bar .search-placeholder {
    position: absolute;
    width: 90%;
    left: 0;
    top: 15px;
    color: #ccc;
  }
  .search-bar .search-placeholder.focused div {
    display:none;
  }
  .search-bar .search-placeholder i {
    position: absolute;
    left: 10%;
    font-size: 20px;
  }
  .search-bar .search-placeholder div {
    position: absolute;
    left: 20%;
    font-size:16px; 
    font-family: 'MinSans-Regular'
  }
  // Mobile Footer bar
  #footer-container{
    width: 100%;
    min-width:300px;
    height: 80px;
    background-color: #fff;
    display: flex;
    justify-content: flex-end;
    flex-direction: column;
    overflow: hidden;
    position: fixed;
    bottom: 0;
    font-size: 17px;
    z-index: 0;
    margin: 0 auto;
  }
  #footer-container.inactive{
    display:none;
  }

  #my-footer{
    width: 100%;
    height: 60px;
    background-color: #faf4e4;
    position: absolute;
  }

  #bubbleWrapper{
    position: absolute;
    display: flex;
    justify-content: space-around;
    width: 100%;
    bottom: 25px;
  }

  .bubble{
    background-color: #faf4e4;
    width: 50px;
    height: 50px;
    bottom: 85px;
    border-radius: 50%;
    z-index: 1;
    transform: translateY(120%);
    display: flex;
    justify-content: center;
    align-items: center;
  }
  .icon{
    opacity: 0;
  }

  #bubble1{
    transform: translateY(0%);
    box-shadow: 0 1px 3px rgba(0,0,0,0.12), 0 1px 2px rgba(0,0,0,0.24);
    > span{
      opacity: 0.7;
    }
  }

  #bgWrapper{
    filter: url(#goo);
    width: 100%;
    height: 30px;
    position: absolute;
    bottom: 60px;
  }
  #bg{
    background-color: #fff;
    width: 120%;
    height: 100%;
    margin-left: -10%;
  }
  #bgBubble{
    position: absolute;
    background-color: #fff;
    width: 70px;
    height: 70px;
    border-radius: 50%;
    bottom: -50px;
    left: 10%;
    transform: translateX(-50%);
  }

  #menuWrapper{
    position: absolute;
    width: 100%;
    display: flex;
    justify-content: space-around;
  }

  .menuElement{
    opacity: 0.4;
    transform: translateY(100%);
    cursor: pointer;
    &:hover{
      opacity: 0.5;
    }
  }

  #contentWrapper{
    position: absolute;
    top: 50%;
    width: 100%;
    transform: translateY(-50%);
    display: flex;
    justify-content: center;
    align-items: center;
    h2{
      color: #faf4e4;
      font-family: sans-serif;
      font-weight: 400;
    }
  }
  .content{
    display: none;
    opacity: 0;
  }
}

@media screen and (min-width:768px){
  .navcontainer {
    width: 1000px;
    position: relative;
    margin:0 auto;
  }
  .navTrigger {
    display: block;
  }

  .my-nav span.logo div {
    text-decoration: none;
    color: #333;
    font-size: 5rem;
  }
  .my-nav div.logo {
    margin-left: 15px;
  }
  .my-nav div.media_button {
    display: block;
  }
  #my-footer{
    display: none;
  }
}


/* Animation */
/* Inspiration taken from Dicson https://codemyui.com/simple-hamburger-menu-x-mark-animation/ */

.navTrigger {
  cursor: pointer;
  position: absolute;
  width: 30px;
  height: 25px;
  margin: 20px 20px;
  right: 10px;
}

.navTrigger i {
  background-color: #333;
  border-radius: 2px;
  content: '';
  display: block;
  width: 100%;
  height: 4px;
}

.navTrigger i:nth-child(1) {
  -webkit-animation: outT 0.8s backwards;
  animation: outT 0.8s backwards;
  -webkit-animation-direction: reverse;
  animation-direction: reverse;
}

.navTrigger i:nth-child(2) {
  margin: 5px 0;
  -webkit-animation: outM 0.8s backwards;
  animation: outM 0.8s backwards;
  -webkit-animation-direction: reverse;
  animation-direction: reverse;
}

.navTrigger i:nth-child(3) {
  -webkit-animation: outBtm 0.8s backwards;
  animation: outBtm 0.8s backwards;
  -webkit-animation-direction: reverse;
  animation-direction: reverse;
}

.navTrigger.active i:nth-child(1) {
  -webkit-animation: inT 0.8s forwards;
  animation: inT 0.8s forwards;
}

.navTrigger.active i:nth-child(2) {
  -webkit-animation: inM 0.8s forwards;
  animation: inM 0.8s forwards;
}

.navTrigger.active i:nth-child(3) {
  -webkit-animation: inBtm 0.8s forwards;
  animation: inBtm 0.8s forwards;
}

@-webkit-keyframes inM {
  50% {
    -webkit-transform: rotate(0deg);
  }

  100% {
    -webkit-transform: rotate(45deg);
  }
}

@keyframes inM {
  50% {
    transform: rotate(0deg);
  }

  100% {
    transform: rotate(45deg);
  }
}

@-webkit-keyframes outM {
  50% {
    -webkit-transform: rotate(0deg);
  }

  100% {
    -webkit-transform: rotate(45deg);
  }
}

@keyframes outM {
  50% {
    transform: rotate(0deg);
  }

  100% {
    transform: rotate(45deg);
  }
}

@-webkit-keyframes inT {
  0% {
    -webkit-transform: translateY(0px) rotate(0deg);
  }

  50% {
    -webkit-transform: translateY(9px) rotate(0deg);
  }

  100% {
    -webkit-transform: translateY(9px) rotate(135deg);
  }
}

@keyframes inT {
  0% {
    transform: translateY(0px) rotate(0deg);
  }

  50% {
    transform: translateY(9px) rotate(0deg);
  }

  100% {
    transform: translateY(9px) rotate(135deg);
  }
}

@-webkit-keyframes outT {
  0% {
    -webkit-transform: translateY(0px) rotate(0deg);
  }

  50% {
    -webkit-transform: translateY(9px) rotate(0deg);
  }

  100% {
    -webkit-transform: translateY(9px) rotate(135deg);
  }
}

@keyframes outT {
  0% {
    transform: translateY(0px) rotate(0deg);
  }

  50% {
    transform: translateY(9px) rotate(0deg);
  }

  100% {
    transform: translateY(9px) rotate(135deg);
  }
}

@-webkit-keyframes inBtm {
  0% {
    -webkit-transform: translateY(0px) rotate(0deg);
  }

  50% {
    -webkit-transform: translateY(-9px) rotate(0deg);
  }

  100% {
    -webkit-transform: translateY(-9px) rotate(135deg);
  }
}

@keyframes inBtm {
  0% {
    transform: translateY(0px) rotate(0deg);
  }

  50% {
    transform: translateY(-9px) rotate(0deg);
  }

  100% {
    transform: translateY(-9px) rotate(135deg);
  }
}

@-webkit-keyframes outBtm {
  0% {
    -webkit-transform: translateY(0px) rotate(0deg);
  }

  50% {
    -webkit-transform: translateY(-9px) rotate(0deg);
  }

  100% {
    -webkit-transform: translateY(-9px) rotate(135deg);
  }
}

@keyframes outBtm {
  0% {
    transform: translateY(0px) rotate(0deg);
  }

  50% {
    transform: translateY(-9px) rotate(0deg);
  }

  100% {
    transform: translateY(-9px) rotate(135deg);
  }
}

.affix {
  padding: 0;
  background-color: #faf4e4;
}

</style>