const commonUrl = process.env.NEXT_PUBLIC_BASE_URL;

export const postStocksAll = async () => {
  const res = await fetch(`${commonUrl}/stock/all`, {
    method: 'POST',
    cache: 'no-cache',
    headers: {
      'Content-Type': 'application/json',
    },
  });
  return await res.json();
};

export const getRankingTotal = async () => {
  const res = await fetch(`${commonUrl}/ranking/total`, {
    method: 'GET',
    cache: 'no-cache',
  });
  return await res.json();
};

export const getRankingMine = async () => {
  const res = await fetch(`${commonUrl}/ranking/mine`, {
    method: 'GET',
    cache: 'no-cache',
  });
  return await res.json();
};

export const postStockMine = async () => {
  const res = await fetch(`${commonUrl}/stock/mine`, {
    method: 'POST',
    cache: 'no-cache',
  });
  return await res.json();
};

export const postParkingAccount = async () => {
  const res = await fetch(`${commonUrl}/parking/account`, {
    method: 'POST',
    cache: 'no-cache',
  });
  return await res.json();
};

export const getParkingAccount = async () => {
  const res = await fetch(`${commonUrl}/parking/history`, {
    method: 'GET',
    cache: 'no-cache',
  });
  return await res.json();
};

export const postStockOrderWait = async () => {
  const res = await fetch(`${commonUrl}/stock/order/wait`, {
    method: 'POST',
    cache: 'no-cache',
  });
  return await res.json();
};
