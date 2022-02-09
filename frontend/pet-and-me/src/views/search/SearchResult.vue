<template>
  <div>
    <div>검색결과</div>
    <div>단어 검색 : {{searchWord}}</div>
    <div>아이디 검색 : {{resultId}}</div>
    <div>닉네임 검색 : {{resultNickname}}</div>
    <div>최근 검색: {{resultRecent}}</div>
    
  </div>
</template>

<script>
import axios from 'axios'

export default {
  name: 'SearchResult',
  data: function () {
    return {
      resultId: null,
      resultNickname: null,
      resultRecent: null,
      searchWord: this.$route.params.searchWord,
      myUserNumber : 1,
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
        url: 'http://i6b106.p.ssafy.io:8080/search/rt/userid/' + this.$route.params.searchWord,
        // url: 'http://172.30.1.12:8080/search/rt/userid/' + this.$route.params.searchWord,
      })
        .then(response => {
          this.resultId = response.data
        })
        .catch(err => {
          console.log(err)
        })

      axios({
        method: 'get',
        url: 'http://i6b106.p.ssafy.io:8080/search/rt/userName/' + this.$route.params.searchWord,
        // url: 'http://172.30.1.12:8080/search/rt/userName/' + this.$route.params.searchWord,
      })
        .then(response => {
          this.resultNickname = response.data
        })
        .catch(err => {
          console.log(err)
        })

      const searchSave = {
        searchWord : this.searchWord,
        userNumber : this.myUserNumber,
      }

      axios({
        method: 'post',
        // http://i6b106.p.ssafy.io:8080/search?searchWord=pe&userNumber=1
        // url: 'http://i6b106.p.ssafy.io:8080/search',
        url: 'http://i6b106.p.ssafy.io:8080/search?searchWord=' + this.searchWord + '&userNumber=' + this.myUserNumber,
        // url: 'http://172.30.1.12:8080/search/',
        // data: searchSave,
      })
        .then(() => {
          console.log(searchSave)
          console.log('검색어 저장')
          axios({
            method: 'get',
            url: 'http://i6b106.p.ssafy.io:8080/search/' + this.myUserNumber,
            // url: 'http://172.30.1.12:8080/search/' + this.myUserNumber,
          })
            .then(response => {
              this.resultRecent = response.data
            })
            .catch(err => {
              console.log(err)
            })
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