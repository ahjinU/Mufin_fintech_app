export interface LoanApplyType {
  reason: string;
  amount: number;
  paymentTotalCnt: number;
  paymentDate: number;
  conversation?: string;
  penalty: string;
}
