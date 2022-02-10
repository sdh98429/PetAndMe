<template>
  <div>
    <input type="text" v-model.trim="searchWord" @input="search" @keyup.enter="goToSearchResult(searchWord)">
    {{searchWord}}
    <div v-for="(recent, idx) in resultRecent" :key="idx">
      <div>{{recent}}</div>
    </div>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  name: 'Search',
  data: function () {
    return {
      searchWord: null,
      resultRecent: null,

      myUserNumber: 1,
    }
  },
  components: {

  },
  props: {

  },
  methods: {
    goToSearchResult: function(searchWord){ // 검색 결과 페이지로 이동
      console.log('이동')
      this.$router.push({path: `/search/${searchWord}`})
    },

    search: function(){
      console.log(this.searchWord)
    },

    getSearchHistory: function(){
      axios({
        method: 'get',
        url: 'http://i6b106.p.ssafy.io:8080/search/' + this.myUserNumber,
      })
        .then(response => {
          this.resultRecent = response.data
        })
        .catch(err => {
          console.log(err)
        })
    }
  },

  created : function(){
    this.getSearchHistory()
  }
}
</script>

<style>

</style>