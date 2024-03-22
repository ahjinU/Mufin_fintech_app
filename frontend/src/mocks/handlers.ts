import { HttpResponse, http } from 'msw';

export const handlers = [
  http.get('/api/user', ({ request }) => {
    return HttpResponse.json({ username: 'admin' });
  }),

  http.get('/ranking/total', () => {
    return HttpResponse.json({
      data: {
        ranks: [
          {
            childName: '아이1',
            balance: 700,
            rank: 1,
          },
          {
            childName: '아이2',
            balance: 600,
            rank: 2,
          },
          {
            childName: '아이3',
            balance: 800,
            rank: 3,
          },
          {
            childName: '아이4',
            balance: 550,
            rank: 4,
          },
          {
            childName: '아이5',
            balance: 900,
            rank: 5,
          },
          {
            childName: '아이6',
            balance: 650,
            rank: 6,
          },
          {
            childName: '아이7',
            balance: 750,
            rank: 7,
          },
          {
            childName: '아이8',
            balance: 850,
            rank: 8,
          },
          {
            childName: '아이9',
            balance: 720,
            rank: 9,
          },
          {
            childName: '아이10',
            balance: 620,
            rank: 10,
          },
        ],
      },
    });
  }),

  http.get('/ranking/mine', () => {
    return HttpResponse.json({
      data: {
        childName: '나',
        balance: 21000970,
        rank: 427,
      },
    });
  }),

  http.post('/stock/all', () => {
    return HttpResponse.json({
      data: {
        stock: [
          {
            name: '아이스크림 회사',
            price: 15000,
            incomeRatio: 0.05,
            transCnt: 10000,
          },
          {
            name: '우산 회사',
            price: 25000,
            incomeRatio: 0.03,
            transCnt: 8000,
          },
          {
            name: '선글라스 회사',
            price: 18000,
            incomeRatio: 0.08,
            transCnt: 12000,
          },
          {
            name: '마스크 회사',
            price: 22000,
            incomeRatio: -0.02,
            transCnt: 6000,
          },
          {
            name: '바람막이 회사',
            price: 28000,
            incomeRatio: 0.06,
            transCnt: 15000,
          },
          {
            name: '눈오리 회사',
            price: 20000,
            incomeRatio: 0.04,
            transCnt: 9000,
          },
        ],
      },
    });
  }),

  http.post('/stock/mine', () => {
    return HttpResponse.json({
      data: {
        myStockList: [
          {
            name: '우산 회사',
            cnt: 100,
            income: -2000,
            incomeRatio: -0.05,
            priceAvg: 2500,
            priceCur: 2400,
            totalPriceCur: 240000,
            totalPriceAvg: 250000,
          },
          {
            name: '아이스크림 회사',
            cnt: 50,
            income: 1500,
            incomeRatio: 0.03,
            priceAvg: 3000,
            priceCur: 3200,
            totalPriceCur: 160000,
            totalPriceAvg: 150000,
          },
          {
            name: '바람막이 회사',
            cnt: 80,
            income: 5000,
            incomeRatio: 0.08,
            priceAvg: 4000,
            priceCur: 4200,
            totalPriceCur: 336000,
            totalPriceAvg: 320000,
          },
        ],
        totalIncome: 4500,
        totalPrice: 736000,
      },
    });
  }),

  http.post('/parking/account', () => {
    return HttpResponse.json({
      data: {
        balanceToday: 5000000,
        ratio: 0.035,
        balanceTmrw: 5175000,
        interest: 175000,
      },
    });
  }),
];
