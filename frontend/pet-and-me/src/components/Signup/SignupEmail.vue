<template>
  <div>
    <h1>단계표시 2 / 4 진행 중 (Progress bar)</h1>
    <h1>회원가입 - 이메일</h1>
    <div>
      <h3>이메일 인증</h3>
        <input type="text" v-model="credentials.userEmail" placeholder="이메일을 입력해주세요" @focus="checkFlag = false"/>
      <span v-if="checkFlag && !credentials.userEmail">이메일을 입력해주세요</span>
      <button @click="certi"> 인증번호 전송 </button>
    </div>

    <div v-show="certiFlag">
      <input type="text" placeholder="인증번호를 입력해주세요"/>
      <button> 인증 </button>
    </div>
    <!-- emit -->
    <button @click="saveData"> 다음 </button>

    <!-- next button -->

  </div>
</template>

<script>
export default {
  filters: {
    comma(val) {
      return String(val).replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    },
  },
  data() {
    return {
      checkFlag: false,
      certiFlag: false,
      credentials: {
        userEmail: "",
      },
    };
  },
  computed: {
    idValid() {
      return /^[A-Za-z0-9]+$/.test(this.email);
    },
    nextPageDisabledFlag() {
      let flag = true;
      if (this.email !== "") {
        flag = false;
      }
      return flag;
    },
  },
  methods: {
    saveData() {
      this.$emit("email-update", this.credentials)
    },
    // 데이터 empty 체크
    isEmpty(data) {
      if (data === "" || data === null || data === undefined) {
        return true;
      } else {
        return false;
      }
    },
    certi() {
      this.certiFlag = true;
    },
  },
};
</script>