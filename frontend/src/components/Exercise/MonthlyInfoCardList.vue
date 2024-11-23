<script setup lang="ts">
import ExerciseLogApiFacade from '@/api/apiFacade/ExerciseLogApiFacade'
import { Up } from '../../assets'
import { Down } from '../../assets'
import { FaArrowRightLong } from 'vue3-icons/fa6'
import TodayExerciseTime from './TodayExerciseTime.vue'

const { data } = ExerciseLogApiFacade.useFetchCardDataList()
</script>

<template>
  <div class="bg-blue-400">
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
              {{
                cardData.cardType === 'MINUTE'
                  ? (cardData.currentValue.toLocaleString() / 60).toFixed(2)
                  : cardData.currentValue
              }}{{ cardData.cardType }}
            </p>
          </div>
          <img :src="cardData?.cardIconUrl" alt="icon" class="h-12 w-12" />
        </div>
        <p class="flex items-center w-full text-xs px-4 pb-3">
          <span v-if="cardData.currentValue === cardData.lastValue" class="flex border">
            <FaArrowRightLong class="flex items-center border h-full"/>
            지난주와 동일
          </span>
          <span v-else class="border flex items-center">
            <img :src="cardData.up ? Up : Down" alt="change" class="h-4 w-4 m-2" />
            <span :class="cardData.up ? 'text-[#00B69B]' : 'text-[#F93C65]'"
              >{{ cardData.currentValue - cardData.lastValue + cardData.cardType }}
              {{ cardData.up ? ' 증가' : ' 감소' }}</span
            >
          </span>
        </p>
      </li>
    </ul>
    <TodayExerciseTime />
  </div>
</template>
