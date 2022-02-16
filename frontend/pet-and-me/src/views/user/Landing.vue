<template>
  <div class="landing-container">
    <section class="home">
    </section>
    <button @click="signup" class="bttn-pill bttn-md bttn-success">Signup</button>
    <button @click="login" class="bttn-pill bttn-md bttn-success">Login</button>
    <button @click="newsfeed" class="bttn-pill bttn-md bttn-success">NewsFeed</button>
    <button @click="logout" class="bttn-pill bttn-md bttn-success">LogOut</button>
    <div style="height: 1000px">
      <h2 class="myH2">Pet And Me</h2>
      <p class="myP">
        Lorem, ipsum dolor sit amet consectetur adipisicing elit. Ipsum ratione facere animi impedit rem labore sint
        repellendus ipsa sapiente voluptatem aut excepturi quo itaque, ab earum cumque. Voluptatem beatae id inventore
        quod voluptate qui deserunt, quis placeat, tempora ex totam, dolore sequi harum eos voluptatibus animi labore
        officiis minus laboriosam
        Lorem, ipsum dolor sit amet consectetur adipisicing elit. Ipsum ratione facere animi impedit rem labore sint
        repellendus ipsa sapiente voluptatem aut excepturi quo itaque, ab earum cumque. Voluptatem beatae id inventore
        quod voluptate qui deserunt, quis placeat, tempora ex totam, dolore sequi harum eos voluptatibus animi labore
        officiis minus laboriosam
        Lorem, ipsum dolor sit amet consectetur adipisicing elit. Ipsum ratione facere animi impedit rem labore sint
        repellendus ipsa sapiente voluptatem aut excepturi quo itaque, ab earum cumque. Voluptatem beatae id inventore
        quod voluptate qui deserunt, quis placeat, tempora ex totam, dolore sequi harum eos voluptatibus animi labore
        officiis minus laboriosam
        Lorem, ipsum dolor sit amet consectetur adipisicing elit. Ipsum ratione facere animi impedit rem labore sint
        repellendus ipsa sapiente voluptatem aut excepturi quo itaque, ab earum cumque. Voluptatem beatae id inventore
        quod voluptate qui deserunt, quis placeat, tempora ex totam, dolore sequi harum eos voluptatibus animi labore
        officiis minus laboriosam
        Lorem, ipsum dolor sit amet consectetur adipisicing elit. Ipsum ratione facere animi impedit rem labore sint
        repellendus ipsa sapiente voluptatem aut excepturi quo itaque, ab earum cumque. Voluptatem beatae id inventore
        quod voluptate qui deserunt, quis placeat, tempora ex totam, dolore sequi harum eos voluptatibus animi labore
        officiis minus laboriosam
      </p>
    </div>
  </div>
</template>

<script>
// import $ from 'jquery'
import { mapGetters, mapActions } from "vuex"

export default {
  computed: {
    ...mapGetters(['getUserNumber', 'getUserInfo'])
  },
  methods: {
    ...mapActions([
      'logoutRemoveToekn'
    ]),
    signup() {
      this.$router.push({name:'Signup'})
    },
    login() {
      this.$router.push({name:'Login'})
    },
    newsfeed() {
      this.$router.push({name:'NewsFeed'})
    },
    logout() {
      console.log(this.getUserNumber)
      console.log(this.getUserInfo)
      this.logoutRemoveToekn()
      this.$router.push({name:'Landing'})
    }
  },
  mounted() {
    //   $('.my-nav').removeClass('affix')
    //   $(window).scroll(function() {
    //     if ($(document).scrollTop() > 200) {
    //       $('.my-nav').addClass('affix');
    //     } else {
    //       $('.my-nav').removeClass('affix');
    //     }
    //   });
    const footerEl = document.querySelector('#footer-container')
    footerEl.classList.add('inactive')
    const inputEl = document.querySelector('.my-search-bar')
    inputEl.classList.add('inactive')
  }
}
</script>

<style lang="scss" scoped>
.landing-container {
  position: relative;
  top:100px;
  z-index: 3;
}
// .home {
//   width: 100%;
//   height: 130vh;
//   background-image: url('../../assets/background.jpg');
//   background-position: center top;
//   background-size: cover;
// }

.myH2 {
  text-align: center;
  font-size: 4rem;
}

.myP {
  text-align: justify;
  padding-left: 15%;
  padding-right: 15%;
  font-size: 20px; 
  background-color: #fff;
}
@media all and (max-width:700px) {
  .myP {
    padding: 2%;
  }
}
</style>