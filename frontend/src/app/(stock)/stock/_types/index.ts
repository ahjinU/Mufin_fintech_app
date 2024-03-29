export interface StockAllType {
  name: string;
  price: number;
  incomeRatio: number;
  transCnt: number;
  imageUrl: string;
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
  imageUrl: string;
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

export interface TransactionType {
  transName: string; // 우산회사
  amount: number; // 18,000 자수민
  type: string; // 이자, 매도, 매수
  cnt: number; // 체결 수
  price: number; // 매도/매수 일경우 : 18000/2 = 9000 자스만
  ratio?: number; // 이자 일 경우 : 파킹통장 이자율
  date?: string;
}
