const commonUrl = process.env.NEXT_PUBLIC_BASE_URL;
const baseUrl = 'https://mufin.life/api';

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

export const getStockCandleData = async (name: string, period: number) => {
  const res = await fetch(`${baseUrl}/stock/price/history/bar`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify({ name, period }),
  });
  return res.json();
};
