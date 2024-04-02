export type LoanListType = {
  childName: string;
  reason: string;
  amount: number;
  paymentDate: number;
  penalty: string;
  paymentTotalCnt: number;
  paymentNowCnt: number;
  statusCode: string;
  overdueCnt: number;
  startDate: string;
  endDate: string;
};

export type RequestListType = {
  childName: string;
  reason: string;
  loanUuid: string;
  amount: number;
  paymentDate: number;
  penalty: string;
  paymentTotalCnt: number;
  chatBotConversation: string[];
};
