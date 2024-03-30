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
