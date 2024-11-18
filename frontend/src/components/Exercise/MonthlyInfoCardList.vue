<script setup lang="ts">
import ExerciseLogApiFacade from '@/api/apiFacade/ExerciseLogApiFacade'
import { Up } from '../../assets'
import { Down } from '../../assets'
import { PurpleCardIcon } from '../../assets'

const { data } = ExerciseLogApiFacade.useFetchCardDataList()
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
            {{
              cardData.cardType === 'MINUTE'
                ? cardData.currentValue.toLocaleString()
                : cardData.currentValue
            }}{{ cardData.cardType }}
          </p>
        </div>
        <img :src="PurpleCardIcon" alt="icon" class="h-12 w-12" />
      </div>
      <p class="flex items-center w-full text-xs px-4 pb-3">
        <img :src="cardData.up ? Up : Down" alt="change" class="h-4 w-4 m-2" />
        지난주보다
        <span :class="cardData.up ? 'text-[#00B69B]' : 'text-[#F93C65]'">{{
          cardData.currentValue - cardData.lastValue + cardData.cardType
        }}</span>
        {{ cardData.up ? ' 증가' : ' 감소' }}
      </p>
    </li>
  </ul>
</template>
