import useFetch from '@/hooks/useFetch';

export default function StockChartApis() {
  const { postFetch } = useFetch();

  // 선 차트 조회
  const getStockLineData = async (name: string, period: number) => {
    const res = await postFetch({
      api: '/stock/price/history/line',
      data: { name, period },
    });
    return res;
  };

  // 봉 차트 조회
  const getStockCandleData = async (name: string, period: number) => {
    const res = await postFetch({
      api: '/stock/price/history/bar',
      data: { name, period },
    });
    return res;
  };

  return { getStockLineData, getStockCandleData };
}

// // 내 파킹통장 정보 조회
// export const getChocoChipPocketData = async () => {
//   const res = await fetch(`${commonUrl}/parking/account`, {
//     method: 'POST',
//     headers: {
//       'Content-Type': 'application/json',
//     },
//   });
//   return res.json();
// };

// // 매수하기
// export const buyStock = async (
//   name: string,
//   price: number,
//   cnt_total: number,
// ) => {
//   const res = await fetch(`${commonUrl}/stock/buy`, {
//     method: 'POST',
//     headers: {
//       'Content-Type': 'application/json',
//     },
//     body: JSON.stringify({ name, price, cnt_total }),
//   });
//   return res.json();
// };
