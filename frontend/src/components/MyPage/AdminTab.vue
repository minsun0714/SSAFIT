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
        <TableBody>
          <TableRow>
            <TableCell> INV001 </TableCell>
            <TableCell>Paid</TableCell>
            <TableCell>Credit Card</TableCell>
            <TableCell> $250.00 </TableCell>
            <TableCell>Credit Card</TableCell>
            <TableCell>
              <Button variant="outline">승인</Button>
              <Button variant="destructive">거절</Button>
            </TableCell>
          </TableRow>
        </TableBody>
      </Table>
    </div>
  </div>
</template>
