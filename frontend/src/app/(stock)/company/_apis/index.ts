const commonUrl = process.env.NEXT_PUBLIC_BASE_URL;
const baseUrl = 'https://mufin.life/api';
const localUrl = 'http://localhost:9090';

// 선 차트 데이터 조회
export const getStockLineData = async (name: string, period: number) => {
  const res = await fetch(`${commonUrl}/stock/price/history/line`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    credentials: 'include',
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
    credentials: 'include',
    body: JSON.stringify({ name, period }),
  });
  return res.json();
};

// 내 파킹통장 정보 조회
export const getChocoChipPocketData = async () => {
  const res = await fetch(`${baseUrl}/parking/account`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
      Authorization:
        'Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzc2FmeSIsImF1dGgiOiJST0xFX1VTRVIiLCJleHAiOjE3MTE1MDM0MTd9.tC1gi6P7gtZshLSHLaUXYJqpyGggwrdn-zZNOkRKCsg',
    },
    credentials: 'include',
  });
  return res.json();
};

// 개별 회사 주식 정보 조회
export const getCompanyData = async (name: string) => {
  const res = await fetch(`${localUrl}/stock/detail`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    credentials: 'include',
    body: JSON.stringify({ name }),
  });
  return res.json();
};

// 매수하기
export const buyStock = async (
  name: string,
  price: number,
  cnt_total: number,
) => {
  const res = await fetch(`${baseUrl}/stock/buy`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    credentials: 'include',
    body: JSON.stringify({ name, price, cnt_total }),
  });
  return res.json();
};

export const sellStock = async (
  name: string,
  price: number,
  cnt_total: number,
) => {
  const res = await fetch(`${baseUrl}/stock/sell`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    credentials: 'include',
    body: JSON.stringify({ name, price, cnt_total }),
  });
  return res.json();
};
