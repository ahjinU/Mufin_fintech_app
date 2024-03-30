export interface LoanType {
  reason: string;
  loanUuid: string;
  amount: number;
  paymentTotalCnt: number;
  paymentNowCnt: number;
  status: string;
  overDueCnt: number;
  loanRefusalReason: string | null;
}

export interface LoanDetailType {
  reason: string;
  totalAmount: number;
  remainderAmount: number;
  startDate: Date;
  remainderDay: string;
  paymentDate: number;
  overDueCnt: number;
}
