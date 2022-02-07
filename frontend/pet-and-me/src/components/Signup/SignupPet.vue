<template>
  <div class>
    <h1>단계표시 3 / 4 진행 중 (Progress bar)</h1>
    <h1>회원가입 - 반려동물 </h1>

      <div>
        <h3>반려동물 있는지 여부 체크</h3>
        <button @click="isYes()">Yes</button>
        <button @click="isNo()">No</button>
      </div>
      <div v-if="credentials.petCheck">
        <div>
          <div>
            <h3>이름</h3>
              <input v-model="credentials.petName" type="text" placeholder="이름을 입력해주세요" maxlength="20" @focus="checkFlag = false"/>
            <span v-if="checkFlag && !credentials.petName">이름을 입력하세요</span>
          </div>

          <!-- datepicker -->
          <DatePicker v-model="credentials.petBirth" @date-update="dateSave"></DatePicker>

          <div>
            <h3>성별</h3>
              <select
                v-model="credentials.petGender"
                @focus="checkFlag = false"
              >
                <option value="">성별</option>
                <option
                  v-for="(item, index) in genderList"
                  :key="index"
                  :value="item.value"
                >
                  {{ item.text }}
                </option>
              </select>
            <span v-if="checkFlag && !credentials.petGender">성별을 선택하세요</span>
          </div>
        </div>

        <div>
          <h3>종류</h3>
            <label> <input type="radio" v-model="credentials.petType" value="1"> 강아지 </label>
            <label> <input type="radio" v-model="credentials.petType" value="2"> 고양이 </label>
            <label> <input type="radio" v-model="credentials.petType" value="3"> 조류 </label>
            <label><input type="radio" v-model="credentials.petType" value="4"> 설치류 </label>
            <label> <input type="radio" v-model="credentials.petType" value="5"> 기타 </label>
        </div>

        <p>{{ this.credentials }}</p>
      </div>
        <!-- emit -->
        <button @click="saveData"> 다음 </button>

  </div>
</template>

<script>
import DatePicker from '../../components/Signup/DatePicker'

export default {
  filters: {
    comma(val) {
      return String(val).replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    },
  },
  data() {
    return {
      credentials: {
        petCheck: false,
        petName: null,
        petBirth: null,
        petGender: null,
        petType: null,
      },
      checkFlag: false,
      genderList: [
        {
          value: "Male",
          text: "남아",
        },
        {
          value: "FeMale",
          text: "여아",
        },
      ],
    };
  },
  computed: {
    idValid() {
      return /^[A-Za-z0-9]+$/.test(this.email);
    },
  },
  watch: {
    onlyNum() {
      this.onlyNum = this.onlyNum.replace(/[^0-9]/g, "");
    },
  },
  methods: {
    isYes () {
      this.credentials.petCheck = true;
    },
    isNo () {
      this.credentials.petCheck = false;
    },
    dateSave(datePickerString) {
      this.credentials.petBirth = datePickerString
    },
    saveData() {
      this.$emit("pet-update", this.credentials)
    },

    // 데이터 empty 체크
    isEmpty(data) {
      if (data === "" || data === null || data === undefined) {
        return true;
      } else {
        return false;
      }
    },
  },
  components: {
    DatePicker
  }
};
</script>
