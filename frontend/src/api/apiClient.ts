import axios from 'axios';

const api = axios.create({
    baseURL: import.meta.env.VITE_API_URL,
});

// 요청 Interceptor
api.interceptors.request.use(
    (config) => {
        const token = localStorage.getItem('token');
        if (token) {
            config.headers.Authorization = `Bearer ${token}`;
        }
        return config;
    },
    (error) => {
        return Promise.reject(error);
    }
);

// 응답 Interceptor
api.interceptors.response.use(
  (response) => {
      return response;
  },
  async (error) => {
      const originalRequest = error.config;

      // 401 처리
      if (error.response && error.response.status === 401 && !originalRequest._retry) {
          originalRequest._retry = true; // 중복 요청 방지 플래그
          try {
              // Refresh token 요청 (쿠키에서 자동으로 전송)
              const { data } = await axios.post(
                  `${import.meta.env.VITE_API_URL}/api/auth/refresh`,
                  {},
                  {
                      withCredentials: true, // 쿠키를 포함한 요청
                  }
              );

              // 토큰 갱신 성공 시 로컬 스토리지 업데이트
              localStorage.setItem('token', data.accessToken);

              // 실패했던 요청 재시도
              originalRequest.headers.Authorization = `Bearer ${data.accessToken}`;
              return api(originalRequest);
          } catch (refreshError) {
              console.error('Token refresh failed, redirecting to login...');
              localStorage.removeItem('token');
              window.location.href = '/login'; // 로그인 페이지로 리다이렉트
              return Promise.reject(refreshError);
          }
      }

      return Promise.reject(error);
  }
);

export default api;
