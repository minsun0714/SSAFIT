import { createApp } from 'vue'
import { createPinia } from 'pinia'
import { QueryClient, VueQueryPlugin } from '@tanstack/vue-query'
import AntDesignVue from 'ant-design-vue';

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

app.use(createPinia())
app.use(router)
app.use(VueQueryPlugin, { queryClient })
app.use(AntDesignVue);

app.mount('#app')
