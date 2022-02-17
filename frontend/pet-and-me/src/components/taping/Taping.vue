<template>
  <v-app>
    <div class="taping-container">

      <div class="radio1">
      <h1>style 선택</h1>
        <div class="form_radio_group">
          <div class="form_radio_group-item">
            <input id="radio-1" type="radio" name="radio" value="1" v-model="types.type" checked>
            <label for="radio-1">기본</label>
          </div>
          <div class="form_radio_group-item">
            <input id="radio-2" type="radio" name="radio" value="2" v-model="types.type">
            <label for="radio-2">만화풍</label>
          </div>
          <div class="form_radio_group-item">
            <input id="radio-3" type="radio" name="radio" value="3" v-model="types.type">
            <label for="radio-3">좌우 대칭</label>
          </div>
        </div>
      </div>
      
      <div class="radio2">
      <h1>bgm 선택</h1>
        <div class="form_radio_group">
          <div class="form_radio_group-item">
            <input id="bgm-radio-1" type="radio" name="radio1" value="1" v-model="types.bgm" checked>
            <label for="bgm-radio-1">밝은</label>
          </div>
          <div class="form_radio_group-item">
            <input id="bgm-radio-2" type="radio" name="radio1" value="2" v-model="types.bgm">
            <label for="bgm-radio-2">차분한</label>
          </div>
          <div class="form_radio_group-item">
            <input id="bgm-radio-3" type="radio" name="radio1" value="3" v-model="types.bgm">
            <label for="bgm-radio-3">발랄한</label>
          </div>
          <div class="form_radio_group-item">
            <input id="bgm-radio-4" type="radio" name="radio1" value="4" v-model="types.bgm">
            <label for="bgm-radio-4">추억</label>
          </div>
          <div class="form_radio_group-item">
            <input id="bgm-radio-5" type="radio" name="radio1" value="5" v-model="types.bgm">
            <label for="bgm-radio-5">크리스마스</label>
          </div>
          <div class="form_radio_group-item">
            <input id="bgm-radio-6" type="radio" name="radio1" value="6" v-model="types.bgm">
            <label for="bgm-radio-6">행복</label>
          </div>
          <div class="form_radio_group-item">
            <input id="bgm-radio-7" type="radio" name="radio1" value="7" v-model="types.bgm">
            <label for="bgm-radio-7">웅장한</label>
          </div>
          <div class="form_radio_group-item">
            <input id="bgm-radio-8" type="radio" name="radio1" value="8" v-model="types.bgm">
            <label for="bgm-radio-8">슬픔</label>
          </div>
          <div class="form_radio_group-item">
            <input id="bgm-radio-9" type="radio" name="radio1" value="9" v-model="types.bgm">
            <label for="bgm-radio-9">코메딕</label>
          </div>
          <div class="form_radio_group-item">
            <input id="bgm-radio-10" type="radio" name="radio1" value="10" v-model="types.bgm">
            <label for="bgm-radio-10">소개</label>
          </div>
        </div>
      </div>

      <div class="btn-taping-">
        <v-btn color="orange lighten-5" x-large @click="createTape">스타일 및 bgm 선택 완료</v-btn>
      </div>
    </div>
    
    
  </v-app>
</template>

<script>

export default {
  name: 'Taping',
  data() {
    return {
      radioGroup: 1,
      types: {
        type: null,
        bgm: null,
      },
    }
  },
  methods: {
    createTape() {
      if (this.types.type === null) {
        alert('영상 스타일을 선택해주세요!')
      } else if (this.types.bgm === null) {
        alert('영상 배경음악을 선택해주세요!')
      } else {
        this.$emit("tapedata-update", this.types)
      }

    }
  },
}
</script>

<style scoped>
.taping-container {
  width: 800px;
  border: 1px solid #ccc;
  padding-top: 100px;
  text-align: center;
}

.form_radio_group {
	display: inline-block;
	overflow: hidden;
}
.form_radio_group-item {
	display: inline-block;
	float: left;    
}
.form_radio_group input[type=radio] {
	display: none;
}
.form_radio_group label {
	display: inline-block;
	cursor: pointer;
	padding: 0px 15px;
	line-height: 34px;
	border: 1px solid #999;
	border-right: none;
	user-select: none;
}
 
.form_radio_group .form_radio_group-item:first-child label {
	border-radius: 6px 0 0 6px;
}
.form_radio_group .form_radio_group-item:last-child label {
	border-radius: 0 6px 6px 0;
	border-right: 1px solid #999;
}
 
/* Checked */
.form_radio_group input[type=radio]:checked + label {
	background: #ffe0a6;
}
 
/* Hover */
.form_radio_group label:hover {
	color: #666;
}
 
/* Disabled */
.form_radio_group input[type=radio]:disabled + label {
	background: #efefef;
	color: #666;
}

.btn-container {
  padding: 2em;
}

.radio1 {
  padding: 2em;
}

.radio2 {
  padding: 2em;
}

</style>