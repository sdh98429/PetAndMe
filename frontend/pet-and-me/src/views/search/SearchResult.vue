<template>
  <div>
    <div>검색결과</div>
    <div>{{searchWord}}</div>
    <div>{{result}}</div>
    
  </div>
</template>

<script>
import axios from 'axios'

export default {
  name: 'SearchResult',
  data: function () {
    return {
      result: null,
      searchWord: this.$route.params.searchWord,
    }
  },
  components: {

  },
  props: {

  },
  methods: {
    getSearchResult: function (){ // 검색 결과 가져오기
      axios({
        method: 'get',
        url: 'http://i6b106.p.ssafy.io:8080/search/' + this.$route.params.searchWord,
      })
        .then(response => {
          this.result = response.data
        })
        .catch(err => {
          console.log(err)
        })
    }
  },
  created: function(){
    this.getSearchResult()
  }
}
</script>

<style>

</style>