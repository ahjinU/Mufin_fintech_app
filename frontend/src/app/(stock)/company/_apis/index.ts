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

  // 내 초코칩 보관함 확인하기
  const getChocoChipPocketData = async () => {
    const res = await postFetch({
      api: '/parking/account',
    });
    return res;
  };

  // 매수하기
  const buyStock = async (name: string, price: number, cnt_total: number) => {
    const res = await postFetch({
      api: '/stock/buy',
      data: { name, price, cnt_total },
    });
    return res;
  };

  // 매도하기
  const sellStock = async (name: string, price: number, cnt_total: number) => {
    const res = await postFetch({
      api: '/stock/sell',
      data: { name, price, cnt_total },
    });
    return res;
  };

  // 회사 개별 주식 가져오기
  const getStockDetail = async (name: string) => {
    const res = await postFetch({
      api: '/stock/detail',
      data: { name },
    });
    return res;
  };

  return {
    getStockLineData,
    getStockCandleData,
    getChocoChipPocketData,
    buyStock,
    sellStock,
    getStockDetail,
  };
}
