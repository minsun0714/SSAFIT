type CardType = 'MINUTE' | 'KCAL' | 'KG'
export const exerciseCardUtil = (cardType: CardType) => {
  console.log(cardType)
  switch (cardType) {
    case 'MINUTE':
      return { compareTime: '어제보다', unit: '분' }
    case 'KCAL':
      return { compareTime: '전달 대비', unit: '%' }
    case 'KG':
      return { compareTime: '어제보다', unit: '%' }
    default:
      throw new Error('단위가 잘못 되었습니다.')
  }
}

export const pad = (time: number): string => {
  return String(time).padStart(2, '0')
}
