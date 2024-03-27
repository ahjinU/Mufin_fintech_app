import { HttpResponse, http } from 'msw';

interface RequestBodyType {
  name: string;
}

export default http.post<never, RequestBodyType, never, '/stock/detail'>(
  '/stock/detail',
  async ({ request }) => {
    const requestBody = await request.json();
    const { name } = requestBody;

    const data =
      name === '바람막이'
        ? { price: 50000, incomeRatio: 44.8, transCnt: 45, imageUrl: null }
        : { price: 3000, incomeRatio: -3.5, transCnt: 1, imageUrl: null };

    return HttpResponse.json({
      data,
    });
  },
);
