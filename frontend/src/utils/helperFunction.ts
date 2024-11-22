export const pad = (time: number): string => {
  return String(time).padStart(2, '0')
}

export const getTimeFromSecond = (second: number): string => {
  const hours = Math.floor(second / 3600)
  const minutes = Math.floor((second % 3600) / 60)
  const seconds = second % 60

  return `${pad(hours)}:${pad(minutes)}:${pad(seconds)}`
}
