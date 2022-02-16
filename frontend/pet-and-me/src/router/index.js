import Vue from 'vue'
import VueRouter from 'vue-router'
import Landing from '../views/user/Landing'
import Login from '../views/user/Login'
import PasswordChange from '../views/user/PasswordChange'
import Signup from '../views/user/Signup'
import Taping from '../views/taping/Taping'
import SimilarAnimal from '../views/similaranimal/SimilarAnimal'
import Search from '../views/search/Search'
import SearchResult from '../views/search/SearchResult'
import FeedCreate from '../views/feed/FeedCreate'
import NewsFeed from '../views/feed/NewsFeed'
import UserFeed from '../views/feed/UserFeed'
import FollowList from '../views/feed/FollowList'
import UserFeedUpdate from '../views/feed/UserFeedUpdate'
import FeedDetail from '../views/feed/FeedDetail'

import store from '../store'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Landing',
    component: Landing,
    meta: {requiresAuth: true}
  },
  {
    path: '/user/login',
    name: 'Login',
    component: Login,
  },
  {
    path: '/user/find',
    name: 'Find',
    component: PasswordChange,
  },
  {
    path: '/user/signup',
    name: 'Signup',
    component: Signup,
  },
  {
    path: '/taping/:yourUserId',
    name: 'Taping',
    component: Taping,
  },
  {
    path: '/similar/',
    name: 'SimilarAnimal',
    component: SimilarAnimal,
  },
  {
    path: '/search',
    name: 'Search',
    component: Search,
  },

  {
    path: '/search/:searchWord',
    name: 'SearchResult',
    component: SearchResult,
  },
  {
    path: '/feed/create',
    name: 'FeedCreate',
    component: FeedCreate,
  },
  {
    path: '/feed/newsfeed',
    name: 'NewsFeed',
    component: NewsFeed,
    meta: {requiresAuth: true}
  },
  {
    path: '/feed/userfeed/:yourUserId',
    name: 'UserFeed',
    component: UserFeed,
  },
  {
    path: '/feed/userfeed/:yourUserId/follow',
    name: 'FollowList',
    component: FollowList,
  },
  {
    path: '/feed/userfeed/:yourUserId/update',
    name: 'UserFeedUpdate',
    component: UserFeedUpdate,
  },
  {
    path: '/feed/:feedNumber',
    name: 'FeedDetail',
    component: FeedDetail,
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

router.beforeEach((to, from, next) => {

  const isLogin = store.getters['isLogin']
  console.log(isLogin)
  
 // requiresAuth 체크
  if (to.matched.some(record => record.meta.requiresAuth)) {

    if(to.name === 'Landing' && isLogin) {
      next('/feed/newsfeed')
    }
    else if(to.name === 'NewsFeed') {
      if(!isLogin) {
        alert('로그인이 필요합니다')
        next('/')
      }

      if(!store.getters['getUserInfo'].userNickName) {
        next('/similar')
      }
      
    }
  }
  // requiresAuth가 false일 때 (권한이 필요 없는 페이지)
  next()
})
export default router