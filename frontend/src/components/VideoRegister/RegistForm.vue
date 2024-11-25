<template>
  <div class="flex items-center justify-center min-h-screen bg-black">
    <div class="bg-white rounded-lg shadow-md p-8 w-full max-w-lg flex flex-col items-center">
      <h1 class="text-4xl font-bold text-center mb-6 text-black">Create Your Activity</h1>
      <iframe src="https://giphy.com/embed/VFDeGtRSHswfe" width="480" height="302" style="" frameBorder="0" class="giphy-embed rounded-lg" allowFullScreen></iframe>
      <br>
      <a-form
        ref="formRef"
        :model="formState"
        :rules="rules"
        :label-col="labelCol"
        :wrapper-col="wrapperCol"
      >
        <!-- URL Input -->
        <a-form-item ref="name" label="Url" name="name">
          <a-input v-model:value="formState.videoUrl" placeholder="Enter a URL" />
        </a-form-item>

        <!-- Type Selection -->
        <a-form-item label="Type" name="resource">
          <a-radio-group v-model:value="formState.part" class="flex justify-evenly">
            <a-radio value="1">🏃 Run</a-radio>
            <a-radio value="2">💪 Strength</a-radio>
            <a-radio value="3">🧘 Relax</a-radio>
          </a-radio-group>
        </a-form-item>

        <!-- Description -->
        <a-form-item label="Intro" name="desc">
          <a-textarea
            v-model:value="formState.introduceText"
            placeholder="Please write a brief introduction."
            auto-size="{ minRows: 3, maxRows: 6 }"
          />
        </a-form-item>
        <br>

        <!-- Buttons -->
        <a-form-item :wrapper-col="{ span: 24 }" class="text-center">
          <a-button type="primary" class="w-1/3 black-button" @click="onSubmit">Create</a-button>
          <a-button class="w-1/3 ml-4 white-button" @click="resetForm">Reset</a-button>
        </a-form-item>
      </a-form>
    </div>
  </div>
</template>

<script lang="ts" setup>
import { ref, reactive } from "vue";
import { notification } from "ant-design-vue";
import VideoService from "@/api/services/VideoService"; // API 서비스
import { VideoRegistDTO } from "@/api/interfaces/request";

type Part = 'RUN' | 'STRENGTH' | 'RELAX'
// Enum 매핑
const partMapping: Record<number, Part> = {
  1: "RUN",
  2: "STRENGTH",
  3: "RELAX",
};

// 폼 상태
const formState = reactive({
  videoUrl: "", // 비디오 URL
  part: 1, // 기본 값: 1 (RUN)
  introduceText: "", // 소개 텍스트
});

// 유효성 검사 규칙
const rules = {
  videoUrl: [
    { required: true, message: "Please input the video URL", trigger: "change" },
    { type: "url", message: "Please enter a valid URL", trigger: "blur" },
  ],
  part: [{ required: true, message: "Please select a video type", trigger: "change" }],
  introduceText: [{ required: true, message: "Please provide a description", trigger: "blur" }],
};

const formRef = ref();

// 폼 제출
const onSubmit = async () => {
  try {
    await formRef.value.validate(); // 폼 유효성 검사
    const payload: VideoRegistDTO = {
      videoUrl: formState.videoUrl,
      part: partMapping[formState.part],
      introduceText: formState.introduceText,
    };

    // API 호출
    const response = await VideoService.registerVideo(payload);
    console.log('Response:', response.introductUrl, response.part, response.videoUrl);

    // 성공 알림
    notification.success({
      message: "Success",
      description: "The video has been registered successfully!",
    });

    resetForm(); // 폼 초기화
  } catch (error: any) {
    console.error("Error during submission:", error);

    // 실패 알림
    notification.error({
      message: "Error",
      description: error.response?.data?.message || "Failed to register the video.",
    });
  }
};

// 폼 초기화
const resetForm = () => {
  formRef.value.resetFields();
};
</script>

<style scoped>
/* Background */
.bg-gray-100 {
  background-color: #f5f5f5;
}

/* Title Styling */
h1 {
  color: black;
  font-weight: bold;
}

/* Button Styles */
.black-button {
  background-color: black;
  color: white;
  border: none;
  border-radius: 8px;
  font-weight: bold;
}

.black-button:hover {
  background-color: #333;
}

.white-button {
  background-color: white;
  color: black;
  border: 2px solid black;
  border-radius: 8px;
  font-weight: bold;
}

.white-button:hover {
  background-color: #f0f0f0;
  color: black;
}

/* Input and Textarea Styles */
a-input,
a-textarea {
  border: 1px solid black;
}

a-input:focus,
a-textarea:focus {
  border-color: black;
  box-shadow: 0 0 5px rgba(0, 0, 0, 0.2);
}

/* Radio Button Customization */
a-radio {
  color: black;
}

a-radio-wrapper:hover {
  color: black !important;
}
</style>
