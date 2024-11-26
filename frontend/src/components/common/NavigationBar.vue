<script setup lang="ts">
import { RouterLink, useRouter } from 'vue-router'
import '@/assets/index.css'
import { Routes } from '../../utils/enum'
import MemberApiFacade from '@/api/apiFacade/MemberApiFacade'
import { UserOutlined, LogoutOutlined } from '@ant-design/icons-vue';


const { data: memberInfo, isSuccess, refetch } = MemberApiFacade.useFetchUserInfo()
const router = useRouter()
const logout = () => {
  localStorage.removeItem('token')
  refetch()
  router.push('/')
  alert('로그아웃 되었습니다.')
}
</script>

<template>
  <header class="p-10 flex flex-row justify-between items-center">
    <nav class="relative w-full h-98 font-bold">
      <div class="logo-section">
        <img src="@/assets/Home/icon.jpg" alt="Logo Icon" class="logo-icon" />
        <RouterLink :to="{ name: Routes.HOME }" class="logo-text">MUSiCLE</RouterLink>
      </div>
      <!-- Center navigation items -->
      <div class="center-nav">
        <RouterLink :to="{ name: Routes.VIDEOS }" class="nav-link">Video</RouterLink>
        <RouterLink :to="{ name: Routes.EXERCISE }" class="nav-link">Record</RouterLink>
        <RouterLink :to="{ name: Routes.CREATE_VIDEO }" class="nav-link">Regist</RouterLink>
      </div>

      <div v-if="isSuccess" class="absolute-right flex flex-row gap-x-4 items-center">
  <img :src="memberInfo?.profileImg" alt="프로필 사진" class="profile-img" />
  <span class="nickname">{{ memberInfo?.nickname }}</span>
  <RouterLink :to="{ name: Routes.MYPAGE_LIKED_VIDEOS }" class="icon-link">
    <UserOutlined class="aligned-icon" /> <!-- 마이페이지 아이콘 -->
  </RouterLink>
  <button @click="logout" class="icon-link">
    <LogoutOutlined class="aligned-icon" /> <!-- 로그아웃 아이콘 -->
  </button>
</div>


      <div v-else class="absolute-right flex flex-row gap-x-1 text-blue-100">
        <RouterLink :to="{ name: Routes.LOGIN }">Login</RouterLink>
        <RouterLink :to="{ name: Routes.SIGN_UP }">Register</RouterLink>
      </div>
    </nav>
  </header>
</template>

<style scoped>
/* Logo Section Styles */
.logo-section {
  display: flex;
  align-items: center;
  gap: 5px; /* Space between icon and text */
  position: absolute;
  left: 0;
  top: 50%;
  transform: translateY(-50%);
}

.logo-icon {
  height: 30px; /* Adjust to match the text size */
  width: 30px; /* Maintain square aspect ratio */
  object-fit: contain; /* Prevent image distortion */
}

.logo-text {
  font-size: 2rem;
  font-family: Georgia, 'Times New Roman', Times, serif;
  font-weight: bold;
  color: #0001c8;
  text-transform: uppercase;
}


/* Navigation links */
.nav-link {
  font-size: 1.3rem;
  padding: 0.5rem;
  color: black;
  text-transform: capitalize;
  transition: color 0.3s ease;
}

.nav-link:hover {
  color: #0001c8;
}

/* Center the navigation items */
.center-nav {
  position: absolute;
  left: 50%;
  transform: translateX(-50%);
  display: flex;
  gap: 2rem;
  top: 50%;
  transform: translate(-50%, -50%);
  align-items: center;
}

/* Logo aligned to the left */
.absolute-left {
  position: absolute;
  left: 0;
  top: 50%;
  transform: translateY(-50%);
}

/* User info or login/register aligned to the right */
.absolute-right {
  position: absolute;
  right: 0;
  top: 50%;
  transform: translateY(-50%);
}

/* Optional styles for nav */
nav {
  position: relative;
  width: 100%;
  height: 40px;
  display: flex;
  align-items: center;
}

/* Profile image rounded and aligned */
.profile-img {
  border-radius: 50%; /* Make the image circular */
  object-fit: cover; /* Ensure the image is not distorted */
  height: 40px; /* Fixed height for consistency */
  width: 40px; /* Fixed width for consistency */
}

/* Nickname styles */
.nickname {
  font-size: 1rem; /* Match the size with icons */
  line-height: 1; /* Align text with the profile image */
  color: black; /* Customize as needed */
}

/* Icon styles */
.icon-link {
  display: flex; /* Ensure icons align properly */
  align-items: center; /* Vertically align icons */
  justify-content: center; /* Center icon inside button */
  font-size: 2rem; /* Match icon size */
  color: black; /* Default icon color */
  transition: color 0.3s ease;
}

.icon-link:hover {
  color: #0001c8; /* Hover color for icons */
}

/* Aligned icons */
.aligned-icon {
  font-size: 1.3rem; /* Ensure consistent icon size */
  vertical-align: middle; /* Align icon with text vertically */
  display: inline-block; /* Prevent inline formatting issues */
}

/* Ensure flex items are properly aligned */
.absolute-right {
  display: flex;
  align-items: center; /* Vertically align all items */
  gap: 1rem; /* Uniform gap between items */
}

</style>
