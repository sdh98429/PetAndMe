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
import FeedDetail from '../views/feed/FeedDetail'


Vue.use(VueRouter)

const routes = [
  {
    path: '/home',
    name: 'Landing',
    component: Landing,
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
    path: '/taping',
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
  },
  {
    path: '/feed/userfeed/:yourUserNumber',
    name: 'UserFeed',
    component: UserFeed,
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

export default router