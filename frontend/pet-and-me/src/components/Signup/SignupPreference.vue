<template>
  <div>
    <h1>단계표시 4 / 4 진행 중 (Progress bar)</h1>
    <h1>회원가입 - 선호동물</h1>
    <h1>항목 개인별 수정 가능 여부</h1>
    <!-- 아이디, 비밀번호 입력 -->
    <h3>선호동물 체크</h3>

    <!-- <div>
      <input type="checkbox" value="1" v-model="credentials.selected"> 강아지
      <input type="checkbox" value="2" v-model="credentials.selected"> 고양이
      <input type="checkbox" value="3" v-model="credentials.selected"> 조류
      <input type="checkbox" value="4" v-model="credentials.selected"> 설치류
      <input type="checkbox" value="5" v-model="credentials.selected"> 기타
    </div> -->

    <div>
      <span v-for="(animal, index) in animalList" class="saveAnimalIndex" v-bind:key="animal">
        <input type="checkbox" :value="animal.animalNumber" v-model="credentials.selected">{{animal.animalName}}
        <br v-if="(index+1) % 3 == 0">
      </span>
    </div>

    <!-- emit -->
    <button @click="saveData"> 가입완료 </button>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  data() {
    return {
      animalList: [
        {
          animalNumber: null,
          animalName: null
        }
      ],
      credentials: {
        selected: [],
      }
    };
  },
  created() {
    this.getAnimal()
  },
  computed: {
  },
  methods: {
    saveData() {
      if (this.credentials.selected.length < 1) {
          alert('선호동물 미선택')
        } else {
          this.$emit("preference-update", this.credentials)
        } 
    },
    getAnimal() {
      axios({
          method: 'get',
          url: 'http://i6b106.p.ssafy.io:8080/animal',
        })
        .then((res) => {
          console.log(res)
          this.animalList = res.data
        })
        .catch(err => {
          console.log(err.response) 
        })
    }
  },
};
</script>
