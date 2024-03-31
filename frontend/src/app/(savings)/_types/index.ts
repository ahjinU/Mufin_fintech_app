export interface SavingsListType {
  savingsUuid: string;
  interest: number;
  period: number;
  name: string;
  createdAt: string;
}

export interface AppliedSavingsListType {
  accountNumber: string;
  accountUuid: string;
  balance: number;
  state: string;
  paymentAmount: number;
  paymentDate: number;
  paymentCycle: number;
  createdAt: string;
  savingsInterest: number;
  savingsPeriod: number;
  savingsName: string;
  overdueCnt: number;
  expiredAt: string;
}

export interface ChildrenSavingsStateListType {
  userName: string;
  savingsDetailList: SavingsDetailListType[];
}

interface SavingsDetailListType {
  createdAt: string;
  expiredAt: string;
  period: number;
  interest: number;
  paymentAmount: number;
  interestAmount: number | null;
  state: string;
  balance: number;
}
