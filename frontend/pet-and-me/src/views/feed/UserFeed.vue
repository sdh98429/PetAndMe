<template>
  <div class="container">
    <h2>유저 피드</h2>
    <div><Profile
      :userNumber="userNumber"
      />
    </div>
    <div>팔로우하기 버튼</div>
    <br>
    <div>follower, following 표시</div>
    <div>영상 썸네일, 영상 제목, 영상 내용</div>
    <div>영상 만드는 버튼</div>
    <br>
    <div><UserFeedList
      />
    </div>
  </div>
</template>

<script>
import Profile from '@/components/feed/Profile'
import UserFeedList from '@/components/feed/UserFeedList'


export default {
  name: 'UserFeed',
  data: function () {
    return {
      userNumber: "null",
    }
  },
  components: {
    Profile,
    UserFeedList,

  },
  props: {

  },
  methods: {
    getUserNumber : function (){
      this.userNumber = null // 현재 페이지의 유저로 userNumber 가져오는 로직
    }
  },
  created: function(){
    this.getUserNumber()
  }
}
</script>

<style>

</style>