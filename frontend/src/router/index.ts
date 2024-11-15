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
      path: '/mypage/:id',
      name: Routes.MY_PAGE,
      component: () => import('../views/MyPageView.vue'),
    },
    {
      path: '/videos',
      name: Routes.VIDEOS,
      component: () => import('../views/VideoListView.vue'),
      children: [
        {
          path: 'create',
          name: Routes.CREATE_VIDEO,
          component: () => import('../views/VideoCreateView.vue'),
        },
      ],
    },
    {
      path: '/video/:id',
      name: Routes.VIDEO,
      component: () => import('../views/VideoDetailView.vue'),
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
