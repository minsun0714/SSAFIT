// src/shims-vue-agile.d.ts
declare module 'vue-agile' {
  import { DefineComponent } from 'vue';

  // vue-agile을 Vue 컴포넌트로 정의
  const VueAgile: DefineComponent<{
    arrows?: boolean;
    speed?: number;
    timing?: string;
    fade?: boolean;
    autoplay?: boolean;
  }>;

  export default VueAgile;
}
