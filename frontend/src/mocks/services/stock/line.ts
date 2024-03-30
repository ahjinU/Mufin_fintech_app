import { HttpResponse, http } from 'msw';

interface RequestBodyType {
  name: string;
  period: string;
}

export default http.post<
  never,
  RequestBodyType,
  never,
  '/stock/price/history/line'
>('/stock/price/history/line', async ({ request }) => {
  const requestBody = await request.json();

  const data = [];
  for (let i = 0; i < parseInt(requestBody?.period); i++) {
    data.push({ data: '2024-03-25', price: Math.floor(Math.random() * 50) });
  }

  return HttpResponse.json({
    data,
  });
});
