<script setup lang="ts">
import { RouterView } from 'vue-router'
import NavigationBar from './components/common/NavigationBar.vue'
import { VueQueryDevtools } from '@tanstack/vue-query-devtools'
import { useQueryClient } from '@tanstack/vue-query';
import FollowApiFacade from './api/apiFacade/FollowAPIFacade';
import ExerciseLogApiFacade from './api/apiFacade/ExerciseLogApiFacade';

const queryClient = useQueryClient();

const followResult = FollowApiFacade.useFetchFollowRelations();
queryClient.setQueryData(['follow'], followResult);
const grassResult = ExerciseLogApiFacade.useFetchExerciseGrass()
queryClient.setQueryData(['exerciseGrass'], grassResult)

</script>

<template>
  <NavigationBar />
  <RouterView />
  <VueQueryDevtools :initial-is-open="false" />
</template>
