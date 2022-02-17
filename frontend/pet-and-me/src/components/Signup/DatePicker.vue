<template>
  <div>
  <div class="center">
    <h3 style="font-size: 20px; position: relative; margin-top:25px;" >반려동물 생년월일</h3>
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
input#datepicker {
  width: 90%;
  margin-left: 5%;
  border: 1px solid #ccc;
}
</style>

