import type { Ref } from 'vue'

export class Timer {
  private timerId: ReturnType<typeof setInterval> | null = null // 내부 타이머 ID
  private time: Ref<number>
  private isRunning: Ref<boolean>

  constructor(time: Ref<number>, isRunning: Ref<boolean>) {
    this.time = time
    this.isRunning = isRunning
  }

  start() {
    if (!this.timerId) {
      this.isRunning.value = true
      this.timerId = setInterval(() => {
        this.time.value += 1
      }, 1000)
    }
  }

  pause() {
    if (this.timerId) {
      clearInterval(this.timerId)
      this.timerId = null
      this.isRunning.value = false
    }
  }

  toggle() {
    if (this.isRunning.value) {
      this.pause()
    } else {
      this.start()
    }
  }

  reset() {
    this.pause() // 타이머 멈춤
    this.time.value = 0 // 시간 초기화
  }
}
