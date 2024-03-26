const commonUrl = process.env.NEXT_PUBLIC_BASE_URL;
const baseUrl = 'https://mufin.life/api';

// 선 차트 데이터 조회
export const getStockLineData = async (name: string, period: number) => {
  const res = await fetch(`${commonUrl}/stock/price/history/line`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify({ name, period }),
  });
  return res.json();
};

// 봉 차트 데이터 조회
export const getStockCandleData = async (name: string, period: number) => {
  const res = await fetch(`${commonUrl}/stock/price/history/bar`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify({ name, period }),
  });
  return res.json();
};

// 내 파킹통장 정보 조회
export const getChocoChipPocketData = async () => {
  const res = await fetch(`${commonUrl}/parking/account`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
  });
  return res.json();
};

// 매수하기
export const buyStock = async (
  name: string,
  price: number,
  cnt_total: number,
) => {
  const res = await fetch(`${commonUrl}/stock/buy`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify({ name, price, cnt_total }),
  });
  return res.json();
};
