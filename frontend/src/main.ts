import { createApp } from 'vue'
import { createPinia } from 'pinia'
import { QueryClient, VueQueryPlugin } from '@tanstack/vue-query'
import VueAgile from 'vue-agile'

import App from './App.vue'
import router from './router'

const app = createApp(App)

// QueryClient 인스턴스 생성 및 기본 옵션 설정
export const queryClient = new QueryClient({
  defaultOptions: {
    queries: {
      refetchOnWindowFocus: true,
    },
  },
})

// VueAgile을 전역 컴포넌트로 등록
app.component('VueAgile', VueAgile)

app.use(createPinia())
app.use(router)
app.use(VueQueryPlugin, { queryClient })

app.mount('#app')
