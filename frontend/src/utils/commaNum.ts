export const commaNum = (num: number | undefined): string => {
  if (num === undefined) {
    return '';
  }

  let str = num.toString();
  str = str.replace(/\B(?=(\d{3})+(?!\d))/g, ',');
  return str;
};
