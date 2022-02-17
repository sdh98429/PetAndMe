<template>
  <div class="second-step">
    <h2>원하는 닉네임을 클릭하시면 <br> 닉네임이 변경됩니다.</h2>
    <div class="nickname-box">
      <div 
        v-for="(nickname, idx) in propData.recommendNicknameList"
        :key="idx"
        class="nickname-result bttn-gradient bttn-sm bttn-default"
        :id="`nickname-btn${idx}`"
        @click="changeNickname(idx)"
        @mouseover="onHover(idx)"
        @mouseleave="onBlur(idx)"
      >
        {{nickname}}
      </div>
    </div>
    <div class="btn-box">
      <button class="bttn-gradient bttn-md bttn-warning back-btn" @click="goBack">돌아가기</button>
      <button class="bttn-gradient bttn-md bttn-warning next-step-btn" @click="getNicknameList">새로추천받기</button>
    </div>
  </div>
</template>

<script>
import axios from 'axios'
import { BASE_API_URL } from '@/config/config.js'
import {mapActions} from 'vuex'

export default {
  name: 'SimilarAnimalResult',
  props: {
    propData: Object,
  },
  methods: {
    ...mapActions([
      'nicknameRenew'
    ]),
    getNicknameList() {
      axios({
        method: 'get',
        url: `${BASE_API_URL}/deco/${this.propData.animalNumber}`
      })
        .then(res =>{
          this.propData.recommendNicknameList = res.data
        })
        .catch(err =>{
          console.log(err)
        })
    },
    goBack(){
      this.$emit('go-back')
    },
    changeNickname(idx){
      const checkNickname = this.propData.recommendNicknameList[idx]
      this.nicknameRenew(checkNickname)
      axios({
        url: `${BASE_API_URL}/user/nickName`,
        method: 'put',
        data: {
          "userNumber": `${this.myUserNumber}`,
          "userNickName": `${checkNickname}`
        }
      })
        .then(res => {
          console.log(res)
          alert('닉네임이 변경되었습니다.')
          localStorage.setItem('nickName', checkNickname)
        })
        .catch(err =>{
          console.log(err)
        })
    },
    onHover(idx){
      const btnEl = document.querySelector(`#nickname-btn${idx}`)
      btnEl.classList.remove('bttn-default')
      btnEl.classList.add('bttn-warning')
    },
    onBlur(idx){
      const btnEl = document.querySelector(`#nickname-btn${idx}`)
      btnEl.classList.remove('bttn-warning')
      btnEl.classList.add('bttn-default')
    },
  },
  computed: {
    myUserNumber() {
      return this.$store.getters.getUserNumber
    },
  }
}
</script>

<style>

</style>