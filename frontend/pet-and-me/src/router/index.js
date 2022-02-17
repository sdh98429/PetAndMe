import Vue from 'vue'
import VueRouter from 'vue-router'
// import Landing from '../views/user/Landing'
import Login from '../views/user/Login'
import Signup from '../views/user/Signup'
import Taping from '../views/taping/Taping'
import SimilarAnimal from '../views/similaranimal/SimilarAnimal'
import FeedCreate from '../views/feed/FeedCreate'
import NewsFeed from '../views/feed/NewsFeed'
import UserFeed from '../views/feed/UserFeed'
import FollowList from '../views/feed/FollowList'
import UserFeedUpdate from '../views/feed/UserFeedUpdate'
import FeedDetail from '../views/feed/FeedDetail'
import Search from'../views/search/Search.vue'
import store from '../store'

Vue.use(VueRouter)

const routes = [
  // {
  //   path: '/',
  //   name: 'Landing',
  //   component: Landing,
  //   meta: {requiresAuth: true}
  // },
  {
    path: '/test',
    name: 'Search',
    component: Search,
  },
  {
    path: '/login',
    name: 'Login',
    component: Login,
  },
  {
    path: '/signup',
    name: 'Signup',
    component: Signup,
  },
  {
    path: '/taping/:yourUserId',
    name: 'Taping',
    component: Taping,
    meta: {requiresAuth: true}
  },
  {
    path: '/similar/',
    name: 'SimilarAnimal',
    component: SimilarAnimal,
    meta: {requiresAuth: true}
  },
  {
    path: '/feed/create',
    name: 'FeedCreate',
    component: FeedCreate,
    meta: {requiresAuth: true}
  },
  {
    path: '/',
    name: 'NewsFeed',
    component: NewsFeed,
    meta: {requiresAuth: true}
  },
  {
    path: '/feed/userfeed/:yourUserId',
    name: 'UserFeed',
    component: UserFeed,
    meta: {requiresAuth: true}
  },
  {
    path: '/feed/userfeed/:yourUserId/follow/',
    name: 'FollowList',
    component: FollowList,
    meta: {requiresAuth: true}
  },
  {
    path: '/feed/userfeed/:yourUserId/update',
    name: 'UserFeedUpdate',
    component: UserFeedUpdate,
    meta: {requiresAuth: true}
  },
  {
    path: '/feed/:feedNumber',
    name: 'FeedDetail',
    component: FeedDetail,
    meta: {requiresAuth: true}
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
    if(to.name === 'NewsFeed' && !isLogin) {
      next('/login')
    }
    else if(to.name !== 'Login' && to.name !== 'Signup') {
      if(!isLogin) {
        alert('로그인이 필요합니다')
        next('/login')
      }
      else if(to.name !== 'SimilarAnimal' && !store.getters['getUserInfo'].userNickName && !store.getters['getUserNickName']) {
        next('/similar')
      }
    }
    else{
      next('/login')
    }
  }
  // requiresAuth가 false일 때 (권한이 필요 없는 페이지)
  next()
})
export default router