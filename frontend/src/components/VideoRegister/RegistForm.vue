<template>
  <div class="flex items-center justify-center min-h-screen bg-gray-100">
    <div class="bg-white rounded-lg shadow-md p-8 w-full max-w-lg">
      <h1 class="text-4xl font-bold text-center mb-6 text-black">Create Your Activity</h1>
      <iframe src="https://giphy.com/embed/VFDeGtRSHswfe" width="480" height="302" style="" frameBorder="0" class="giphy-embed" allowFullScreen></iframe>
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
          <a-input v-model:value="formState.name" placeholder="Enter a URL" />
        </a-form-item>

        <!-- Type Selection -->
        <a-form-item label="Type" name="resource">
          <a-radio-group v-model:value="formState.resource" class="flex justify-evenly">
            <a-radio value="1">🏃 Run</a-radio>
            <a-radio value="2">💪 Strength</a-radio>
            <a-radio value="3">🧘 Relax</a-radio>
          </a-radio-group>
        </a-form-item>

        <!-- Description -->
        <a-form-item label="Description" name="desc">
          <a-textarea
            v-model:value="formState.desc"
            placeholder="Describe the activity..."
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
import { Dayjs } from 'dayjs';
import { reactive, ref, toRaw } from 'vue';
import type { UnwrapRef } from 'vue';
import type { Rule } from 'ant-design-vue/es/form';

interface FormState {
  name: string;
  region: string | undefined;
  date1: Dayjs | undefined;
  delivery: boolean;
  type: string[];
  resource: string;
  desc: string;
}
const formRef = ref();
const labelCol = { span: 5 };
const wrapperCol = { span: 19 };
const formState: UnwrapRef<FormState> = reactive({
  name: '',
  region: undefined,
  date1: undefined,
  delivery: false,
  type: [],
  resource: '',
  desc: '',
});
const rules: Record<string, Rule[]> = {
  name: [
    { required: true, message: 'Please input the activity URL', trigger: 'change' },
    { type: 'url', message: 'Please enter a valid URL', trigger: 'blur' },
  ],
  resource: [{ required: true, message: 'Please select an activity type', trigger: 'change' }],
  desc: [{ required: true, message: 'Please provide a description', trigger: 'blur' }],
};
const onSubmit = () => {
  formRef.value
    .validate()
    .then(() => {
      console.log('Submitted Values:', formState, toRaw(formState));
    })
    .catch((error: Error) => {
      console.log('Validation Error:', error);
    });
};
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
