<template>
  <div>
    <input type="text" v-model.trim="searchWord" @input="search" @keyup.enter="goToSearchResult(searchWord)">
    {{searchWord}}
  </div>
</template>

<script>
export default {
  name: 'Search',
  data: function () {
    return {
      searchWord: null,
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
    }
  }
}
</script>

<style>

</style>