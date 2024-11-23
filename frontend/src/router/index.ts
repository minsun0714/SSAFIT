import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import { Routes } from '@/utils/enum'

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
      path: '/mypage',
      name: Routes.MY_PAGE,
      component: () => import('../views/MyPageView.vue'),
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
      component: () => import('../views/MyPageView.vue'),
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
      path: '/video/:id',
      name: Routes.VIDEO,
      component: () => import('../views/VideoDetailView.vue'),
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
    },
    {
      path: '/exercise',
      name: Routes.EXERCISE,
      component: () => import('../views/ExerciseView.vue'),
    },
    { path: '/:catchAll(.*)', redirect: '/' }, // 404 처리
  ],
})

export default router
