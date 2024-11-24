<script setup lang="ts">
import {
  Table,
  TableBody,
  TableCell,
  TableHead,
  TableHeader,
  TableRow,
} from '@/components/ui/table'
import Button from '../ui/button/Button.vue'
import CommonPagination from '../common/CommonPagination.vue'
import AdminApiFacade from '@/api/apiFacade/AdminApiFacade'

const { data } = AdminApiFacade.useFetchPendingVideos()
</script>

<template>
  <div class="flex justify-center items-center w-full h-full">
    <div>
      <Table class="w-full border">
        <TableHeader>
          <TableRow>
            <TableHead>썸네일</TableHead>
            <TableHead>영상 제목</TableHead>
            <TableHead>영상 분류</TableHead>
            <TableHead>작성자</TableHead>
            <TableHead>등록 일시</TableHead>
            <TableHead>승인 여부</TableHead>
          </TableRow>
        </TableHeader>
        <TableBody v-if="data?.totalItems">
          <TableRow v-for="video in data?.data" :key="video?.videoId">
            <TableCell><img /></TableCell>
            <TableCell>{{ video?.title }}</TableCell>
            <TableCell>{{ video?.part }}</TableCell>
            <TableCell>{{ video?.publishedAt }}</TableCell>
            <TableCell>{{ video?.channelTitle }}</TableCell>
            <TableCell v-if="video?.videoStatus === 'PENDING'">
              <Button variant="outline">승인</Button>
              <Button variant="destructive">거절</Button>
            </TableCell>
            <TableCell>
              {{ video?.videoStatus }}
            </TableCell>
          </TableRow>
        </TableBody>
        <TableRow v-else>
          <TableCell colspan="6" class="text-center border"> 등록된 영상이 없습니다. </TableCell>
        </TableRow>
      </Table>
    </div>
  </div>
  <div>
    <CommonPagination :data="data" />
  </div>
</template>
