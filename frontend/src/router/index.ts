import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'

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
      name: 'login',
      component: () => import('../views/LoginView.vue'),
    },
    {
      path: '/signup',
      name: 'signup',
      component: () => import('../views/SignUpView.vue'),
    },
    {
      path: '/mypage/:id',
      name: 'mypage',
      component: () => import('../views/MyPageView.vue'),
    },
    {
      path: '/videos',
      name: 'videos',
      component: () => import('../views/VideoListView.vue'),
      children: [
        {
          path: 'create',
          name: 'createVideo',
          component: () => import('../views/VideoCreateView.vue'),
        },
      ],
    },
    {
      path: '/video/:id',
      name: 'video',
      component: () => import('../views/VideoDetailView.vue'),
    },
    {
      path: '/exercise',
      name: 'exercise',
      component: () => import('../views/ExerciseView.vue'),
    },
  ],
})

export default router
