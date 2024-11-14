<script setup lang="ts">
import { Up } from '../../assets'
import { Down } from '../../assets'
import { PurpleCardIcon } from '../../assets'
import { exerciseCardUtil } from '../../utils/helperFunction'

const data = [
  {
    title: '이번 달 운동시간',
    imageUrl: PurpleCardIcon,
    type: 'minute',
    value: 3231,
    isUp: true,
    changedValue: 60,
  },
  {
    title: '이번 달 소모 칼로리',
    imageUrl: PurpleCardIcon,
    type: 'kcal',
    value: 10293,
    isUp: true,
    changedValue: 1.3,
  },
  {
    title: '없애버린 지방',
    imageUrl: PurpleCardIcon,
    type: 'kg',
    value: 4.7,
    isUp: false,
    changedValue: 4.3,
  },
]
</script>

<template>
  <ul class="flex flex-row justify-center gap-4 p-4 font-bold">
    <li
      v-for="(cardData, index) in data"
      :key="index"
      class="bg-white rounded-lg flex flex-col items-center text-center w-[300px] border"
    >
      <div class="flex flex-row justify-between w-full p-6">
        <div class="flex flex-col justify-around items-start gap-y-4">
          <p class="text-sm text-gray-500">{{ cardData.title }}</p>
          <p class="text-2xl text-gray-900">
            {{ cardData.type === 'minute' ? cardData.value.toLocaleString() : cardData.value
            }}{{ cardData.type }}
          </p>
        </div>
        <img :src="cardData.imageUrl" alt="icon" class="h-12 w-12" />
      </div>
      <p class="flex items-center w-full text-xs px-4 pb-3">
        <img :src="cardData.isUp ? Up : Down" alt="change" class="h-4 w-4 m-2" />
        {{ exerciseCardUtil(cardData.type as 'minute' | 'kcal' | 'kg').compareTime
        }}<span :class="cardData.isUp ? 'text-[#00B69B]' : 'text-[#F93C65]'">{{
          cardData.changedValue + exerciseCardUtil(cardData.type as 'minute' | 'kcal' | 'kg').unit
        }}</span>
        {{ cardData.isUp ? ' 증가' : ' 감소' }}
      </p>
    </li>
  </ul>
</template>
