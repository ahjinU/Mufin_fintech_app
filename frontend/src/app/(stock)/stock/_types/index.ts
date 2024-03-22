export interface StockAllType {
  name: string;
  price: number;
  incomeRatio: number;
  transCnt: number;
}

export interface RankType {
  childName: string;
  balance: number;
  rank: number;
}

export interface StockInfo {
  name: string;
  cnt: number;
  income: number;
  incomeRatio: number;
  priceAvg: number;
  priceCur: number;
  totalPriceAvg: number;
  totalPriceCur: number;
}

export interface MyStockType {
  myStockList: StockInfo[];
  totalIncome: number;
  totalPrice: number;
}

export interface MyParkingType {
  balanceToday: number;
  ratio: number;
  balanceTmrw: number;
  interest: number;
}
