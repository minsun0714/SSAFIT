import {
  createRouter,
  createWebHistory,
  type NavigationGuardNext,
  type RouteLocationNormalized,
} from 'vue-router'
import HomeView from '../views/HomeView.vue'
import { Routes } from '@/utils/enum'

const isAuthenticated = () => {
  return !!localStorage.getItem('token')
}

const requireAuth = (
  to: RouteLocationNormalized,
  from: RouteLocationNormalized,
  next: NavigationGuardNext,
) => {
  if (isAuthenticated()) {
    next()
  } else {
    const userConfirmed = confirm('로그인이 필요한 요청입니다. 로그인 하시겠습니까?')
    if (userConfirmed) {
      next({ name: Routes.LOGIN })
    } else {
      next(false)
    }
  }
}

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomeView,
    },
    {
      path: '/login',
      name: Routes.LOGIN,
      component: () => import('../views/LoginView.vue'),
    },
    {
      path: '/signup',
      name: Routes.SIGN_UP,
      component: () => import('../views/SignUpView.vue'),
    },
    {
      // 컴포넌트 가드 필요
      path: '/mypage',
      name: Routes.MY_PAGE,
      component: () => import('../views/MyPageView.vue'),
      beforeEnter: requireAuth,
      children: [
        {
          path: 'liked-videos',
          name: 'liked-videos',
          component: () => import('../components/MyPage/LikedVideoListTab.vue'),
        },
        {
          path: 'reviews',
          name: 'reviews',
          component: () => import('../components/MyPage/MyReviewListTab.vue'),
        },
        {
          path: 'info',
          name: 'info',
          component: () => import('../components/MyPage/InfoManagementTab.vue'),
        },
        {
          path: 'admin',
          name: 'admin',
          component: () => import('../components/MyPage/AdminTab.vue'),
        },
      ],
    },
    {
      path: '/mypage/:memberId',
      name: 'others-mypage',
      component: () => import('../views/OthersMyPageView.vue'),
      children: [
        {
          path: 'liked-videos',
          name: 'others-liked-videos',
          component: () => import('../components/MyPage/LikedVideoListTab.vue'),
        },
        {
          path: 'reviews',
          name: 'others-reviews',
          component: () => import('../components/MyPage/MyReviewListTab.vue'),
        },
      ],
    },
    {
      path: '/video/:videoId',
      name: Routes.VIDEO,
      component: () => import('../views/VideoDetailView.vue'),
      redirect: to => ({
        name: Routes.VIDEO_PLAY,
        params: to.params,
      }),
      children: [
        {
          path: 'video-play',
          name: Routes.VIDEO_PLAY,
          component: () => import('../components/VideoDetail/VideoPlayTab.vue'),
        },
        {
          path: 'video-reviews',
          name: Routes.VIDEO_REVIEWS,
          component: () => import('../components/VideoDetail/VideoReviewListTab.vue'),
        },
      ],
    },
    {
      path: '/videos',
      name: Routes.VIDEOS,
      component: () => import('../views/VideoListView.vue'),
    },
    {
      path: '/createVideo',
      name: Routes.CREATE_VIDEO,
      component: () => import('../views/VideoCreateView.vue'),
      beforeEnter: requireAuth
    },
    {
      path: '/exercise',
      name: Routes.EXERCISE,
      component: () => import('../views/ExerciseView.vue'),
      beforeEnter: requireAuth
    },
    { path: '/:catchAll(.*)', redirect: '/' }, // 404 처리
  ],
})

export default router
