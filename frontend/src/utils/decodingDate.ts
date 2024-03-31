export const decodingDate = (currentUrl: string) => {
  var dateString = currentUrl && decodeURI(currentUrl?.split('/')[2]);
  const dateParts =
    dateString && dateString.match(/(\d{4})년 (\d{1,2})월 (\d{1,2})일/);

  const year = dateParts && parseInt(dateParts[1]);
  const month = dateParts && parseInt(dateParts[2]) - 1;
  const day = dateParts && parseInt(dateParts[3]);

  const date = year && month && day && new Date(year, month, day);

  return date;
};
