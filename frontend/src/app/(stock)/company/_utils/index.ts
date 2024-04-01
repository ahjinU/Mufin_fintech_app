export const getMaxMinValueIndex = (series: any) => {
  let maxValue: number = -1;
  let minValue: number = -1;
  let maxValueIndex: number | undefined = undefined;
  let minValueIndex: number | undefined = undefined;
  let currentValue: number = -1;

  series[0].data.forEach((obj: any, index: number) => {
    if (maxValue === -1 || minValue === -1) {
      maxValue = obj.y[1];
      minValue = obj.y[2];
      maxValueIndex = index;
      minValueIndex = index;
    } else {
      if (obj.y[1] > maxValue) {
        maxValue = obj.y[1];
        maxValueIndex = index;
      }
      if (obj.y[2] < minValue) {
        minValue = obj.y[2];
        minValueIndex = index;
      }
    }

    if (currentValue === -1 && index === series[0].data.length - 1)
      currentValue = obj.y[3];
  });

  return [
    maxValue.toFixed(0),
    minValue.toFixed(0),
    maxValueIndex,
    minValueIndex,
    currentValue,
  ];
};

export const toCompanyKoreanName = (name: string) => {
  switch (name) {
    case 'snowduck':
      return '눈오리';
    case 'pinwheel':
      return '바람개비';
    case 'umbrella':
      return '우산';
    case 'icecream':
      return '아이스크림';
  }
};

export const toCompanyEnglishName = (name: string) => {
  switch (name) {
    case '눈오리':
      return 'snowduck';
    case '바람개비':
      return 'pinwheel';
    case '우산':
      return 'umbrella';
    case '아이스크림':
      return 'icecream';
  }
};
