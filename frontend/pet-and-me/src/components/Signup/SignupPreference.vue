<template>
  <div class="final-step">
    <h3 style="text-align:center; ">피드를 보고싶은 동물을 선택해주세요</h3>
    <div class="save-animal-box">
      <div v-for="(animal,index) in animalList" class="save-animal-checkbox" v-bind:key="index">
        <input type="checkbox" :value="animal.animalNumber" v-model="credentials.selected" :id="animal.animalName">
        <label :for="animal.animalName">{{animal.animalName}}</label>
      </div>
    </div>
    

    <!-- emit -->
      <button class="next-btn bttn-gradient bttn-md bttn-warning" @click="saveData">가입완료</button>
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
