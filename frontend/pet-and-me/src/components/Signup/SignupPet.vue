<template>
  <div class="third-step">
    <h1>반려동물을 기르고 계신가요?</h1>

      <div class="btn-container">
        <button style="width:150px;" class="bttn-gradient bttn-sm bttn-warning" @click="isYes()">Yes</button>
        <button style="width:150px;" class="bttn-gradient bttn-sm bttn-warning" @click="isNo()">No</button>
      </div>
      <div v-if="credentials.petCheck">
        <div>
          
          <div class="group">
            <input class="my-input" type="text" id="petName" v-model="credentials.petName" placeholder="반려동물이름을 적어주세요" maxlength="20" @focus="checkFlag=false">
            <label for="petName">이름</label>
            <span class="highlight"></span>
            <span class="bar"></span>
          </div>
          <!-- <input v-model="credentials.petName" type="text" placeholder="이름을 입력해주세요" maxlength="20" @focus="checkFlag = false"/> -->
          <!-- <span v-if="checkFlag && !credentials.petName">이름을 입력하세요</span> -->

          <!-- datepicker -->
          <DatePicker v-model="credentials.petBirth" @date-update="dateSave"></DatePicker>
          <h3 style="margin-top:50px;">성별</h3>
            <div class="select-box">
                <!-- <select
                  class="select-input"
                  v-model="credentials.petGender"
                  @focus="checkFlag = false"
                >
                  <option value="">성별</option>
                  <option
                    v-for="(item, index) in genderList"
                    :key="index"
                    :value="item.value"
                  >
                  </option>
                </select>
                    {{ item.text }} -->
                <div v-for="(gender, index) in genderList" class="select-input" v-bind:key="index">
                  <input type="radio" :value="gender.value" v-model="credentials.petGender" :id="gender.value">
                  <label :for="gender.value">{{gender.text}}</label>
                </div>
              <span v-if="checkFlag && !credentials.petGender">성별을 선택하세요</span>
            </div>
        </div>

        <h3 style="margin-top:50px;">종류</h3>
        <div class="save-animal-box">
          <div v-for="animal in animalList" class="save-animal-checkbox" v-bind:key="animal">
            <input type="radio" :value="animal.animalNumber" v-model="credentials.petType" :id="animal.animalName">
            <label :for="animal.animalName">{{animal.animalName}}</label>
          </div>
        </div>
      </div>
        <!-- emit -->
      <button class="next-btn bttn-gradient bttn-md bttn-warning" @click="saveData">Next</button>
  </div>
</template>

<script>
import DatePicker from '../../components/Signup/DatePicker'
import axios from 'axios'
export default {
  filters: {
    comma(val) {
      return String(val).replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    },
  },
  data() {
    return {
      acceptNext: false,
      credentials: {
        petCheck: false,
        petName: '',
        petBirth: '',
        petGender: '',
        petType: '',
      },
      animalList: [
        {
          animalNumber: null,
          animalName: null
        }
      ],
      checkFlag: false,
      genderList: [
        {
          value: "M",
          text: "남아",
        },
        {
          value: "F",
          text: "여아",
        },
      ],
    };
  },
  created() {
    this.getAnimal()
  },
  computed: {

  },
  watch: {

  },
  methods: {
    isYes () {
      this.credentials.petCheck = true;
      this.acceptNext = true;
    },
    isNo () {
      this.credentials.petCheck = false;
      this.acceptNext = true;
    },
    dateSave(datePickerString) {
      this.credentials.petBirth = datePickerString
    },
    saveData() {
      if (this.credentials.petCheck){
        if (!this.credentials.petName) {
          alert('반려동물 이름 미입력')
        } else if (!this.credentials.petBirth) {
          alert('반려동물 생일 미입력')
        } else if (!this.credentials.petGender) {
          alert('반려동물 성별 미입력')
        } else if (!this.credentials.petType) {
          alert('반려동물 종류 미입력')
        } else {
          this.$emit("pet-update", this.credentials)
        } 
      } else{
        this.$emit("pet-update", this.credentials)
      }
    },

    // 데이터 empty 체크
    isEmpty(data) {
      if (data === "" || data === null || data === undefined) {
        return true;
      } else {
        return false;
      }
    },

    getAnimal() {
      axios({
          method: 'get',
          url: 'http://i6b106.p.ssafy.io:8080/animal',
        })
        .then((res) => {
          // console.log(res)
          this.animalList = res.data
        })
        .catch(err => {
          console.log(err.response) 
        })
    }
  },
  components: {
    DatePicker
  }
};
</script>
