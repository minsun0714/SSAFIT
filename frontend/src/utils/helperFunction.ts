type CardType = 'minute' | 'kcal' | 'kg'
export const exerciseCardUtil = (cardType: CardType) => {
  switch (cardType) {
    case 'minute':
      return { compareTime: '어제보다', unit: '분' }
    case 'kcal':
      return { compareTime: '전달 대비', unit: '%' }
    case 'kg':
      return { compareTime: '어제보다', unit: '%' }
    default:
      throw new Error('단위가 잘못 되었습니다.')
  }
}
