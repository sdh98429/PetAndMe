<template>
  <div class="test-container">
    <div>검색 단어 : {{searchWord}}</div>
    <button v-for="(tab, index) in tabs"
      :key="index"
      v-bind="{active: currentTab === index}"
      @click="currentTab = index">{{tab}}
    </button>
    <div>
      <div v-show="currentTab==0">
        <div v-for="(Id, idx) in resultId" :key="idx" style="border: 1px solid;">
          <img @click="toUserFeed(Id.userID)" :src="'http://i6b106.p.ssafy.io:8080/main/image?file=' + Id.saveFolder + Id.userPhotoName" alt="프로필 사진" style="width: 300px; height: 150px; object-fit: contain;">
          <div>{{Id.userNickName}}</div>
          <div>@{{Id.userID}}</div>
        </div>
      </div>
      <div v-show="currentTab==1">
        <div v-for="(Nickname, idx) in resultNickname" :key="idx" style="border: 1px solid;">
          <img @click="toUserFeed(Nickname.userID)" :src="'http://i6b106.p.ssafy.io:8080/main/image?file=' + Nickname.saveFolder + Nickname.userPhotoName" alt="프로필 사진" style="width: 300px; height: 150px; object-fit: contain;">
          <div>{{Nickname.userNickName}}</div>
          <div>@{{Nickname.userID}}</div>
        </div>
      </div>
      <div v-show="currentTab==2">
        <div v-for="(Animal, idx) in resultAnimal" :key="idx" style="border: 1px solid;">
          <img @click="toUserFeed(Animal.userID)" :src="'http://i6b106.p.ssafy.io:8080/main/image?file=' + Animal.saveFolder + Animal.userPhotoName" alt="프로필 사진" style="width: 300px; height: 150px; object-fit: contain;">
          <div>{{Animal.userNickName}}</div>
          <div>@{{Animal.userID}}</div>
        </div>
      </div>
    </div>    
  </div>
</template>

<script>
import axios from 'axios'

export default {
  name: 'SearchResult',
  data: function () {
    return {
      NewSearchWord : null,

      resultId: null, // ID 검색 결과
      resultNickname: null, // 닉네임 검색 결과
      resultAnimal: null, // 반려동물 종류 검색 결과
      resultRecent: null, // 최근 검색 결과
      searchWord: this.$route.params.searchWord, // 검색 단어


      currentTab: 0, // 현재 탭
      // tabs: ['아이디 검색 결과', '닉네임 검색 결과', '최근 검색 단어'],
      tabs: ['아이디 검색 결과', '닉네임 검색 결과', '반려동물 종류 검색 결과']
    }
  },
  components: {

  },
  props: {

  },
  methods: {
    goToSearchResult: function(NewSearchWord){ // 검색 결과 페이지로 이동
      if (NewSearchWord){
        this.$router.push({path: `/search/${NewSearchWord}`})
        this.$router.go();
        console.log(this.searchWord)
      }      
    },

    getSearchResult: function (){ // 검색 결과 가져오기
    if (this.$route.params.searchWord){

      // userid 검색 결과
      axios({
        method: 'get',
        url: 'http://i6b106.p.ssafy.io:8080/search/rt/userid/' + this.$route.params.searchWord,
      })
        .then(response => {
          this.resultId = response.data
        })
        .catch(err => {
          console.log(err)
        })

      // nickname 검색 결과
      axios({
        method: 'get',
        url: 'http://i6b106.p.ssafy.io:8080/search/rt/userName/' + this.$route.params.searchWord,
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

      // 반려동물 종류 검색 결과
      axios({
        method: 'get',
        url: 'http://i6b106.p.ssafy.io:8080/search/animal/' + this.$route.params.searchWord,
      })
        .then(response => {
          this.resultAnimal = response.data
        })
        .catch(err => {
          console.log(err)
        })

      // 최근 검색 결과 저장 및 조회
      axios({
        method: 'post',
        // url: 'http://i6b106.p.ssafy.io:8080/search?searchWord=' + this.searchWord + '&userNumber=' + this.myUserNumber,
        url: 'http://i6b106.p.ssafy.io:8080/search',
        data: searchSave,
      })
        .then(() => {
          console.log('검색어 저장 완료')
        })
        .catch(err => {
          console.log(err)
        })
        .finally(() => {
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

        })

    }


    },
    toUserFeed : function(userId){ // 유저 피드로 이동
      this.$router.push({name: `UserFeed`, params : {yourUserId: userId}})
    }
  },
  created: function(){
    this.getSearchResult()
  },

  watch: {
    searchWord: function(){
      this.getSearchResult()
    }
  },
  computed: {
    myUserNumber () {
      return this.$store.getters.getUserNumber
    }
  },
}
</script>

<style>
.test-container {
  position: relative;
  top:100px;
}
</style>