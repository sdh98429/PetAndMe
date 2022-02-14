<template>
  <div>
  <div class="center">
    <h2>반려동물 생년월일</h2>
    <datepicker
      id="datepicker"
      @input="formatPicker()"
      v-model="model.date"
      valueType="format"
      :format="DatePickerFormat"
      :language="ko"
    >
    </datepicker>
    </div>
    {{ this.model.date }}
  </div>
</template>

<script>
import Datepicker from "vuejs-datepicker";
import {ko} from 'vuejs-datepicker/dist/locale'

export default {
  components: {
    Datepicker,
  },
  data() {
    return {
      ko: ko,
      model: {
        date: ""
      },
      DatePickerFormat: "yyyy-MM-dd",
    };
  },
  methods: {
    // 그레고리력 날짜로 변경
    formatPicker: function() {
      var today = this.model.date;
      var year = today.getFullYear();
      var month = ('0' + (today.getMonth() + 1)).slice(-2);
      var day = ('0' + today.getDate()).slice(-2);
      var dateString = year + '-'  + month  + '-' + day;
      console.log(dateString);
      this.$emit("date-update", dateString)
    }
  },
};
</script>

<style>
.center {
    width: 30%;
    margin: 0 auto;
}
</style>

