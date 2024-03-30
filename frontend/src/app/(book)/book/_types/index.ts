interface Transaction {
  counterpartyName: string;
  code: string;
  transactionUuid: string;
  amount: number;
  date: string;
}

interface MonthBookDetailType {
  transactionDtoList: Transaction[];
  monthIncome: number;
  monthOutcome: number;
}

interface DayDetail {
  date: string;
  incomeDay: string;
  outcomeDay: string;
}

interface DayData {
  amount: number;
  code: string;
  counterpartyName: string;
  date: string;
  memo: string | null;
  receipts: any;
  transactionUuid: string;
}
