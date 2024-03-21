export const commaNum = (num: number): string => {
  let str = num.toString();
  str = str.replace(/\B(?=(\d{3})+(?!\d))/g, ',');
  return str;
};
